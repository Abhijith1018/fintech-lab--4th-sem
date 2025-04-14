package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.CustomerDTO;
import com.example.ecommerce.DTO.OrderDTO;
import com.example.ecommerce.DTO.OrderStatusDTO;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Entity.Order;
import com.example.ecommerce.Entity.Product;

import java.util.List;

public interface EcommerceService {
    Product saveProduct(ProductDTO productDTO);
    Customer saveCustomer(CustomerDTO customerDTO);
    Order createOrder(OrderDTO orderDTO);
    Order updateOrderStatus(Integer orderId, OrderStatusDTO statusDTO);
    List<Order> getCustomerOrders(Integer customerId);
}
