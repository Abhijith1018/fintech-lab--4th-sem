package com.example.lab.service;



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

public interface CustomerService {
    CUST_DETAILS addCustomerDetails(CustomerDetailsDTO customerDetailsDTO);
    CUST_CL addClassification(TypeValue nameTypeValue);
    CUST_POI addCust_POI(Long id, CustomerPoiDTO customerPoiDTO);
    CUST_ID addCust_ID(Long id, TypeValue IdTypeValue);
    CUST_ADDRESS addCustomerAddress(Long id, TypeValue AddressTypeValue);
    CUST_SIGNIN addSignIn(Long id, TypeValue userPassTypeValue);
    FIN_INSTITUTIONS addFinInstitution(TypeValue FinInstitutionTypeValue);
    CUST_DETAILS updateCustomerDetails(Long id, CustomerDetailsDTO customerDetailsDTO);
    CUST_ADDRESS updateCustomerAddress(Long id, TypeValue AddressTypeValue);
}

