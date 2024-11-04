package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.Entity.Users;
import com.eazybytes.springsection1.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticesController {

    @Autowired
    private UsersRepo usersRepo;


    @GetMapping("/myNotices")
    public String getNoticesDetails()
    {
        return "here are the details of my notices";
    }

    @PostMapping("/checkJpa")
    public ResponseEntity<List<Users>> checkingTheJpa()
    {
        System.out.println("asdfasdfasdfasdf");
        List<Users>list=usersRepo.findAllByNativeQuery();
        return ResponseEntity.ok(list);
    }

}
