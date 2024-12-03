package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.Repository.AccountsRepository;
import com.eazybytes.springsection1.model.Accounts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {



    @GetMapping("/myPaisa")
    public String getAccountPaisa()
    {
        return "here are the details of my myPaisa";
    }

    @Autowired
    private  AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam long id) {
        Accounts accounts = accountsRepository.findByCustomerId(id);
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }

}
