package com.example.lab.service.Impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lab.DAO.CustomerAddressRepository;
import com.example.lab.DAO.CustomerClassificationRepository;
import com.example.lab.DAO.CustomerDetailsRepository;
import com.example.lab.DAO.CustomerIdRepository;
import com.example.lab.DAO.CustomerNameRepository;
import com.example.lab.DAO.CustomerPoiRepository;
import com.example.lab.DAO.CustomerSignInRepository;
import com.example.lab.DAO.FinancialInstitutionRepository;
import com.example.lab.DTO.CustomerDetailsDTO;
import com.example.lab.DTO.CustomerPoiDTO;
import com.example.lab.DTO.TypeValue;
import com.example.lab.entity.CUST_ADDRESS;
import com.example.lab.entity.CUST_CL;
import com.example.lab.entity.CUST_DETAILS;
import com.example.lab.entity.CUST_ID;
import com.example.lab.entity.CUST_NAME;
import com.example.lab.entity.CUST_POI;
import com.example.lab.entity.CUST_SIGNIN;
import com.example.lab.entity.FIN_INSTITUTIONS;
import com.example.lab.service.CustomerService;
import com.example.lab.service.Helper.CustomerServiceHelper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;
    @Autowired
    CustomerClassificationRepository customerClassificationRepository;
    @Autowired
    CustomerServiceHelper customerServiceHelper;
    @Autowired
    CustomerNameRepository customerNameRepository;
    @Autowired
    CustomerAddressRepository customerAddressRepository;
    @Autowired
    CustomerPoiRepository customerPoiRepository;
    @Autowired
    CustomerIdRepository customerIdRepository;
    @Autowired
    FinancialInstitutionRepository financialInstitutionRepository;
    @Autowired
    CustomerSignInRepository customerSignInRepository;

    @Override
    public CUST_DETAILS addCustomerDetails(CustomerDetailsDTO customerDetailsDTO)
    {
        CUST_DETAILS cust_DETAILS = customerServiceHelper.generateCust_DETAILS(null, customerDetailsDTO);
        cust_DETAILS.setCrud_value('C');
        customerDetailsRepository.save(cust_DETAILS);
        addName(cust_DETAILS, customerDetailsDTO.getCustomerFullName());
        addId(cust_DETAILS);
        addAddress(cust_DETAILS, customerDetailsDTO.getCustomerFullAddress());
        return cust_DETAILS;
    }

    @Override
    public CUST_DETAILS updateCustomerDetails(Long id, CustomerDetailsDTO customerDetailsDTO)
    {
        CUST_DETAILS cust_DETAILS = customerDetailsRepository.findCustomerRecord(id);
        if (cust_DETAILS == null)
        {
            System.out.println("User doesnt exist");
            return null;
        }
        CUST_DETAILS updatedCust_DETAILS = customerServiceHelper.generateCust_DETAILS(id, customerDetailsDTO);
        updatedCust_DETAILS.setCrud_value('U');
        customerDetailsRepository.save(updatedCust_DETAILS);
        updateName(cust_DETAILS, customerDetailsDTO.getCustomerFullName(), updatedCust_DETAILS);
        updateId(cust_DETAILS, updatedCust_DETAILS);
        updateAddress(cust_DETAILS, customerDetailsDTO.getCustomerFullAddress(), updatedCust_DETAILS);
        return updatedCust_DETAILS;
    }

    @Override
    public CUST_CL addClassification(TypeValue nameTypeValue)
    {
        CUST_CL cust_CL = customerServiceHelper.generateCust_CL(nameTypeValue);
        customerClassificationRepository.save(cust_CL);
        return cust_CL;
    }

    @Override
    public CUST_POI addCust_POI(Long id, CustomerPoiDTO customerPoiDTO)
    {
        Optional<CUST_DETAILS> cust_DETAILS =  customerDetailsRepository.findById(id);
        CUST_POI cust_POI = customerServiceHelper.generateCust_POI(cust_DETAILS, customerPoiDTO);
        cust_POI.setCrud_value('C');
        customerPoiRepository.save(cust_POI);
        return cust_POI;
    }

    @Override
    public CUST_ID addCust_ID(Long id, TypeValue IdTypeValue)
    {
        Optional<CUST_DETAILS> cust_DETAILS = customerDetailsRepository.findById(id);
        CUST_ID cust_ID = customerServiceHelper.generateCust_ID(cust_DETAILS, IdTypeValue);
        cust_ID.setCrud_value('C');
        customerIdRepository.save(cust_ID);
        return cust_ID;
    }

    @Override
    public CUST_ADDRESS addCustomerAddress(Long id, TypeValue AddressTypeValue)
    {
        Optional<CUST_DETAILS> cust_DETAILS = Optional.of(customerDetailsRepository.findCustomerRecord(id));
        CUST_ADDRESS cust_ADDRESS = customerServiceHelper.generateCust_ADDRESS(cust_DETAILS, AddressTypeValue);
        cust_ADDRESS.setCrud_value('C');
        customerAddressRepository.save(cust_ADDRESS);
        return cust_ADDRESS;
    }

    @Override
    public CUST_ADDRESS updateCustomerAddress(Long id, TypeValue AddressTypeValue)
    {
        Optional<CUST_DETAILS> cust_DETAILS = Optional.of(customerDetailsRepository.findCustomerRecord(id));
        CUST_ADDRESS cust_ADDRESS = customerServiceHelper.generateCust_ADDRESS(cust_DETAILS, AddressTypeValue);
        cust_ADDRESS.setCrud_value('U');
        customerAddressRepository.save(cust_ADDRESS);
        // checking if the country is the same or changed
        if (!cust_DETAILS.get().getCountry().equals(customerAddressRepository.findCustomerTerritory(cust_DETAILS.get().getIdfr(), "country")))
        {
            cust_DETAILS.get().setCountry(AddressTypeValue.getValue());
        }
        return cust_ADDRESS;
    }

    @Override
    public CUST_SIGNIN addSignIn(Long id, TypeValue userPassTypeValue)
    {
        Optional<CUST_DETAILS> cust_DETAILS = Optional.of(customerDetailsRepository.findCustomerRecord(id));
        if (cust_DETAILS.isPresent())
        {
            CUST_SIGNIN cust_SIGNIN = new CUST_SIGNIN();
            cust_SIGNIN.setCust_DETAILS(cust_DETAILS.get());
            cust_SIGNIN.setLdbid(cust_DETAILS.get().getLdbid());
            final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            cust_SIGNIN.setPassword(encoder.encode(userPassTypeValue.getValue()));
            cust_SIGNIN.setUserName(userPassTypeValue.getType());
            cust_SIGNIN.setRole("USER");
            cust_SIGNIN.setCrud_value('C');
            customerServiceHelper.setAuditLog(cust_SIGNIN);
            customerSignInRepository.save(cust_SIGNIN);
            return cust_SIGNIN;
        }
        return null;
    }

    @Override
    public FIN_INSTITUTIONS addFinInstitution(TypeValue FinInstitutionTypeValue)
    {
        FIN_INSTITUTIONS fin_INSTITUTIONS = new FIN_INSTITUTIONS();
        fin_INSTITUTIONS.setType(FinInstitutionTypeValue.getType());
        fin_INSTITUTIONS.setName(FinInstitutionTypeValue.getValue());
        fin_INSTITUTIONS.setCrud_value('C');
        customerServiceHelper.setAuditLog(fin_INSTITUTIONS);
        financialInstitutionRepository.save(fin_INSTITUTIONS);
        return fin_INSTITUTIONS;
    }

    public void updateName(CUST_DETAILS cust_DETAILS, List<TypeValue> nameParts, CUST_DETAILS updatedCust_DETAILS)
    {
        if(cust_DETAILS.getFullName().equals(updatedCust_DETAILS.getFullName()))
        {
            return;
        }
        for (TypeValue namepart: nameParts)
        {
            CUST_NAME cust_NAME = customerServiceHelper.generateCust_NAME(cust_DETAILS, namepart);
            cust_NAME.setCrud_value('U');
            customerNameRepository.save(cust_NAME);
        }
    }
    public void addName(CUST_DETAILS cust_DETAILS, List<TypeValue> nameParts)
    {
        for (TypeValue namepart: nameParts)
        {
            CUST_NAME cust_NAME = customerServiceHelper.generateCust_NAME(cust_DETAILS, namepart);
            cust_NAME.setCrud_value('C');
            customerNameRepository.save(cust_NAME);
        }
    }
    public void addId(CUST_DETAILS cust_DETAILS)
    {
        TypeValue mobileTypeValue = new TypeValue();
        mobileTypeValue.setType("mobile");
        mobileTypeValue.setValue(cust_DETAILS.getMobile());
        CUST_ID mobileId = customerServiceHelper.generateCust_ID(Optional.of(cust_DETAILS), mobileTypeValue);
        mobileId.setCrud_value('C');
        customerIdRepository.save(mobileId);

        TypeValue emaiTypeValue = new TypeValue();
        emaiTypeValue.setType("email");
        emaiTypeValue.setValue(cust_DETAILS.getEmail());
        CUST_ID emailId = customerServiceHelper.generateCust_ID(Optional.of(cust_DETAILS), emaiTypeValue);
        emailId.setCrud_value('C');
        customerIdRepository.save(emailId);
    }
    public void updateId(CUST_DETAILS cust_DETAILS, CUST_DETAILS updatedCust_DETAILS)
    {
        if (!cust_DETAILS.getMobile().equals(updatedCust_DETAILS.getMobile()))
        {
            TypeValue mobileTypeValue = new TypeValue();
            mobileTypeValue.setType("mobile");
            mobileTypeValue.setValue(updatedCust_DETAILS.getMobile());
            CUST_ID mobileId = customerServiceHelper.generateCust_ID(Optional.of(updatedCust_DETAILS), mobileTypeValue);
            mobileId.setCrud_value('U');
            customerIdRepository.save(mobileId);
        }
        else if (!cust_DETAILS.getEmail().equals(updatedCust_DETAILS.getEmail()))
        {
            TypeValue emaiTypeValue = new TypeValue();
            emaiTypeValue.setType("email");
            emaiTypeValue.setValue(updatedCust_DETAILS.getEmail());
            CUST_ID emailId = customerServiceHelper.generateCust_ID(Optional.of(updatedCust_DETAILS), emaiTypeValue);
            emailId.setCrud_value('U');
            customerIdRepository.save(emailId);
        }
    }
    public void addAddress(CUST_DETAILS cust_DETAILS, List<TypeValue> address)
    {
        for (TypeValue territory: address)
        {
            CUST_ADDRESS cust_ADDRESS = customerServiceHelper.generateCust_ADDRESS(Optional.of(cust_DETAILS), territory);
            cust_ADDRESS.setCrud_value('C');
            customerAddressRepository.save(cust_ADDRESS);
        }
    }

    public void updateAddress(CUST_DETAILS cust_DETAILS, List<TypeValue> address, CUST_DETAILS updateCust_DETAILS)
    {
        Long cstDetIdfr = cust_DETAILS.getIdfr();
        List<TypeValue> prevAddress = customerAddressRepository.findCustomerAddress(cstDetIdfr);
        for (TypeValue territory: address)
        {
            int flag = 0;
            for (TypeValue prevTerritory: prevAddress)
            {
                if (prevTerritory.equals(territory))
                {
                    flag = -1;
                    break;
                }
            }
            if (flag == 0)
            {
                CUST_ADDRESS cust_ADDRESS = customerServiceHelper.generateCust_ADDRESS(Optional.of(updateCust_DETAILS), territory);
                cust_ADDRESS.setCrud_value('U');
                customerAddressRepository.save(cust_ADDRESS);
            }
        }
    }
}
