package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HRMS_Employees")
public class Employee {
	@Id
	@Column(name = "empl_id")
	private Integer emplId;

	@Column(name = "empl_frstname", nullable = true)
	private String emplFirstname;

	@Column(name = "empl_lastname", nullable = true)
	private String emplLastname;

	@Column(name = "empl_surname", nullable = true)
	private String emplSurname;

	@Column(name = "empl_rmanager_empl_id", nullable = true)
	private Integer emplRmanagerEmplId;

	@Column(name = "empl_hr_empl_id", nullable = true)
	private Integer emplHrEmplId;

	@Column(name = "empl_jbgr_id", nullable = true)
	private String emplJbgrId;

	@Column(name = "empl_photo", nullable = true)
	private String emplPhoto;

	@Column(name = "empl_jondate", nullable = true)
	private Date emplJondate;

	@Column(name = "empl_dob", nullable = true)
	private Date emplDob;

	@Column(name = "empl_designation", nullable = true)
	private String emplDesignation;

	@Column(name = "empl_offemail", nullable = true)
	private String emplOffemail;

	@Column(name = "empl_pemail", nullable = true)
	private String emplPemail;

	@Column(name = "empl_mobile", nullable = true)
	private long emplMobile;

	@Column(name = "empl_alemail", nullable = true)
	private String emplAlemail;

	@Column(name = "empl_bloodgroup", nullable = true)
	private String emplBloodgroup;

	@Column(name = "empl_gender", nullable = true)
	private char emplGender;

	@Column(name = "empl_address", nullable = true)
	private String emplAddress;

	@Column(name = "empl_fname", nullable = true)
	private String emplFname;

	@Column(name = "empl_luudate", nullable = true)
	private Timestamp emplLuudate;

	@Column(name = "empl_luuser", nullable = true)
	private Integer emplLuuser;

	@Column(name = "empl_ctc", nullable = true)
	private Double empl_ctc;

	@Column(name = "empl_basicsal", nullable = true)
	private Double empl_basicsal;

	@Column(name = "empl_fixedsal", nullable = true)
	private Double empl_fixedsal;

	@Column(name = "empl_variablesal", nullable = true)
	private Double empl_variablesal;

	@Column(name = "empl_status")
	private String empl_status;

	@Column(name = "password")
	private String password;

	// Constructors, getters, and setters

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getEmpl_ctc() {
		return empl_ctc;
	}

	public void setEmpl_ctc(Double empl_ctc) {
		this.empl_ctc = empl_ctc;
	}

	public Double getEmpl_basicsal() {
		return empl_basicsal;
	}

	public void setEmpl_basicsal(Double empl_basicsal) {
		this.empl_basicsal = empl_basicsal;
	}

	public Double getEmpl_fixedsal() {
		return empl_fixedsal;
	}

	public void setEmpl_fixedsal(Double empl_fixedsal) {
		this.empl_fixedsal = empl_fixedsal;
	}

	public Double getEmpl_variablesal() {
		return empl_variablesal;
	}

	public void setEmpl_variablesal(Double empl_variablesal) {
		this.empl_variablesal = empl_variablesal;
	}

	public String getEmpl_status() {
		return empl_status;
	}

	public void setEmpl_status(String empl_status) {
		this.empl_status = empl_status;
	}

	public Employee() {
	}

	// Getters and setters

	public Employee(String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getEmplId() {
		return emplId;
	}

	public void setEmplId(Integer emplId) {
		this.emplId = emplId;
	}

	public String getEmplFirstname() {
		return emplFirstname;
	}

	public void setEmplFirstname(String emplFirstname) {
		this.emplFirstname = emplFirstname;
	}

	public String getEmplLastname() {
		return emplLastname;
	}

	public void setEmplLastname(String emplLastname) {
		this.emplLastname = emplLastname;
	}

	public String getEmplSurname() {
		return emplSurname;
	}

	public void setEmplSurname(String emplSurname) {
		this.emplSurname = emplSurname;
	}

	public Integer getEmplRmanagerEmplId() {
		return emplRmanagerEmplId;
	}

	public void setEmplRmanagerEmplId(Integer emplRmanagerEmplId) {
		this.emplRmanagerEmplId = emplRmanagerEmplId;
	}

	public Integer getEmplHrEmplId() {
		return emplHrEmplId;
	}

	public void setEmplHrEmplId(Integer emplHrEmplId) {
		this.emplHrEmplId = emplHrEmplId;
	}

	public String getEmplJbgrId() {
		return emplJbgrId;
	}

	public void setEmplJbgrId(String emplJbgrId) {
		this.emplJbgrId = emplJbgrId;
	}

	public String getEmplPhoto() {
		return emplPhoto;
	}

	public void setEmplPhoto(String emplPhoto) {
		this.emplPhoto = emplPhoto;
	}

	public Date getEmplJondate() {
		return emplJondate;
	}

	public void setEmplJondate(Date emplJondate) {
		this.emplJondate = emplJondate;
	}

	public Date getEmplDob() {
		return emplDob;
	}

	public void setEmplDob(Date emplDob) {
		this.emplDob = emplDob;
	}

	public String getEmplDesignation() {
		return emplDesignation;
	}

	public void setEmplDesignation(String emplDesignation) {
		this.emplDesignation = emplDesignation;
	}

	public String getEmplOffemail() {
		return emplOffemail;
	}

	public void setEmplOffemail(String emplOffemail) {
		this.emplOffemail = emplOffemail;
	}

	public String getEmplPemail() {
		return emplPemail;
	}

	public void setEmplPemail(String emplPemail) {
		this.emplPemail = emplPemail;
	}

	public long getEmplMobile() {
		return emplMobile;
	}

	public void setEmplMobile(long emplMobile) {
		this.emplMobile = emplMobile;
	}

	public String getEmplAlemail() {
		return emplAlemail;
	}

	public void setEmplAlemail(String emplAlemail) {
		this.emplAlemail = emplAlemail;
	}

	public String getEmplBloodgroup() {
		return emplBloodgroup;
	}

	public void setEmplBloodgroup(String emplBloodgroup) {
		this.emplBloodgroup = emplBloodgroup;
	}

	public char getEmplGender() {
		return emplGender;
	}

	public void setEmplGender(char emplGender) {
		this.emplGender = emplGender;
	}

	public String getEmplAddress() {
		return emplAddress;
	}

	public void setEmplAddress(String emplAddress) {
		this.emplAddress = emplAddress;
	}

	public String getEmplFname() {
		return emplFname;
	}

	public void setEmplFname(String emplFname) {
		this.emplFname = emplFname;
	}

	public Timestamp getEmplLuudate() {
		return emplLuudate;
	}

	public void setEmplLuudate(Timestamp emplLuudate) {
		this.emplLuudate = emplLuudate;
	}

	public Integer getEmplLuuser() {
		return emplLuuser;
	}

	public void setEmplLuuser(Integer emplLuuser) {
		this.emplLuuser = emplLuuser;
	}
}
