package com.mrocha.desafio.service;

import com.mrocha.desafio.model.Depoimento;
import com.mrocha.desafio.repository.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class DepoimentoService {

    private RestTemplate restTemplate;

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

    public List<Depoimento> buscarTresDepoimentos() {
        List<Depoimento> depoimentos = depoimentoRepository.findAll();
        if (depoimentos.isEmpty()) {
            return depoimentos;
        }
        shuffleList(depoimentos, new Random());

        return depoimentos.subList(0, Math.min(depoimentos.size(), 3));
    }

    private <T> void shuffleList(List<T> list, Random random) {
        for (int i = list.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            T temp = list.get(index);
            list.set(index, list.get(i));
            list.set(i, temp);
        }
    }
}
