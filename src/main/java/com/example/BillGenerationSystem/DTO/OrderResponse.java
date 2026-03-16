package com.example.BillGenerationSystem.DTO;

import org.springframework.http.HttpStatus;

public class OrderResponse {
    private BillResponse bill;
    private HttpStatus status;
    private String message;

    public OrderResponse(){}

    public OrderResponse(BillResponse bill, HttpStatus status, String message) {
        this.bill = bill;
        this.status = status;
        this.message = message;
    }

    public BillResponse getBill() {
        return bill;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
