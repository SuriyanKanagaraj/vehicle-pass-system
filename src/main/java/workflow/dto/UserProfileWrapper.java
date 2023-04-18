package workflow.dto;

import java.util.List;

import workflow.model.UserProfile;

public class UserProfileWrapper {

	private UserProfile userProfile;
	private List<String> departmentList;

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<String> departmentList) {
		this.departmentList = departmentList;
	}

}
