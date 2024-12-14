package com.eazybytes.springsection1.controller;

//import com.eazybytes.springsection1.Entity.Customer;
import com.eazybytes.springsection1.Entity.Users;
import com.eazybytes.springsection1.Repository.CustomerRepo;
import com.eazybytes.springsection1.Repository.CustomerRepository;
import com.eazybytes.springsection1.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {


//    private final CustomerRepo customerRepo;
//    private final PasswordEncoder passwordEncoder;
//
//    @PostMapping("/registerUser")
//    private ResponseEntity<String>registerUser(@RequestBody Customer customer)
//    {
//        try{
//            String hashPass=passwordEncoder.encode(customer.getPass());
//            customer.setPass(hashPass);
//            Customer savedCustomer=customerRepo.save(customer);
//            if(customer.getId()>0)
//            {
//                return ResponseEntity.status(HttpStatus.CREATED)
//                        .body("User created with id "+savedCustomer.getId());
//            }
//            else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("cant create user");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("an exception occurred " + e.getMessage());
//        }
//    }


    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).
                        body("Given user details are successfully registered");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body("User registration failed");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred: " + ex.getMessage());
        }
    }

    @RequestMapping("/user"
    )
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

}
