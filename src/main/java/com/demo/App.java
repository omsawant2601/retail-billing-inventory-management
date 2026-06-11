package com.demo;

import java.util.Scanner;

import com.dao.CashierDao;
import com.dao.InventoryDao;
import com.dao.ManagerDao;
import com.util.Loginverify;

public class App {
	public static void main(String[] args) {
		CashierDao cr = new CashierDao();
		InventoryDao id = new InventoryDao();
		ManagerDao md = new ManagerDao();
		Loginverify lu = new Loginverify();

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome in RetailEdge");
		while (true) {
			System.out.println("Press 1 for Inventory Assistance");
			System.out.println("Press 2 for Cashier");
			System.out.println("Press 3 for Manager");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter your password :- ");
				int iAPass= sc.nextInt();
				if(lu.inventoryAssistanceVerify(iAPass)) {
					id.inventoryAssistant();					
				} else {
					System.out.println("Wrong Password, try again");
				}
				break;

			case 2:
				System.out.print("Enter your password :- ");
				int cPass= sc.nextInt();
				if(lu.cashiorVerify(cPass)) {					
					cr.cashier();
				} else {
					System.out.println("Wrong Password, try again");
				}
				break;

			case 3:
				System.out.print("Enter your password :- ");
				int mPass= sc.nextInt();
				if(lu.managerVerify(mPass)) {				
					md.manager();
				} else {
					System.out.println("Wrong Password, try again");
				}
				break;

			}
			System.out.print("shout Down Application y or n :-");
			char yesOrNo = sc.next().charAt(0);
			if(yesOrNo == 'y' || yesOrNo =='Y') {
				System.out.println("Thanks to use RetailEdge, have a nice day sir");
				break;
			}
		}
		sc.close();

	}
}
