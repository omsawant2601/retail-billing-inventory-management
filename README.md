# RetailPro - Smart Retail Billing & Inventory Management System

RetailPro is a Java-based Retail Billing and Inventory Management System designed to simulate real-world supermarket operations such as DMart, Reliance Smart, and other retail stores.

The system manages products, inventory, billing, discounts, offers, sales history, and revenue reports while maintaining complete transaction records.

---

## Features

### Authentication & Authorization

* Owner Login
* Manager Login
* Role-Based Access Control

### Product Management

* Add Product
* Update Product
* Delete Product
* Search Product by ID or Name
* View All Products

### Billing System

* Create Customer Cart
* Add Products using Product ID or Name
* Quantity Validation
* Product-wise Discount Calculation
* Generate Customer Bill
* Store Bill History

### Inventory Management

* Automatic Stock Deduction after Billing
* Inventory Update Tracking
* Inventory History Records
* Low Stock Alerts

### Offer Management

* Product-wise Discounts
* Flat Discounts
* Percentage Discounts
* Future Offer Extensions

### Reports

* Daily Revenue Report
* Monthly Revenue Report
* Yearly Revenue Report
* Top Selling Products
* Sales Analytics

---

## Tech Stack

### Backend

* Java
* JDBC
* MySQL

### Collections Framework

* HashMap
* ArrayList
* List
* Map

### Database

* MySQL

### Future Enhancements

* Spring Boot REST API
* React Frontend
* JWT Authentication
* PDF Bill Generation
* Dashboard Analytics

---


---

## Database Modules

### Products

Stores product details and inventory.

### Bills

Stores bill-level information.

### Bill Items

Stores products purchased in each bill.

### Users

Stores Owner and Manager credentials.

### Inventory History

Tracks stock changes and updates.

### Offers

Stores product discount information.

---

## Billing Workflow

```text
Display Products
      |
      V
Select Product
      |
      V
Enter Quantity
      |
      V
Validate Stock
      |
      V
Add To Cart
      |
      V
Apply Discount
      |
      V
Generate Bill
      |
      V
Save Bill
      |
      V
Update Inventory
      |
      V
Store Inventory History
```

---

## Current Progress

* [x] Database Design
* [x] Product Model
* [x] JDBC Connection
* [x] Product CRUD
* [x] Display Products
* [x] Cart Management
* [x] Bill History
* [ ] Inventory History
* [x] Revenue Reports
* [ ] Offer Management
* [ ] React Frontend
* [ ] Spring Boot Migration

---

## Learning Outcomes

This project demonstrates:

* Object-Oriented Programming
* JDBC Database Connectivity
* SQL CRUD Operations
* Collections Framework
* Inventory Management Logic
* Billing System Design
* Layered Architecture
* Real-World Retail Workflow

---

## Author

Omkar Sawant

Final Year Computer Engineering Student

Java | JDBC | MySQL | React | MERN Stack
