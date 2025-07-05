package com.mbs.apigw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mbs.apigw.comunicacao.PacienteServiceRoteamento; 
import com.mbs.apigw.entidades.Paciente; 

@RestController
@CrossOrigin(origins = "http://localhost:9005")
@RequestMapping("/v1/pacientes") 
public class PacienteGatewayController {

    @Autowired
    private PacienteServiceRoteamento pacienteServiceRoteamento;

    @GetMapping("/existe_paciente/{id}")
    public ResponseEntity<Boolean> existePaciente(@PathVariable Integer id) {
        return pacienteServiceRoteamento.existePaciente(id);
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody Paciente paciente) {
        return pacienteServiceRoteamento.salvar(paciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return pacienteServiceRoteamento.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        return pacienteServiceRoteamento.deletar(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id) {
        return pacienteServiceRoteamento.buscar(id);
    }

    @PutMapping
    public ResponseEntity<String> atualizar(@RequestBody Paciente paciente) {
        return pacienteServiceRoteamento.atualizar(paciente);
    }
}