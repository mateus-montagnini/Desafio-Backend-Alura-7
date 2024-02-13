package com.mrocha.desafio.controller;

import com.mrocha.desafio.dto.DepoimentoDTO;
import com.mrocha.desafio.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService service;

    @Autowired
    private DepoimentoFotoService serviceFoto;

    @GetMapping
    public ResponseEntity<List<DepoimentoDTO>> buscarTodosDepoimentos() {
        return ResponseEntity.ok(service.buscarTodosDepoimentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepoimentoDTO> buscarDepoimentoPorId(@PathVariable("id")  Long id) {
        Optional<DepoimentoDTO> response = service.buscarDepoimentoPorId(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<DepoimentoDTO> criarDepoimento(@RequestBody DepoimentoDTO depoimento) throws IOException {
        Optional<DepoimentoDTO> novoDepoimento = service.criarDepoimento(depoimento);
        if (novoDepoimento.isPresent()) {
            return new ResponseEntity<>(novoDepoimento.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepoimentoDTO> atualizarDepoimento(@PathVariable("id") Long id,
                                                          @RequestBody DepoimentoDTO depoimento) {
        Optional<DepoimentoDTO> depoimentoAtualizado = service.atualizarDepoimento(id, depoimento);
        if (depoimentoAtualizado.isPresent()) {
            return ResponseEntity.ok(depoimentoAtualizado.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDepoimento(@PathVariable("id") Long id) {
        boolean depoimentoDeletado = service.deletarDepoimento(id);
        if (depoimentoDeletado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
