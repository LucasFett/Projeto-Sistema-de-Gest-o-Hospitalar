package com.mbs.notificacaoServices;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mbs.notificacaoServices.entidades.Consulta;
import com.mbs.notificacaoServices.services.EmailService;
import java.time.format.DateTimeFormatter;

@Component
public class ConsultaListener {

    @Autowired
    private EmailService emailService;

	@RabbitListener(queues = {"${queue}"})
    public void receive(@Payload Consulta consulta) {
        System.out.println("Objeto Consulta recebido da fila: " + consulta);
        

        String corpoEmail = criarCorpoEmail(consulta);
        String tituloEmail = "Confirmação de Agendamento de Consulta";

        emailService.enviar(corpoEmail, tituloEmail, "hospital@sistema.com", consulta.getEmail());
    }

    private String criarCorpoEmail(Consulta consulta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        String dataHoraFormatada = consulta.getDataHoraConsulta().format(formatter);

        return new StringBuilder()
                .append("Olá!\n\n")
                .append("A sua consulta foi agendada com sucesso.\n\n")
                .append("Detalhes do Agendamento:\n")
                .append("  - Especialidade: ").append(consulta.getEspecialidadeMedica()).append("\n")
                .append("  - Médico(a): ").append(consulta.getNomeMedico()).append("\n")
                .append("  - Data e Hora: ").append(dataHoraFormatada).append("\n\n")
                .append("Status: ").append(consulta.getStatus()).append("\n\n")
                .append("Obrigado por confiar nos nossos serviços.")
                .toString();
    }
}