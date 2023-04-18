package workflow.service.impl;


import workflow.model.ChangeTS;
import workflow.model.LeaveOfAb;
import workflow.repository.ChangeTSRepository;
import workflow.repository.LeaveOfAbRepository;
import workflow.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import workflow.config.GlobalSettings;
import workflow.model.Form;
import workflow.repository.FormRepository;
import workflow.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailServiceImpl implements EmailService {

   private final FormRepository formRepository;
   private final ChangeTSRepository changeTSRepository;
   private final LeaveOfAbRepository leaveOfAbRepository;
   private final UserRepository userRepository;
   private GlobalSettings globalSettings;

   @Autowired
   public EmailServiceImpl(UserRepository userRepository, FormRepository formRepository,
		   ChangeTSRepository changeTSRepository, LeaveOfAbRepository leaveOfAbRepository, GlobalSettings globalSettings) {
	   this.userRepository = userRepository;
	   this.formRepository = formRepository;
	   this.changeTSRepository = changeTSRepository;
	   this.leaveOfAbRepository = leaveOfAbRepository;
	   this.globalSettings = globalSettings;
   }

   @Autowired
   private JavaMailSender emailSender;

   @Override
   public void sendSimpleMessage(String to, String subject, String text) {
	   SimpleMailMessage message = new SimpleMailMessage();
	   message.setFrom("noreply@yuredteam.com");
	   message.setTo(to);
	   message.setSubject(subject);
	   message.setText(text);
	   emailSender.send(message);
   }

   @Override
   public void sendNextMessage(Long formId) throws MessagingException {
	   String nextApproverEmail = "";
	   Form form = formRepository.findById(formId).get();
	   int currentStep = form.getCurrent();
	   int totalSteps = form.getTotalSteps();
	   if (currentStep <= totalSteps) { // the workflow is still live
		   nextApproverEmail = userRepository.findByUsername(form.getCurrentApprover()).get().getEmail();
		   this.sendNewApprovalHtmlMessage(nextApproverEmail, globalSettings.accessibleWebsiteUrl + "processingPage/processForm/"+form.getId());
	   }
	   else {                          // the workflow has ended
		   String text = String.format("Form #%d has just been completely approved", form.getId());
		   this.sendSimpleMessage(globalSettings.registrarEmail, "Registrar Form Completion Notification", text);
		   this.sendStudentApprovalMessage(form.getEmail(), form.getTrackingId());
	   }
   }

   @Override
   public void sendNextChangeTSMessage(Long formId) throws MessagingException {
	   String nextApproverEmail = "";
	   ChangeTS form = changeTSRepository.findById(formId).get();
	   int currentStep = form.getCurrent();
	   int totalSteps = form.getTotalSteps();
	   if (currentStep <= totalSteps) { // the workflow is still live
		   nextApproverEmail = userRepository.findByUsername(form.getCurrentApprover()).get().getEmail();
		   this.sendNewApprovalHtmlMessage(nextApproverEmail, globalSettings.accessibleWebsiteUrl + "processingPage/processChangeTSForm/"+form.getId());
	   }
	   else {                          // the workflow has ended
		   String text = String.format("Change of Torah Studies Form #%d has just been completely approved", form.getId());
		   this.sendSimpleMessage(globalSettings.registrarEmail, "Registrar Form Completion Notification", text);
		   this.sendStudentApprovalMessage(form.getEmail(), form.getTrackingId());
	   }
   }

   @Override
   public void sendNextLeaveOfAbMessage(Long formId) throws MessagingException {
	   String nextApproverEmail = "";
	   LeaveOfAb form = leaveOfAbRepository.findById(formId).get();
	   int currentStep = form.getCurrent();
	   int totalSteps = form.getTotalSteps();
	   if (currentStep <= totalSteps) { // the workflow is still live
		   nextApproverEmail = userRepository.findByUsername(form.getCurrentApprover()).get().getEmail();
		   this.sendNewApprovalHtmlMessage(nextApproverEmail, globalSettings.accessibleWebsiteUrl + "processingPage/processLeaveOfAbForm/"+form.getId());
	   }
	   else {                          // the workflow has ended
		   String text = String.format("Leave Of Absence Form #%d has just been completely approved", form.getId());
		   this.sendSimpleMessage(globalSettings.registrarEmail, "Registrar Form Completion Notification", text);
		   this.sendStudentApprovalMessage(form.getEmail(), form.getTrackingId());
	   }
   }

   @Override
   public void sendNewApprovalHtmlMessage(String to, String linkUrl) throws MessagingException {
	   MimeMessage mimeMessage = emailSender.createMimeMessage();
	   MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	   String htmlMsg = String.format(
			   "<h3>You have a form awaiting your approval.</h3>"
			   + "<br></br>"
			   + "<h4> Please click <a href=\"%s\">here</a> to process it! </h4>"
			   , linkUrl);
	   helper.setText(htmlMsg, true);
	   helper.setTo(to);
	   helper.setSubject("Registrar Forms Update");
	   helper.setFrom("suriyan.k1993@gmail.com");
	   emailSender.send(mimeMessage);
   }

   @Override
   public void sendStudentDenialMessage(String studentEmail, String trackingId, String denyer) throws MessagingException {
	   MimeMessage mimeMessage = emailSender.createMimeMessage();
	   MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	   String trackingUrl = globalSettings.accessibleWebsiteUrl + "tracking/" + trackingId;
	   String htmlMsg = String.format(
			   "<h3>Your form has been denied by %s.</h3>"
			   + "<br></br>"
			   + "<h4> Please click <a href=\"%s\">here</a> to review it,"
			   + " or visit <a href=\""
			   + globalSettings.accessibleWebsiteUrl
			   + "tracking/\"> the tracking portal </a> and enter your tracking code: %s</h4>"
			   , denyer, trackingUrl, trackingId);
	   helper.setText(htmlMsg, true);
	   helper.setTo(studentEmail);
	   helper.setSubject("Registrar Form Denied");
	   helper.setFrom("suriyan.k1993@gmail.com");
	   emailSender.send(mimeMessage);
   }

   @Override
   public void sendStudentApprovalMessage(String studentEmail, String trackingId) throws MessagingException {
	   MimeMessage mimeMessage = emailSender.createMimeMessage();
	   MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	   String trackingUrl = globalSettings.accessibleWebsiteUrl + "tracking/" + trackingId;
	   String htmlMsg = String.format(
			   "<h3>Your form has completed the approval process!</h3>"
			   + "<br></br>"
			   + "<h4> Please click <a href=\"%s\">here</a> to review it,"
			   + " or visit <a href=\""
			   + globalSettings.accessibleWebsiteUrl
			   + "tracking/\"> the tracking portal </a> and enter your tracking code: %s</h4>"
			   , trackingUrl, trackingId);
	   helper.setText(htmlMsg, true);
	   helper.setTo(studentEmail);
	   helper.setSubject("Registrar Form Approved");
	   helper.setFrom("suriyan.k1993@gmail.com");
	   emailSender.send(mimeMessage);
   }

   @Override
   public void sendInitialStudentMessage(String studentEmail, String trackingId, String name, String major, String type) throws MessagingException {
	   String subject = null;
	   String formType = null;
	   switch (type)
	   {
		   case "form":
		   {
			   subject = "Received Major Declaration Form";
			   formType = "Major Declaration Form";
			   break;
		   }
		   case "changeTS":
		   {
			   subject = "Received Change of Torah Studies Form";
			   formType = "Change of Torah Studies Form";
			   break;
		   }
		   case "leaveOfAb":
		   {
			   subject = "Received Leave of Absence Form";
			   formType = "Leave of Absence Form";
			   break;
		   }
	   }
   	    MimeMessage mimeMessage = emailSender.createMimeMessage();
	   MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	   String trackingUrl = globalSettings.accessibleWebsiteUrl + "tracking/" + trackingId;
	   String htmlMsg = String.format(
	   		   "<h3>Dear %s,</h3>"
			   +"<h3>Your %s has been submitted!</h3>"
			   + "<br></br>"
			   + "<h4> Please click <a href=\"%s\">here</a> to track it,"
			   + " or visit <a href=\""
			   + globalSettings.accessibleWebsiteUrl
			   + "tracking/\"> the tracking portal </a> and enter your tracking code: %s</h4>"
			   , name, formType, trackingUrl, trackingId);
	   helper.setText(htmlMsg, true);
	   helper.setTo(studentEmail);
	   helper.setSubject(subject);
	   helper.setFrom("suriyan.k1993@gmail.com");
	   emailSender.send(mimeMessage);
   }
}
