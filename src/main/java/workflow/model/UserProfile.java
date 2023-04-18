package workflow.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "userProfile")
public class UserProfile {

	@Id
	@Column(name = "linkUsername", unique = true)
    private String linkUsername;

	@Column(name = "nameOfTheStudent", unique = true)
    @NotEmpty(message = "*Please provide your name")
    private String nameOfTheStudent;

	@Column(name = "department", unique = true)
    @NotEmpty(message = "*Please provide your department")
    private String department;

	@Column(name = "degree", unique = true)
    @NotEmpty(message = "*Please provide your degree")
    private String degree;

	@Column(name = "branch", unique = true)
    @NotEmpty(message = "*Please provide your branch")
    private String branch;

	@Column(name = "registerNumber", unique = true)
	@NotNull(message = "*Please provide your registerNumber")
    private Integer registerNumber;

	@Column(name = "studentMobileNumber", unique = true)
	@Length(max = 10, message = "*Your studentMobileNumber must be max to 10 characters")
    @NotEmpty(message = "*Please provide your studentMobileNumber")
    private String studentMobileNumber;

	@Column(name = "parentName", unique = true)
    @NotEmpty(message = "*Please provide your parentName")
    private String parentName;

	@Column(name = "parentMobileNumber", unique = true)
	@Length(max = 10, message = "*Your parentMobileNumber must be max to 10 characters")
    @NotEmpty(message = "*Please provide your parentMobileNumber")
    private String parentMobileNumber;

	@Column(name = "drivingLicenseNumber", unique = true)
    @NotEmpty(message = "*Please provide your drivingLicenseNumber")
    private String drivingLicenseNumber;

	@Column(name = "residentialAddress")
    @NotEmpty(message = "*Please provide your residentialAddress")
    private String residentialAddress;

	public String getLinkUsername() {
		return linkUsername;
	}

	public void setLinkUsername(String linkUsername) {
		this.linkUsername = linkUsername;
	}

	public String getNameOfTheStudent() {
		return nameOfTheStudent;
	}

	public void setNameOfTheStudent(String nameOfTheStudent) {
		this.nameOfTheStudent = nameOfTheStudent;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Integer getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getStudentMobileNumber() {
		return studentMobileNumber;
	}

	public void setStudentMobileNumber(String studentMobileNumber) {
		this.studentMobileNumber = studentMobileNumber;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobileNumber() {
		return parentMobileNumber;
	}

	public void setParentMobileNumber(String parentMobileNumber) {
		this.parentMobileNumber = parentMobileNumber;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

}