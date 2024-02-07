package com.mrocha.desafio.service;

import com.mrocha.desafio.dto.DepoimentoDTO;
import com.mrocha.desafio.repository.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    public List<DepoimentoDTO> buscarTodosDepoimentos() {
    }

    public DepoimentoDTO buscarDepoimentoPorId() {

    }

    public DepoimentoDTO criarDepoimento() {

    }

    public DepoimentoDTO atualizarDepoimento() {

    }

    public DepoimentoDTO deletarDepoimento() {

    }
}
