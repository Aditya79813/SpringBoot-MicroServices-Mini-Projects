package in.thiru.service;

import java.util.List;

import in.thiru.dtos.DashboardResponseDTO;
import in.thiru.dtos.EnquiryFilterDTO;
import in.thiru.dtos.StudentEnquiriesDTO;

public interface IEnquiryService {
	
	public DashboardResponseDTO getDashboardEnquiries(Integer counsellorId);
	public boolean addEnquiries(StudentEnquiriesDTO enqDTO,Integer counsellorId);
	public List<StudentEnquiriesDTO> getAllEnquiries(Integer counsellorId);
	public List<StudentEnquiriesDTO> getAllEnquiries(EnquiryFilterDTO filterDTO,  Integer counsellorId);
	public StudentEnquiriesDTO updateEnquiries(Integer studentId);

}
