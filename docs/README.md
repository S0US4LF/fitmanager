# Documentação do FitManager

Esta pasta reúne a documentação técnica e acadêmica do **FitManager**, sistema de gerenciamento de academia desenvolvido como projeto do curso de Análise e Desenvolvimento de Sistemas do Instituto Federal do Piauí (IFPI) – Campus Picos.

O objetivo desta documentação é registrar os requisitos, as funcionalidades, as decisões de projeto e a estrutura do sistema, facilitando sua compreensão, manutenção e evolução.

---

## 1. Organização da documentação

A documentação está dividida em arquivos que abordam diferentes aspectos do desenvolvimento.

| Documento | Descrição |
|---|---|
| [Casos de uso](casos-de-uso.md) | Apresenta os casos de uso, atores e interações previstas no sistema. |
| [Histórias de usuário](historias-de-usuario.md) | Descreve as necessidades dos usuários e as funcionalidades sob a perspectiva de cada perfil. |
| [Glossário](glossario.md) | Reúne os principais termos técnicos e conceitos utilizados no projeto. |
| [Diagramas](diagramas/) | Armazena as representações visuais e os arquivos-fonte dos diagramas do sistema. |

## 2. Diagramas do sistema

Os diagramas documentam diferentes perspectivas do FitManager, desde as funcionalidades oferecidas até sua organização estrutural.

### 2.1 Diagrama de casos de uso

Representa os atores que interagem com o sistema e as funcionalidades associadas a cada perfil.

- [Visualizar diagrama (PNG)](diagramas/casos-de-uso-fitmanager.png)
- [Consultar arquivo-fonte (PlantUML)](diagramas/casos-de-uso-fitmanager.puml)

### 2.2 Diagrama de atividades

Representa os principais fluxos de execução da aplicação, incluindo autenticação e operações relacionadas ao gerenciamento de alunos.

- [Visualizar diagrama (PNG)](diagramas/atividades-fitmanager.png)
- [Consultar arquivo-fonte (Mermaid)](diagramas/atividades-fitmanager.mmd)
- [Consultar descrição (Markdown)](diagramas/atividades-fitmanager.md)

### 2.3 Diagrama de classes

Apresenta a organização das classes do sistema e os relacionamentos entre seus principais componentes.

- [Visualizar diagrama (PNG)](diagramas/classes-fitmanager.png)
- [Consultar arquivo-fonte (Mermaid)](diagramas/classes-fitmanager.mmd)
- [Consultar descrição (Markdown)](diagramas/classes-fitmanager.md)

### 2.4 Diagrama de pacotes

Representa a organização lógica do projeto, destacando a separação dos componentes em pacotes e suas responsabilidades.

- [Visualizar diagrama (PNG)](diagramas/pacotes-fitmanager.png)
- [Consultar arquivo-fonte (Mermaid)](diagramas/pacotes-fitmanager.mmd)
- [Consultar descrição (Markdown)](diagramas/pacotes-fitmanager.md)

## 3. Estrutura de diretórios

```text
docs/
├── README.md
├── casos-de-uso.md
├── historias-de-usuario.md
├── glossario.md
└── diagramas/
    ├── casos-de-uso-fitmanager.puml
    ├── casos-de-uso-fitmanager.png
    ├── atividades-fitmanager.md
    ├── atividades-fitmanager.mmd
    ├── atividades-fitmanager.png
    ├── classes-fitmanager.md
    ├── classes-fitmanager.mmd
    ├── classes-fitmanager.png
    ├── pacotes-fitmanager.md
    ├── pacotes-fitmanager.mmd
    └── pacotes-fitmanager.png
```

## 4. Padrão de documentação dos diagramas

Sempre que aplicável, os diagramas são armazenados em três formatos:

- **PNG:** imagem utilizada para visualização direta no GitHub e em outros documentos.
- **MMD ou PUML:** arquivo-fonte utilizado para editar e gerar o diagrama.
- **Markdown (MD):** documento complementar com a descrição e o contexto do diagrama.

Essa organização permite consultar as representações visuais e também acessar seus respectivos arquivos-fonte.

## 5. Considerações

A documentação acompanha a evolução do FitManager e poderá ser atualizada conforme novas funcionalidades, requisitos e componentes forem implementados.

As descrições devem permanecer alinhadas ao comportamento efetivamente desenvolvido, distinguindo funcionalidades implementadas daquelas ainda planejadas.

---

**FitManager — Sistema de Gerenciamento de Academia**  
Instituto Federal do Piauí (IFPI) – Campus Picos  
Curso: Análise e Desenvolvimento de Sistemas