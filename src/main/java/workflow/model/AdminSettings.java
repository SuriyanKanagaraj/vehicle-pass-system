package workflow.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "adminSettings")
public class AdminSettings {

	@Id
	@Column(name = "collegeName", unique = true, nullable = false)
    @NotEmpty(message = "*Please provide the college name")
    private String collegeName;

	@Column(name = "orgApprover", unique = true, nullable = false)
    @NotEmpty(message = "*Please provide the Organization Approver")
    private String orgApprover;

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getOrgApprover() {
		return orgApprover;
	}

	public void setOrgApprover(String orgApprover) {
		this.orgApprover = orgApprover;
	}

}
