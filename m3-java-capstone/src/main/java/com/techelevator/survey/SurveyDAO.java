package com.techelevator.survey;

import java.util.List;

import com.techelevator.park.Park;

public interface SurveyDAO {

	public void submitSurvey(Survey survey);
	
	public List<Park> getSurveyResults();
}
