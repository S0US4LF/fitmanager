# Histórias de Usuário — Sistema de Gerenciamento de Academia

## Objetivo
Registrar as necessidades dos perfis que utilizarão o sistema.

## Atores
- **Administrador:** gerencia configurações, planos, equipe e relatórios.
- **Atendente:** administra cadastros, matrículas, pagamentos e frequência.
- **Instrutor:** organiza treinos e registra avaliações físicas.
- **Aluno:** consulta sua matrícula e seus treinos.

## Histórias de usuário

| ID | História | Prioridade | Critérios de aceitação resumidos |
|---|---|---|---|
| HU01 | Como usuário autorizado, quero entrar com login e senha para acessar somente as funções permitidas ao meu perfil. | P1 | Credenciais válidas permitem acesso; inválidas exibem aviso; o perfil limita as funções disponíveis. |
| HU02 | Como atendente, quero cadastrar alunos para manter seus dados organizados. | P1 | Nome e CPF são obrigatórios; CPF duplicado é rejeitado; cadastro válido é confirmado. |
| HU03 | Como atendente, quero consultar alunos para localizar um cadastro. | P1 | O sistema permite listar ou pesquisar alunos cadastrados. |
| HU04 | Como administrador, quero cadastrar planos para oferecer opções de mensalidade. | P1 | O plano possui nome, duração e preço válidos. |
| HU05 | Como atendente, quero matricular um aluno em um plano para registrar sua adesão à academia. | P1 | Aluno e plano devem existir; a matrícula registra início e validade. |
| HU06 | Como atendente, quero renovar uma matrícula para atualizar o período de acesso do aluno. | P1 | A matrícula é identificada e sua validade é atualizada conforme a regra definida. |
| HU07 | Como atendente, quero registrar pagamentos para acompanhar as mensalidades recebidas. | P1 | Valor positivo, aluno e referência do pagamento são necessários. |
| HU08 | Como aluno, quero consultar a validade do meu plano para saber se minha matrícula está ativa. | P1 | O sistema apresenta situação e data de validade da matrícula. |
| HU09 | Como atendente, quero registrar a entrada de alunos para controlar a frequência. | P2 | A entrada é registrada com data e hora e segue a regra de matrícula válida. |
| HU10 | Como administrador, quero cadastrar instrutores para organizar a equipe. | P2 | Os dados obrigatórios do instrutor são validados. |
| HU11 | Como instrutor, quero elaborar treinos para orientar os exercícios dos alunos. | P2 | O treino fica associado a um aluno e contém exercícios. |
| HU12 | Como aluno, quero consultar meu treino para saber quais exercícios realizar. | P2 | O aluno visualiza apenas o treino associado a ele. |
| HU13 | Como instrutor, quero registrar avaliações físicas para acompanhar a evolução dos alunos. | P2 | A avaliação guarda data e medidas autorizadas. |
| HU14 | Como administrador, quero consultar pagamentos pendentes para acompanhar a inadimplência. | P2 | O relatório lista registros pendentes conforme as regras definidas. |
| HU15 | Como atendente, quero cancelar uma matrícula para manter a situação do aluno atualizada. | P3 | O cancelamento altera a situação e registra a data. |

## Priorização
- **P1 — Alta:** funções essenciais para o primeiro fluxo do sistema.
- **P2 — Média:** funções importantes para a operação, após o fluxo básico.
- **P3 — Baixa:** funções que podem ser implementadas depois.

## Observação
As histórias de usuário apresentadas são propostas iniciais para o Sistema de Gerenciamento de Academia. O grupo pretende priorizar as funcionalidades essenciais, como cadastro e consulta de alunos, gerenciamento de planos e matrículas e registro de pagamentos.

Inicialmente, o acesso às funcionalidades será definido conforme o perfil de cada usuário, considerando possíveis permissões para administrador e recepcionista. As regras de pagamento, o controle de mensalidades em atraso e os critérios de aceitação serão definidos de acordo com as necessidades do sistema.
