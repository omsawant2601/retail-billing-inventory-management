package com.model;

public class Bill {
	private Product product;
	private int quantity ;
	public int getquantity() {
		return quantity;
	}
	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product; 
	}
	public Bill( Product product,int quantity) {
		super();
		this.quantity = quantity;
		this.product = product;
	}
	
}
