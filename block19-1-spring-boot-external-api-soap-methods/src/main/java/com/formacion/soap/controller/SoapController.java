package com.formacion.soap.controller;

import com.formacion.soap.client.SoapService;
import com.formacion.soap.wsdl.AddResponse;
import com.formacion.soap.wsdl.DivideResponse;
import com.formacion.soap.wsdl.MultiplyResponse;
import com.formacion.soap.wsdl.SubtractResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/soap")
public class SoapController {

    @Autowired
    private SoapService soapService;

    @PostMapping("/sumar")
    public ResponseEntity<?> add(@RequestParam int numberA, @RequestParam int numberB){

        AddResponse addResponse = soapService.getAddResponse(numberA, numberB);

        Map<String, Integer> response = new HashMap<>();
        response.put("resultado", addResponse.getAddResult());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/restar")
    public ResponseEntity<?> subtract(@RequestParam int numberA, @RequestParam int numberB){

        SubtractResponse subtractResponse = soapService.getSubtractResponse(numberA,numberB);

        Map<String, Integer> response = new HashMap<>();
        response.put("resultado", subtractResponse.getSubtractResult());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/multiplicar")
    public ResponseEntity<?> multiply(@RequestParam int numberA, @RequestParam int numberB){

        MultiplyResponse multiplyResponse = soapService.getMultiplyResponse(numberA,numberB);

        Map<String, Integer> response = new HashMap<>();
        response.put("resultado", multiplyResponse.getMultiplyResult());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/dividir")
    public ResponseEntity<?> divide(@RequestParam int numberA, @RequestParam int numberB){

        DivideResponse divideResponse = soapService.getDivideResponse(numberA, numberB);

        Map<String, Integer> response = new HashMap<>();
        response.put("resultado", divideResponse.getDivideResult());

        return ResponseEntity.ok(response);
    }
}
