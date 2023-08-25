package com.formacion.mail.sender.controller;

import com.formacion.mail.sender.domain.EmailFileInputDto;
import com.formacion.mail.sender.domain.EmailInputDto;
import com.formacion.mail.sender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailInputDto emailInputDto){
        System.out.println("Mensaje recibido "+emailInputDto);

        emailService.sendEmail(emailInputDto);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Enviado");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendMessageFile")
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileInputDto emailFileInputDto){

        emailService.receiveAndSendEmailWithFile(emailFileInputDto);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Enviado");
        response.put("file", emailFileInputDto.getFile().getName());

        return  ResponseEntity.ok(response);
    }
}
