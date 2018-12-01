package com.techelevator.controller;

import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.park.JDBCParkDAO;
import com.techelevator.park.Park;
import com.techelevator.weather.JDBCWeatherDAO;
import com.techelevator.weather.Weather;

@Controller
public class HomeController {
	

	@Autowired
	JDBCParkDAO parkDao;
	@Autowired
	JDBCWeatherDAO weatherDao;
	
	@RequestMapping("/")
	public String displayHomePage(ModelMap map, HttpSession session) {
		
		List<Park> allParks = parkDao.getAllParks();
		map.put("parks", allParks);
		
			session.removeAttribute("weather");
		

		return "homePage";
	}
	
	@RequestMapping("/parkDetail") 
	public String displayParkDetails(HttpSession session, 
			@RequestParam String parkCode) {
		
		
		Park park = parkDao.getParkByCode(parkCode);
		session.setAttribute("park", park);
		
		//Adds weather info for ParkCode
		Queue<Weather> weather;
		if (session.getAttribute("weather") == null) {
			weather = weatherDao.getFiveDay(parkCode);
			session.setAttribute("weather", weather);	
		}
		else {
			weather = (Queue<Weather>) session.getAttribute("weather");
			
		}	if(session.getAttribute("tempScale") ==null) {
			session.setAttribute("tempScale", "fahrenheit");
			
		}
		else if(session.getAttribute("tempScale").equals("celsius")&& weather.peek().getScale().equals("F")){
			session.setAttribute("tempScale", "celsius");
			setToCelsius(session,weather);
		}
		else if(session.getAttribute("tempScale").equals("fahrenheit") && weather.peek().getScale().equals("C")){
			setToFahrenheit(session,weather);
		}
		return "parkDetail";
	}

	@RequestMapping("/parkDetailsTempScale")
	public String changeTempScale(HttpSession session, 
			@RequestParam String tempScale) {

		Park park = (Park) session.getAttribute("park");
		session.setAttribute("tempScale", tempScale);
		return"redirect:/parkDetail?parkCode="+park.getParkCode();	
	}

	private void setToCelsius(HttpSession session, Queue<Weather> weather) {
		for(Weather day : weather) {
			day.setScale("C");
		double celsiusLow = (day.getLow() - 32) / 1.8;
		day.setLow(celsiusLow);
		

		double celsiusHigh = (day.getHigh() - 32) / 1.8;
		day.setHigh(celsiusHigh);
		session.setAttribute("tempScale", "celsius");
		}
	}

	private void setToFahrenheit(HttpSession session, Queue<Weather> weather) {
		for(Weather day : weather) {
			day.setScale("F");
			double fahrenheitLow = (day.getLow() * 1.8 + 32);
			day.setLow((int)fahrenheitLow );
			
			double fahrenheitHigh = (day.getHigh() * 1.8 + 32);
			day.setHigh(fahrenheitHigh);
			session.setAttribute("tempScale", "fahrenheit");
			}
	}

} //END OF CLASS
