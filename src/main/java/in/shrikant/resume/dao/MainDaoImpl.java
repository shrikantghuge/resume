package in.shrikant.resume.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import in.shrikant.resume.exceptions.InvalidIpException;
import in.shrikant.resume.modal.User;
import in.shrikant.resume.model.Enquiry;

public class MainDaoImpl implements MainDao  {
	
	private JdbcTemplate jdbcTemplate;
	private static Logger LOGGER = Logger.getLogger(MainDaoImpl.class);
	private Connection connection;
	
	@Override
	public boolean checkIpStatus(String ipAddress)
			throws InvalidIpException {
			/*PreparedStatement preparedStatement=null; 
			try{	
				connection = jdbcTemplate.getDataSource().getConnection();
				preparedStatement =  connection.prepareStatement("select * from visitor_details where ipaddress=\""+ipAddress+"\"");			
				ResultSet resultSet =preparedStatement.executeQuery();
				LOGGER.info("select * from visitor_details where ipaddress=\""+ipAddress+"\" AND result set next is :");
				if(resultSet.next()){
					LOGGER.info("MainDaoImpl.checkIpStatus visitor ip address  is present");
					return true;
				}else{
					return false;
				}
			}catch(Exception exception){
				exception.printStackTrace();
			}finally{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}*/
			return false;				
		}

	@Override
	public boolean storeEnquiryForm(Enquiry enquiry) throws SQLException {	
		/*	LOGGER.info("storeEnquiryForm enter");			
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into enquiry values (\""+enquiry.getName()+"\",\""+enquiry.getEmail()+"\",\""+enquiry.getSubject()+"\",\""+enquiry.getMessage()+"\")");
			preparedStatement.executeUpdate();
			LOGGER.debug("The final query is :"+"insert into enquiry values (\""+enquiry.getName()+"\",\""+enquiry.getEmail()+"\",\""+enquiry.getSubject()+"\",\""+enquiry.getMessage()+"\")");
			LOGGER.info("storeEnquiryForm exit");
			connection.close();
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
			return true;
			
	}

	@Override
	public boolean storeVisitorDetails(User user) throws Exception {
		LOGGER.info("Into visitor detail dao");
		LOGGER.info("Query to execute ::-->"+"insert into visitor_details(name,ipaddress,phone,cause) values(\""+user.getName()+"\",\""+user.getIpAddress()+"\",\""+user.getContactDetails()+"\",\""+(user.getReqType()==null?"":user.getReqType()+"\")"));
		//jdbcTemplate.execute("insert into visitor_details(name,ipaddress,phone,cause) values(\""+user.getName()+"\",\""+user.getIpAddress()+"\",\""+user.getContactDetails()+"\",\""+(user.getReqType()==null?"":user.getReqType()+"\")"));
		return true;
	}
	
	@Override
	public boolean storeSkippedVisitorDetails(User user) throws Exception {
		LOGGER.info("into storeSkippedVisitorDetails ");
		/*connection = ConnectionProvider.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into visitor_details values(\"\",\""+user.getIpAddress()+"\",\"\",\"skiped\")");
		preparedStatement.executeUpdate();*/
		jdbcTemplate.execute("insert into visitor_details values(\"skiped\",\""+user.getIpAddress()+"\",\"\",\"\")");
		//connection.close();
		return true;
	}

	@Override
	public void setDataSource(DataSource dataSource) {		
		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
		jdbcTemplate = new JdbcTemplate(dataSource);	
		LOGGER.info("The jdbc template is initialized"+jdbcTemplate);
	}

}
