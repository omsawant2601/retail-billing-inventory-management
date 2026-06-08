package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dao.ProductDao;
import com.util.DatabaseConnection;

public class Customer {

	// display Product to user

	public void displayItems() {
		ProductDao productDao = new ProductDao();
		Map<String, List> listOfProduct = productDao.displayProductListToUser();
		List<Integer> listOfId = listOfProduct.get("listOfId");
		List<String> listOfName = listOfProduct.get("listOfName");
		List<String> listOfCategory = listOfProduct.get("listOfCategory");
		List<Double> listOfPrice = listOfProduct.get("listOfPrice");
		List<String> listOfDsT = listOfProduct.get("listOfDsT");
		List<Double> listOfDs = listOfProduct.get("listOfDs");

		System.out.println("+----------+----------------------+----------+--------------+");
		System.out.printf("| %-8s | %-20s | %-8s | %-12s |%n", "ID", "NAME", "PRICE", "DISCOUNT");
		System.out.println("+----------+----------------------+----------+--------------+");

		for (int i = 0; i < listOfId.size(); i++) {

			System.out.printf("| %-8d | %-20s | %-8.2f | %-12s |%n", listOfId.get(i), listOfName.get(i),
					listOfPrice.get(i), listOfDsT.get(i));
		}

		System.out.println("+----------+----------------------+----------+--------------+");

	}

	// Add to cart logic
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

		// user Interaction with console
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
		printBill(cart);
		updateDatabase(cart);
	}

	public static void printBill(Map<Integer, Bill> cart) {

		double total = 0;
		double totalDiscount = 0;

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

		System.out.println("====================================================================================");

		System.out.printf("| %-52s | %-10.2f |%n", "Total Amount", total);

		System.out.printf("| %-52s | %-10.2f |%n", "Total Discount", totalDiscount);

		System.out.printf("| %-52s | %-10.2f |%n", "Payable Amount", total - totalDiscount);

		System.out.println("====================================================================================");
	}

	public static void updateDatabase(Map<Integer, Bill> cart) {
		String sql = "update product set quantity = ? where id = ?";
		try (Connection con = DatabaseConnection.createConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			for (Bill item : cart.values()) {
				Product p = item.getProduct();
				int newQty = p.getQuantity() - item.getquantity();
				pst.setInt(1, newQty);
				pst.setInt(2, p.getId());
				pst.executeUpdate();

			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
