# Projeto Eco Memória

Este projeto é um aplicativo Android chamado "Eco Memória", desenvolvido por Yago de Lima França. O jogo tem como objetivo incentivar a proteção ecológica, promovendo a conscientização ambiental de forma lúdica. Os jogadores podem jogar, salvar suas pontuações e visualizar suas pontuações passadas.

## Tecnologias Utilizadas

- **Java**: A principal linguagem de programação utilizada neste projeto.
- **Kotlin**: Usado em algumas partes do projeto, principalmente para scripts do Gradle.
- **Gradle**: Usado para gerenciar dependências do projeto e configurações de build.
- **Android SDK**: O kit de desenvolvimento de software para a plataforma Android.
- **Room**: Uma biblioteca de persistência que fornece uma camada de abstração sobre o SQLite, usada para armazenamento de dados neste projeto.

## Funcionalidades

- **Gameplay**: Os usuários podem jogar o jogo e suas pontuações são registradas.
- **Placar**: Os usuários podem visualizar suas pontuações passadas.
- **Gerenciamento de Jogadores**: Os usuários podem inserir seus nomes antes de jogar.

## Estrutura do Projeto

O projeto segue a estrutura padrão de um projeto Android, com arquivos de origem em Java e Kotlin, scripts de build do Gradle e arquivos de layout XML.

- `MainActivity.java`: Esta é a atividade principal do aplicativo. Ela gerencia a criação do jogo e do menu.
- `FragmentJogo.java`: Este fragmento gerencia a lógica do jogo.
- `FragmentMenu.java`: Este fragmento exibe o menu.
- `FragmentListaPartida.java`: Este fragmento exibe a lista de pontuações passadas.
- `model`: Este pacote contém os modelos de dados para `Jogador` (Player) e `Partida` (Game).
- `dao`: Este pacote contém os Objetos de Acesso a Dados (DAOs) para `Jogador` e `Partida`.
- `utils`: Este pacote contém classes utilitárias, como `ConversorDeEnums`.

## Construindo o Projeto

Para construir o projeto, você precisa ter o Android Studio instalado. Abra o projeto no Android Studio e clique em `Build > Make Project` no menu.

## Executando o Projeto

Para executar o projeto, você precisa ter um dispositivo Android conectado ao seu computador ou um emulador Android instalado. Clique em `Run > Run 'app'` no menu.

## Contribuições

Contribuições são bem-vindas. Por favor, abra uma issue para discutir suas ideias antes de fazer alterações.

## Licença

Este projeto está licenciado sob os termos da licença MIT.
