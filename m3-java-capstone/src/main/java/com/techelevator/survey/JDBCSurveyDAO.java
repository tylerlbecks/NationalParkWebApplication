package com.techelevator.survey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.park.Park;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void submitSurvey(Survey survey) {
		 String sql="INSERT INTO survey_result(parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ?);";
		 jdbcTemplate.update(sql, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
	}

	@Override
	public List<Park> getSurveyResults() {
		List<Park> surveyList = new ArrayList<Park>();
		String sql = "SELECT p.parkcode, parkname, count(*) AS parkcount FROM park p "
				+ "JOIN survey_result sr ON p.parkcode = sr.parkcode "
				+ "GROUP BY p.parkcode, parkname "
				+ "ORDER BY parkcount DESC, parkname;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			Park park = new Park();
			park.setParkCode(results.getString("parkcode"));
			park.setParkName(results.getString("parkname"));
			park.setNumOfSurveys(results.getInt("parkcount"));
			surveyList.add(park);
		}
		return surveyList;
	}
	
	
}


