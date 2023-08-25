package com.formacion.mail.sender.service;

import com.formacion.mail.sender.domain.EmailFileInputDto;
import com.formacion.mail.sender.domain.EmailInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements EmailService{

    @Value(("${email.sender}"))
    private String emailUser;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailInputDto emailInputDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailUser);
        mailMessage.setTo(emailInputDto.getToUser());
        mailMessage.setSubject(emailInputDto.getSubject());
        mailMessage.setText(emailInputDto.getMessage());

        mailSender.send(mailMessage);
    }

    @Override
    public void receiveAndSendEmailWithFile(EmailFileInputDto emailFileInputDto){
        try{
            String fileName = emailFileInputDto.getFile().getOriginalFilename();

            Path path = Paths.get("src/main/resources/files/"+fileName);
            Files.createDirectories(path.getParent());      //Si el directorio no existe lo creamos
            Files.copy(emailFileInputDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            sendEmailWithFile(emailFileInputDto.getToUser(), emailFileInputDto.getSubject(), emailFileInputDto.getMessage(), file);
        } catch (Exception e){
            throw new RuntimeException("Error al procesar el archivo "+e.getMessage());
        }
    }

    public void sendEmailWithFile(String[] toUser, String subject, String message, File file) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.addAttachment(file.getName(), file);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el Email con archivo "+e.getMessage());
        }

    }
}
