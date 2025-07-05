package com.mbs.agendamentoServices.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.agendamentoServices.entidades.Consulta;
import com.mbs.agendamentoServices.repositorio.AgendamentoRepositorio;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepositorio agendamentoRepositorio;

    public Integer salvar(Consulta consulta) throws Exception {
        validacao(consulta);
        
        consulta.setStatus("AGENDADA");
        
        return agendamentoRepositorio.salvar(consulta);
    }

    private void validacao(Consulta consulta) throws Exception {
        if (consulta.getIdPaciente() == null) {
            throw new Exception("O ID do paciente é obrigatório.");
        }
        if (consulta.getEspecialidadeMedica() == null || consulta.getEspecialidadeMedica().isBlank()) {
            throw new Exception("A especialidade médica é obrigatória.");
        }
        if (consulta.getDataHoraConsulta() == null) {
            throw new Exception("A data e hora da consulta são obrigatórias.");
        }
        if (consulta.getDataHoraConsulta().isBefore(LocalDateTime.now())) {
            throw new Exception("Não é possível agendar uma consulta em uma data passada.");
        }
    }

    public List<Consulta> listar() {
        return agendamentoRepositorio.listar();
    }
    
    
}