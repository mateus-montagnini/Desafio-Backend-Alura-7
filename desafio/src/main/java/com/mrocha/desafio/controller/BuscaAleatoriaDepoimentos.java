package com.mrocha.desafio.controller;

import com.mrocha.desafio.dto.DepoimentoDTO;
import com.mrocha.desafio.model.Depoimento;
import com.mrocha.desafio.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("depoimentos-home")
public class BuscaAleatoriaDepoimentos {

    @Autowired
    private DepoimentoService service;

    @GetMapping
    public ResponseEntity<List<DepoimentoDTO>> buscarTresDepoimentos() {
        return ResponseEntity.ok(service.buscarTresDepoimentos());
    }
}
