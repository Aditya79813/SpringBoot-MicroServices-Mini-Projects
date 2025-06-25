package in.thiru.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.thiru.entity.StudentEnquiryEntity;

public interface StudentEnquiryRepo extends JpaRepository<StudentEnquiryEntity, Integer>{
	
	public List<StudentEnquiryEntity> findByCounsellorCounsellorId(Integer counsellorId);

}
