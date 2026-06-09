package com.model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
		updateDatabase(cart);
		printBill(cart);
	}

	public static void printBill(Map<Integer, Bill> cart) {

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

		saveBill(bill_Amount, cart);
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
				System.out.println("Generated Bill ID: " + billId);
				saveBillItems(billId , cart);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveBillItems(int billId, Map<Integer, Bill> cart) {
		/*
		 * CREATE TABLE bill_items( item_id INT PRIMARY KEY AUTO_INCREMENT, bill_id INT,
		 * product_id INT, quantity INT, price DOUBLE, amount DOUBLE, discount DOUBLE,
		 * final_amount DOUBLE );
		 */
		String billItemsSql = "insert into bill_items(bill_id  , product_id , quantity, price ,amount, discount, final_amount) values (?,?,?,?,?,?,?)";
		try(Connection con = DatabaseConnection.createConnection();
				PreparedStatement pst = con.prepareStatement(billItemsSql);){
			for(Bill item: cart.values()) {
				Product p = item.getProduct();
				double amount = (p.getPrice() * p.getQuantity());
				double discount = (p.getDiscount() * p.getQuantity());
				double final_amount = amount - discount;
				pst.setInt(1, billId);
				pst.setInt(2, p.getId());
				pst.setInt(3, p.getQuantity());
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
