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

## 4. Especificação dos casos de uso

### UC01 — Autenticar usuário
- **Ator principal:** Usuário autorizado.
- **Objetivo:** validar login e senha para conceder acesso às funções do perfil.
- **Pré-condições:** usuário cadastrado no sistema.
- **Fluxo principal:**
  1. O usuário acessa a tela de login.
  2. O sistema solicita login e senha.
  3. O usuário informa as credenciais.
  4. O sistema valida os dados e identifica o perfil.
  5. O sistema concede o acesso e carrega o painel correspondente ao perfil.
- **Fluxos alternativos:**
  - Se as credenciais forem inválidas, o sistema exibe mensagem de erro e solicita nova tentativa.
- **Pós-condição:** usuário autenticado no sistema.

### UC02 — Cadastrar aluno
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

### UC03 — Consultar aluno
- **Ator principal:** Atendente.
- **Objetivo:** localizar e visualizar os dados cadastrais de um aluno.
- **Pré-condições:** atendente autenticado e autorizado.
- **Fluxo principal:**
  1. O atendente seleciona “Consultar aluno”.
  2. O sistema solicita um termo de pesquisa (nome ou CPF).
  3. O atendente informa o termo desejado.
  4. O sistema busca e exibe a lista com os alunos encontrados.
  5. O atendente seleciona o aluno para visualizar os detalhes.
- **Fluxos alternativos:**
  - Se nenhum aluno for encontrado, o sistema exibe mensagem informando que não há registros correspondentes.
- **Pós-condição:** dados do aluno exibidos na tela.

### UC04 — Cadastrar plano
- **Ator principal:** Administrador.
- **Objetivo:** registrar uma nova opção de plano de mensalidade.
- **Pré-condições:** administrador autenticado e autorizado.
- **Fluxo principal:**
  1. O administrador seleciona “Cadastrar plano”.
  2. O sistema solicita nome, duração (em meses) e valor da mensalidade.
  3. O administrador informa os dados.
  4. O sistema valida os valores e a consistência das informações.
  5. O sistema registra o novo plano.
  6. O sistema confirma o cadastro.
- **Fluxos alternativos:**
  - Se o valor ou duração forem inválidos (menores ou iguais a zero), o sistema exige a correção.
- **Pós-condição:** novo plano disponível para vinculação em matrículas.

### UC05 — Realizar matrícula
- **Ator principal:** Atendente.
- **Objetivo:** vincular um aluno a um plano ativo.
- **Pré-condições:** atendente autenticado, aluno e plano previamente cadastrados.
- **Fluxo principal:**
  1. O atendente seleciona “Realizar matrícula”.
  2. O atendente seleciona o aluno e o plano desejado.
  3. O sistema calcula a data de início e a data de vencimento.
  4. O sistema registra a matrícula do aluno.
  5. O sistema confirma a realização da matrícula.
- **Fluxos alternativos:**
  - Se o aluno já possuir uma matrícula ativa, o sistema avisa o atendente e sugere a renovação.
- **Pós-condição:** aluno matriculado e ativo no sistema.

### UC06 — Renovar matrícula
- **Ator principal:** Atendente.
- **Objetivo:** estender o período de vigência da matrícula de um aluno.
- **Pré-condições:** atendente autenticado e aluno com matrícula existente.
- **Fluxo principal:**
  1. O atendente pesquisa o aluno e seleciona “Renovar matrícula”.
  2. O sistema exibe o plano atual e solicita confirmação do período de renovação.
  3. O atendente confirma a renovação.
  4. O sistema recalcula a nova data de vencimento.
  5. O sistema atualiza a situação da matrícula para ativa.
  6. O sistema confirma a renovação.
- **Fluxos alternativos:**
  - Se o aluno escolher alterar de plano, o sistema permite a troca antes de recalcular a nova validade.
- **Pós-condição:** data de validade da matrícula atualizada.

### UC07 — Registrar pagamento
- **Ator principal:** Atendente.
- **Objetivo:** dar baixa e registrar o recebimento da mensalidade de um aluno.
- **Pré-condições:** atendente autenticado e matrícula existente.
- **Fluxo principal:**
  1. O atendente seleciona “Registrar pagamento”.
  2. O atendente identifica o aluno e a referência da mensalidade.
  3. O sistema exibe o valor devido.
  4. O atendente confirma o recebimento e a forma de pagamento.
  5. O sistema registra o pagamento e emite o comprovante.
- **Fluxos alternativos:**
  - Se o valor informado for inconsistente ou negativo, o sistema rejeita a operação.
- **Pós-condição:** pagamento registrado e histórico financeiro atualizado.

### UC08 — Consultar validade do plano
- **Ator principal:** Aluno.
- **Objetivo:** permitir ao aluno verificar a situação atual de sua matrícula.
- **Pré-condições:** aluno autenticado.
- **Fluxo principal:**
  1. O aluno acessa a opção “Minha Matrícula”.
  2. O sistema recupera a matrícula vinculada ao aluno logado.
  3. O sistema exibe o plano contratado, a situação (ativa/vencida) e a data de vencimento.
- **Fluxos alternativos:**
  - Se o aluno não tiver matrícula cadastrada, o sistema exibe aviso orientado a procurar o atendimento.
- **Pós-condição:** dados da matrícula exibidos ao aluno.

### UC09 — Registrar entrada do aluno
- **Ator principal:** Atendente.
- **Objetivo:** validar a permissão e registrar o acesso presencial do aluno à academia.
- **Pré-condições:** atendente autenticado e aluno cadastrado.
- **Fluxo principal:**
  1. O atendente informa o CPF ou código do aluno na portaria/recepção.
  2. O sistema verifica a situação da matrícula.
  3. O sistema confirma que a matrícula está ativa.
  4. O sistema registra a frequência com data e hora atual.
  5. O sistema exibe mensagem de acesso liberado.
- **Fluxos alternativos:**
  - Se a matrícula estiver vencida ou inativa, o sistema bloqueia o acesso e exibe alerta de pendência.
- **Pós-condição:** registro de frequência salvo no histórico do aluno.

### UC10 — Cadastrar instrutor
- **Ator principal:** Administrador.
- **Objetivo:** cadastrar novos profissionais para elaboração de treinos e avaliações.
- **Pré-condições:** administrador autenticado e autorizado.
- **Fluxo principal:**
  1. O administrador seleciona “Cadastrar instrutor”.
  2. O sistema solicita nome, CPF, telefone e CREF.
  3. O administrador preenche os dados.
  4. O sistema valida os campos obrigatórios e registra o instrutor.
  5. O sistema confirma o cadastro e gera as credenciais iniciais.
- **Fluxos alternativos:**
  - Se faltar algum dado obrigatório ou o CPF/CREF já estiver em uso, o sistema bloqueia o cadastro.
- **Pós-condição:** instrutor cadastrado e apto a gerenciar treinos.

### UC11 — Elaborar treino
- **Ator principal:** Instrutor.
- **Objetivo:** criar ou atualizar a ficha de exercícios de um aluno.
- **Pré-condições:** instrutor autenticado e aluno com matrícula ativa.
- **Fluxo principal:**
  1. O instrutor seleciona “Elaborar treino” e busca o aluno.
  2. O sistema apresenta o histórico de treinos do aluno.
  3. O instrutor adiciona os exercícios, séries, repetições e carga.
  4. O instrutor salva o treino.
  5. O sistema vincula a nova ficha de treino ao aluno.
- **Fluxos alternativos:**
  - Se a lista de exercícios estiver vazia, o sistema impede o salvamento até que ao menos um exercício seja inserido.
- **Pós-condição:** treino cadastrado e disponível para o aluno.

### UC12 — Consultar treino
- **Ator principal:** Aluno.
- **Objetivo:** permitir ao aluno visualizar sua ficha de exercícios atual.
- **Pré-condições:** aluno autenticado.
- **Fluxo principal:**
  1. O aluno seleciona “Meus Treinos”.
  2. O sistema busca o treino ativo vinculado ao seu perfil.
  3. O sistema exibe a lista de exercícios, séries e repetições recomendadas.
- **Fluxos alternativos:**
  - Se o aluno não possuir treino cadastrado, o sistema informa que ele deve solicitar a elaboração ao seu instrutor.
- **Pós-condição:** ficha de treino visualizada pelo aluno.

### UC13 — Registrar avaliação física
- **Ator principal:** Instrutor.
- **Objetivo:** cadastrar medidas corporais e resultados de testes físicos do aluno.
- **Pré-condições:** instrutor autenticado e aluno cadastrado.
- **Fluxo principal:**
  1. O instrutor seleciona “Registrar avaliação física” e busca o aluno.
  2. O sistema solicita a data e as medições (peso, altura, percentual de gordura, dobras cutâneas).
  3. O instrutor preenche os campos.
  4. O sistema calcula os índices corporais (ex.: IMC).
  5. O sistema salva o registro e confirma o armazenamento.
- **Fluxos alternativos:**
  - Se forem inseridos valores corporais discrepantes ou negativos, o sistema avisa para revisão dos dados.
- **Pós-condição:** avaliação física salva no histórico do aluno.

### UC14 — Gerar relatório de inadimplência
- **Ator principal:** Administrador.
- **Objetivo:** consultar e listar os alunos com pagamentos em atraso.
- **Pré-condições:** administrador autenticado.
- **Fluxo principal:**
  1. O administrador acessa “Relatórios” e escolhe “Inadimplência”.
  2. O sistema consulta as matrículas e pagamentos do banco de dados.
  3. O sistema lista os alunos que possuem parcelas ou mensalidades vencidas.
  4. O sistema exibe o total acumulado e o tempo de atraso de cada caso.
- **Fluxos alternativos:**
  - Se não houver pendências registradas, o sistema exibe a mensagem de que não existem inadimplentes no período.
- **Pós-condição:** relatório financeiro de pendências gerado na tela.

### UC15 — Cancelar matrícula
- **Ator principal:** Atendente.
- **Objetivo:** alterar o status da matrícula do aluno para cancelada antes do término do plano.
- **Pré-condições:** atendente autenticado e aluno com matrícula ativa.
- **Fluxo principal:**
  1. O atendente busca o aluno e seleciona “Cancelar matrícula”.
  2. O sistema exibe os detalhes da matrícula atual e solicita o motivo do cancelamento.
  3. O atendente informa o motivo e confirma o cancelamento.
  4. O sistema altera o status da matrícula para “Cancelada” e registra a data.
  5. O sistema confirma a alteração.
- **Fluxos alternativos:**
  - Se o atendente desistir da operação na tela de confirmação, o sistema cancela a ação sem alterar o status da matrícula.
- **Pós-condição:** matrícula alterada para o status cancelada e acesso do aluno bloqueado.

## 5. Regras de negócio iniciais

- **RN01 — CPF único:** não deve haver dois alunos ou instrutores cadastrados com o mesmo CPF.
- **RN02 — Campos obrigatórios:** o cadastro exige nome e CPF.
- **RN03 — Controle de acesso:** cada perfil só pode executar as operações autorizadas.
- **RN04 — Matrícula válida:** o acesso à academia deve respeitar a situação e a validade da matrícula, conforme regra aprovada pelo grupo.
- **RN05 — Pagamento:** o valor de um pagamento deve ser maior que zero.

## 6. Rastreabilidade
Cada caso de uso está ligado a uma história de usuário (HU) que representa a necessidade que o originou. A lista deve ser mantida sincronizada com as Issues do GitHub e com o diagrama UML.
