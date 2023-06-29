package service_interfaces;

public interface MailService {

	boolean sendOtpMail(String to, String otp);

}