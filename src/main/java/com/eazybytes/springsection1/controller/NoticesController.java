package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.Entity.Users;
import com.eazybytes.springsection1.Repository.NoticeRepository;
import com.eazybytes.springsection1.Repository.UsersRepo;
import com.eazybytes.springsection1.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private  NoticeRepository noticeRepository;

    @GetMapping("/'notices'")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        System.out.println("notices: " + notices);
        if (notices != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        } else {
            return null;
        }
    }

}
