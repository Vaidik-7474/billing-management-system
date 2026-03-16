package com.example.BillGenerationSystem.service;

import com.example.BillGenerationSystem.DTO.BillResponse;
import com.example.BillGenerationSystem.DTO.OrderRequest;
import com.example.BillGenerationSystem.DTO.OrderResponse;
import com.example.BillGenerationSystem.config.TwilioConfig;
import com.example.BillGenerationSystem.model.Customer;
import com.example.BillGenerationSystem.model.Order;
import com.example.BillGenerationSystem.model.Product;
import com.example.BillGenerationSystem.repository.CustomerRepository;
import com.example.BillGenerationSystem.repository.OrderRepository;
import com.example.BillGenerationSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TwilioConfig twilioConfig;

    public OrderResponse placeOrder(OrderRequest request) {
        Customer customer = customerRepository.findById(request.getcId()).orElse(null);
        // Checks if customer exists or not in database
        if (customer == null) {
            return new OrderResponse(null, HttpStatus.OK, "customer does not exists or invalid customer");
        }

        Product product = productRepository.findById(request.getpId()).orElse(null);
        // Checks if product exists or not in database
        if (product == null) {
            return new OrderResponse(null, HttpStatus.OK, "product does not exists");
        }

        // Checks if requested quantity is greater than available quantity
        if (request.getQuantity() > product.getQuantity()) {
            return new OrderResponse(null, HttpStatus.OK, "invalid quantity, available stock: " + product.getQuantity());
        }

        // Checks payment
        if (!makePayment()) {
            return new OrderResponse(null, HttpStatus.OK, "⚠️ payment failed, try again");
        }

        // update available_quantity in product_info table after placing order
        productRepository.updateQuantity(request.getQuantity(), request.getpId());

        double subTotal = request.getQuantity() * product.getPrice();
        double gstAmount = subTotal * ( (double) product.getGst() /100);
        double totalAmount = subTotal + gstAmount;

        // Saves record in order_info table
        orderRepository.save(new Order(customer, product, request.getQuantity(), totalAmount));

        Twilio.init(
                twilioConfig.getAccountSid(),
                twilioConfig.getAuthToken()
        );

        Message message = Message
                .creator(
                        new PhoneNumber("whatsapp:+919328707470"),
                        new PhoneNumber("whatsapp:"), // Paste your twilio number here
                        "Order placed successfully, order details 👇\n\nProduct: " + product.getName() + "\nQuantity: " + request.getQuantity() + "\nAmount: " + totalAmount
                )
                .create();


        if (productRepository.getQuantity(request.getpId()) <= product.getThreshold()) {
            Message stockReminder = Message
                    .creator(
                            new PhoneNumber("whatsapp:+919328707470"),
                            new PhoneNumber("whatsapp:"), // Paste your twilio number here
                            "🚨 " + product.getName() + " is about to get out of stock"
                    )
                    .create();
        }

        return new OrderResponse(new BillResponse(customer.getName(), product.getName(), product.getPrice(), product.getGst(), request.getQuantity(), subTotal, gstAmount, totalAmount), HttpStatus.OK, "✔️ order placed successfully");
    }

    private boolean makePayment() {
        return ThreadLocalRandom.current().nextInt(5) != 0;
    }
}
