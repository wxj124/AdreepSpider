package com.wenba.adreep;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdreepConnection {
	private static final String url = "jdbc:mysql://localhost:3306/Adreep?characterEncoding=UTF-8";
	private static final String username = "root";
	private static final String password = "root";

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn =  DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static boolean insert(String title,String writer,String content,String url) throws UnsupportedEncodingException {
		Connection conn;
		int i = 0;
		try {
			conn = getConnection();
			PreparedStatement pre = null;
			String sql = "insert into t_adreep(title,writer,content,url) values(?,?,?,?)";
			pre = conn.prepareStatement(sql);
			title = new String(title.getBytes("utf-8")); 
			pre.setString(1, title);
			System.out.println(title);
			pre.setString(2, writer);
	
			pre.setString(3, content);
			pre.setString(4, url);
			i = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (i > 0) {
			return true;
		} else {
			return false;
		}

	}
	
}
