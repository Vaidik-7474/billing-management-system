package com.example.BillGenerationSystem.DTO;

public class StockUpdateRequest {
    private int pId;
    private int quantity;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
