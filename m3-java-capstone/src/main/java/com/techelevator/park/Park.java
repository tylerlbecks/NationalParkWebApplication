package com.techelevator.park;

import java.text.DecimalFormat;

public class Park {
	
	private String parkCode;
	private String parkName;
	private String parkDescription;
	private int entryFee;
	private int numOfAnimalSpecies;
	private String inspQuote;
	private String inspQuoteSource;
	private int visitorCount;
	private int yearFounded;
	private String climate;
	private int numOfCampsites;
	private int acreage;
	private int elevationInFeet;
	private double milesOfTrail;
	private String state;
	private int numOfSurveys;
	DecimalFormat formatter = new DecimalFormat("#,###");
	private String visitorC;
	private String acreageString;
	private String elevationString;
	private String campsiteString;
	
	public int getNumOfSurveys() {
		return numOfSurveys;
	}
	public void setNumOfSurveys(int numOfSurveys) {
		this.numOfSurveys = numOfSurveys;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public int getEntryFee() {
		return entryFee;
	}
	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}
	public int getNumOfAnimalSpecies() {
		return numOfAnimalSpecies;
	}
	public void setNumOfAnimalSpecies(int numOfAnimalSpecies) {
		this.numOfAnimalSpecies = numOfAnimalSpecies;
	}
	public String getInspQuote() {
		return inspQuote;
	}
	public void setInspQuote(String inspQuote) {
		this.inspQuote = inspQuote;
	}
	public String getInspQuoteSource() {
		return inspQuoteSource;
	}
	public void setInspQuoteSource(String inspQuoteSource) {
		this.inspQuoteSource = inspQuoteSource;
	}
	public int getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(int visitorCount) {
		this.visitorCount = visitorCount;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public int getNumOfCampsites() {
		return numOfCampsites;
	}
	public void setNumOfCampsites(int numOfCampsites) {
		this.numOfCampsites = numOfCampsites;
		double campsites =(double) numOfCampsites;
		campsiteString = formatter.format(campsites); 
	}
	public int getAcreage() {
		return acreage;
	}
	public void setAcreage(int acreage) {
		this.acreage = acreage;
		double ace =(double) acreage;
		acreageString = formatter.format(ace); 
	}
	public int getElevationInFeet() {
		return elevationInFeet;
	}
	public void setElevationInFeet(int elevationInFeet) {
		this.elevationInFeet = elevationInFeet;
		double ele =(double) elevationInFeet;
		elevationString = formatter.format(ele); 
	}
	public double getMilesOfTrail() {
		return milesOfTrail;
	}
	public void setMilesOfTrail(double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVisitorC() {
		return visitorC;
	}
	public void setVisitorC(int visitorCount) {
		double visitor =(double) visitorCount;
		visitorC = formatter.format(visitor); 	
	}
	public String getAcreageString() {
		return acreageString;
	}
	public String getElevationString() {
		return elevationString;
	}
	public String getCampsiteString() {
		return campsiteString;
	}
}
