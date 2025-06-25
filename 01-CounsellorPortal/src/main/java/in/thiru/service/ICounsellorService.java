package in.thiru.service;

import in.thiru.dtos.CounsellorDTO;

public interface ICounsellorService {
	
	
	//note if we use boolean we are not able to get logged in user details(id)
	//we should be use(Id or CounsellorDTO Object)
	public CounsellorDTO login(CounsellorDTO counsDTO);
	public boolean uniqueEmailCheck(String email);
	public boolean registerCounsellor(CounsellorDTO counsDTO);

}
