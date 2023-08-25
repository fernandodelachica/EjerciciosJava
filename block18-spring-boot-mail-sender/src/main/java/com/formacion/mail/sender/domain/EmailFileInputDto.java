package com.formacion.mail.sender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailFileInputDto {

    private String[] toUser;
    private String subject;
    private String message;
    private MultipartFile file;
}
