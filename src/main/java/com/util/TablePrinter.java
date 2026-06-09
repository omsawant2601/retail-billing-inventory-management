package com.util;

import java.util.List;
import java.util.Map;

import com.dao.ProductDao;

public class TablePrinter {
 
	// display Product to user

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

	
}
