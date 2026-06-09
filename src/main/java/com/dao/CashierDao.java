package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.model.Bill;
import com.model.Product;
import com.util.DatabaseConnection;
import com.util.TablePrinter;

public class CashierDao {
	 
	public void cashier(){
		//Display products to Cashier
		TablePrinter.displayItems();
		addToCart();
		
		
	}
	// Add product to cart logic
		public static void addToCart() {
			// get products from database and add to map
			Map<Integer, Product> products = new HashMap<>();
			String sql = "select * from product ";
			try (Connection con = DatabaseConnection.createConnection();
					PreparedStatement pst = con.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					Product p = new Product();
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					p.setQuantity(rs.getInt("quantity"));
					p.setCategory(rs.getString("category"));
					p.setDiscountType(rs.getString("discountType"));
					p.setDiscount(rs.getDouble("discount"));
					products.put(p.getId(), p);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Cashier Interaction with console
			Scanner sc = new Scanner(System.in);
			Map<Integer, Bill> cart = new HashMap<>();
			while (true) {

				// ask for id of product and validation
				System.out.print("Enter Product id :- ");
				int id = sc.nextInt();

				Product product = products.get(id);
				if (product == null) {
					System.out.println("Product not found");
					continue;
				}

				// Ask for quantity and check available stock
				System.out.print("Enter Quanity :-");
				int qyt = sc.nextInt();
				if (qyt > product.getQuantity()) {
					System.out.println("Sorry only " + product.getQuantity() + " items are available");
					continue;
				}

				// Prevent duplicate addition of product or Quantity update
				if (cart.containsKey(id)) {
					Bill execting = cart.get(id);
					cart.put(id, new Bill(product, execting.getquantity() + qyt));
				} else {
					cart.put(id, new Bill(product, qyt));
				}

				System.out.print("Do you want to add another products (y/n) :-");
				char ynrespo = sc.next().charAt(0);
				if (ynrespo == 'n' || ynrespo == 'N') {
					break;
				}
			}
			DatabaseDao.updateDatabase(cart);
			BillDao.generateBill(cart);
		}
		

		
	
}
