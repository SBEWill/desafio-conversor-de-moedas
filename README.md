# 💱 Conversor de Moedas Java

## 🧾 Descrição do Projeto

O **Conversor de Moedas** é um aplicativo de linha de comando desenvolvido em **Java**, que permite realizar conversões entre diferentes moedas utilizando uma **API externa** para obter taxas de câmbio em tempo real. Além disso, o sistema mantém um **histórico persistente em JSON** e um **log de operações em arquivo de texto**, permitindo consultar ou limpar o histórico de conversões realizadas.

Este projeto foi criado como um desafio prático do programa One(ORACLE) em parceria com a Alura para consolidar conhecimentos de **POO, manipulação de arquivos, coleções, APIs HTTP, e tratamento de exceções** em Java.

---

## 📂 Estrutura de Diretórios

```
📦 conversor-moedas
├── src
│   ├── main
│   │   └── Main.java                # Classe principal que executa o programa
│   ├── model
│   │   ├── ConversionRecord.java    # Modelo que representa uma conversão registrada
│   │   └── Currency.java            # Enum que lista as moedas disponíveis
│   ├── service
│   │   ├── HistoryManager.java      # Gerencia o histórico em memória e arquivo JSON
│   │   └── LogService.java          # Registra logs de cada conversão em arquivo
│   └── utils
│       ├── Conversion.java          # Realiza o cálculo de conversão entre moedas
│       └── TaxService.java          # Conecta-se à API externa e obtém a taxa de câmbio
│
├── history.json                     # Arquivo JSON com o histórico de conversões
├── conversions.log                  # Arquivo de log com registros detalhados
└── README.md                        # Documento de explicação e instruções do projeto
```

---

## ⚙️ Funcionalidades Principais

✅ Conversão de valores entre diferentes moedas
✅ Obtenção de taxa de câmbio em tempo real via **ExchangeRate API**
✅ Armazenamento automático de histórico de conversões em arquivo JSON
✅ Registro de logs detalhados em arquivo de texto
✅ Menu interativo de fácil navegação no terminal
✅ Opção para limpar o histórico e visualizar as conversões mais recentes primeiro
✅ Facilidade para implementação de mais moedas


---

## 🧩 Tecnologias Utilizadas

* **Java 17+**
* **Gson** (para serialização/deserialização JSON)
* **Java HTTP Client** (para consumir a API de câmbio)
* **API ExchangeRate** ([https://www.exchangerate-api.com](https://www.exchangerate-api.com))
* **Collections (Deque, List, ArrayList)**
* **I/O (java.nio.file)** para manipulação de arquivos

---

## 🏗️ Funcionamento do Programa

### 1. Execução Principal (`Main.java`)

O programa inicia exibindo um menu com as opções:

1. Fazer conversão
2. Ver histórico
3. Limpar histórico
4. Sair

Cada escolha leva a um fluxo distinto:

* **Conversão:** solicita as moedas de origem e destino, e o valor a converter. Depois chama a `TaxService` para obter a taxa e realiza o cálculo com `Conversion`.
* **Histórico:** lista todas as conversões registradas no arquivo JSON (mais recentes primeiro).
* **Limpar histórico:** apaga o conteúdo do histórico em memória e do arquivo.

### 2. Persistência do Histórico (`HistoryManager.java`)

Usa uma estrutura `Deque` (fila dupla) para armazenar até 50 registros recentes. O histórico é salvo em `history.json` via **Gson** e carregado na inicialização do programa.

### 3. Log de Operações (`LogService.java`)

Cada conversão bem-sucedida é gravada também no arquivo `conversions.log`, incluindo data/hora, moedas, taxa e resultado.

### 4. Obtenção da Taxa de Câmbio (`TaxService.java`)

Faz uma requisição HTTP para a API ExchangeRate, no formato:

```
https://v6.exchangerate-api.com/v6/{API_KEY}/pair/{from}/{to}
```

Recebe a resposta JSON e extrai o campo `conversion_rate`, retornando-o como `BigDecimal`.

### 5. Conversão de Moeda (`Conversion.java`)

Realiza o cálculo:

```
valor_convertido = valor_original * taxa
```

Ajusta o resultado para 2 casas decimais com `RoundingMode.HALF_UP`.

---

## 🧠 Conceitos Praticados

* Programação Orientada a Objetos (POO)
* Manipulação de JSON com Gson
* Persistência de dados em arquivos (texto e JSON)
* Requisições HTTP com `HttpClient`
* Tratamento de exceções (IOException, InterruptedException, etc.)
* Uso de coleções (`Deque`, `List`, `ArrayList`)
* Imutabilidade com classes de modelo
* Enumeração para representação de tipos fixos (moedas)

---

## 🔐 Sobre a API Key

A chave de API utilizada (`TaxService.apiKey`) deve ser obtida gratuitamente em [ExchangeRate API](https://www.exchangerate-api.com/).
Para segurança, recomenda-se **não deixar a chave exposta no código**, mas sim carregá-la via variável de ambiente ou arquivo `.env`.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* Java 17+ instalado
* Conexão com a internet (para chamadas à API)

### Passos

1. Clone o repositório ou copie os arquivos-fonte.
2. Compile o projeto:

   ```bash
   javac -d out $(find src -name "*.java")
   ```
3. Execute:

   ```bash
   java -cp out main.Main
   ```

O programa abrirá no terminal, exibindo o menu interativo.

---

## 📜 Estrutura dos Arquivos de Saída

### `history.json`

Armazena um array de conversões no formato:

```json
[
  {
    "timestampIso": "2025-11-01T12:34:56Z",
    "from": "USD",
    "to": "BRL",
    "amount": 100.00,
    "rate": 5.12,
    "result": 512.00
  }
]
```

### `conversions.log`

Registra cada operação como texto:

```
2025-11-01T12:34:56Z | USD -> BRL | amount=100.00 | rate=5.12 | result=512.00
```

---

## 👤 Dados do Autor

**Nome:** Willian Diniz

**E-mail:** williandiniz2412@hotmail.com

**GitHub:** https://github.com/SBEWill

**LinkedIn:** https://www.linkedin.com/in/willian-diniz-2360b74b/

---

## 🧩 Possíveis Melhorias Futuras

* Adicionar suporte a mais moedas dinamicamente via API.
* Implementar interface gráfica (Swing ou JavaFX).
* Permitir exportar histórico em CSV.
* Testes automatizados com JUnit.
* Parametrizar API key e diretórios via arquivo de configuração.

---

## 🏁 Conclusão

O **Conversor de Moedas** é um projeto completo e didático, ideal para quem deseja compreender na prática conceitos essenciais de **Java moderno, APIs REST, persistência e boas práticas de código**. Ele demonstra como unir múltiplos conceitos em um sistema funcional e extensível.