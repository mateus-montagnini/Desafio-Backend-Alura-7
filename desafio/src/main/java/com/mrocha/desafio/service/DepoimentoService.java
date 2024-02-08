package com.mrocha.desafio.service;

import com.mrocha.desafio.dto.DepoimentoDTO;
import com.mrocha.desafio.model.Depoimento;
import com.mrocha.desafio.repository.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    public List<Depoimento> buscarTodosDepoimentos() {
        List<Depoimento> depoimentos = depoimentoRepository.findAll();
        return depoimentos;
    }

    public Optional<Depoimento> buscarDepoimentoPorId(Long id) {
        Optional<Depoimento> depoimentoId = depoimentoRepository.findById(id);
        return depoimentoId;
    }

    public Optional<Depoimento> criarDepoimento(Depoimento depoimento) {
        Depoimento novoDepoimento = depoimentoRepository.save(depoimento);
        return Optional.of(novoDepoimento);
    }

    public Optional<Depoimento> atualizarDepoimento(Depoimento depoimento) {
        Depoimento depoimentoAtualizado = depoimentoRepository.save(depoimento);
        return Optional.of(depoimentoAtualizado);
    }

    public Boolean deletarDepoimento(Long id) {
        Optional<Depoimento> depoimento = depoimentoRepository.findById(id);
        if (depoimento.isPresent()) {
            depoimentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
