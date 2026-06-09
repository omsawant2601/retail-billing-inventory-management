package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.model.Bill;
import com.model.Product;
import com.util.DatabaseConnection;

public class BillDao {
	
	//Print bill by Cashier
	public static void generateBill(Map<Integer, Bill> cart) {

		double total = 0;
		double totalDiscount = 0;
		Map<String, Double> bill_Amount = new HashMap<>();

		System.out.println();
		System.out.println("====================================================================================");

		System.out.printf("| %-20s | %-5s | %-10s | %-10s | %-10s | %-10s |%n", "Product", "Qty", "Price", "Amount",
				"Discount", "Final");

		System.out.println("====================================================================================");

		for (Bill item : cart.values()) {

			Product p = item.getProduct();

			double amount = p.getPrice() * item.getquantity();
			double discount = p.getDiscount() * item.getquantity();
			double finalTotal = amount - discount; 

			total += amount;
			totalDiscount += discount;

			System.out.printf("| %-20s | %-5d | %-10.2f | %-10.2f | %-10.2f | %-10.2f |%n", p.getName(),
					item.getquantity(), p.getPrice(), amount, discount, finalTotal);
		}
		double payable_Amount = total - totalDiscount;
		bill_Amount.put("total_Amount", total);
		bill_Amount.put("total_Discount", totalDiscount);
		bill_Amount.put("payable_Amount", payable_Amount);

		System.out.println("====================================================================================");

		System.out.printf("| %-52s | %-10.2f |%n", "Total Amount", total);

		System.out.printf("| %-52s | %-10.2f |%n", "Total Discount", totalDiscount);

		System.out.printf("| %-52s | %-10.2f |%n", "Payable Amount", payable_Amount);

		System.out.println("====================================================================================");
		
		System.out.printf("| %-52s | %-10.2f |%n", "Saves Amount", totalDiscount);

		System.out.println("====================================================================================");

		BillDao.saveBill(bill_Amount, cart);
	}
	
	//Save bill by cashier 
	public static void saveBill(Map<String, Double> bill_Amount,Map<Integer, Bill> cart) {
		String saveBill = "insert into bills(total_amount, total_discount, final_amount) values(?,?,?)";
		try (Connection con = DatabaseConnection.createConnection();
				PreparedStatement pst = con.prepareStatement(saveBill, Statement.RETURN_GENERATED_KEYS);) {
			pst.setDouble(1, bill_Amount.get("total_Amount"));
			pst.setDouble(2, bill_Amount.get("total_Discount"));
			pst.setDouble(3, bill_Amount.get("payable_Amount"));
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {
				int billId = rs.getInt(1);
				System.out.println("Bill ID: " + billId);
				BillitemsDao.saveBillItems(billId, cart);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}

	}
	
}
