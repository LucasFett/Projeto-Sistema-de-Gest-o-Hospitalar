package com.mbs.pacienteServices.servico;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.pacienteServices.entidades.Paciente;
import com.mbs.pacienteServices.repositorio.PacienteRepositorio;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	public Integer salvar(Paciente paciente) throws Exception {
		if (paciente.getNome() == null || paciente.getNome().length() <= 2) {
			throw new Exception("Nome do paciente deve ter no mínimo 3 caracteres.");
		}
		
		if (paciente.getDataNascimento() != null && paciente.getDataNascimento().isAfter(LocalDate.now())) {
			throw new Exception("A data de nascimento não pode ser uma data futura.");
		}
		 
		return pacienteRepositorio.salvar(paciente);
	}
	
	public List<Paciente> listar() {		
		return pacienteRepositorio.listar();
	}
	
	public boolean deletar(Integer id) {
		return pacienteRepositorio.deletar(id);		
	}
	
	public Boolean existePaciente(Integer id) {		
		return pacienteRepositorio.existePaciente(id);
	}
	
	public Paciente buscar(Integer id) {
		return pacienteRepositorio.buscar(id);		
	}
	
	public void atualizar(Paciente paciente) throws Exception{
		if (paciente.getId() == null) {
			throw new Exception("Paciente deve ter um ID para ser atualizado.");
		}
		
		if (paciente.getNome() == null || paciente.getNome().length() <= 2) {			
			throw new Exception("Nome do paciente deve ter no mínimo 3 caracteres.");
		}
        if (paciente.getDataNascimento() != null && paciente.getDataNascimento().isAfter(LocalDate.now())) {
			throw new Exception("A data de nascimento não pode ser uma data futura.");
		}

		pacienteRepositorio.atualizar(paciente);		
	}
}