package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forgotOtphrms")
public class EntityForgotPassword {

	@Id
	@Column(name = "mail")
	private String mail;

	@Column(name = "otp")
	private String otp;

	EntityForgotPassword() {

	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String string) {
		this.otp = string;
	}

}
