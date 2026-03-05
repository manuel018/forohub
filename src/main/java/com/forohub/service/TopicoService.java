package com.forohub.service;

import com.forohub.dto.TopicoDTO;
import com.forohub.dto.TopicoUpdateDTO;
import com.forohub.model.Topico;
import com.forohub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    private static final Logger log = LoggerFactory.getLogger(TopicoService.class);

    public Topico getTopicoById(Long id) {
        try {
            return topicoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No existe el topico solicitado"));
        } catch (Error e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public List<Topico> getTopics() {
        try {
            return topicoRepository.findAll();
        } catch (Error e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public Topico addTopico(TopicoDTO topicoDTO) {
        try {
            return topicoRepository.save(transformToEntity(topicoDTO));
        } catch (Error e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    public Topico updateTopic(TopicoUpdateDTO dto, Long id) {
        Topico topico = getTopicoById(id);

        if (dto.titulo() != null) topico.setTitulo(dto.titulo());
        if (dto.mensaje() != null) topico.setMensaje(dto.mensaje());
        if (dto.status() != null) topico.setStatus(dto.status());
        if (dto.autor() != null) topico.setAutor(dto.autor());
        if (dto.curso() != null) topico.setCurso(dto.curso());

        return topico;
    }

    public void deleteTopic(Long id) {
        try {
            topicoRepository.delete(getTopicoById(id));
        } catch (Error e) {
            log.error(e.getMessage());
            throw e;
        }
    }


    private Topico transformToEntity(TopicoDTO topicoDTO) {
        return Topico.builder()
                .autor(topicoDTO.autor())
                .fechaCreacion(LocalDate.now().toString())
                .mensaje(topicoDTO.mensaje())
                .curso(topicoDTO.curso())
                .status(topicoDTO.status())
                .titulo(topicoDTO.titulo())
                .build();
    }

}
