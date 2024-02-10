package com.mrocha.desafio.service;

import com.mrocha.desafio.dto.DepoimentoDTO;
import com.mrocha.desafio.model.Depoimento;
import com.mrocha.desafio.repository.DepoimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class DepoimentoService {

    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    public List<DepoimentoDTO> buscarTodosDepoimentos() {
        List<Depoimento> depoimentos = depoimentoRepository.findAll();
        List<DepoimentoDTO> response = new ArrayList<>();

        for (Depoimento depoimento : depoimentos) {
            DepoimentoDTO res = mapper.map(depoimento, DepoimentoDTO.class);
            response.add(res);
        }
        return response;
    }

    public Optional<DepoimentoDTO> buscarDepoimentoPorId(Long id) {
        Optional<Depoimento> depoimentoId = depoimentoRepository.findById(id);
        if(depoimentoId.isPresent()) {
            return Optional.of(mapper.map(depoimentoId.get(), DepoimentoDTO.class));
        }
        return Optional.empty();
    }

    public Optional<DepoimentoDTO> criarDepoimento(DepoimentoDTO depoimento) {
        Depoimento novoDepoimento = mapper.map(depoimento, Depoimento.class);

        depoimentoRepository.save(novoDepoimento);

        DepoimentoDTO response = mapper.map(novoDepoimento, DepoimentoDTO.class);
        return Optional.of(response);
    }

    public Optional<DepoimentoDTO> atualizarDepoimento(Long id, DepoimentoDTO depoimento) {
        Optional<Depoimento> depoimentoAtualizado = depoimentoRepository.findById(id);
        if (depoimentoAtualizado.isPresent()) {
            depoimentoAtualizado.get().setNome(depoimento.getNome());
            depoimentoAtualizado.get().setDepoimento(depoimento.getDepoimento());
            depoimentoAtualizado.get().setFoto(depoimento.getFoto());
            depoimentoRepository.save(depoimentoAtualizado.get());
            return Optional.of(mapper.map(depoimentoAtualizado.get(), DepoimentoDTO.class));
        }
        return Optional.empty();
    }

    public Boolean deletarDepoimento(Long id) {
        Optional<Depoimento> depoimento = depoimentoRepository.findById(id);
        if (depoimento.isPresent()) {
            depoimentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DepoimentoDTO> buscarTresDepoimentos() {
        List<Depoimento> depoimentos = depoimentoRepository.findAll();
        List<DepoimentoDTO> response = new ArrayList<>();

        for (Depoimento depoimento : depoimentos) {
            DepoimentoDTO res = mapper.map(depoimento, DepoimentoDTO.class);
            response.add(res);
        }
        if (response.isEmpty()) {
            return response;
        }
        shuffleList(response, new Random());

        return response.subList(0, Math.min(response.size(), 3));
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
