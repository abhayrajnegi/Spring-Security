package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.Repository.LoanRepository;
import com.eazybytes.springsection1.model.Loans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

//    @GetMapping("/myLoans")
//    public String getLoansDetails()
//    {
//        return "here are the details of my loans";
//    }

    @Autowired
    private  LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam long id) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }

}
