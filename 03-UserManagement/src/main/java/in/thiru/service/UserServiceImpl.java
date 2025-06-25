package in.thiru.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.thiru.dto.QuotesResponseDTO;
import in.thiru.dto.ResetPasswordDTO;
import in.thiru.dto.UserDTO;
import in.thiru.entity.CityEntity;
import in.thiru.entity.CountryEntity;
import in.thiru.entity.StateEntity;
import in.thiru.entity.UserEntity;
import in.thiru.repo.CityRepo;
import in.thiru.repo.CountryRepo;
import in.thiru.repo.StateRepo;
import in.thiru.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CountryRepo countryRepo;;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public UserDTO login(UserDTO userdto) {

		UserEntity findByEmailAndPswrd2 = userRepo.findByEmailAndPswrd(userdto.getEmail(), userdto.getPswrd());

		if (findByEmailAndPswrd2 != null) {
			UserDTO dto = new UserDTO();

			BeanUtils.copyProperties(findByEmailAndPswrd2, dto);

			return dto;
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUniqueEmail(String mail) {
		UserEntity findByEmail = userRepo.findByEmail(mail);
		if (findByEmail == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean register(UserDTO userDTO) throws Exception {

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDTO, entity);

		// generate a random password
		entity.setPswrd(generateRandomPassword());
		entity.setIsPswrdUpdated("NO");

		// association mapping
		CountryEntity countryEntity = countryRepo.findById(userDTO.getUserId()).get();
		StateEntity stateEntity = stateRepo.findById(userDTO.getUserId()).get();
		CityEntity cityEntity = cityRepo.findById(userDTO.getUserId()).get();

		entity.setCountry(countryEntity);
		entity.setState(stateEntity);
		entity.setCity(cityEntity);

		UserEntity userEntity = userRepo.save(entity);

		if (userEntity != null) {
			// send email whenever register
			String subject = "Account Created";
			String body = "your password is :: " + entity.getPswrd();
			String email = userDTO.getEmail();
			emailService.sendEmail(subject, body, email);
		}

		return userEntity.getUserId() != null;
	}

	private String generateRandomPassword() {

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		String pwd = RandomStringUtils.random(5, characters);

		return pwd;
	}

	@Override
	public Map<Integer, String> getCountry() {

		Map<Integer, String> countryMap = new HashMap<>();

		List<CountryEntity> countryEntity = countryRepo.findAll();

		countryEntity.forEach(countries -> countryMap.put(countries.getCountryId(), countries.getCountryName()));

		return countryMap;
	}

	@Override
	public Map<Integer, String> getState(Integer countryId) {

		Map<Integer, String> stateMap = new HashMap<Integer, String>();

		List<StateEntity> findByCountry = stateRepo.findByCountryCountryId(countryId);

		findByCountry.forEach(state -> stateMap.put(state.getStateId(), state.getStateName()));

		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer cityId) {
		// TODO Auto-generated method stub
		Map<Integer, String> citiesMap = new HashMap<Integer, String>();

		List<CityEntity> findByStateStateId = cityRepo.findByStateStateId(cityId);

		findByStateStateId.forEach(cities -> citiesMap.put(cities.getCityId(), cities.getCityName()));

		return citiesMap;
	}

	@Override
	public boolean resetPswrd(ResetPasswordDTO resetDTO) {

		Optional<UserEntity> findById = userRepo.findById(resetDTO.getUserId());
		if (findById.isPresent()) {
			UserEntity userEntity = findById.get();
			userEntity.setPswrd(resetDTO.getNewPassword());
			userEntity.setIsPswrdUpdated("YES");
		}

		return false;
	}

	@Override
	public QuotesResponseDTO getQuote() {

		String apiUrl = "https://dummyjson.com/quotes/random";

		RestTemplate template = new RestTemplate();
		ResponseEntity<QuotesResponseDTO> forEntity = template.getForEntity(apiUrl, QuotesResponseDTO.class);
		QuotesResponseDTO body = forEntity.getBody();

		return body;
	}

}
