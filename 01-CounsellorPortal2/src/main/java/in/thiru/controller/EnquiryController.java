package in.thiru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.thiru.constant.AppConstants;
import in.thiru.dtos.EnquiryFilterDTO;
import in.thiru.dtos.StudentEnquiriesDTO;
import in.thiru.service.IEnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private IEnquiryService enquiryService;

	@GetMapping("/view-enquiries")
	public String viewEnquiries(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute(AppConstants.COUNSELLOR_ID);

		List<StudentEnquiriesDTO> allEnquiries = enquiryService.getAllEnquiries(counsellorId);

		model.addAttribute("enquiries", allEnquiries);

		EnquiryFilterDTO enquiryDto = new EnquiryFilterDTO();

		model.addAttribute("enquiryDto", enquiryDto);

		return "view-enquiry";
	}

	@PostMapping("/enquiry-filter")
	public String filterEnquiries(@ModelAttribute("enquiryDto") EnquiryFilterDTO enquiryDto, HttpServletRequest req,
			Model model) {

		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute(AppConstants.COUNSELLOR_ID);

		List<StudentEnquiriesDTO> allEnquiries = enquiryService.getAllEnquiries(enquiryDto, counsellorId);

		model.addAttribute("enquiries", allEnquiries);

		return "view-enquiry";
	}

	@GetMapping("/add-enquiry")
	public String addEnq(Model model) {
		StudentEnquiriesDTO enquiry = new StudentEnquiriesDTO();

		model.addAttribute("enquiry", enquiry);

		return AppConstants.ADD_ENQUIRY;
	}

	@PostMapping("/add-enquiry")
	public String addEnquiries(HttpServletRequest req, @ModelAttribute("enquiry") StudentEnquiriesDTO enquiry,
			Model model) {

		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute(AppConstants.COUNSELLOR_ID);

		boolean status = enquiryService.addEnquiries(enquiry, counsellorId);

		if (status) {
			model.addAttribute("smsg", "Enquiry Saved");
		} else {
			model.addAttribute("smsg", "Failed To Save Enquiry");
		}

		return AppConstants.ADD_ENQUIRY;
	}

	@GetMapping("/edit")
	public String editEnquiry(@ModelAttribute("enquiry") StudentEnquiriesDTO enquiry, Model model) {

		StudentEnquiriesDTO enquiry1 = enquiryService.updateEnquiries(enquiry.getEnqId());

		model.addAttribute("enquiry", enquiry1);
		return AppConstants.ADD_ENQUIRY; // Reuse the add-enquiry page for editing
	}

}
