package com.formacion.api.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {

    @Autowired
    private RestTemplate restTemplate;


    private String typicodeApi = "https://jsonplaceholder.typicode.com";

    private String exchangeApi = "https://api.exchangerate.host/latest";

    @Override
    public ResponseEntity<?> getAllPosts(){
        Object result = restTemplate.getForObject(typicodeApi+"/posts", Object.class);

        return ResponseEntity.ok().body(result);

    }


    @Override
    public ResponseEntity<?> getCoinBankExchange(String coin, String outputType){
        Map<String, Object> result = restTemplate.getForObject(exchangeApi+"?base="+coin+"&source=ecb", Map.class);

        //Filtramos los campos que queremos que aparezcan
        Map<String, Object> filteredResult = new HashMap<>();
        filteredResult.put("base", result.get("base"));
        filteredResult.put("date", result.get("date"));

        //Filtramos los rates que queremos que aparezcan en el output
        Map<String, Object> rates = (Map<String, Object>) result.get("rates");
        Map<String, Object> filteredRates = new HashMap<>();
        filteredRates.put("USD", rates.get("USD"));
        filteredRates.put("JPY", rates.get("JPY"));

        filteredResult.put("rates", filteredRates);

        if(outputType.equalsIgnoreCase("filtered")) {
            return ResponseEntity.ok().body(filteredResult);
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @Override
    public ResponseEntity<?> getCoinCryptoExchange(String coin, String outputType){
        Map<String, Object> result = restTemplate.getForObject(exchangeApi+"?base="+coin+"&source=crypto", Map.class);

        //Filtramos los campos que queremos que aparezcan
        Map<String, Object> filteredResult = new HashMap<>();
        filteredResult.put("base", result.get("base"));
        filteredResult.put("date", result.get("date"));

        //Filtramos los rates que queremos que aparezcan en el output
        Map<String, Object> rates = (Map<String, Object>) result.get("rates");
        Map<String, Object> filteredRates = new HashMap<>();
        filteredRates.put("BTC", rates.get("BTC"));
        filteredRates.put("ETB", rates.get("ETB"));
        filteredRates.put("WATER", rates.get("WATER"));

        filteredResult.put("rates", filteredRates);

        if(outputType.equalsIgnoreCase("filtered")) {
            return ResponseEntity.ok().body(filteredResult);
        } else {
            return ResponseEntity.ok().body(result);
        }
    }
}
