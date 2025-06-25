package in.thiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.thiru.constant.AppConstants;
import in.thiru.dtos.CounsellorDTO;
import in.thiru.dtos.DashboardResponseDTO;
import in.thiru.entity.CounsellorEntity;
import in.thiru.service.ICounsellorService;
import in.thiru.service.IEnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private ICounsellorService counsellorService;

	@Autowired
	private IEnquiryService enquiryService;

	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		session.invalidate();

		model.addAttribute(AppConstants.COUNSELLOR, new CounsellorEntity());

		return AppConstants.INDEX;
	}

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute(AppConstants.COUNSELLOR, new CounsellorEntity());

		return AppConstants.INDEX;
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("counsellor") CounsellorDTO counsellor, Model model,
			HttpServletRequest request) {

		CounsellorDTO counsellorObj = counsellorService.login(counsellor);

		if (counsellorObj == null) {
			model.addAttribute("smsg", "Please enter valid Credentionls");
			return AppConstants.INDEX;
		} else {

			Integer counsellorId = counsellorObj.getCounsellorId();

			HttpSession session = request.getSession(true);
			session.setAttribute("counsellorId", counsellorId);

			DashboardResponseDTO dashboardDTO = enquiryService.getDashboardEnquiries(counsellorId);
			model.addAttribute("dashboardDTO", dashboardDTO);
			return "dashboard";
		}

	}

	@GetMapping("/register")
	public String register(Model model) {

		model.addAttribute(AppConstants.COUNSELLOR, new CounsellorDTO());

		return "register";
	}

	@PostMapping("/register")
	public String handleRegister(@ModelAttribute("counsellor") CounsellorDTO counsellor, Model model) {

		boolean uniqueEmailCheck = counsellorService.uniqueEmailCheck(counsellor.getEmail());

		if (uniqueEmailCheck) {
			boolean registerCounsellor = counsellorService.registerCounsellor(counsellor);
			if (registerCounsellor) {
				model.addAttribute("smsg", "Registration Successfull");
			} else {
				model.addAttribute("emsg", "Failed to Register The user");
			}
		}

		model.addAttribute(AppConstants.COUNSELLOR, new CounsellorDTO());

		return "register";
	}

	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute(AppConstants.COUNSELLOR_ID);

		DashboardResponseDTO dashboardDTO = enquiryService.getDashboardEnquiries(counsellorId);
		model.addAttribute("dashboardDTO", dashboardDTO);

		return "dashboard";
	}

}
