package com.nulogic.payslip.process.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nulogic.payslip.process.model.Employee;
import com.nulogic.payslip.process.model.EmployeeAccountDetails;
import com.nulogic.payslip.process.model.Salarydetails;
import com.nulogic.payslip.process.repository.EmployeeAccountDetailsRepository;
import com.nulogic.payslip.process.repository.EmployeeRepository;
import com.nulogic.payslip.process.repository.SalarydetailsRepository;



@RestController
public class EmployeeController {
	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeAccountDetailsRepository accountDetailsRepository;
    
    @Autowired
    private SalarydetailsRepository salaryRepo;
    
    @Autowired
	private TemplateEngine templateEngine;
    
    @Autowired
    private JavaMailSender emailSender;

    public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}
    // POST method to get employee details
    @PostMapping("/api/employee")
    public ResponseEntity<Employee> getEmployee(@RequestBody Map<String, String> request) {
        String empid = request.get("empid");
        System.out.println("empid "+empid);
        Optional<Employee> employee = employeeRepository.findByEmpid(empid);
        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST method to get employee account details
    @PostMapping("/api/employeeAccountDetails")
    public ResponseEntity<EmployeeAccountDetails> getEmployeeAccountDetails(@RequestBody Map<String, String> request) {
    	String empid = request.get("empid");
    	 
    	 Optional<EmployeeAccountDetails> employeeAccountData = accountDetailsRepository.findByEmpid(empid);
    	 return employeeAccountData.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/api/saveEmployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }
    
    @PostMapping("/api/saveEmployeeAccountDetails")
    public ResponseEntity<EmployeeAccountDetails> saveEmployeeAccountDetails( Model model,@RequestBody EmployeeAccountDetails accountDetails) {
        EmployeeAccountDetails savedAccountDetails = accountDetailsRepository.save(accountDetails);
        return ResponseEntity.ok(savedAccountDetails);
    }
    
//    @PostMapping("/api/employeeSalaryDetails")
//    public ResponseEntity<Salarydetails> getEmployeePayslip(@RequestBody Map<String, String> request) {
//    	String empid = request.get("empid");
//    	String joingingdate = request.get("joingdate");
//    	String[] parts = joingingdate.split(" ");
//		String month = parts[0];
//		String year = parts[1];
//		 System.out.println("Salary joingingdate "+joingingdate);
//		 System.out.println("Salary empid "+empid);
//    	 Optional<Salarydetails> employeeSalaryData = salaryRepo.findByPayslip( month, year,empid);
//    	 System.out.println("Salary data "+employeeSalaryData.toString());
//    	 return employeeSalaryData.map(ResponseEntity::ok)
//        .orElseGet(() -> ResponseEntity.notFound().build());
//    }
    
    
    @PostMapping("/api/employeeSalaryDetails")
    public ResponseEntity<ByteArrayResource> getEmployeePayslip( Model model,@RequestBody Map<String, String> request) throws DocumentException {
        String empid = request.get("empid");
        String payslipMonthAndYear = request.get("payslipMonAndYear");
        String[] parts = payslipMonthAndYear.split(" ");
        String month = parts[0];
        String year = parts[1];
 Optional<Employee> employeeData = employeeRepository.findByEmpid(empid);
        Optional<EmployeeAccountDetails> employeeAccountData = accountDetailsRepository.findByEmpid(empid);
        Optional<Salarydetails> salarydetails = salaryRepo.findByPayslip(month, year, empid);
        

        try {
        	
        	model.addAttribute("salarydetails", salarydetails.get());
			model.addAttribute("employeeAccountDetail", employeeAccountData.get());
			model.addAttribute("employeeDetail", employeeData.get());
			String imageUrl = new ClassPathResource("static/images/nulogic.png").getURL().toString();
	        model.addAttribute("logoImageUrl", imageUrl);
        	
            String htmlContent = generateHtmlContent(model);

            // Generate PDF from HTML content
            byte[] pdfBytes = generatePdfFromHtml(htmlContent);

            // Create ByteArrayResource from PDF bytes
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);
            
        	String filename = employeeData.get().getEmpid() + "_Payslip_" + month + "_" + year + "_.pdf";
			sendEmailWithAttachment(pdfBytes, payslipMonthAndYear,filename, employeeData.get().getEmail());
			

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", filename);
        

            // Return ResponseEntity with ByteArrayResource
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    private String generateHtmlContent(Model model) {
		Context context = new Context();
		model.asMap().forEach(context::setVariable);
		String htmlContent = templateEngine.process("Payslip", context);
		htmlContent = htmlContent.replaceAll("<!DOCTYPE[^>]*>", "");
		return htmlContent;
	}

	private byte[] generatePdfFromHtml(String htmlContent) throws IOException, DocumentException {
		System.out.println("HTML Content: " + htmlContent);
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(sanitizeHtmlContent(htmlContent));
			renderer.layout();
			renderer.createPDF(outputStream);
			return outputStream.toByteArray();
		}
	}
	
	private String sanitizeHtmlContent(String htmlContent) {
		Document doc = Jsoup.parse(htmlContent);
		return doc.html().trim();
	}
	
	   private void sendEmailWithAttachment(byte[] pdfBytes, String payslipdate, String filename, String email) {
	        try {
	            MimeMessage message = emailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);

	            helper.setTo(email); // Replace with recipient's email address
	            helper.setSubject("Payslip for " + payslipdate);
	            helper.setText("Please find the attached payslip for " + payslipdate);
	            helper.addAttachment(filename, new ByteArrayResource(pdfBytes));

	            emailSender.send(message);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }

}
