Sistema de Gestão Hospitalar - Microsserviços com API REST e Mensageria

Resumo do Projeto
Este projeto prático implementa um Sistema de Gestão Hospitalar simplificado, utilizando uma arquitetura de microsserviços para gerir pacientes e agendamentos. O objetivo principal é demonstrar competências na criação e orquestração de APIs e no uso de sistemas de mensageria para comunicação assíncrona.

Conhecimentos em API Demonstrados

API Gateway: Utilização de um ponto de entrada único (apiGw) que centraliza e roteia as requisições para os microsserviços internos, abstraindo a complexidade do sistema para o cliente.

Serviços RESTful: Criação de APIs com Spring Boot (@RestController) seguindo os princípios REST para as entidades Paciente e Agendamento, com operações CRUD completas.

Comunicação Síncrona: Uso de Spring Cloud OpenFeign para uma comunicação declarativa e eficiente entre o API Gateway e os serviços de backend, simplificando as chamadas HTTP.

Orquestração de Serviços: O API Gateway orquestra o fluxo de negócio principal (agendamento de consulta), validando o paciente e registando a consulta através de chamadas síncronas a múltiplas APIs, garantindo a integridade da operação.

Conhecimentos em Mensageria Demonstrados

RabbitMQ: Utilização como message broker para gerir a comunicação assíncrona, garantindo a entrega de mensagens e a resiliência do sistema.

Desacoplamento de Serviços: O serviço de notificação (notificacaoService) é totalmente desacoplado do fluxo principal. Ele reage a eventos (novas consultas) sem que o fluxo principal precise de esperar pela sua conclusão, melhorando a performance e a escalabilidade.

Padrão Produtor/Consumidor: O apiGw atua como Produtor, publicando uma mensagem de "consulta agendada", enquanto o notificacaoService atua como Consumidor, processando a mensagem para enviar uma notificação simulada por e-mail.

Spring AMQP: Aplicação do RabbitTemplate para produzir e da anotação @RabbitListener para consumir mensagens de forma eficiente e integrada ao ecossistema Spring.

Tecnologias Utilizadas

Java 17

Spring Boot

Maven

RabbitMQ

Spring Cloud OpenFeign

API REST
