package com.mbs.apigw.comunicacao;

import com.mbs.apigw.entidades.Paciente; 

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "PacienteService", url = "http://localhost:9003/")
public interface PacienteServiceRoteamento {

    @GetMapping("/v1/pacientes/existe_paciente/{id}") 
    ResponseEntity<Boolean> existePaciente(@PathVariable Integer id);

    @PostMapping("/v1/pacientes") 
    ResponseEntity<String> salvar(@RequestBody Paciente paciente); 

    @GetMapping("/v1/pacientes") 
    ResponseEntity<List<Paciente>> listar(); 

    @DeleteMapping("/v1/pacientes/{id}") 
    ResponseEntity<Void> deletar(@PathVariable Integer id);

    @GetMapping("/v1/pacientes/{id}") 
    ResponseEntity<Paciente> buscar(@PathVariable Integer id); 

    @PutMapping("/v1/pacientes") 
    ResponseEntity<String> atualizar(@RequestBody Paciente paciente); 
}