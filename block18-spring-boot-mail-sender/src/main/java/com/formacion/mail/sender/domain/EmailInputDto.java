package com.formacion.mail.sender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailInputDto {

    private String[] toUser;
    private String subject;
    private String message;
}
