package com.example.BillGenerationSystem.DTO;

import org.springframework.http.HttpStatus;

public class StockUpdateResponse {
    private HttpStatus status;
    private String message;

    public StockUpdateResponse(){}

    public StockUpdateResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
