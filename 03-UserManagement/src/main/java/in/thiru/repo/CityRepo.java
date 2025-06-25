package in.thiru.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.thiru.entity.CityEntity;


public interface CityRepo extends  JpaRepository<CityEntity, Integer>{
	
//	public List<CityEntity> findByState(StateEntity state);
	public List<CityEntity> findByStateStateId(Integer stateId);

}
