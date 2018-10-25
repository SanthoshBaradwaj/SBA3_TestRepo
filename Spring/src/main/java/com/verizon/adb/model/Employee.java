package com.verizon.adb.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;

	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 5, max = 20, message = "Name length must be between 5 to 20 chars")
	private String empName;

	@Column(name = "basic")
	private double empBasic;

	@NotEmpty(message = "Email ID cannot be null")
	@Email(message = "Invalid Email Id")
	private String empEmail;

	@NotEmpty(message = "Mobile number cannot be empty")
	@Pattern(regexp = "\\d{10}", message = "mobile number can be only 10 digits")
	@Column(name = "mob")
	private String empMobile;

	@NotEmpty(message = "Dept cannot be null")
	private String empDept;

	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Date of Birth cannot be left blank")
	@Column(name = "dob")
	private LocalDate dateOfBirth;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getEmpBasic() {
		return empBasic;
	}

	public void setEmpBasic(double empBasic) {
		this.empBasic = empBasic;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
