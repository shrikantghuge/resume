package in.shrikant.resume.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.shrikant.resume.model.Enquiry;
import in.shrikant.resume.service.MainService;

@Controller
@RequestMapping("/*")
public class MainController {
	static Logger LOGGER = Logger.getLogger(MainController.class);
	
	@Autowired
	private MainService mainService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String getFirstPage(){
		LOGGER.info("Entered into Main controller");
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
}