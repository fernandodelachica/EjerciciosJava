package com.soap.controller;

import com.soap.application.SoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soap")
public class SoapController {

    @Autowired
    private SoapService soapService;

    @GetMapping("/continents")
    public ResponseEntity<?> listAllContinents(){
        return ResponseEntity.ok().body(soapService.getAllContinents());
    }

    @GetMapping("/countries")
    public ResponseEntity<?> listAllCountries(){
        return ResponseEntity.ok().body(soapService.getAllCountries());
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> listAllCurrencies(){
        return ResponseEntity.ok().body(soapService.getAllCurrenciesByName());
    }

    @GetMapping("/currency/{code}")
    public ResponseEntity<?> getCurrencyName(@PathVariable String code){
        return ResponseEntity.ok().body(soapService.getCurrencyByName(code.toUpperCase()));
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<?> getCountryNameByCode(@PathVariable String code){
        return ResponseEntity.ok().body(soapService.getCountryByCode(code.toUpperCase()));
    }

}
