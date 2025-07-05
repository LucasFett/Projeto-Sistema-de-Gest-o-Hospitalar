package com.mbs.agendamentoServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mbs.agendamentoServices.entidades.Consulta;
import com.mbs.agendamentoServices.service.AgendamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API de Agendamentos", description = "API para gestão de agendamentos de consultas")
@RestController
@RequestMapping("/v1/agendamentos") 
@CrossOrigin(origins = "http://localhost:9005")
public class AgendamentoControllerAPI {

    @Autowired
    private AgendamentoService agendamentoService;

    @Operation(summary = "Regista uma nova consulta")
    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody Consulta consulta) {
        try {
            Integer id = agendamentoService.salvar(consulta);
            return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista todas as consultas registadas")
    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        return ResponseEntity.ok(agendamentoService.listar());
    }
}