package in.shrikant.resume.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import in.shrikant.resume.bean.UserIpDetails;
import in.shrikant.resume.exceptions.InvalidIpException;
import in.shrikant.resume.modal.User;
import in.shrikant.resume.model.Enquiry;

public interface MainDao {
	
	public boolean checkIpStatus(UserIpDetails userIpDetails) throws InvalidIpException;
	public boolean storeEnquiryForm(Enquiry enquiry) throws SQLException;
	public boolean storeVisitorDetails(User user) throws Exception;
	public void setDataSource(DataSource dataSource);

}
