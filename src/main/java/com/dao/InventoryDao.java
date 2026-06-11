package com.dao;

import java.util.List;
import java.util.Scanner;

import com.model.Product;
import com.util.TablePrinter;

public class InventoryDao {
	public  void inventoryAssistant() {
		TablePrinter.displayItems();

		Scanner sc = new Scanner(System.in);
		DatabaseDao dd = new DatabaseDao();
		while (true) {
			System.out.println("welcome in Inventory, Please Select any one option");
			System.out.println("1 = add new Product");
			System.out.println("2 = update Product");
			System.out.println("3 = add new quantity in exiting Quantity of Product");
			System.out.println("4 = verify one Product");
			System.out.println("5 = verify all product" );
			System.out.println("6 = delete Product" );
			System.out.println("7 = low quantity" );
			System.out.println("8 = go back to Logout page" );
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				Product p1 = new Product();
				System.out.print("Enter name of product :- ");
				String nameadd = sc.next();
				p1.setName(nameadd);
				
				System.out.print("Enter Categoty of product :- ");
				String categoryadd = sc.next();
				p1.setCategory(categoryadd);

				System.out.print("Enter price of product :- ");
				double priceadd = sc.nextDouble();
				p1.setPrice(priceadd);
				
				System.out.print("Enter Quantity of product :- ");
				int quantityadd = sc.nextInt();
				p1.setQuantity(quantityadd);
				
				System.out.print("Enter DicountType of product :- ");
				String discountTypeadd = sc.next();
				p1.setDiscountType(discountTypeadd);
				
				System.out.print("Enter Dicount of product :- ");
				double discountadd = sc.nextDouble();
				p1.setDiscount(discountadd);
				
				int resultOfAdd = dd.addNewProduct(p1) ;
				if (resultOfAdd== 1) {
					System.out.println("new Product added Successfully");
				} else {
					System.out.println("facing problem to add new product");
				}
				break;

			case 2:
				System.out.println("Enter id of product to update :- ");
				int id = sc.nextInt();
				Product p = dd.findProductByiId(id);
				
				System.out.print("Enter Quantity of product to update :- ");
				int quantityupdate = sc.nextInt();
				p.setQuantity(quantityupdate);
				
				int resultOfUpdate = dd.updateProductQuantity(p);
				if (resultOfUpdate == 1) {
					System.out.println("Product updated Successfully");
				} else {
					System.out.println("facing problem to update product");
				}
				break;
			case 3:
				System.out.println("Enter id of product to Add new Quantity :- ");
				int idAdd = sc.nextInt();
				Product pAddQuantity = dd.findProductByiId(idAdd);
				
				System.out.print("Enter Quantity of product to Add :- ");
				int quantityAddQuantity = sc.nextInt();
				pAddQuantity.setQuantity(quantityAddQuantity + pAddQuantity.getQuantity() );
				
				int resultOfAddQuantity = dd.updateProductQuantity(pAddQuantity);
				if (resultOfAddQuantity == 1) {
					System.out.println("Quantity added Successfully");
				} else {
					System.out.println("facing problem to update product");
				}
				break;

			case 4:
				System.out.print("Enter the id to verify product :- ");
				int idfind = sc.nextInt();
				System.out.println(dd.findProductByiId(idfind));
				TablePrinter.displaySingleProduct(dd.findProductByiId(idfind));
				break;

			case 5 :
				TablePrinter.displayProducts(dd.displayAllProducts());
				break;
				
			case 6:
				System.out.print("Enter the id to delete product :- ");
				int idDelete = sc.nextInt();
				int resultOfDelete = dd.deleteProduct(idDelete);
				if (resultOfDelete == 1) {
					System.out.println("Product deleted");
				} else {
					System.out.println("facing problem to delete product");
				}
				break;
				
			case 7:
				System.out.println("Enter low Quantity :- ");
				int lowQuantity = sc.nextInt();
				List<Product> plist = dd.lowStock(lowQuantity);
				if(!plist.isEmpty()) {
					TablePrinter.displayProducts(plist);
				}
				break;
				
			case 8 :
				break;

			}
			System.out.print("log out as Inventory Assistance press ()Y/N :- ");
			char yesOrNo = sc.next().charAt(0);
			if(yesOrNo == 'y' || yesOrNo =='Y') {
				System.out.println("Redirecting to Main manu...");
				break;
			}
		}

	}

}
