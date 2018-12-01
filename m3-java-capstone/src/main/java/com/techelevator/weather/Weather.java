package com.techelevator.weather;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;

public class Weather {

	private String parkCode;
	private int fiveDayNum;
	private double low;
	private double high;
	private String forecast;
	private String scale;
	DecimalFormat df = new DecimalFormat("#.00"); 
	private String decimalStringLow;
	private String decimalStringHigh;
	private String recommendation;	
	private String tempRecommendation;
	private DayOfWeek dow;
	LocalDate date = LocalDate.now();
	
	

	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayNum() {
		return fiveDayNum;
	}
	public void setFiveDayNum(int fiveDayNum) {
		this.fiveDayNum = fiveDayNum;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
		this.decimalStringLow = df.format(this.low);	
	}
	public double getHigh() {
		return high;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getDecimalStringLow() {
		return decimalStringLow;
	}
	public String getDecimalStringHigh() {
		return decimalStringHigh;
	}
	public void setHigh(double high) {
		this.high = high;
		this.decimalStringHigh = df.format(high);	
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public String getTempRecommendation() {
		return tempRecommendation;
	}
	public String getForecastFile() {
		String forecastFile;
		String stringArray[]=forecast.split(" ");
		String secondWord;
			if(stringArray.length>1) {
			String capitol = stringArray[1].substring(0, 1).toUpperCase();
			secondWord = capitol +stringArray[1].substring(1);
			forecastFile = stringArray[0]+secondWord;
			} 
			else {
		 forecastFile=forecast.replaceAll("\\s","");
			}
		return forecastFile;	
	}
	public DayOfWeek getDow() {
		return dow;
	}
	public String generateRecommendation(String forecast, double high, double low) {
		tempRecommendation = "";
		recommendation = "";
		if(forecast.equals("snow")) {
			recommendation = "Bring snowshoes.";
		} if(forecast.equals("rain")) {
			recommendation = "Pack rain gear and wear waterproof shoes.";
		} if(forecast.equals("thunderstorms")) {
			recommendation = "Seek shelter and avoid hiking on exposed ridges.";
		} if(forecast.equals("sun")) {
			recommendation = "Pack sunblock.";
		} if(high > 75) {
			tempRecommendation = "Bring an extra gallon of water";
		} if(high - low > 20) {
			tempRecommendation = "Wear breathable layers";
		} if(low < 20) {
			tempRecommendation = "DANGER: Fridgid Temperatures!";
		} 
		return recommendation + " " + tempRecommendation;
	}
	public void changeDow(Queue<Weather> weather) {
		date = LocalDate.now();
		for (Weather days : weather) {
			dow = date.getDayOfWeek();
			date = date.plusDays(1);
		}
	}
}
