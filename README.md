# Projeto Sudoku em Java ☕️♨️

Este é um projeto de um jogo de Sudoku funcional desenvolvido em Java, como parte dos desafios propostos pela [Digital Innovation One (DIO)](https://www.dio.me/). O foco foi criar um jogo com uma interface gráfica amigável, utilizando a biblioteca nativa **Java Swing**.
---

### ✨ Funcionalidades

* **Interface Gráfica:** Jogue Sudoku em uma janela intuitiva e fácil de usar, sem a necessidade do terminal.
* **Níveis de Dificuldade:** Ao iniciar um novo jogo, é possível escolher entre os níveis **Fácil**, **Médio** e **Difícil**.
* **Validação de Jogo:** Verifique a qualquer momento se sua solução está correta, incompleta ou contém erros.
* **Controles Simples:** Botões para iniciar um "Novo Jogo", "Verificar" a solução e "Limpar" os números que você inseriu.
* **Feedback Visual:** Os números pré-definidos do tabuleiro são fixos e possuem uma cor diferente, para que o jogador saiba quais casas não pode alterar.

---

### 🎲 Regras do Jogo (A Lógica do Sudoku)

O objetivo do Sudoku é preencher um tabuleiro 9x9 com números, de modo que cada linha, coluna e sub-grade 3x3 contenha todos os números de 1 a 9, sem repeti-los.

As três regras fundamentais são:

1.  **Regra da Linha:** Cada uma das 9 linhas deve conter os números de 1 a 9, sem que nenhum se repita.
2.  **Regra da Coluna:** Cada uma das 9 colunas deve conter os números de 1 a 9, sem que nenhum se repita.
3.  **Regra da Sub-grade 3x3:** Cada uma das nove "caixas" ou sub-grades 3x3 deve conter os números de 1 a 9, sem que nenhum se repita.

---

### 💡 Dicas e Estratégias

Algumas dicas de como abordar o problema:

* **Varredura por Número (Scanning):** Escolha um número (por exemplo, o "5") e verifique todas as linhas, colunas e caixas 3x3 para ver onde ele já aparece. Ao fazer isso, você pode encontrar uma caixa 3x3 onde o "5" só pode ir em um único lugar vago.

* **Procure a Casa Óbvia:** Em vez de focar em um número, foque em uma casa vazia. Olhe para a linha, a coluna e a caixa 3x3 em que ela se encontra. Veja todos os números que já estão nessas áreas. Se faltar apenas um número de 1 a 9, essa é a resposta para aquela casa!

* **Comece pelos Mais Frequentes:** No início, olhe para os números que mais aparecem no tabuleiro. Eles restringem mais as possibilidades e geralmente permitem encontrar as primeiras jogadas com mais facilidade.

---

### 🚀 Como Usar o Projeto

#### 1. Para Executar o Jogo

* **Pré-requisito:** Tenha o **JDK 21** (ou superior) instalado.
* **Abra na sua IDE:** Clone o repositório e abra a pasta do projeto em uma IDE Java (IntelliJ, Eclipse, etc.).
* **Execute:** Encontre e rode o arquivo `Main.java`. A janela do jogo aparecerá.

#### 2. Para Jogar

* **Novo Jogo:** Clique no botão "Novo Jogo" para iniciar uma partida e escolher a dificuldade.
* **Preencher:** Números fixos são exibidos em **azul** e não podem ser alterados. Clique nas casas vazias para digitar seus números (em **preto**).
* **Controles:**
    * **`Verificar`**: Checa se a sua solução está correta, incompleta ou possui erros.
    * **`Limpar`**: Apaga todos os números que você inseriu, mantendo o desafio original.

---

### 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Interface Gráfica:** Java Swing
* **IDE:** IntelliJ IDEA

