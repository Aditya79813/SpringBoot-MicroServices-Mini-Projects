package in.thiru.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.thiru.dto.QuotesResponseDTO;
import in.thiru.dto.UserDTO;
import in.thiru.entity.CityEntity;
import in.thiru.entity.StateEntity;
import in.thiru.service.IUserService;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

  
    @GetMapping("/")
    public String loadLoginPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "index";
    }

    
    
    @PostMapping("/login")
    public String handleLoginPage(UserDTO userDTO,  Model model) {
    	
    	UserDTO login = userService.login(userDTO);
    	
    	QuotesResponseDTO quote = userService.getQuote();
    	
    	if(login != null)
    	{
    		model.addAttribute("quote", quote);
    	}else
    	{
    		model.addAttribute("emsg", "Invalid Credentiols");
    	}
    	
        return "index";
    }

    
    
    
    @GetMapping("/register")
    public String loadRegisterPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        
        Map<Integer, String> country = userService.getCountry();

        // Add countries to the model
        model.addAttribute("countries", country);
        return "register";
    }

    @PostMapping("/register")
    public String handleRegistration(Model model, UserDTO userDTO) throws Exception {
        boolean uniqueEmail = userService.isUniqueEmail(userDTO.getEmail());

        if (uniqueEmail) {
            boolean register = userService.register(userDTO);

            if (register) {
                model.addAttribute("smsg", "Registered Successfully");
            } else {
                model.addAttribute("smsg", "Failed to Register");
            }
        } else {
            model.addAttribute("smsg", "Please use Unique Email");
        }

        // Reload countries for the form
        model.addAttribute("countries", userService.getCountry());
        return "register";
    }

    @GetMapping("/states")
    @ResponseBody
    public Map<Integer, String> getStatesByCountry(@RequestParam Integer countryId) {
    	
    	Map<Integer, String> state = userService.getState(countryId);
    	
    	
        return userService.getState(countryId);
    }

    @GetMapping("/cities")
    @ResponseBody
    public Map<Integer, String> getCitiesByState(@RequestParam Integer stateId) {
    	Map<Integer, String> cities = userService.getCities(stateId);
        return userService.getCities(stateId);
    }
}
