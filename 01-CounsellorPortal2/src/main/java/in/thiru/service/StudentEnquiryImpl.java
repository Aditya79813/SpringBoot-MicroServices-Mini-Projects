package in.thiru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.thiru.dtos.DashboardResponseDTO;
import in.thiru.dtos.EnquiryFilterDTO;
import in.thiru.dtos.StudentEnquiriesDTO;
import in.thiru.entity.CounsellorEntity;
import in.thiru.entity.StudentEnquiryEntity;
import in.thiru.repo.CounsellorRepo;
import in.thiru.repo.StudentEnquiryRepo;
import jakarta.persistence.EntityNotFoundException;


@Service
public class StudentEnquiryImpl implements IEnquiryService {
	
	@Autowired
	private StudentEnquiryRepo enquiryRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;

	@Override
	public DashboardResponseDTO getDashboardEnquiries(Integer counsellorId) {
		DashboardResponseDTO dashboard=new DashboardResponseDTO();
		
		
//		Integer openCount=0;
//		Integer lostCount=0;
//		Integer enrolledCount=0;
//		
//		List<StudentEnquiryEntity> studentEnquiries = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
//		
//		for(StudentEnquiryEntity enq:studentEnquiries)
//		{
//			if(enq.getEnqStatus().equalsIgnoreCase("OPEN"))
//			{
//				openCount++;
//			}
//			else if(enq.getEnqStatus().equalsIgnoreCase("ENROLLED"))
//			{
//				enrolledCount++;
//			}
//			else if(enq.getEnqStatus().equalsIgnoreCase("LOST"))
//			{
//				enrolledCount++;
//			}
//		}
		
		List<StudentEnquiryEntity> studentEnquiries = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		int open = studentEnquiries.stream().filter(enq->enq.getEnqStatus().equalsIgnoreCase("OPEN")).collect(Collectors.toList()).size();
		int lost = studentEnquiries.stream().filter(enq->enq.getEnqStatus().equalsIgnoreCase("LOST")).collect(Collectors.toList()).size();
		int enrolled = studentEnquiries.stream().filter(enq->enq.getEnqStatus().equalsIgnoreCase("ENROLLED")).collect(Collectors.toList()).size();
		
		dashboard.setTotalEnquiries(studentEnquiries.size());
		dashboard.setEnrolledEnquiries(enrolled);
		dashboard.setLostEnquiries(lost);
		dashboard.setOpenEnquiries(open);
		
		return dashboard;
	}

	@Override
	public boolean addEnquiries(StudentEnquiriesDTO enqDTO, Integer counsellorId) {
		// TODO Auto-generated method stub
		
		StudentEnquiryEntity studentEnq=new StudentEnquiryEntity();
		BeanUtils.copyProperties(enqDTO, studentEnq);
		
		
		
		//setting foriegn key here
		Optional<CounsellorEntity> entity = counsellorRepo.findById(counsellorId);
		if(entity.isPresent())
		{
			CounsellorEntity counsellorEntity = entity.get();
			studentEnq.setCounsellor(counsellorEntity);
		}
//		studentEnq.setName(enqDTO.getName());
//		studentEnq.setClassMode(enqDTO.getClassMode());
//		studentEnq.setCourse(enqDTO.getCourse());
//		studentEnq.setEnqStatus(enqDTO.getEnqStatus());
//		studentEnq.setPhno(enqDTO.getPhno());
		
		StudentEnquiryEntity save = enquiryRepo.save(studentEnq);
		return save.getEnqId() != null;
	}

	@Override
	public List<StudentEnquiriesDTO> getAllEnquiries(Integer counsellorId) {
		List<StudentEnquiryEntity> allEnq = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		
		List<StudentEnquiriesDTO> dtosList=new ArrayList<>();
		
            for(StudentEnquiryEntity sEnqEnt:allEnq)
            {
            	StudentEnquiriesDTO studetnDto=new StudentEnquiriesDTO();
            	
            	BeanUtils.copyProperties(sEnqEnt, studetnDto);
            	
            	dtosList.add(studetnDto);
            	
            }
		return dtosList;
	}

	@Override
	public List<StudentEnquiriesDTO> getAllEnquiries(EnquiryFilterDTO filterDTO, Integer counsellorId) {
		
		List<StudentEnquiriesDTO> enquiryList=new ArrayList<>();
		StudentEnquiryEntity enquiryEntity=new StudentEnquiryEntity();

//	    // Fetch all enquiries for the given counsellor
//	    List<StudentEnquiryEntity> allEnq = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
//
//	    // Convert entities to DTOs using Java 8 Streams
//	    List<StudentEnquiriesDTO> sEnqDto = allEnq.stream()
//	        .map(studentEnt -> {
//	            StudentEnquiriesDTO senqDto = new StudentEnquiriesDTO();
//	            BeanUtils.copyProperties(studentEnt, senqDto);
//	            return senqDto;
//	        })
//	        .collect(Collectors.toList());
//
//	    // Apply additional filtering based on filterDTO (if required)
//	    List<StudentEnquiriesDTO> filteredEnquiries = sEnqDto.stream()
//	        .filter(dto -> {
//	            // Example filter conditions, adapt as needed
//	            boolean matchesStatus = filterDTO.getEnqStatus() == null || filterDTO.getEnqStatus().equals(dto.getEnqStatus());
//	            boolean matchesCourse = filterDTO.getCourse() == null || filterDTO.getCourse().equals(dto.getCourse());
//	            return matchesStatus && matchesCourse;
//	        })
//	        .collect(Collectors.toList());
//
//	    return filteredEnquiries;
		
		if(!filterDTO.getClassMode().equals("") &&  filterDTO.getClassMode() != null)
		{
			enquiryEntity.setClassMode(filterDTO.getClassMode());
		}
		
		if(filterDTO.getCourse() != null && !filterDTO.getCourse().equals(""))
		{
			enquiryEntity.setCourse(filterDTO.getCourse());
		}
		
		if(filterDTO.getEnqStatus() != null && !filterDTO.getEnqStatus().equals(""))
		{
			enquiryEntity.setEnqStatus(filterDTO.getEnqStatus());
		}
		
		//Based on this counsellor id it will be filter
		CounsellorEntity counsellor=new CounsellorEntity();
		counsellor.setCounsellorId(counsellorId);
		
		
		enquiryEntity.setCounsellor(counsellor);
		
		
		List<StudentEnquiryEntity> listOfEntities = enquiryRepo.findAll(Example.of(enquiryEntity));
		
		for (StudentEnquiryEntity studentEnquiryEntity : listOfEntities) {
			
			StudentEnquiriesDTO studentDto=new StudentEnquiriesDTO();
			
		     BeanUtils.copyProperties(studentEnquiryEntity, studentDto);
		     
		     
		     enquiryList.add(studentDto);
		}
		
		return enquiryList;
		
	}

	@Override
	public StudentEnquiriesDTO updateEnquiries(Integer studentId) {

//	    // Find the student enquiry by ID
//	    Optional<StudentEnquiryEntity> optionalEnquiryEntity = enquiryRepo.findById(studentId);
//
//	    // Check if the entity exists
//	    if (optionalEnquiryEntity.isPresent()) {
//	        StudentEnquiryEntity existingEntity = optionalEnquiryEntity.get();
//
//	        // Update the existing entity with data from the DTO
//	        BeanUtils.copyProperties(updatedEnquiryDTO, existingEntity, "studentId");
//
//	        // Save the updated entity to the database
//	        StudentEnquiryEntity updatedEntity = enquiryRepo.save(existingEntity);
//
//	        // Convert the updated entity to DTO
//	        StudentEnquiriesDTO responseDTO = new StudentEnquiriesDTO();
//	        BeanUtils.copyProperties(updatedEntity, responseDTO);
//
//	        return responseDTO;
//	    } else {
//	        throw new EntityNotFoundException("Enquiry with ID " + studentId + " not found.");
//	    }
		
		Optional<StudentEnquiryEntity> enquiriesEntity = enquiryRepo.findById(studentId);
		
		if(enquiriesEntity.isPresent())
		{
			StudentEnquiriesDTO dto=new StudentEnquiriesDTO();
			StudentEnquiryEntity studentEnquiryEntity = enquiriesEntity.get();
			BeanUtils.copyProperties(studentEnquiryEntity, dto);
			return dto;
		}
		
		return null;
		
	}


}
