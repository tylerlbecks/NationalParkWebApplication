package com.techelevator.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sql = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			Park park = createPark(results);
			allParks.add(park);
		}
		return allParks;

	}

	public Park getParkByCode(String parkCode) {
		Park park = new Park();
		String sql = "SELECT * FROM park WHERE parkcode =?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		while (results.next()) {
			park = createPark(results);
		}
		return park;

	} // Creates a Park from a RowSet

	private Park createPark(SqlRowSet results) {

		Park park = new Park();
		park.setParkCode(results.getString("parkcode"));
		park.setParkName(results.getString("parkname"));
		park.setParkDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getInt("entryfee"));
		park.setNumOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		park.setInspQuote(results.getString("inspirationalquote"));
		park.setInspQuoteSource(results.getString("inspirationalquotesource"));
		park.setVisitorCount(results.getInt("annualvisitorcount"));
		park.setYearFounded(results.getInt("yearfounded"));
		park.setClimate(results.getString("climate"));
		park.setNumOfCampsites(results.getInt("numberofcampsites"));
		park.setAcreage(results.getInt("acreage"));
		park.setElevationInFeet(results.getInt("elevationinfeet"));
		park.setMilesOfTrail(results.getDouble("milesoftrail"));
		park.setState(results.getString("state"));
		park.setVisitorC(park.getVisitorCount());
		return park;
	}

}
