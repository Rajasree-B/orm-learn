package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;

	public static void main(String[] args) throws CountryNotFoundException {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		
		//testGetAllCountries();
		//getAllCountriesTest();
		//testAddCountry();
		//testUpdateCountry();
		//testDeleteCountry();
		//testFindByNameContains();
		//testFindByNameContainsOrderByName();
		testFindByNameStartingWith();
	}
	
	private static void testGetAllCountries()
	{
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllContries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("END");
	}
	
	private static void getAllCountriesTest() throws CountryNotFoundException
	{
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("IN");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}
	
	private static void testAddCountry() throws CountryNotFoundException
	{
		Country country=new Country("NW", "NEWCOUNTRY");
		countryService.addCountry(country);
		countryService.findCountryByCode("NW");
		
		
	}
	
	private static void testUpdateCountry()
	{
		countryService.updateCountry("NW", "LATESTCOUNTRY");
	}
	
	private static void testDeleteCountry()
	{
		countryService.deleteCountry("NW");
	}
	private static void testFindByNameContains()
	{
		List<Country> cList = countryService.findByNameContains("ou");
		System.out.println("COUNTRIES WITH 'ou' IN NAME");
		for(Country c:cList)
		{
			System.out.println(c.getCode() + " " + c.getName());
		}
	}
	
	private static void testFindByNameContainsOrderByName()
	{
		List<Country> cList = countryService.findByNameContainsOrderByName("ou");
		System.out.println("COUNTRIES WITH 'ou' IN NAME IN ASCENDING ORDER");
		for(Country c:cList)
		{
			System.out.println(c.getCode() + " " + c.getName());
		}
	}
	
	private static void testFindByNameStartingWith()
	{
		List<Country> cList = countryService.findByNameStartingWith("Z");
		System.out.println("COUNTRIES STARTING WITH Z");
		for(Country c : cList)
		{
			System.out.println(c.getCode() + " " + c.getName());
		}
	}
}
