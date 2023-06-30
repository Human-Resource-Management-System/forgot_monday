package models;

public class MailOtpModel {

	private String email;

	private String otp;

	MailOtpModel() {

	}

	@Override
	public String toString() {
		return "MailOtpModel [email=" + email + ", otp=" + otp + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp2) {
		this.otp = otp2;
	}
}