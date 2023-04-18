package workflow.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "approver")
public class Approver {

	@Id
	@Column(name = "departmentCode", unique = true, nullable = false)
    @NotEmpty(message = "*Please provide the departmentCode")
    private String departmentCode;

    @Column(name = "staffApprover", unique = true, nullable = false)
    @NotEmpty(message = "*Please provide a valid staff approver")
    private String staffApprover;

    @Column(name = "departmentApprover", unique = true, nullable = false)
    @NotEmpty(message = "*Please provide a valid department approver")
    private String departmentApprover;

    @Column(name = "orgApprover", unique = false, nullable = false)
    @NotEmpty(message = "*Please provide a valid org approver")
    private String orgApprover;

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getStaffApprover() {
		return staffApprover;
	}

	public void setStaffApprover(String staffApprover) {
		this.staffApprover = staffApprover;
	}

	public String getDepartmentApprover() {
		return departmentApprover;
	}

	public void setDepartmentApprover(String departmentApprover) {
		this.departmentApprover = departmentApprover;
	}

	public String getOrgApprover() {
		return orgApprover;
	}

	public void setOrgApprover(String orgApprover) {
		this.orgApprover = orgApprover;
	}

}
