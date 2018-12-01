package com.techelevator.weather;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.park.Park;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Queue<Weather> getFiveDay(String parkCode){
		Weather weather = new Weather();
		Queue<Weather>fiveDayForecast = new LinkedList<Weather>();
		String sql = "SELECT * FROM weather WHERE parkcode =? ORDER BY fivedayforecastvalue";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		while(results.next()) {
			weather = createWeather(results);
			fiveDayForecast.add(weather);
			weather.changeDow(fiveDayForecast);
		} return fiveDayForecast;
		
	}
	private Weather createWeather(SqlRowSet results) {
		
		Weather weather = new Weather();
		weather.setParkCode(results.getString("parkcode"));
		weather.setFiveDayNum(results.getInt("fivedayforecastvalue"));
		weather.setLow((double)results.getInt("low"));
		weather.setHigh((double)results.getInt("high"));
		weather.setForecast(results.getString("forecast"));
		weather.generateRecommendation(weather.getForecast(), weather.getHigh(), weather.getLow());
		weather.setScale("F");
		return weather;
	}
	
}
