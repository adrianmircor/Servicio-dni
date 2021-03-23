package com.mycompany.pruebamaven;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Main_2 {

    public static void main(String[] args) {

        // Specify the base URL to the RESTful web service
        //Uso de Rest Assured
        RestAssured.baseURI = "http://api.valuta.money/v1/quotes/USD/PEN/";
        RequestSpecification request = RestAssured.given();

        //Debe tener una estructura
        Response response = request.queryParam("quantity", "1")
                .queryParam("key", "7597|5zZTAUe7tOodOwOsrOX6P82Vfc^1he4h")
                .get("/json");

        String jsonString = response.asString();
        System.out.println("Json completo: " + jsonString);

        //1ra forma: mediante mapeo de objetos y claves del JSON
        /*Gson gson = new Gson();

        Map<?, ?> result = gson.fromJson(jsonString, Map.class);
        System.out.println("result map: " + result);

        ClaseJson claseJson = gson.fromJson(jsonString, ClaseJson.class);
        System.out.println("Valor: " + claseJson.getResultadito().getValue());*/
        
        //2da forma: acceso directo a la clave del JSON
        double valor;
        try {
            //    contenidoJson es tu string conteniendo el json.
            JSONObject mainObject = new JSONObject(jsonString);
            //Obtenemos los objetos dentro del objeto principal.
            Iterator<String> keys = mainObject.keys();

            // obtiene el nombre del objeto.
            String key = keys.next();
            System.out.println("Pimera key obtenida: " + key);
            JSONObject jsonObject = mainObject.getJSONObject(key);

            //obtiene valores dentro del objeto.
            valor = jsonObject.getDouble("value");

            //Imprimimos los valores.
            System.out.println("El valor es: " + valor);
            
            //Accediendo a la 2da key del principal Objeto del JSON
            key = keys.next();
            System.out.println("2da key obtenida: " + key); //será status
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Error JSON: " + e.getMessage());
        }

    }

}
