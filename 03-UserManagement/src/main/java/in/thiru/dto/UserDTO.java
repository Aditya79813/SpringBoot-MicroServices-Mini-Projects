package in.thiru.dto;

import in.thiru.entity.CityEntity;
import in.thiru.entity.CountryEntity;
import in.thiru.entity.StateEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
	
	private Integer userId;
	private String name;
	private String email;
	private Long phno;
	private String pswrd;
	private String isPswrdUpdated;
	
	private CountryEntity country;
	private StateEntity state;
	private CityEntity city;

}
