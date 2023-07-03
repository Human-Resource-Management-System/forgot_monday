package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hrms_candidates")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cand_id")
	private Integer candId;

	@Column(name = "cand_firstname")
	private String candFirstName;

	@Column(name = "cand_middlename")
	private String candMiddleName;

	@Column(name = "cand_lastname")
	private String candLastName;

	@Column(name = "cand_rdate")
	private Date candRDate;

	@Column(name = "cand_gender")
	private String candGender;

	@Column(name = "cand_dob")
	private Date candDOB;

	@Column(name = "cand_email")
	private String candEmail;

	@Column(name = "cand_mobile")
	private Long candMobile;

	@Column(name = "cand_address")
	private String candAddress;

	@Column(name = "cand_ludate")
	private Date candLUDate;

	@Column(name = "cand_status")
	private String candStatus;

	// Getters and setters

	public Candidate(String string) {
		// TODO Auto-generated constructor stub
	}

	public Candidate() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCandId() {
		return candId;
	}

	public void setCandId(Integer candId) {
		this.candId = candId;
	}

	public String getCandFirstName() {
		return candFirstName;
	}

	public void setCandFirstName(String candFirstName) {
		this.candFirstName = candFirstName;
	}

	public String getCandMiddleName() {
		return candMiddleName;
	}

	public void setCandMiddleName(String candMiddleName) {
		this.candMiddleName = candMiddleName;
	}

	public String getCandLastName() {
		return candLastName;
	}

	public void setCandLastName(String candLastName) {
		this.candLastName = candLastName;
	}

	public Date getCandRDate() {
		return candRDate;
	}

	public void setCandRDate(Date candRDate) {
		this.candRDate = candRDate;
	}

	public String getCandGender() {
		return candGender;
	}

	public void setCandGender(String candGender) {
		this.candGender = candGender;
	}

	public Date getCandDOB() {
		return candDOB;
	}

	public void setCandDOB(Date candDOB) {
		this.candDOB = candDOB;
	}

	public String getCandEmail() {
		return candEmail;
	}

	public void setCandEmail(String candEmail) {
		this.candEmail = candEmail;
	}

	public Long getCandMobile() {
		return candMobile;
	}

	public void setCandMobile(Long candMobile) {
		this.candMobile = candMobile;
	}

	public String getCandAddress() {
		return candAddress;
	}

	public void setCandAddress(String candAddress) {
		this.candAddress = candAddress;
	}

	public Date getCandLUDate() {
		return candLUDate;
	}

	public void setCandLUDate(Date candLUDate) {
		this.candLUDate = candLUDate;
	}

	public String getCandStatus() {
		return candStatus;
	}

	public void setCandStatus(String candStatus) {
		this.candStatus = candStatus;
	}
}
