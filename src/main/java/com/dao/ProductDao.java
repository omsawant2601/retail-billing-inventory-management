package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Product;
import com.util.DatabaseConnection;

public class ProductDao {
	Product p = new Product();
	
	// insert product logic
	public int insertProduct(String name,String category, double price, String discountType,int quantity, double discount) {
		int check = 0;
		String sql = "insert into product (name,category, price, discountType, quantity , discount ) values(?,?,?,?,?,?)";
		try(Connection con = DatabaseConnection.createConnection();
				PreparedStatement pst = con.prepareStatement(sql);){
			pst.setString(1, name);
			pst.setString(2, category);
			pst.setDouble(3, price);
			pst.setString(4, discountType);
			pst.setInt(5, quantity);
			pst.setDouble(6, discount);
			check = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	public List displayProduct() {
		List<Product> list = new ArrayList<>();
		String sql  = "select id,name,category, price, discountType,quantity, discount from product";
		try(Connection con = DatabaseConnection.createConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()){
			list.addAll(DatabaseConnection.productRowMapper(rs));	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public Map<String, List> displayProductListToUser(){
		Map<String, List> productMap = new HashMap<>();
		String sql = "select id , name, category, price, discountType, discount from product";
		try(Connection con = DatabaseConnection.createConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()){
			List<Integer> listOfId = new ArrayList<>();
			List<String> listOfName = new ArrayList<>();
			List<String> listOfCategory = new ArrayList<>();
			List<Double> listOfPrice = new ArrayList<>();
			List<String> listOfDsT = new ArrayList<>();
			List<Double> listOfDs = new ArrayList<>();
			while(rs.next()) {
				listOfId.add(rs.getInt("id"));
				listOfName.add(rs.getString("name"));
				listOfCategory.add(rs.getString("category"));
				listOfPrice.add(rs.getDouble("price"));
				listOfDsT.add(rs.getString("discountType"));
				listOfDs.add(rs.getDouble("discount"));
			}
			productMap.put("listOfId", listOfId);
			productMap.put("listOfName", listOfName);
			productMap.put("listOfCategory", listOfCategory);
			productMap.put("listOfPrice", listOfPrice);
			productMap.put("listOfDsT", listOfDsT);
			productMap.put("listOfDs", listOfDs);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productMap;
	}
	
}
