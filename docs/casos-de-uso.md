# Casos de Uso — Sistema de Gerenciamento de Academia

## 1. Visão geral
O Sistema de Gerenciamento de Academia organiza informações de alunos, planos, matrículas, pagamentos, frequência, instrutores, treinos e avaliações físicas. Cada perfil deve acessar apenas as funções autorizadas.

## 2. Atores
- **Administrador:** gerencia planos, equipe, permissões e relatórios.
- **Atendente:** cadastra e consulta alunos, administra matrículas, pagamentos e frequência.
- **Instrutor:** elabora treinos e registra avaliações físicas.
- **Aluno:** consulta a própria matrícula e os treinos associados.

## 3. Lista de casos de uso e rastreabilidade

| ID | Caso de uso | Ator principal | História relacionada |
|---|---|---|---|
| UC01 | Autenticar usuário | Usuário autorizado | HU01 |
| UC02 | Cadastrar aluno | Atendente | HU02 |
| UC03 | Consultar aluno | Atendente | HU03 |
| UC04 | Cadastrar plano | Administrador | HU04 |
| UC05 | Realizar matrícula | Atendente | HU05 |
| UC06 | Renovar matrícula | Atendente | HU06 |
| UC07 | Registrar pagamento | Atendente | HU07 |
| UC08 | Consultar validade do plano | Aluno | HU08 |
| UC09 | Registrar entrada do aluno | Atendente | HU09 |
| UC10 | Cadastrar instrutor | Administrador | HU10 |
| UC11 | Elaborar treino | Instrutor | HU11 |
| UC12 | Consultar treino | Aluno | HU12 |
| UC13 | Registrar avaliação física | Instrutor | HU13 |
| UC14 | Gerar relatório de inadimplência | Administrador | HU14 |
| UC15 | Cancelar matrícula | Atendente | HU15 |

## 4. Especificação de caso de uso — UC02 Cadastrar aluno

- **Ator principal:** Atendente.
- **Objetivo:** registrar um novo aluno.
- **Pré-condições:** atendente autenticado e autorizado.
- **Fluxo principal:**
  1. O atendente seleciona “Cadastrar aluno”.
  2. O sistema solicita nome, CPF, telefone e data de nascimento.
  3. O atendente informa os dados.
  4. O sistema valida os campos obrigatórios e verifica se o CPF já existe.
  5. O sistema registra o aluno.
  6. O sistema confirma o cadastro.
- **Fluxos alternativos:**
  - Se faltar um campo obrigatório, o sistema informa que os dados devem ser corrigidos.
  - Se o CPF já estiver cadastrado, o sistema rejeita o registro duplicado.
- **Pós-condição:** o aluno fica cadastrado e disponível para consulta.

## 5. Regras de negócio iniciais

- **RN01 — CPF único:** não deve haver dois alunos cadastrados com o mesmo CPF.
- **RN02 — Campos obrigatórios:** o cadastro exige nome e CPF.
- **RN03 — Controle de acesso:** cada perfil só pode executar as operações autorizadas.
- **RN04 — Matrícula válida:** o acesso à academia deve respeitar a situação e a validade da matrícula, conforme regra aprovada pelo grupo.
- **RN05 — Pagamento:** o valor de um pagamento deve ser maior que zero.

## 6. Rastreabilidade
Cada caso de uso está ligado a uma história de usuário (HU) que representa a necessidade que o originou. A lista deve ser mantida sincronizada com as Issues do GitHub e com o diagrama UML.
