package com.dao;

import java.time.LocalDate;
import java.util.Scanner;

public class ManagerDao {
	public void manager() {
		DatabaseDao dd = new DatabaseDao();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome to Manager Dashboard, Please select any one option");
			System.out.println("1 = Todays revenue");
			System.out.println("2 = perticular day revenue");
			System.out.println("3 = Monthly revenue");
			System.out.println("4 = Yearly revenue");
			System.out.println("5 = go back Logout Page");
			int choice = sc.nextInt();
			switch (choice) {
			
				case 1:
					String todaysDate = LocalDate.now().toString();
					System.out.println("Revenue of Todays is :- " + dd.dailyRevenue(todaysDate));
					break;
					
				case 2: 
					System.out.print("Enter date in format YYYY-MM-DD :- ");
					String date = sc.next();
					System.out.println("Revenue of" + date + "is :- " + dd.dailyRevenue(date));
					break;
					
				case 3:
					System.out.print("Enter month :-");
					int month = sc.nextInt();
					System.out.print("Enter year :- ");
					int year = sc.nextInt();
					System.out.println("Monthly revenue of " + month + " " +year + "is :- " +dd.monthlyRevenue(month, year));
					break;
					
				case 4 :
					System.out.print("Enter year :- ");
					int yearly = sc.nextInt();
					System.out.print("Revenue of year" + yearly + " is :- " + dd.yearlyRevenue(yearly));
					break;
					
				case 5:
					break;
					
			}
			System.out.println("log out as Manager (Y/N) :-");
			char yesOrNo = sc.next().charAt(0);
			if(yesOrNo == 'y' || yesOrNo =='Y') {
				System.out.println("Thank you manager");
				break;
			}
		}
	}
}
