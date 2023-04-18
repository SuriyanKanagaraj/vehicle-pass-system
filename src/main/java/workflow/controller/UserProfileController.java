package workflow.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import workflow.dto.UserProfileWrapper;
import workflow.model.Approver;
import workflow.model.UserProfile;
import workflow.service.ApproverService;
import workflow.service.UserProfileService;

@Controller
public class UserProfileController {

	private final UserProfileService userProfileService;

	private final ApproverService approverService;

	@Autowired
	public UserProfileController(UserProfileService userProfileService, ApproverService approverService) {
		this.userProfileService = userProfileService;
		this.approverService = approverService;
	}

    @GetMapping("/userProfile")
    public ModelAndView getUserProfileDetails() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = null;
        Optional<UserProfile> optionalUserProfile = userProfileService.findByLinkUsername(auth.getName());
        if(optionalUserProfile.isPresent()) {
        	userProfile = optionalUserProfile.get();
        } else {
        	userProfile = new UserProfile();
        }
        userProfile.setLinkUsername(auth.getName());
        List<Approver> approverList = approverService.findAll();
        List<String> departmentList = new LinkedList<>();
        for(int i = 0; i < approverList.size(); i++) {
        	departmentList.add(approverList.get(i).getDepartmentCode());
        }
        UserProfileWrapper userProfileWrapper = new UserProfileWrapper();
        userProfileWrapper.setUserProfile(userProfile);
        userProfileWrapper.setDepartmentList(departmentList == null ? new LinkedList<>() : departmentList);
        modelAndView.addObject("wrapper", userProfileWrapper);
        modelAndView.setViewName("/userProfile");
        return modelAndView;
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.POST)
    public ModelAndView updateUserProfileDetails(@ModelAttribute UserProfileWrapper wrapper) {
        ModelAndView modelAndView = new ModelAndView();
        userProfileService.updateUserProfile(wrapper.getUserProfile());
        modelAndView.addObject("successMessage", "Profile updated successfully");
        List<Approver> approverList = approverService.findAll();
        List<String> departmentList = new LinkedList<>();
        for(int i = 0; i < approverList.size(); i++) {
        	departmentList.add(approverList.get(i).getDepartmentCode());
        }
        UserProfileWrapper userProfileWrapper = new UserProfileWrapper();
        userProfileWrapper.setUserProfile(wrapper.getUserProfile());
        userProfileWrapper.setDepartmentList(departmentList);
        modelAndView.addObject("wrapper", userProfileWrapper);
        modelAndView.setViewName("/userProfile");
        return modelAndView;
    }

}
