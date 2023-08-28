package com.formacion.soap.client;

import com.formacion.soap.wsdl.AddResponse;
import com.formacion.soap.wsdl.DivideResponse;
import com.formacion.soap.wsdl.MultiplyResponse;
import com.formacion.soap.wsdl.SubtractResponse;

public interface SoapService {
    AddResponse getAddResponse(int numberA, int numberB);

    SubtractResponse getSubtractResponse(int numberA, int numberB);

    MultiplyResponse getMultiplyResponse(int numberA, int numberB);

    DivideResponse getDivideResponse(int numberA, int numberB);
}
