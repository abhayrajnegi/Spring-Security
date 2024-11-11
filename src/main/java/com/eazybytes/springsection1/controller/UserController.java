package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.Entity.Customer;
import com.eazybytes.springsection1.Entity.Users;
import com.eazybytes.springsection1.Repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registerUser")
    private ResponseEntity<String>registerUser(@RequestBody Customer customer)
    {
        try{
            String hashPass=passwordEncoder.encode(customer.getPass());
            customer.setPass(hashPass);
            Customer savedCustomer=customerRepo.save(customer);
            if(customer.getId()>0)
            {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("User created with id "+savedCustomer.getId());
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("cant create user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("an exception occurred " + e.getMessage());
        }
    }

}
