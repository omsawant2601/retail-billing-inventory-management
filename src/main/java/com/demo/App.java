package com.demo;

import java.util.Scanner;

import com.dao.CashierDao;
import com.dao.DatabaseDao;
import com.dao.InventoryDao;
import com.dao.ProductDao;
import com.model.Customer;
import com.model.Product;

public class App {
	public static void main(String[] args) {
		CashierDao cr = new CashierDao();
		InventoryDao id = new InventoryDao();

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome in RetailEdge");
		while (true) {
			System.out.println("Press 1 for Inventory Assistance");
			System.out.println("Press 2 for Cashier");
			System.out.println("Press 3 for Manager");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				id.inventoryAssistant();
				break;

			case 2:
				cr.cashier();
				break;

			case 3:
				System.out.println("Development under Progress");
				break;

			}
			System.out.print("your are in main menu to continue press y or n :-");
			char yesOrNo = sc.next().charAt(0);
			if(yesOrNo == 'n' || yesOrNo =='N') {
				System.out.println("Thanks to use RetailEdge, have a nice day sir");
				break;
			}
		}
		sc.close();

	}
}
