package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.park.JDBCParkDAO;
import com.techelevator.park.Park;
import com.techelevator.survey.JDBCSurveyDAO;
import com.techelevator.survey.Survey;

@Controller
public class SurveyController {

	private Pattern pattern;
	private Matcher matcher;
	private String emailPattern;
	private List<String>states;
	private List<String>activityLevel;
	
	@Autowired
	JDBCSurveyDAO surveyDao;
	@Autowired
	JDBCParkDAO parkDao;

	@RequestMapping("/survey")
	public String displaySurvey(ModelMap map) {
		List<Park> allParks = parkDao.getAllParks();
		createStateList(); 
		createActivityList();
		map.put("parks", allParks);
		map.put("states", states);
		map.put("activityLevel", activityLevel);
		return "survey";
	}
	
	@RequestMapping(path = "/surveyResults", method = RequestMethod.GET)
	public String resultsPage(ModelMap map) {
		map.put("results", surveyDao.getSurveyResults());
		return "surveyResults";
	}

	@RequestMapping(path = "/surveyResults", method = RequestMethod.POST)
	public String submitReview(RedirectAttributes redirAttribs, 
			ModelMap map, 
			@RequestParam(defaultValue="") String parkCode,
			@RequestParam(defaultValue="") String emailAddress, @RequestParam(defaultValue="") String state, @RequestParam(defaultValue="") String activityLevel) {

		boolean isValid = true;
		List<String> errorMessages = new ArrayList<String>();
		
		// Confirms an email address was entered
		if (emailAddress.isEmpty()) {
			isValid = false;
			errorMessages.add("Email is required.");
		}
		// Uses Regix to determine if an email address is formatted
		emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(emailAddress);

		if (!(matcher.matches())) {
			isValid = false;
			errorMessages.add("Please enter a valid email");
		}
		if (parkCode.isEmpty()) {
			isValid = false;
			errorMessages.add("Please select a park");
		}
		if (state.isEmpty()) {
			isValid = false;
			errorMessages.add("Please select a state");
		}
		if (activityLevel.isEmpty()) {
			isValid = false;
			errorMessages.add("Please select activity level");
		}
		redirAttribs.addFlashAttribute("parkCode", parkCode);
		redirAttribs.addFlashAttribute("state", state);
		redirAttribs.addFlashAttribute("activity", activityLevel);
		redirAttribs.addFlashAttribute("emailAddress", emailAddress);
		
		if (!isValid) {
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/survey";
		} else {
			Survey survey = new Survey();
			survey.setParkCode(parkCode);
			survey.setEmailAddress(emailAddress);
			survey.setState(state);
			survey.setActivityLevel(activityLevel);
			surveyDao.submitSurvey(survey);

			map.put("results", surveyDao.getSurveyResults());

			return "redirect:/surveyResults";
		}
		
	} 
	public void createActivityList() {
		activityLevel = new ArrayList<String>();
		
		activityLevel.add("Inactive");
		activityLevel.add("Sedentary");
		activityLevel.add("Active");
		activityLevel.add("Extremely Active"); 
	}
	public void createStateList(){
		states = new ArrayList<String>();
		
		states.add("Alabama");
		states.add("Alaska");
		states.add("Arizona");
		states.add("Arkansas");
		states.add("California");
		states.add("Colorado");
		states.add("Connecticut");
		states.add("Delaware");
		states.add("Florida");
		states.add("Georgia");
		states.add("Hawaii");
		states.add("Idaho");
		states.add("Illinois");
		states.add("Indiana");
		states.add("Iowa");
		states.add("Kansas");
		states.add("Kentucky");
		states.add("Louisiana");
		states.add("Maine");
		states.add("Maryland");
		states.add("Massachusetts");
		states.add("Michigan");
		states.add("Minnesota");
		states.add("Mississippi");
		states.add("Missouri");
		states.add("Montana");
		states.add("Nebraska");
		states.add("Nevada");
		states.add("New Hampshire");
		states.add("New Jersey");
		states.add("New Mexico");
		states.add("New York");
		states.add("North Carolina");
		states.add("North Dakota");
		states.add("Ohio");
		states.add("Oklahoma");
		states.add("Oregon");
		states.add("Pennsylvania");
		states.add("Rhode Island");
		states.add("South Carolina");
		states.add("South Dakota");
		states.add("Tennessee");
		states.add("Texas");
		states.add("Utah");
		states.add("Vermont");
		states.add("Virginia");
		states.add("Washington");
		states.add("West Virginia");
		states.add("Wisconsin");
		states.add("Wyoming");
	}
}
