package workflow.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import workflow.model.VehicleApplication;
import workflow.service.VehicleApplicationService;

@Controller
public class ApproverDashboardController {

	private final VehicleApplicationService vehicleApplicationService;

	@Autowired
	public ApproverDashboardController(VehicleApplicationService vehicleApplicationService) {
		this.vehicleApplicationService = vehicleApplicationService;
	}

	@GetMapping("/approverDashboard")
	public ModelAndView getApplications() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("applicationList", vehicleApplicationService.findAllByStage(2));
		modelAndView.addObject("applicationDetails", new VehicleApplication());
		modelAndView.addObject("isToOpenDialog", "false");
		modelAndView.setViewName("/userDashboard");
		return modelAndView;
	}

	@RequestMapping(value = "/updateApplication", method = RequestMethod.POST)
    public void updateApplication(@Valid VehicleApplication vehicleApplication) {
        
    }

}
