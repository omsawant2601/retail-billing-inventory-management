package com.util;

import java.util.List;
import java.util.Map;

import com.dao.ProductDao;
import com.model.Product;

public class TablePrinter {

	// display Product cashier

	public static void displayItems() {
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

	// display products to manager/ Inventory Assistance
	public static void displayProducts(List<Product> plist) {
		System.out.println(
				"========================================================================================================");

		System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-8s | %-15s | %-10s |%n", "ID", "Name", "Category",
				"Price", "Qty", "Discount Type", "Discount");

		System.out.println(
				"========================================================================================================");
		for (Product p : plist) {
			System.out.printf("| %-5d | %-20s | %-15s | %-10.2f | %-8d | %-15s | %-10.2f |%n", p.getId(), p.getName(),
					p.getCategory(), p.getPrice(), p.getQuantity(), p.getDiscountType(), p.getDiscount());
		}
		System.out.println(
				"========================================================================================================");
	}
	
	//print table for single product
	public static void displaySingleProduct(Product p) {
		System.out.println(
				"========================================================================================================");
		
		System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-8s | %-15s | %-10s |%n", "ID", "Name", "Category",
				"Price", "Qty", "Discount Type", "Discount");
		
		System.out.println(
				"========================================================================================================");
			System.out.printf("| %-5d | %-20s | %-15s | %-10.2f | %-8d | %-15s | %-10.2f |%n", p.getId(), p.getName(),
					p.getCategory(), p.getPrice(), p.getQuantity(), p.getDiscountType(), p.getDiscount());
		System.out.println(
				"========================================================================================================");
	}

}
