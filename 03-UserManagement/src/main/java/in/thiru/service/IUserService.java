package in.thiru.service;

import java.util.Map;

import in.thiru.dto.QuotesResponseDTO;
import in.thiru.dto.ResetPasswordDTO;
import in.thiru.dto.UserDTO;

public interface IUserService {
	
	public UserDTO login(UserDTO userdto);
//	public UserDTO login(String email, String password);
	
	public boolean isUniqueEmail(String mail);
	public boolean register(UserDTO userDTO) throws Exception;
	
	public Map<Integer,String> getCountry();
	public Map<Integer,String> getState(Integer countryId);
	public Map<Integer,String> getCities(Integer cityId);
	
	public boolean resetPswrd(ResetPasswordDTO resetDTO);
	public QuotesResponseDTO getQuote();
	
	

}
