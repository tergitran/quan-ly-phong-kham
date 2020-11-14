package DAO;

import java.sql.*;

public class MysqlConnection {
	Connection con;
	Statement stm;
	public MysqlConnection() {
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phongbenh","root","mylove123");
//			stm = con.createStatement();
//			int result = stm.executeUpdate("insert into benh values(null, 'Ruột thừa')");
//			System.out.print(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi select");
		}
		return rs;
	}

	
	public int excuteUpdate(String sql) {
		int rs = -1;
		try {
			stm = con.createStatement();
			rs = stm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi update");
		}
		return rs;
	}
		
	

}
