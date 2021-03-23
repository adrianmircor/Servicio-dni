package com.cambiomoneda.service;

import com.cambiomoneda.dto.ImporteDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class ConversorService {

    public double conversor(ImporteDTO importeDto, String monedaCambio) {

        double valorConversor, importeConvertido;
        String nombreMonedaOrigen, nombreMonedaDestino;
        String api;

        nombreMonedaOrigen = formatoMoneda(importeDto.getMoneda());
        nombreMonedaDestino = formatoMoneda(monedaCambio);

        api = jsonApi(nombreMonedaOrigen, nombreMonedaDestino);
        valorConversor = valorClave(api);

        importeConvertido = importeDto.getValor() * valorConversor;

        importeConvertido = Math.round(importeConvertido * 1000.0) / 1000.0;

        return importeConvertido;
    }

    private String formatoMoneda(String monedaCambio) {

        String nombreMonedaCambio = "";

        if (monedaCambio.equals("SOLES")) {
            nombreMonedaCambio = "PEN";
        } else if (monedaCambio.equals("DÓLARES")) {
            nombreMonedaCambio = "USD";
        } else if (monedaCambio.equals("EUROS")) {
            nombreMonedaCambio = "EUR";
        }

        return nombreMonedaCambio;
    }

    private String jsonApi(String nombreMonedaOrigen, String nombreMonedaDestino) {

        String jsonString = "";

        RestAssured.baseURI = "http://api.valuta.money/v1/quotes/"
                + nombreMonedaOrigen + "/"
                + nombreMonedaDestino + "/";

        //given permite el uso de parametros
        RequestSpecification request = RestAssured.given();

        //RequestSpecification: permite añadir propiedades a baseURI
        //queryParam: permite enviar parametros de consulta en la peticion
        Response response = request.queryParam("quantity", "1")
                .queryParam("key", "7597|5zZTAUe7tOodOwOsrOX6P82Vfc^1he4h")
                .get("/json");//json, parte de la ruta antes de los parametros

        jsonString = response.asString(); //retorna como String

        return jsonString;
    }

    private double valorClave(String jsonString) {

        double valor = 0;
        String key = "";
        try {
            //Construye un objeto JSONObject a partir de la api
            JSONObject mainObject = new JSONObject(jsonString);
            //Obtenemos los objetos dentro del objeto principal.
            Iterator<String> keys = mainObject.keys();
            // obtiene el nombre del objeto.
            key = keys.next();
            //Obtiene el objeto de clave 'key'
            JSONObject jsonObject = mainObject.getJSONObject(key);
            //accede al valor de clave 'value' 
            valor = jsonObject.getDouble("value");

            System.out.println("El valor es: " + valor);

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Error JSON: " + e.getMessage());
        }

        return valor;
    }

}
