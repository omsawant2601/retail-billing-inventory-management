package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;

public class DatabaseConnection {
	public static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/retailedge", "root","Adiom@0801");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return  con;
	}
	public static void closeConnection(Connection con,PreparedStatement pst) {
		try {
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeConnection(Connection con, PreparedStatement pst,ResultSet rs) {
		try {
			rs.close();
			closeConnection(con, pst);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static List<Product> productRowMapper(ResultSet rs) throws SQLException{
		List<Product> list = new ArrayList();
		while(rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getDouble("price"));
			p.setCategory(rs.getString("category"));
			p.setQuantity(rs.getInt("quantity"));
			p.setDiscountType(rs.getString("discountType"));
			p.setDiscount(rs.getDouble("discount"));
			list.add(p);
		}
		rs.close();
		
		return list;
	}
}
