package com.demo;



import com.dao.ProductDao;
import com.model.Customer;
import com.model.Product;

public class App 
{
    public static void main( String[] args )
    {
        Product p = new Product();
        ProductDao productDao = new ProductDao();
        

        
        //Display products to user        
        Customer customer = new Customer();
        customer.displayItems();
//        
//        System.out.println(customer.addToCart("parle"));
        
        
//        Customer.cart();
        Customer.addToCart();
        
    }
}
