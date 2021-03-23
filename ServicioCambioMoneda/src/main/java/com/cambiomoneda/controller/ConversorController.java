package com.cambiomoneda.controller;

import com.cambiomoneda.dto.ImporteDTO;
import com.cambiomoneda.service.ConversorService;

public class ConversorController {

    public double valorConversor(ImporteDTO importeDto, String monedaCambio) {

        double valor;
        ConversorService conversorService = new ConversorService();

        valor = conversorService.conversor(importeDto, monedaCambio);

        return valor;

    }

}
