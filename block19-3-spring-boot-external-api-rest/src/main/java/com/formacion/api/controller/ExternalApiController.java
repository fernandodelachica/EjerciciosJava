package com.formacion.api.controller;

import com.formacion.api.application.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExternalApiController {

    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts(){
        return externalApiService.getAllPosts();

    }

    @GetMapping("/exchange/bank/{coin}")
    public ResponseEntity<?> getCoinBankExchange(@PathVariable String coin, @RequestParam(defaultValue = "simple") String outputType){
        return externalApiService.getCoinBankExchange(coin, outputType);
    }

    @GetMapping("/exchange/crypto/{coin}")
    public ResponseEntity<?> getCoinCryptoExchange(@PathVariable String coin, @RequestParam(defaultValue = "simple") String outputType){
        return externalApiService.getCoinCryptoExchange(coin, outputType);
    }

    @GetMapping("/posts/users/{userId}")
    public void getAllPostByUserId(@PathVariable Long userId){

    }
}
