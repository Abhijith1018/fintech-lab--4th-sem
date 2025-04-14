package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.CustomerDTO;
import com.example.ecommerce.DTO.OrderDTO;
import com.example.ecommerce.DTO.OrderStatusDTO;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Entity.Order;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Service.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EcommerceController {
    @Autowired
    private EcommerceService service;

    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductDTO productDTO){
        return service.saveProduct(productDTO);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody CustomerDTO customerDTO){
        return service.saveCustomer(customerDTO);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderDTO orderDTO){
        return service.createOrder(orderDTO);
    }

    @PutMapping("/orders/{id}/status")
    public Order updateOrderStatus(@PathVariable Integer id, @RequestBody OrderStatusDTO statusDTO){
        return service.updateOrderStatus(id,statusDTO);
    }

    @GetMapping("/customers/{customerId}/orders")
    public List<Order> getCustomerOrders(@PathVariable Integer customerId){
        return service.getCustomerOrders(customerId);
    }


}
