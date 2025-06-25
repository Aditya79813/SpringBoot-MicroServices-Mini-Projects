package in.thiru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	public  String sendEmail(String subject,String body, String email) throws Exception
	{
		
		boolean isSent=false;
		
		MimeMessage createMimeMessage = mailSender.createMimeMessage();
		
		
		MimeMessageHelper helper=new MimeMessageHelper(createMimeMessage);
		
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		
		mailSender.send(createMimeMessage);
		
		return null;
	}

}
