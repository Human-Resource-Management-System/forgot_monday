package service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import service_interfaces.MailService;

@Component
public class OtpMailService implements MailService {

	private String from = "gamertemporary81@gmail.com";
	private String host = "smtp.gmail.com";
	private String apppass = "hdnf tgyi tjut tsvi";

	public boolean sendOtpMail(String to, String otp) {
		try {

			System.out.println("Starting......");

			System.out.println("Setting Properties......");

			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "25");
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);

			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, apppass);
				}
			});

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject("your one time password");

			msg.setText("here is the OTP : " + otp);

			System.out.println("Sending Message.........");

			Transport.send(msg);

			System.out.println("sent successfully.........");

			return true;

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}