package in.shrikant.resume.provider;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionProvider {
	private static Connection con=null;
	public static Connection getConnection() {
		
		if(con!=null){
			try{
				con.prepareStatement("select 1 from dual").executeQuery();
			}catch(Exception exception){
				try {
					Class.forName("com.mysql.jdbc.Driver");			
					
				} catch (ClassNotFoundException e1) {			
					e1.printStackTrace();
				}
			}			
		}else{
			try {
				Class.forName("com.mysql.jdbc.Driver");			
				
			} catch (ClassNotFoundException e1) {			
				e1.printStackTrace();
			}
		}
		System.out.println("New connection ::"+con);
		return con;
	} 
}
