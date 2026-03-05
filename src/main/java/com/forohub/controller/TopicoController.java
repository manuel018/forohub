package com.forohub.controller;

import com.forohub.dto.ApiResponse;
import com.forohub.dto.TopicoDTO;
import com.forohub.dto.TopicoUpdateDTO;
import com.forohub.model.Topico;
import com.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Topico>> getTopic(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.topicoService.getTopicoById(id), "Encontrado", true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Topico>>> getAllTopics() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.topicoService.getTopics(), "Encontrado", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Topico>> saveTopic(@Valid @RequestBody TopicoDTO topicoDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(this.topicoService.addTopico(topicoDto), "Topico guardado", true));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<Topico>> patchTopic(@PathVariable Long id, @Valid @RequestBody TopicoUpdateDTO topicoDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.topicoService.updateTopic(topicoDto, id), "Patcheado", true));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Topico>> putTopic(@PathVariable Long id, @Valid @RequestBody TopicoUpdateDTO topicoDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(this.topicoService.updateTopic(topicoDto, id), "Actualizado completamente", true));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        this.topicoService.deleteTopic(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
