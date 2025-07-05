package com.mbs.apigw.controller;

import com.mbs.apigw.comunicacao.AgendamentoServiceRoteamento;
import com.mbs.apigw.comunicacao.PacienteServiceRoteamento;
import com.mbs.apigw.entidades.Consulta;
import com.mbs.apigw.entidades.Paciente;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9005")
@RestController
@RequestMapping("/v1/agendamentos")
public class AgendamentoControllerAPI {

    @Autowired private PacienteServiceRoteamento pacienteServiceRoteamento;
    @Autowired private AgendamentoServiceRoteamento agendamentoServiceRoteamento;
    @Autowired private RabbitTemplate rabbitTemplate;

    @PostMapping("/processar")
    public ResponseEntity<String> processarAgendamento(@RequestBody Consulta consulta) {
        ResponseEntity<Boolean> existePaciente = pacienteServiceRoteamento.existePaciente(consulta.getIdPaciente());
        if (existePaciente.getBody() == null || !existePaciente.getBody()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente com código " + consulta.getIdPaciente() + " não existe.");
        }
        ResponseEntity<Paciente> pacienteResponse = pacienteServiceRoteamento.buscar(consulta.getIdPaciente());
        if (pacienteResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível obter os dados do paciente.");
        }
        ResponseEntity<String> resultadoAgendamento = agendamentoServiceRoteamento.salvar(consulta);
        if (resultadoAgendamento.getStatusCode() != HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar o agendamento.");
        }

        try {
            consulta.setId(Integer.parseInt(resultadoAgendamento.getBody()));
            consulta.setStatus("AGENDADA");
            consulta.setEmail(pacienteResponse.getBody().getEmail());

            System.out.println("Enviando OBJETO Consulta para o Broker: " + consulta);
            
            rabbitTemplate.convertAndSend("vendas", "routing-key-teste", consulta); 

        } catch (Exception e) {
            System.err.println("AVISO: Agendamento salvo, mas falhou ao enviar para a fila: " + e.getMessage());
            e.printStackTrace(); 
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resultadoAgendamento.getBody());
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        return agendamentoServiceRoteamento.listar();
    }
}