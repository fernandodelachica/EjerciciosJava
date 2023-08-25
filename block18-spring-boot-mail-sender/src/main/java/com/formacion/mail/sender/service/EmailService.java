package com.formacion.mail.sender.service;

import com.formacion.mail.sender.domain.EmailFileInputDto;
import com.formacion.mail.sender.domain.EmailInputDto;

import java.io.File;

public interface EmailService {

    void sendEmail(EmailInputDto emailInputDto);

    void receiveAndSendEmailWithFile(EmailFileInputDto emailFileInputDto);
}
