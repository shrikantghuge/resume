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
	public boolean checkIpStatus(UserIpDetails userIpDetails)
			throws InvalidIpException {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDataSource(DataSource dataSource) {		
		jdbcTemplate = new JdbcTemplate(dataSource);	
		LOGGER.info("The jdbc template is initialized"+jdbcTemplate);
	}

}
