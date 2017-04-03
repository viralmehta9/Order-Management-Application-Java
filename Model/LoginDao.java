

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

	public String validateLogin(String userName) 
	{
		Connection conn = MySqlConnect.getConnection();
		System.out.println(conn);
		Statement stmt=null;
		int userId=0;
		String password = null;
		String role=null;
		int isActive=0;

		try {
			stmt = conn.createStatement();
			String s = "Select *  from loginCredentials where userName ='"+ userName+"'";
			ResultSet rs = stmt.executeQuery(s);

			while (rs.next()) {
				userId = rs.getInt("userId");
				password = rs.getString("password");
				role = rs.getString("role");
				isActive = rs.getInt("isActive");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return userId + "," + password +","+ role+","+ isActive;
	}
}
