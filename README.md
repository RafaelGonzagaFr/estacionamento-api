# API de Gerenciamento de Estacionamento

Este repositório contém uma API para gerenciar um estacionamento. A API oferece funcionalidades para recuperar informações sobre vagas, adicionar novas vagas, atualizar o status das vagas e realizar notificações quando um carro ultrapassa o tempo limite de permanência.

## Endpoints

| Método | Endpoint               | Descrição                                                             |
|--------|------------------------|-----------------------------------------------------------------------|
| GET    | `/recuperarVagas`      | Recupera todas as vagas do estacionamento.                            |
| GET    | `/recuperarVaga/{id}`  | Recupera informações de uma vaga específica pelo ID.                  |
| POST   | `/novaVaga`            | Cria uma nova vaga no estacionamento.                                 |
| PUT    | `/mudarVaga/{id}`      | Atualiza o status de uma vaga (inserindo entrada de carro ou saida).  |

---

## Serviço de Notificação (Schedule)

A API possui um serviço de monitoramento que utiliza o `schedule` para verificar as vagas ocupadas e notificar quando um carro ultrapassar o tempo limite de permanência.

### Funcionamento
- O serviço verifica regularmente o tempo de permanência de cada carro nas vagas.
- Caso o tempo de permanência ultrapasse o limite preestabelecido (configurável), uma notificação será disparada (por exemplo, via log ou outro sistema de alerta).

### Exemplo de Uso:
O serviço roda automaticamente em segundo plano, sem a necessidade de interação manual, e as notificações podem ser configuradas conforme a necessidade do usuário.

---

