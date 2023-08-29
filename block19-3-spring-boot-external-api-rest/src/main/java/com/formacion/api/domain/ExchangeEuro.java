package com.formacion.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeEuro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String coin;
    private BigDecimal actualValue;
    private Date updatedDate;
}
