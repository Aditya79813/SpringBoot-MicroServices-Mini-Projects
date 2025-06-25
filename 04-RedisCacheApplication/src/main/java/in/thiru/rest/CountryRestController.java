package in.thiru.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.thiru.entity.Country;
import in.thiru.repo.CountryRepo;

@RestController
public class CountryRestController {
	
	@Autowired
	private CountryRepo countryRepo;
	
	@PostMapping("/country")
	public Country insertData(@RequestBody Country country)
	{
		Country save = countryRepo.save(country);
		
		return save;
	}
	
	
	@GetMapping("/countries")
	public Iterable<Country> getAllCountries()   
	{
		return countryRepo.findAll();
	}

}
