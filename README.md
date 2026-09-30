# 🏋️ Sistema de Academia

Modelo orientado a objetos para uma academia acompanhar seus **alunos**, **planos**, **professores** e os **treinos** montados com **exercícios** (séries e repetições).

O objetivo principal é permitir consultar rapidamente o treino de cada aluno e o professor responsável por ele.

> Este projeto contém apenas as **classes do modelo** (não possui `main`).

---

## 📋 Funcionalidades

- Cadastro de alunos, professores, planos e exercícios
- Associação de alunos aos planos
- Associação de alunos aos professores
- Criação de treinos e associação aos alunos
- Inclusão de exercícios nos treinos
- Registro de séries e repetições por exercício
- Consulta dos exercícios de um treino
- Consulta do(s) treino(s) de um aluno
- Identificação do professor responsável pelo aluno

## 📌 Regras de negócio

- Todo aluno possui **um plano**.
- Um professor pode acompanhar **vários alunos**.
- Um aluno pode ter **vários treinos** ao longo do tempo.
- Um treino possui **vários exercícios**.
- O mesmo exercício pode aparecer em **treinos diferentes**, cada um com suas próprias séries e repetições.

---

## 🧱 Estrutura de classes

| Classe | Responsabilidade | Principais atributos |
|---|---|---|
| `Aluno` | Dados cadastrais do aluno | id, nome, cpf, telefone, plano, professor, treinos |
| `Professor` | Professor que acompanha alunos | id, nome, cpf, alunos |
| `Plano` | Modalidade de contratação | id, nome (mensal, trimestral...), duração, valor |
| `Exercicio` | Exercício disponível na academia | id, nome, grupo muscular |
| `Treino` | Conjunto de exercícios de um aluno | id, nome, aluno, professor, itens |
| `ItemTreino` | Exercício dentro de um treino | exercício, séries, repetições |
| `Academia` | Guarda as listas gerais do sistema | alunos, professores, planos, exercícios, treinos |

Todas as classes possuem atributos privados, construtor, getters e setters.

### Relacionamentos

```
Plano 1 ────── N Aluno
Professor 1 ── N Aluno
Aluno 1 ────── N Treino
Treino 1 ───── N ItemTreino N ───── 1 Exercicio
```

`ItemTreino` resolve a relação muitos-para-muitos entre `Treino` e `Exercicio`, guardando as informações de execução (séries e repetições).

---

## 🗂️ Organização do projeto

```
sistema-academia/
├── src/
│   ├── Academia.java
│   ├── Aluno.java
│   ├── Professor.java
│   ├── Plano.java
│   ├── Exercicio.java
│   ├── Treino.java
│   └── ItemTreino.java
└── README.md
```

## 🚀 Como usar

1. Clone o repositório:
   ```bash
   git clone https://github.com/rotch-jolibois/sistema-academia.git
   ```
2. Abra o projeto na sua IDE (IntelliJ, por exemplo).
3. Como não há `main`, use as classes a partir de outro projeto ou crie uma classe de teste para instanciar os objetos.

---

## 🛠️ Tecnologias

- Java
- Programação Orientada a Objetos (POO)
- Git e GitHub

## 👤 Autor
**augusto-rech** — estudante de Desenvolvimento de Sistemas.
**MarcoAntonioAbel** — estudante de Desenvolvimento de Sistemas.
**LucasGraveCoan** — estudante de Desenvolvimento de Sistemas.
**rotch-jolibois** — estudante de Desenvolvimento de Sistemas.
