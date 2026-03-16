package com.example.BillGenerationSystem.DTO;

public class BillResponse {
    private String cName;
    private String pName;
    private double price;
    private int gst;
    private int quantity;
    private double subTotal;
    private double gstAmount;
    private double totalAmount;

    public BillResponse(){}

    public BillResponse(String cName, String pName, double price, int gst, int quantity, double subTotal, double gstAmount, double totalAmount) {
        this.cName = cName;
        this.pName = pName;
        this.price = price;
        this.gst = gst;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.gstAmount = gstAmount;
        this.totalAmount = totalAmount;
    }

    public String getcName() {
        return cName;
    }

    public String getpName() {
        return pName;
    }

    public double getPrice() {
        return price;
    }

    public int getGst() {
        return gst;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getGstAmount() {
        return gstAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
