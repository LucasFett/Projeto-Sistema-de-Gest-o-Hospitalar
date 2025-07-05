package com.mbs.apigw.comunicacao;

import com.mbs.apigw.entidades.Consulta; 

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "AgendamentoService", url = "http://localhost:9002/")
public interface AgendamentoServiceRoteamento {

    @PostMapping("/v1/agendamentos") 
    ResponseEntity<String> salvar(@RequestBody Consulta consulta); 

    @GetMapping("/v1/agendamentos") 
    ResponseEntity<List<Consulta>> listar(); 

}