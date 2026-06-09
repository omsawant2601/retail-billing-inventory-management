package com.demo;



import com.dao.CashierDao;
import com.dao.ProductDao;
import com.model.Customer;
import com.model.Product;

public class App 
{ 
    public static void main( String[] args )
    {     
        CashierDao cr = new CashierDao();
        cr.cashier();
        
    }
}
