package com.example.lab.DTO;


import java.sql.Date;
import java.util.List;

public class CustomerDetailsDTO {
    private String type;
    private List<TypeValue> customerFullName;
    private Date dob;
    private String status;
    private String mobile;
    private String email;
    private String bankName;

    private List<TypeValue> customerFullAddress;

    //Getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCustomerFullName(List<TypeValue> customerFullName)
    {
        this.customerFullName = customerFullName;
    }

    public List<TypeValue> getCustomerFullName()
    {
        return customerFullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TypeValue> getCustomerFullAddress() {
        return customerFullAddress;
    }

    public void setCustomerFullAddress(List<TypeValue> customerFullAddress) {
        this.customerFullAddress = customerFullAddress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}

