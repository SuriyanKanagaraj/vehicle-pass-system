package workflow.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import workflow.model.VehicleApplication;
import workflow.service.VehicleApplicationService;

@Controller
public class UserDashboardController {

	private final VehicleApplicationService vehicleApplicationService;

	@Autowired
	public UserDashboardController(VehicleApplicationService vehicleApplicationService) {
		this.vehicleApplicationService = vehicleApplicationService;
	}

	@GetMapping("/userDashboard")
	public ModelAndView userDashboard() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelAndView.addObject("applicationList", vehicleApplicationService.findAllByLinkUsername(auth.getName()));
		modelAndView.addObject("applicationDetails", new VehicleApplication());
		modelAndView.addObject("isToOpenDialog", "false");
		modelAndView.setViewName("/userDashboard");
		return modelAndView;
	}

	@RequestMapping(value = "/createApplication", method = RequestMethod.POST)
    public String createApplication(@Valid VehicleApplication vehicleApplication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        vehicleApplication.setLinkUsername(auth.getName());
        vehicleApplication.setStage(2);
        vehicleApplication.setStatus("In-Progress");
        vehicleApplication.setApplicationDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        vehicleApplicationService.saveAndFlush(vehicleApplication);
        return "redirect:/userDashboard";
    }

}
