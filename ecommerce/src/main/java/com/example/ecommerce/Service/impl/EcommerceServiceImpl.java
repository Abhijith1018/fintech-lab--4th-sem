package com.example.ecommerce.Service.impl;

import com.example.ecommerce.DAO.CustomerRepository;
import com.example.ecommerce.DAO.OrderRepository;
import com.example.ecommerce.DAO.ProductRepository;
import com.example.ecommerce.DTO.CustomerDTO;
import com.example.ecommerce.DTO.OrderDTO;
import com.example.ecommerce.DTO.OrderStatusDTO;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Entity.Order;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Service.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcommerceServiceImpl implements EcommerceService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
@Override
    public Product saveProduct(ProductDTO productDTO){
        //validation
        if(productDTO.getName()==null || productDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("product name cannot be null or empty");
        }

        if(productDTO.getPrice()==null || productDTO.getPrice() <=0) {
            throw new IllegalArgumentException("product price must be greater than zero");
        }

        if (productDTO.getStockQuantity() == null || productDTO.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        return productRepository.save(product);
        }

        @Override
        public Customer saveCustomer(CustomerDTO customerDTO){
        //validation

            if(customerDTO.getName()==null || customerDTO.getName().isEmpty()) {
                throw new IllegalArgumentException("customer name cannot be null or empty");
            }

            if(customerDTO.getEmail()==null || customerDTO.getEmail().isEmpty()) {
                throw new IllegalArgumentException("customer email cannot be null or empty");
            }
            if (!customerDTO.getEmail().contains("@") || !customerDTO.getEmail().contains(".")) {
                throw new IllegalArgumentException("Invalid email format");
            }

            Customer customer = new Customer();
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setAddress(customerDTO.getAddress());

            return customerRepository.save(customer);
            }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("invalid customer ID"));

        Product product = productRepository.findById(orderDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("invalid product ID"));

        // Validate quantity
        if (orderDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Order quantity must be greater than zero");
        }

        // Check stock availability
        if (product.getStockQuantity() < orderDTO.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock available");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalPrice(product.getPrice() * orderDTO.getQuantity());
        order.setStatus("pending");

        product.setStockQuantity(product.getStockQuantity() - orderDTO.getQuantity());
        productRepository.save(product);

        return orderRepository.save(order);
    }

   @Override
    public Order updateOrderStatus(Integer orderId, OrderStatusDTO statusDTO){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("invalid order ID"));

        String status = statusDTO.getStatus().toUpperCase();
        if (!status.equals("PENDING") && !status.equals("SHIPPED") && !status.equals("DELIVERED")) {
            throw new IllegalArgumentException("Invalid order status. Must be PENDING, SHIPPED, or DELIVERED");
        }

        order.setStatus(status);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getCustomerOrders(Integer customerId) {
        // Verify customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Invalid customer ID");
        }

        return orderRepository.findOrdersByCustomerId(customerId);
    }



}






