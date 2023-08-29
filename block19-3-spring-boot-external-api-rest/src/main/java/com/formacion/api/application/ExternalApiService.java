package com.formacion.api.application;

import org.springframework.http.ResponseEntity;

public interface ExternalApiService {

    ResponseEntity<?> getAllPosts();
    ResponseEntity<?> getCoinBankExchange(String coin, String outputType);
    ResponseEntity<?> getCoinCryptoExchange(String coin, String outputType);
}
