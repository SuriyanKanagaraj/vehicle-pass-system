package workflow.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import workflow.model.ChangePassword;
import workflow.model.User;
import workflow.service.UserService;

@Controller
public class ChangePasswordController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ChangePasswordController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
    @GetMapping("/changePassword")
    public ModelAndView changePassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("changePassword", new ChangePassword());
        modelAndView.setViewName("/changePassword");
        return modelAndView;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView createNewSettings(@Valid ChangePassword changePassword) {
        ModelAndView modelAndView = new ModelAndView();
        if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
        	modelAndView.addObject("passwordError", "New and Confirm Password doesn't matched");
        	modelAndView.setViewName("/changePassword");
        	return modelAndView;
        }
        if(changePassword.getOldPassword().equals(changePassword.getNewPassword())) {
        	modelAndView.addObject("passwordError", "Old and New Password shouldn't be same");
        	modelAndView.setViewName("/changePassword");
        	return modelAndView;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsername(auth.getName());
        if(!passwordEncoder.matches(changePassword.getOldPassword(), user.get().getPassword())) {
        	modelAndView.addObject("passwordError", "Wrong old password");
        	modelAndView.setViewName("/changePassword");
        	return modelAndView;
        }
        user.get().setPassword(changePassword.getNewPassword());
        userService.updateUserPassword(user.get());
        modelAndView.addObject("successMessage", "Password updated successfully");
        modelAndView.setViewName("/changePassword");
        return modelAndView;
    }

}
