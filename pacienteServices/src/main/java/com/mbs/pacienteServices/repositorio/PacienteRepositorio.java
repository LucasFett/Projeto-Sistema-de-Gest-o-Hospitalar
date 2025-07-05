package com.mbs.pacienteServices.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mbs.pacienteServices.entidades.Paciente; 

@Repository
public class PacienteRepositorio {

    private List<Paciente> listaPaciente = new ArrayList<>();
    private static Integer id = 0;

    public Integer salvar(Paciente paciente) {
        paciente.setId(++id);
        listaPaciente.add(paciente);
        return id;
    }

    public List<Paciente> listar() {
        return listaPaciente;
    }

    public boolean deletar(Integer id) {
        return listaPaciente.removeIf((paciente) -> paciente.getId().equals(id));
    }

    public Boolean existePaciente(Integer id) {
        return listaPaciente.stream().filter(paciente -> paciente.getId().equals(id)).findFirst().isPresent();
    }

    public Paciente buscar(Integer id) {
        Optional<Paciente> resultado = listaPaciente.stream()
                .filter(paciente -> paciente.getId().equals(id))
                .findFirst();
        return resultado.orElse(null); // Retorna o paciente ou null se não for encontrado
    }

    public void atualizar(Paciente paciente) {
        for (int i = 0; i < listaPaciente.size(); i++) {
            Paciente p = listaPaciente.get(i);
            if (p.getId().equals(paciente.getId())) {
                listaPaciente.set(i, paciente);
                break; 
            }
        }
    }
}