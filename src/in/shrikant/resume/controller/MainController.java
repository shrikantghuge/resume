package in.shrikant.resume.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String getFirstPage(@RequestParam("ipAddress")String ipAddress){
		mainService.isIPRecorded(ipAddress);
		return "index";
		
	}
	
	@RequestMapping(path="registerEnquiry",method = RequestMethod.GET)
	@ResponseBody
	public String registerEnquiry(@ModelAttribute("enquiry")Enquiry enquiry){
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
		LOGGER.info("The user details are ::"+userDetails);
		if(userDetails!=null){			
			mainService.ipWiseVisitorDetails(userDetails);
		}
				
	}
}
