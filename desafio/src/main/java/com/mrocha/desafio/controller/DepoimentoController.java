package com.mrocha.desafio.controller;

import com.mrocha.desafio.dto.DepoimentoDTO;
import com.mrocha.desafio.model.Depoimento;
import com.mrocha.desafio.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService service;

    @GetMapping
    public ResponseEntity<List<Depoimento>> buscarTodosDepoimentos() {
        return ResponseEntity.ok(service.buscarTodosDepoimentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Depoimento> buscarDepoimentoPorId(@PathVariable("id")  Long id) {
        Optional<Depoimento> response = service.buscarDepoimentoPorId(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Depoimento> criarDepoimento(@RequestBody Depoimento depoimento) {
        Optional<Depoimento> novoDepoimento = service.criarDepoimento(depoimento);
        if (novoDepoimento.isPresent()) {
            return new ResponseEntity<>(novoDepoimento.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Depoimento> atualizarDepoimento(@PathVariable("id") Long id,
                                                          @RequestBody Depoimento depoimento) {
        Optional<Depoimento> depoimentoAtualizado = service.atualizarDepoimento(depoimento);
        if (depoimentoAtualizado.isPresent()) {
            return ResponseEntity.ok(depoimentoAtualizado.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

//    @DeleteMapping
//    public deletarDepoimento() {
//
//    }
}
