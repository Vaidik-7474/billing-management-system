package com.example.BillGenerationSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_info")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id") //FK column in DB
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id") //FK column in DB
    private Product product;

    private int quantity;

    @Column(name = "total_amount")
    private double totalAmount;

    public Order(Customer customer, Product product, int quantity, double totalAmount) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
