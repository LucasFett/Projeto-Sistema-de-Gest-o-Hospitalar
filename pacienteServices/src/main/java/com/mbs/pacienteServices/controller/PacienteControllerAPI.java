package com.mbs.pacienteServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mbs.pacienteServices.entidades.Paciente;
import com.mbs.pacienteServices.servico.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API de Pacientes", description = "API para gestão de pacientes do sistema hospitalar")
@RestController 
@RequestMapping("/v1/pacientes") 
@CrossOrigin(origins = "http://localhost:9005")
public class PacienteControllerAPI {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Regista um novo paciente")
    @PostMapping 
    public ResponseEntity<String> salvar(@RequestBody Paciente paciente) {
        try {
            Integer id = pacienteService.salvar(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista todos os pacientes registados")
    @GetMapping 
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @Operation(summary = "Busca um paciente pelo seu ID")
    @GetMapping("/{id}") 
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscar(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Verifica a existência de um paciente pelo ID")
    @GetMapping("/existe_paciente/{id}") 
	public ResponseEntity<Boolean> existePaciente(@PathVariable Integer id) {
		return ResponseEntity.ok(pacienteService.existePaciente(id));
	}

    @Operation(summary = "Atualiza os dados de um paciente existente")
    @PutMapping 
    public ResponseEntity<String> atualizar(@RequestBody Paciente paciente) {
        try {
            pacienteService.atualizar(paciente);
            return ResponseEntity.ok("Paciente atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Remove um paciente pelo seu ID")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean resultado = pacienteService.deletar(id);
        if (resultado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}