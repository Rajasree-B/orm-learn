package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.CountryRepository.CountryRepository;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getAllContries()
	{
		
		List<Country> c=countryRepository.findAll();
		return c;
		
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException
	{
		Optional<Country> result = countryRepository.findById(countryCode);
		Country country = null;
		if(!result.isPresent())
		{
			country = result.get();
		}
		return country;
	}
	
	@Transactional
	public void addCountry(Country country)
	{
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String code, String name)
	{
		Optional<Country> result = countryRepository.findById(code);
		
		Country country=null;
		country=result.get();
		country.setName("LATESTCOUNTRY");
		countryRepository.save(country);
		
	}
	
	@Transactional
	public void deleteCountry(String code)
	{
		countryRepository.deleteById(code);
	}
	
	@Transactional
	public List<Country> findByNameContains(String str)
	{
		List<Country> list = countryRepository.findByNameContains(str);
		return list;
	}
	
	@Transactional
	public List<Country> findByNameContainsOrderByName(String str)
	{
		List<Country> list = countryRepository.findByNameContainsOrderByName(str);
		return list;
	}
	
	@Transactional
	public List<Country> findByNameStartingWith(String str)
	{
		return countryRepository.findByNameStartingWith(str);
	}
}
