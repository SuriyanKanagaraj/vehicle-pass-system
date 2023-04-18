package workflow.controller;

import workflow.dto.SettingsWrapper;
import workflow.model.AdminSettings;
import workflow.model.Approver;
import workflow.model.User;
import workflow.service.AdminSettingsService;
import workflow.service.ApproverService;
import workflow.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class AdminSettingsController {

	private final AdminSettingsService adminSettingsService;
	private final ApproverService approverService;
	private final UserService userService;

	@Autowired
	public AdminSettingsController(AdminSettingsService adminSettingsService, ApproverService approverService, UserService userService) {
		this.adminSettingsService = adminSettingsService;
		this.approverService = approverService;
		this.userService = userService;
	}

    @GetMapping(value = "/adminSettings")
    public ModelAndView settings() {
        ModelAndView modelAndView = new ModelAndView();
        AdminSettings adminSettings = adminSettingsService.findAll().size() == 0 ? new AdminSettings() : adminSettingsService.findAll().get(0);
        List<Approver> emptyApproversList = new ArrayList<>();
        emptyApproversList.add(new Approver());
        List<Approver> approverList = approverService.findAll().size() == 0 ? emptyApproversList : approverService.findAll();
        SettingsWrapper settingsWrapper = new SettingsWrapper();
        settingsWrapper.setAdminSettings(adminSettings);
        settingsWrapper.setApproverList(approverList);
        modelAndView.addObject("wrapper", settingsWrapper);
        modelAndView.addObject("disable", Objects.nonNull(adminSettings.getCollegeName()) ? "true" : "false");
        modelAndView.setViewName("/adminSettings");
        return modelAndView;
    }

    @RequestMapping(value = "/adminSettings", method = RequestMethod.POST)
    public ModelAndView updateSettingsAndApprovers(@ModelAttribute SettingsWrapper wrapper) {
        ModelAndView modelAndView = new ModelAndView();
        for(int i = 0; i < wrapper.getApproverList().size(); i++) {
        	wrapper.getApproverList().get(i).setOrgApprover(wrapper.getAdminSettings().getOrgApprover());
        }
        approverService.saveAndFlush(wrapper.getApproverList());
        AdminSettings adminSettings = wrapper.getAdminSettings();
        adminSettingsService.saveAndFlush(adminSettings);

        User orgUser = new User();
        orgUser.setId(2l);
        orgUser.setActive(1);
        orgUser.setUsername(wrapper.getAdminSettings().getOrgApprover() + "_approver");
        orgUser.setLastName("Approver");
        orgUser.setPassword("password");
        orgUser.setEmail(wrapper.getAdminSettings().getOrgApprover() + ".approver@gmail.com");
        orgUser.setName(wrapper.getAdminSettings().getOrgApprover());
        userService.saveApprover(orgUser);

        for(int i = 0; i < wrapper.getApproverList().size(); i++) {
        	User staffUser = new User();
        	staffUser.setId((long)i + 2);
        	staffUser.setActive(1);
        	staffUser.setUsername(wrapper.getApproverList().get(i).getDepartmentCode() + "_" + 
        			wrapper.getApproverList().get(i).getStaffApprover() + "_approver" );
        	staffUser.setLastName("Approver");
        	staffUser.setPassword("password");
        	staffUser.setEmail(wrapper.getApproverList().get(i).getDepartmentCode() + ".staffapprover@gmail.com");
        	staffUser.setName(wrapper.getApproverList().get(i).getStaffApprover());
            userService.saveApprover(staffUser);

            User deptUser = new User();
            deptUser.setId((long)i + 2);
            deptUser.setActive(1);
            deptUser.setUsername(wrapper.getApproverList().get(i).getDepartmentCode() + "_" + 
        			wrapper.getApproverList().get(i).getDepartmentApprover() + "_approver" );
            deptUser.setLastName("Approver");
            deptUser.setPassword("password");
            deptUser.setEmail(wrapper.getApproverList().get(i).getDepartmentCode() + ".deptapprover@gmail.com");
            deptUser.setName(wrapper.getApproverList().get(i).getDepartmentApprover());
            userService.saveApprover(deptUser);
        }

        List<AdminSettings> adminSettingsList = adminSettingsService.findAll();
        List<Approver> approverList = approverService.findAll();
        SettingsWrapper settingsWrapper = new SettingsWrapper();
        settingsWrapper.setAdminSettings(adminSettingsList.get(0));
        settingsWrapper.setApproverList(approverList);
        modelAndView.addObject("wrapper", settingsWrapper);
        modelAndView.addObject("disable", Objects.nonNull(adminSettingsList.get(0).getCollegeName()) ? "true" : "false");
        modelAndView.setViewName("/adminSettings");
        return modelAndView;
    }

    @RequestMapping(value = "/addApprover", method = RequestMethod.POST)
    public ModelAndView addApprover(@ModelAttribute SettingsWrapper wrapper) {
        ModelAndView modelAndView = new ModelAndView();
        AdminSettings adminSettings = wrapper.getAdminSettings();
        List<Approver> approverList = wrapper.getApproverList();
        approverList = approverList == null ? new ArrayList<>() : approverList;
        approverList.add(new Approver());
        SettingsWrapper settingsWrapper = new SettingsWrapper();
        settingsWrapper.setAdminSettings(adminSettings);
        settingsWrapper.setApproverList(approverList);
        modelAndView.addObject("wrapper", settingsWrapper);
        modelAndView.addObject("disable", "false");
        modelAndView.setViewName("/adminSettings");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteApprover", method = RequestMethod.POST)
    public ModelAndView deleteApprover(@ModelAttribute SettingsWrapper wrapper) {
    	ModelAndView modelAndView = new ModelAndView();
        AdminSettings adminSettings = wrapper.getAdminSettings();
        List<Approver> approverList = wrapper.getApproverList();
        if(approverList != null) {        	
        	approverList.remove(approverList.size() - 1);
        }
        SettingsWrapper settingsWrapper = new SettingsWrapper();
        settingsWrapper.setAdminSettings(adminSettings);
        settingsWrapper.setApproverList(approverList);
        modelAndView.addObject("wrapper", settingsWrapper);
        modelAndView.addObject("disable", "false");
        modelAndView.setViewName("/adminSettings");
        return modelAndView;
    }

}
