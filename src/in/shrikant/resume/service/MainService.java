package in.shrikant.resume.service;

import in.shrikant.resume.modal.User;
import in.shrikant.resume.model.Enquiry;

public interface MainService {
	public boolean isIPRecorded(String ipAddress) ;
	public boolean enquiryFormStore(Enquiry enquiry) ;
	public boolean ipWiseVisitorDetails(User user) ;
}
