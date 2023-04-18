package workflow.dto;

import java.util.List;

import workflow.model.AdminSettings;
import workflow.model.Approver;

public class SettingsWrapper {

	private AdminSettings adminSettings;
	private List<Approver> approverList;

	public AdminSettings getAdminSettings() {
		return adminSettings;
	}
	public void setAdminSettings(AdminSettings adminSettings) {
		this.adminSettings = adminSettings;
	}
	public List<Approver> getApproverList() {
		return approverList;
	}
	public void setApproverList(List<Approver> approverList) {
		this.approverList = approverList;
	}

}
