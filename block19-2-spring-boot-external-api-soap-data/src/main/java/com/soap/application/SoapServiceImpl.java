package com.soap.application;

import com.soap.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

public class SoapServiceImpl extends WebServiceGatewaySupport implements SoapService{

    private final String uri = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";
    private final SoapActionCallback soapActionCallback = new SoapActionCallback("http://www.oorsprong.org/websamples.countryinfo");


    //Lista todos los continentes
    public List<TContinent> getAllContinents(){
        ListOfContinentsByName listOfContinentsByName = new ListOfContinentsByName();

        ListOfContinentsByNameResponse response = (ListOfContinentsByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, listOfContinentsByName, soapActionCallback);

        return response.getListOfContinentsByNameResult().getTContinent();
    }

    //Lista todas las ciudades
    public List<TCountryCodeAndName> getAllCountries(){
        ListOfCountryNamesByCode listOfCountryNamesByCode = new ListOfCountryNamesByCode();

        ListOfCountryNamesByCodeResponse response = (ListOfCountryNamesByCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, listOfCountryNamesByCode, soapActionCallback);

        return response.getListOfCountryNamesByCodeResult().getTCountryCodeAndName();
    }

    //Lista todos los países
    public List<TCurrency> getAllCurrenciesByName(){
        ListOfCurrenciesByCode listOfCurrenciesByCode = new ListOfCurrenciesByCode();

        ListOfCurrenciesByCodeResponse response = (ListOfCurrenciesByCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, listOfCurrenciesByCode, soapActionCallback);

        return response.getListOfCurrenciesByCodeResult().getTCurrency();
    }

    //Obtiene la moneda de un país
    public String getCurrencyByName(String code){
        CurrencyName currencyName = new CurrencyName();
        currencyName.setSCurrencyISOCode(code);

        CurrencyNameResponse response = (CurrencyNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, currencyName, soapActionCallback);

        return response.getCurrencyNameResult();
    }

    //Obtienes el país por el código
    public String getCountryByCode(String code){
        CountryName countryName = new CountryName();
        countryName.setSCountryISOCode(code);

        CountryNameResponse response = (CountryNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(uri, countryName, soapActionCallback);

        return response.getCountryNameResult();
    }
}
