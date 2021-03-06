package in.shrikant.resume.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.shrikant.resume.modal.User;
import in.shrikant.resume.model.Enquiry;
import in.shrikant.resume.service.MainService;

@Controller
@RequestMapping("/*")
public class MainController {
	static Logger LOGGER = Logger.getLogger(MainController.class);
	
	@Autowired
	private MainService mainService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String getFirstPage(ModelMap model,HttpServletRequest request){
		String ipAddress = request.getHeader("X-FORWARDED-FOR");		
		ipAddress = request.getRemoteAddr();
		boolean isVisited=  mainService.isIPRecorded(ipAddress);
		LOGGER.info("IP Address is  ::-->"+ipAddress);
		if(isVisited){
			model.addAttribute("isVisited","none");
		}else{
			model.addAttribute("isVisited","block");
		}			
		return "index";
		
	}
	
	@RequestMapping(path="registerEnquiry",method = RequestMethod.GET)
	@ResponseBody
	public String registerEnquiry(@ModelAttribute("enquiry")Enquiry enquiry,HttpServletRequest request){		
		boolean response=false;
		LOGGER.info("Entered into registerEnquiry");
		if(enquiry!=null){		
				response = mainService.enquiryFormStore(enquiry);	
		}
		LOGGER.info("Exit from registerEnquiry with response "+response);
		if(response){
			return "success";
		}else{
			return "failure";
		}		
	}
	
	@RequestMapping(path="visitorInfo",method = RequestMethod.POST)
	@ResponseBody
	public void storeVisitorInfo(@ModelAttribute("userDetails")User userDetails,HttpServletRequest request){		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		ipAddress = request.getRemoteAddr();
		userDetails.setIpAddress(ipAddress);
		LOGGER.info("We have got ip address as"+ipAddress);
		LOGGER.info("The user details are ::"+userDetails);
		if(userDetails!=null){			
			mainService.ipWiseVisitorDetails(userDetails);
		}
				
	}
}
