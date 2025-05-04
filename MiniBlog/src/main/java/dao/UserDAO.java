package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {

	private final String JDBC_URL ="jdbc:mysql://localhost/simpletodos?useSSL=false&allowPublicKeyRetrieval=true";
	private final String DB_USER = "root";
	private String DB_PASS ="52481001uk";

	public List<User> findAll(){
		List<User> userList = new ArrayList<>();
		
		//jdbcドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバ読み込めませんでした");
		}
		
		try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			
			String sql = "SELECT ID, USERNAME,PASSWORD FROM MYSQL.USERSMINIBLO ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				User user = new User(username,password);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
	
	public boolean isValidUser(User user) {
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS)){
				String sql = "SELECT * FROM USERSMINIBLO WHERE USERNAME = ? AND PASSWORD = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1,  user.getName());
				pStmt.setString(2, user.getPass());
				
				ResultSet rs = pStmt.executeQuery();
				if (rs.next()) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
