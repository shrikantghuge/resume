package in.shrikant.resume.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import in.shrikant.resume.bean.UserIpDetails;
import in.shrikant.resume.exceptions.InvalidIpException;
import in.shrikant.resume.modal.User;
import in.shrikant.resume.model.Enquiry;

public class MainDaoImpl implements MainDao {
	
	private JdbcTemplate jdbcTemplate;
	private static Logger LOGGER = Logger.getLogger(MainDaoImpl.class);
	
	@Override
	public boolean checkIpStatus(String ipAddress)
			throws InvalidIpException {		
		LOGGER.info("select count(*) from visitor_details where ipaddress=\""+ipAddress+"\"");
		Integer count = jdbcTemplate.queryForObject("select count(*) from visitor_details where ipaddress=\""+ipAddress+"\"", Integer.class);
		LOGGER.info("MainDaoImpl.checkIpStatus visitor ip address  count is :"+count);
		if(count>0){
			return false;
		}else{
			return true;
		}		
	}

	@Override
	public boolean storeEnquiryForm(Enquiry enquiry) throws SQLException {	
			LOGGER.info("storeEnquiryForm enter");
			LOGGER.debug("The final query is :"+"insert into enquiry values (\""+enquiry.getName()+"\",\""+enquiry.getEmail()+"\",\""+enquiry.getSubject()+"\",\""+enquiry.getMessage()+"\")");
			jdbcTemplate.execute("insert into enquiry values (\""+enquiry.getName()+"\",\""+enquiry.getEmail()+"\",\""+enquiry.getSubject()+"\",\""+enquiry.getMessage()+"\")");
			LOGGER.info("storeEnquiryForm exit");
			return true;			
	}

	@Override
	public boolean storeVisitorDetails(User user) throws Exception {
		LOGGER.info("Into visitor detail dao");
		//jdbcTemplate.execute("insert into visitor_details values(\""+user.getName()+"\",\""+user.getIpAddress()+"\",\""+user.getContactDetails()+"\",\""+user.getReqType()==null?"":user.getReqType()+"\")");
		jdbcTemplate.execute("insert into visitor_details values(\""+user.getName()+"\",\""+user.getIpAddress()+"\",\""+user.getContactDetails()+"\",\""+user.getReqType()+"\")");
		return true;
	}
	
	@Override
	public boolean storeSkippedVisitorDetails(User user) throws Exception {
		jdbcTemplate.execute("insert into visitor_details values(\"skiped\",\""+user.getIpAddress()+"\",\"\",\"\")");
		return true;
	}

	@Override
	public void setDataSource(DataSource dataSource) {		
		jdbcTemplate = new JdbcTemplate(dataSource);	
		LOGGER.info("The jdbc template is initialized"+jdbcTemplate);
	}

}
