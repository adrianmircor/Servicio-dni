/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebamaven;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        // TODO code application logic here
        Http peticion = new Http();
        String dni = "48686296";
        String url = "https://dni.optimizeperu.com/api/persons/" + dni;
        String jsonApi = "";
        String key = "json?quantity=1&key=7597|5zZTAUe7tOodOwOsrOX6P82Vfc^1he4h";
        String url1 = "http://api.valuta.money/v1/quotes/USD/PEN/";
        String jsonApi1 = "";

        String encodedurl = URLEncoder.encode(key, "UTF-8");
        
        System.out.println("--> "+url1+encodedurl);

        Gson gson = new Gson();
        //jsonApi = peticion.GET(url);
        //jsonApi1 = peticion.GET(url1+encodedurl);

        //Map<?, ?> useMap = gson.fromJson(jsonApi, Map.class);
        Map<?, ?> useMap1 = gson.fromJson(jsonApi1, Map.class);

        //System.out.println("dni: " + useMap.get("dni"));
        //System.out.println("x: " + useMap1.get("status"));

        //System.out.println(jsonApi);
    }

}
