package com.example.lab.controller;


import com.example.lab.DTO.CustomerDetailsDTO;
import com.example.lab.DTO.CustomerPoiDTO;
import com.example.lab.DTO.TypeValue;
import com.example.lab.entity.CUST_ADDRESS;
import com.example.lab.entity.CUST_CL;
import com.example.lab.entity.CUST_DETAILS;
import com.example.lab.entity.CUST_ID;
import com.example.lab.entity.CUST_POI;
import com.example.lab.entity.CUST_SIGNIN;
import com.example.lab.entity.FIN_INSTITUTIONS;
import com.example.lab.entity.User;
import com.example.lab.service.CustomerService;
import com.example.lab.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    /*  @Autowired
     private UserService userService; */
    @Autowired
    private CustomerService customerService;

    /* @GetMapping("/getUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/addUser")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    } */

    @GetMapping("/login")
    public String getloginPage(Model model)
    {
        model.addAttribute("pageTitle", "Login page");
        return "login";
    }
    // Sign up page sends this request
    @PostMapping("/register")
    public CUST_DETAILS createCustomer(@RequestBody CustomerDetailsDTO customerDetailDTO)
    {
        CUST_DETAILS cust_DETAILS = customerService.addCustomerDetails(customerDetailDTO);
        return cust_DETAILS;
    }
    // update customer details
    @PutMapping("/updateDetails/{id}")
    public CUST_DETAILS updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDetailsDTO customerDetailsDTO)
    {
        CUST_DETAILS cust_DETAILS = customerService.updateCustomerDetails(id, customerDetailsDTO);
        return cust_DETAILS;
    }


    @PostMapping("/addClassification")
    public CUST_CL createClassification(@RequestBody TypeValue nameTypeValue)
    {
        CUST_CL cust_cl = customerService.addClassification(nameTypeValue);
        return cust_cl;
    }

    @PostMapping("/addFinancialInstitution")
    public FIN_INSTITUTIONS createFin_INSTITUTIONS(@RequestBody TypeValue institutionTypeValue)
    {
        FIN_INSTITUTIONS fin_INSTITUTIONS = customerService.addFinInstitution(institutionTypeValue);
        return fin_INSTITUTIONS;
    }

    @PostMapping("/addPOI/{id}")
    public CUST_POI createCust_POI(@PathVariable("id") Long id, @RequestBody CustomerPoiDTO customerPoiDTO)
    {
        CUST_POI cust_POI = customerService.addCust_POI(id, customerPoiDTO);
        return cust_POI;
    }

    @PostMapping("/addIdentification/{id}")
    public CUST_ID createCust_Id(@PathVariable("id") Long id, @RequestBody TypeValue IdTypeValue)
    {
        CUST_ID cust_ID = customerService.addCust_ID(id, IdTypeValue);
        return cust_ID;
    }

    @PostMapping("/addAddress/{id}")
    public CUST_ADDRESS createCust_ADDRESS(@PathVariable("id") Long id, @RequestBody TypeValue addressTypeValue)
    {
        CUST_ADDRESS cust_ADDRESS = customerService.addCustomerAddress(id, addressTypeValue);
        return cust_ADDRESS;
    }

    @PutMapping("/updateAddress/{id}")
    public CUST_ADDRESS updateCust_ADDRESS(@PathVariable("id") Long id, @RequestBody TypeValue addressTypeValue)
    {
        CUST_ADDRESS cust_ADDRESS = customerService.updateCustomerAddress(id, addressTypeValue);
        return cust_ADDRESS;
    }
    @PostMapping("/register/{id}")
    public CUST_SIGNIN createCust_SIGNIN(@PathVariable("id") Long id,@RequestBody TypeValue userPassTypeValue)
    {
        CUST_SIGNIN cust_SIGNIN = customerService.addSignIn(id, userPassTypeValue);
        return cust_SIGNIN;
    }
}

