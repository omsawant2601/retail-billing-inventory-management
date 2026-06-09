package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.model.Bill;
import com.model.Product;
import com.util.DatabaseConnection;

public class BillitemsDao {

	public static void saveBillItems(int billId, Map<Integer, Bill> cart) {

		String billItemsSql = "insert into bill_items(bill_id  , product_id , quantity, price ,amount, discount, final_amount) values (?,?,?,?,?,?,?)";
		try(Connection con = DatabaseConnection.createConnection();
				PreparedStatement pst = con.prepareStatement(billItemsSql);){
			for(Bill item: cart.values()) {
				Product p = item.getProduct();
				int quantity = item.getquantity();
				double amount = (p.getPrice() * quantity);
				double discount = (p.getDiscount() * quantity);
				double final_amount = amount - discount;
				pst.setInt(1, billId);
				pst.setInt(2, p.getId());
				pst.setInt(3, quantity); 
				pst.setDouble(4, p.getPrice());
				pst.setDouble(5, amount); 
				pst.setDouble(6, discount);
				pst.setDouble(7, final_amount);
	
				pst.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
