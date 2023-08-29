package com.soap.application;


import com.soap.wsdl.TContinent;
import com.soap.wsdl.TCountryCodeAndName;
import com.soap.wsdl.TCurrency;

import java.util.List;

public interface SoapService {

    List<TContinent> getAllContinents();

    List<TCountryCodeAndName> getAllCountries();
    List<TCurrency> getAllCurrenciesByName();

    String getCurrencyByName(String code);

    String getCountryByCode(String code);

}
