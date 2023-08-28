package com.formacion.soap.client;

import com.formacion.soap.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class SoapServiceImpl extends WebServiceGatewaySupport implements SoapService{


    private String uri = "http://www.dneonline.com/calculator.asmx";

    /** MÉTODO QUE SE ENCARGA DE SUMAR DOS NUMEROS
     * @param numberA
     * @param numberB
     * @return addResponse
     */
    public AddResponse getAddResponse(int numberA, int numberB){
        Add addRequest = new Add();
        addRequest.setIntA(numberA);
        addRequest.setIntB(numberB);

        SoapActionCallback soapActionCallback = new SoapActionCallback("http://tempuri.org/Add");

        AddResponse addResponse = (AddResponse) getWebServiceTemplate().marshalSendAndReceive(
                uri, addRequest, soapActionCallback);

        return addResponse;
    }

    /**
     * MÉTODO QUE SE ENCARGA DE RESTAR DOS NÚMEROS
     * @param numberA
     * @param numberB
     * @return subtractResponse
     */
    public SubtractResponse getSubtractResponse(int numberA, int numberB){
        Subtract subtractRequest = new Subtract();
        subtractRequest.setIntA(numberA);
        subtractRequest.setIntB(numberB);

        SoapActionCallback soapActionCallback = new SoapActionCallback("http://tempuri.org/Subtract");

        SubtractResponse subtractResponse = (SubtractResponse) getWebServiceTemplate().marshalSendAndReceive(
                uri, subtractRequest, soapActionCallback);

        return subtractResponse;
    }

    /**
     * MÉTODO QUE MULTIPLICA DOS NÚMEROS
     * @param numberA
     * @param numberB
     * @return multiplyResponse
     */
    public MultiplyResponse getMultiplyResponse(int numberA, int numberB){
        Multiply multiplyRequest = new Multiply();
        multiplyRequest.setIntA(numberA);
        multiplyRequest.setIntB(numberB);

        SoapActionCallback soapActionCallback = new SoapActionCallback("http://tempuri.org/Multiply");

        MultiplyResponse multiplyResponse = (MultiplyResponse) getWebServiceTemplate().marshalSendAndReceive(
                uri, multiplyRequest, soapActionCallback);

        return multiplyResponse;
    }

    /**
     * MÉTODO QUE DIVIDE DOS NÚMEROS
     * @param numberA
     * @param numberB
     * @return
     */
    public DivideResponse getDivideResponse(int numberA, int numberB){
        Divide divideRequest = new Divide();
        divideRequest.setIntA(numberA);
        divideRequest.setIntB(numberB);

        SoapActionCallback soapActionCallback = new SoapActionCallback("http://tempuri.org/Divide");

        DivideResponse divideResponse = (DivideResponse) getWebServiceTemplate().marshalSendAndReceive(
                uri, divideRequest, soapActionCallback);

        return divideResponse;
    }
}
