package com.example.lab.service.Helper;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.lab.DAO.CustomerClassificationRepository;
import com.example.lab.DAO.CustomerDetailsRepository;
import com.example.lab.DAO.FinancialInstitutionRepository;
import com.example.lab.DTO.CustomerDetailsDTO;
import com.example.lab.DTO.CustomerPoiDTO;
import com.example.lab.DTO.TypeValue;
import com.example.lab.entity.AuditLoggable;
import com.example.lab.entity.CUST_ADDRESS;
import com.example.lab.entity.CUST_CL;
import com.example.lab.entity.CUST_DETAILS;
import com.example.lab.entity.CUST_ID;
import com.example.lab.entity.CUST_NAME;
import com.example.lab.entity.CUST_POI;
import com.example.lab.entity.CUST_SIGNIN;
import com.example.lab.entity.FIN_INSTITUTIONS;

// One major improvement I want to do here is that,
// When you give first name , last name in Type value
// I want to validate it with the CUST_CLS table and fetch
// cls Id from there
// Also I want to rename NameTypeValue to TypeValue since it
// is being reused

@Component
public class CustomerServiceHelper {
    @Autowired
    CustomerClassificationRepository customerClassificationRepository;
    @Autowired
    CustomerDetailsRepository customerDetailsRepository;
    @Autowired
    FinancialInstitutionRepository financialInstitutionRepository;

    public CUST_DETAILS generateCust_DETAILS(Long id, CustomerDetailsDTO customerDetailsDTO)
    {
        CUST_DETAILS cust_DETAILS = new CUST_DETAILS();

        // Generating the id for the new customer;
        if (id == null)
        {
            Long max_id = customerDetailsRepository.findMaxCustId();
            // no record exists in the db, so first record
            if (max_id == null)
            {
                max_id = Long.valueOf(1);
            }
            Long year = Long.valueOf(LocalDate.now().getYear());
            Long cust_id = (year % 1000)*100000 + max_id;
            cust_DETAILS.setId(cust_id);
        }
        // this is for updating the customer
        else
        {
            cust_DETAILS.setId(id);
        }

        // transferring the type from DTO to Entity object by getting Id from CUST_CL
        CUST_CL typeCl = customerClassificationRepository.findByType(customerDetailsDTO.getType());
        cust_DETAILS.setType(typeCl);
        // transferring dob
        cust_DETAILS.setDob(customerDetailsDTO.getDob());
        // transferring status
        cust_DETAILS.setStatus(customerDetailsDTO.getStatus());
        // transferring mobile
        cust_DETAILS.setMobile(customerDetailsDTO.getMobile());
        // transferring contact
        // cust_DETAILS.setContact(customerDetailsDTO.getContact());
        // transferring email
        cust_DETAILS.setEmail(customerDetailsDTO.getEmail());
        // transferring country
        List<TypeValue> address = customerDetailsDTO.getCustomerFullAddress();
        // giving the ldbid
        FIN_INSTITUTIONS fin_inst = financialInstitutionRepository.findIdByName(customerDetailsDTO.getBankName());
        if (fin_inst != null)
        {
            cust_DETAILS.setLdbid(fin_inst);
        }
        else
        {
            System.out.print(customerDetailsDTO.getBankName());
            System.out.println("Some error!");
        }
        for (TypeValue territory: address)
        {
            if (territory.getType().toLowerCase().equals("country"))
            {
                cust_DETAILS.setCountry(territory.getValue());
            }
        }
        // transferring the name
        StringBuilder fullNameBuilder = new StringBuilder();
        List<TypeValue> nameParts = customerDetailsDTO.getCustomerFullName();
        for (TypeValue namePart: nameParts)
        {
            fullNameBuilder.append(namePart.getValue());
            fullNameBuilder.append(" ");
        }
        String fullName = fullNameBuilder.toString().trim();
        cust_DETAILS.setFullName(fullName);

        // setting the audit log
        setAuditLog(cust_DETAILS);

        // create a list of CUST_NAME objects
        /*List<CUST_NAME> cust_NAME_list = generateCust_NAME(cust_DETAILS, nameParts);
        //setting the one to many relationship
        cust_DETAILS.setCust_NAME(cust_NAME_list);*/

        // returning the now filled CUST_DETAILS entity object
        return cust_DETAILS;
    }
    public CUST_CL generateCust_CL(TypeValue nameTypeValue)
    {
        CUST_CL cust_CL = new CUST_CL();

        // transferring the type from DTO to Entity object
        // Setting the classification type
        cust_CL.setType(nameTypeValue.getType());
        // setting the classification value
        cust_CL.setTypeValue(nameTypeValue.getValue());
        cust_CL.setCrud_value('C');
        setAuditLog(cust_CL);
        return cust_CL;
    }

    // when given the name type and value pair it creates the CUST_NAME object
    public CUST_NAME generateCust_NAME(CUST_DETAILS cust_DETAILS, TypeValue namePart)
    {
        CUST_NAME cust_NAME = new CUST_NAME();
        // Setting the type and value, which is many to one
        CUST_CL cust_CL = customerClassificationRepository.findByType(namePart.getType());
        cust_NAME.setCust_CL(cust_CL);
        cust_NAME.setValue(namePart.getValue());
        // setting the relationship , which is many to one
        cust_NAME.setCust_DETAILS(cust_DETAILS);
        // setting the foreign key to the cust_details object
        cust_NAME.setCust_DETAILS(cust_DETAILS);
        // setting the ldbid
        cust_NAME.setLdbid(cust_DETAILS.getLdbid());
        // setting audit log
        setAuditLog(cust_NAME);
        return cust_NAME;
    }

    // when given the address type and value pair it creates the CUST_ADDRESS object
    public CUST_ADDRESS generateCust_ADDRESS(Optional<CUST_DETAILS> cust_DETAILS, TypeValue territory)
    {
        CUST_ADDRESS cust_ADDRESS = new CUST_ADDRESS();
        // get the classification Id for the type of address
        CUST_CL cust_CL = customerClassificationRepository.findByType(territory.getType());
        cust_ADDRESS.setCust_CL(cust_CL);
        // setting the customer whose address it is
        if (cust_DETAILS.isPresent())
        {
            cust_ADDRESS.setCust_DETAILS(cust_DETAILS.get());
            // setting LDBID
            cust_ADDRESS.setLdbid(cust_DETAILS.get().getLdbid());
        }
        else
        {
            //throw new Exception("Id doesnt exist");
        }
        // setting the actual address value
        cust_ADDRESS.setValue(territory.getValue());
        // setting audit log
        setAuditLog(cust_ADDRESS);
        return cust_ADDRESS;
    }

    // when given the Identification type and value pair it creates the CUST_ID object
    public CUST_ID generateCust_ID(Optional<CUST_DETAILS> cust_DETAILS, TypeValue IdTypeValue)
    {
        CUST_ID cust_ID = new CUST_ID();
        // get the classification Id for the type of ID
        CUST_CL cust_CL = customerClassificationRepository.findByType(IdTypeValue.getType());
        cust_ID.setCust_CL(cust_CL);
        // setting the customer whose ID it is
        if (cust_DETAILS.isPresent())
        {
            cust_ID.setCust_DETAILS(cust_DETAILS.get());
            // setting the ldbid
            cust_ID.setLdbid(cust_DETAILS.get().getLdbid());
        }
        else
        {
            //throw new Exception("Id doesnt exist");
        }
        // setting the actual ID value
        cust_ID.setValue(IdTypeValue.getValue());
        // setting audit log
        setAuditLog(cust_ID);
        return cust_ID;
    }

    // when given the POI type and value pair it creates the CUST_POI object
    public CUST_POI generateCust_POI(Optional<CUST_DETAILS> cust_DETAILS, CustomerPoiDTO customerPoiDTO)
    {
        CUST_POI cust_POI = new CUST_POI();
        // get the classification Id for the type of address
        CUST_CL cust_CL = customerClassificationRepository.findByType(customerPoiDTO.getType());
        cust_POI.setCust_CL(cust_CL);
        // set the actual POI value
        cust_POI.setValue(customerPoiDTO.getValue());
        // setting the customer whose address it is
        if (cust_DETAILS.isPresent())
        {
            cust_POI.setCust_DETAILS(cust_DETAILS.get());
        }
        else
        {
            //throw new Exception("Id doesnt exist");
        }
        // set start and end date of the id
        cust_POI.setStart(customerPoiDTO.getStart());
        cust_POI.setEnd(customerPoiDTO.getEnd());
        // setting the audit log
        setAuditLog(cust_POI);
        return cust_POI;
    }

    // sets the audit logs(for all since they implement the interface)
    public <T extends AuditLoggable>void setAuditLog(T cust_DETAILS)
    {
        cust_DETAILS.setEfctv_date(new Date(System.currentTimeMillis()));
        cust_DETAILS.setLocal_ts(new Timestamp(System.currentTimeMillis()));
        cust_DETAILS.setHost_ts(new Timestamp(System.currentTimeMillis()));
        cust_DETAILS.setAcpt_ts(new Timestamp(System.currentTimeMillis()));
        cust_DETAILS.setAcpt_ts_utc_ofst(new Timestamp(System.currentTimeMillis()));
        cust_DETAILS.setWs_id("Mac Jaiwant");
        cust_DETAILS.setUser_id("Jaiwant");
        cust_DETAILS.setPrgm_id("Java prgm 1");
        cust_DETAILS.setUUID(UUID.randomUUID().toString());
    }
}
