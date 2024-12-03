package com.eazybytes.springsection1.controller;

import com.eazybytes.springsection1.Repository.CardsRepository;
import com.eazybytes.springsection1.model.Cards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

//    @GetMapping("/myCards")
//    public String getCardsDetails()
//    {
//        return "here are the details of my cards";
//    }

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam long id) {
        List<Cards> cards = cardsRepository.findByCustomerId(id);
        if (cards != null ) {
            return cards;
        }else {
            return null;
        }
    }
}
