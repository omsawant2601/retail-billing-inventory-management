package com.model;

public class Product {
	private int id ;
	private String name;
	private String category ;
	private double price;
	private int quantity;
	private String discountType;
	private double discount;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String name, String category, double price, int quantity, String discountType,
			double discount) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.discountType = discountType;
		this.discount = discount;
	}
	@Override
//	public String toString() {
//		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", quantity="
//				+ quantity + ", discountType=" + discountType + ", discount=" + discount + "]";
//	}
	public String toString() {
		return  id + "\t" + name + "\t" + category + "\t" + price + "\t"
				+ quantity + "\t" + discountType + "\t" + discount ;
	}
	

}
