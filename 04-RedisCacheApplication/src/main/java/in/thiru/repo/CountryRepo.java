package in.thiru.repo;

import org.springframework.data.repository.CrudRepository;

import in.thiru.entity.Country;

public interface CountryRepo extends CrudRepository<Country, Integer> {

}
