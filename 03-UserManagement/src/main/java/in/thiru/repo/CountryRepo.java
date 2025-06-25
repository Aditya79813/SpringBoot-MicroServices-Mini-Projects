package in.thiru.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.thiru.entity.CountryEntity;

public interface CountryRepo extends  JpaRepository<CountryEntity, Integer>{
	
	

}
