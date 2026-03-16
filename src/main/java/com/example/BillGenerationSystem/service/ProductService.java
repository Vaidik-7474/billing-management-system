package com.example.BillGenerationSystem.service;

import com.example.BillGenerationSystem.DTO.StockUpdateRequest;
import com.example.BillGenerationSystem.DTO.StockUpdateResponse;
import com.example.BillGenerationSystem.model.Product;
import com.example.BillGenerationSystem.repository.ProductRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void addProducts(List<Product> products) {
        productRepository.saveAll(products);
    }

    public StockUpdateResponse addStock(StockUpdateRequest request) {
        Product product = productRepository.findById(request.getpId()).orElse(null);
        if (product == null) {
            return new StockUpdateResponse(HttpStatus.OK, "invalid product id");
        }
        productRepository.addStock(request.getQuantity(), request.getpId());
        return new StockUpdateResponse(HttpStatus.OK, "stock updated for requested product");
    }

    public File generateStockCsv() throws Exception {

        List<Product> products = productRepository.findAll();

        File file = new File("stock-report.csv");
        FileWriter writer = new FileWriter(file);

        writer.append("Product ID,Product Name,Stock\n");

        for(Product p : products) {
            writer.append(p.getId() + "," +
                    p.getName() + "," +
                    p.getQuantity() + "\n");
        }

        writer.flush();
        writer.close();

        return file;
    }

    public void sendMailWithAttachment(File file) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("valathiya02@gmail.com");
        helper.setSubject("Daily Stock Report");
        helper.setText("Attached is today's stock report");

        helper.addAttachment(file.getName(), file);

        mailSender.send(message);
    }

    @Scheduled(cron = "0 0 20 * * *") // every day at 8 PM
    public void sendDailyStockReport() {

        try {
            File file = generateStockCsv();
            sendMailWithAttachment(file);
            System.out.println("Stock report sent successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
