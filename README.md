# FitManager

**Sistema de Gerenciamento de Academia**

## Sobre o projeto

O FitManager é um sistema de gerenciamento de academias desenvolvido como projeto acadêmico do curso de Análise e Desenvolvimento de Sistemas do Instituto Federal do Piauí (IFPI) – Campus Picos.

O sistema tem como objetivo facilitar a administração de uma academia, centralizando informações de alunos, instrutores, matrículas, planos, pagamentos, frequência e treinos em uma única aplicação.

## Objetivos

Desenvolver uma aplicação que auxilie na organização e no controle das atividades administrativas e operacionais de uma academia, tornando o gerenciamento das informações mais eficiente e organizado.

## Funcionalidades previstas

- Cadastro e consulta de alunos;
- Cadastro de instrutores;
- Cadastro e gerenciamento de planos;
- Realização, renovação e cancelamento de matrículas;
- Registro e consulta de pagamentos;
- Consulta da validade dos planos;
- Registro de entrada dos alunos;
- Elaboração e consulta de treinos;
- Registro de avaliações físicas;
- Geração de relatórios de inadimplência;
- Controle de acesso conforme o perfil do usuário.

## Tecnologias utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java 21 | Linguagem de programação |
| JavaFX | Desenvolvimento da interface gráfica |
| Maven | Gerenciamento do projeto e dependências |
| SQLite | Banco de dados |
| JDBC | Comunicação entre a aplicação e o banco de dados |
| Git e GitHub | Controle de versão e colaboração |

## Pré-requisitos

Para executar o projeto, é necessário ter instalado:

- JDK 21;
- Apache Maven;
- Git (opcional, para clonar o repositório).

## Como executar o projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/S0US4LF/fitmanager.git
   ```

2. Acesse a pasta do projeto:

   ```bash
   cd fitmanager
   ```

3. Execute a aplicação:

   ```bash
   mvn javafx:run
   ```

## Estrutura do projeto

```text
fitmanager/
├── docs/
│   ├── diagramas/
│   │   ├── casos-de-uso-fitmanager.puml
│   │   └── casos-de-uso.png
│   ├── casos-de-uso.md
│   └── historias-de-usuario.md
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
├── pom.xml
├── .gitignore
└── README.md
```

## Documentação

A documentação do projeto está disponível na pasta `docs/`.

### Diagrama de casos de uso

O diagrama apresenta os atores e as principais funcionalidades previstas para o sistema.

![Diagrama de casos de uso](docs/diagramas/casos-de-uso.png)

**Documentos relacionados:**

- [Casos de uso](docs/casos-de-uso.md)
- [Histórias de usuário](docs/historias-de-usuario.md)
- [Código-fonte do diagrama (PlantUML)](docs/diagramas/casos-de-uso-fitmanager.puml)

## Integrantes

- Leandro Feitosa de Sousa
- José Arteiro Feitosa Noronha Neto
- Bruna de Oliveira Santos
- Geovani de Araújo Alves

## Instituição

**Instituto Federal do Piauí (IFPI)**  
Campus Picos  
Curso: Análise e Desenvolvimento de Sistemas

---

*Projeto acadêmico desenvolvido para fins educacionais.*