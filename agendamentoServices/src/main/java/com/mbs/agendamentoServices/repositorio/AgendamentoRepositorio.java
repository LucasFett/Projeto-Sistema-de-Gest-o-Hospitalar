package com.mbs.agendamentoServices.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mbs.agendamentoServices.entidades.Consulta;

@Repository
public class AgendamentoRepositorio {

    private static Integer id = 0;
   
    private final HashMap<Integer, Consulta> mapaAgendamento = new HashMap<>();

    public Integer salvar(Consulta consulta) {
        Integer idNovo = id + 1;
        consulta.setId(idNovo);
        mapaAgendamento.put(idNovo, consulta);
        id = idNovo;
        return idNovo;
    }

    public List<Consulta> listar() {
        return new ArrayList<>(mapaAgendamento.values());
    }
}