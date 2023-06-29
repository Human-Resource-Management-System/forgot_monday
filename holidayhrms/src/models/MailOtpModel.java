package models;

public class MailOtpModel {

	private String email;
	private String otp;

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

	public int getOtp() {
		return Integer.parseInt(otp.trim());
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}