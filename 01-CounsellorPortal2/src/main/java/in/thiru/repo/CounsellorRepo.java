package in.thiru.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.thiru.entity.CounsellorEntity;


public interface CounsellorRepo extends JpaRepository<CounsellorEntity, Integer>{
	
	
	public CounsellorEntity findByEmailAndPassword(String email, String password);
	
	public CounsellorEntity findByEmail(String email);
	
	
	

}
