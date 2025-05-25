package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {

	
	//DB接続情報
	private final String JDBC_URL ="jdbc:mysql://localhost:3306/miniblog";
	private final String DB_USER = "root";
	private final String DB_PASS ="52481001uk";

	
	public boolean isValidUser(User user) {
		String sql = "SELECT * FROM users WHERE username = ? AND password ?";
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)){
					stmt.setString(1,  user.getUsername());
					stmt.setString(2,  user.getPassword());
					ResultSet rs = stmt.executeQuery();
					return rs.next();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
	}
	
	}
