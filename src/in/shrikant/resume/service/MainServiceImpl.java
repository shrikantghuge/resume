package in.shrikant.resume.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import in.shrikant.resume.bean.UserIpDetails;
import in.shrikant.resume.dao.MainDao;
import in.shrikant.resume.exceptions.InvalidIpException;
import in.shrikant.resume.modal.User;
import in.shrikant.resume.model.Enquiry;

public class MainServiceImpl  implements MainService{
	
	private static Logger LOGGER = Logger.getLogger(MainServiceImpl.class);
	
	@Autowired
	private MainDao mainDao;
	
	@Override
	public boolean isIPRecorded(String ipAddress){
		try {
			return mainDao.checkIpStatus(ipAddress);
		} catch (InvalidIpException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean enquiryFormStore(Enquiry enquiry)  {
		LOGGER.info("Entered into enquiryFormStore");
		try {
			mainDao.storeEnquiryForm(enquiry);
			LOGGER.info("Exit from enquiryFormStore");
			return true;
		} catch (SQLException e) {
			LOGGER.error("Error occured",e);			
			return false;
		}		
	}

	@Override
	public boolean ipWiseVisitorDetails(User user)  {
		
		if("skip".equalsIgnoreCase(user.getReqType())){
			try {
				LOGGER.info("Going with request for skip");
				mainDao.storeSkippedVisitorDetails(user);
			} catch (Exception e) {
				LOGGER.info("The db error occured for User Details :"+user+" Error is:"+e.getMessage());				
			}
		}else{
			try {
				mainDao.storeVisitorDetails(user);
			} catch (Exception e) {
				LOGGER.info("The db error occured for User Details :"+user+" Error is:"+e.getMessage());
			}
		}
		return true;
	}

}
