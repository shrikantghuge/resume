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
					con=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6144051", "sql6144051", "XuHVt4ZI2w");
				} catch (ClassNotFoundException e1) {			
					e1.printStackTrace();
				} catch (SQLException e1) {			
					e1.printStackTrace();
				}
			}			
		}else{
			try {
				Class.forName("com.mysql.jdbc.Driver");			
				con=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6144051", "sql6144051", "XuHVt4ZI2w");
			} catch (ClassNotFoundException e1) {			
				e1.printStackTrace();
			} catch (SQLException e1) {			
				e1.printStackTrace();
			}
		}
		System.out.println("New connection ::"+con);
		return con;
	} 
}
