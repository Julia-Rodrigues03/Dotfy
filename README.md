# 🎵 Dotfy - Sistema de Streaming de Música

O **Dotfy** é uma aplicação Java de console que simula as funcionalidades de uma plataforma de streaming. O projeto foi desenvolvido com foco na aplicação prática dos pilares da **Programação Orientada a Objetos (POO)**: Herança, Polimorfismo, Encapsulamento e Abstração.

## 🚀 Funcionalidades

- **Sistema de Login:** O usuário pode se identificar e escolher entre os planos **Free** ou **Premium**.
- **Área de Reprodução:** Interface de console que simula o player de música, exibindo detalhes da faixa e qualidade sonora baseada no tipo de conta.
- **Gestão de Playlists:** - Usuários **Free**: Limite de 5 músicas por playlist.
  - Usuários **Premium**: Playlists ilimitadas e áudio em alta fidelidade.
- **Catálogo de Áudio:** Suporte para Músicas (com artistas e gêneros) e Podcasts (com apresentadores).
- **Busca e Edição:** Localização de músicas por título e alteração dinâmica de gêneros musicais.

## 🛠️ Tecnologias e Conceitos Aplicados

- **Linguagem:** Java 17+
- **Herança:** Uso de classes pai (`audio`, `usuario`) para compartilhar comportamentos com subclasses.
- **Polimorfismo:** Métodos como `exibirDetalhes()` e `getLimitePlaylist()` que se adaptam conforme o objeto (Musica vs Podcast / Free vs Premium).
- **Encapsulamento:** Proteção de dados sensíveis usando modificadores de acesso e métodos assessores (Getters/Setters).
- **Pacotes:** Organização lógica em `main` (lógica do sistema) e `usuario` (lógica de perfis).

## 📂 Estrutura do Projeto

```text
src/
├── main/
│   ├── audio.java           # Classe base para áudios
│   ├── Musica.java          # Especialização para faixas musicais
│   ├── podcast.java         # Especialização para episódios de podcast
│   ├── playlist.java        # Lógica de coleções de músicas
│   └── dotfy.java           # Classe principal e menus
└── usuario/
    ├── usuario.java         # Classe abstrata de usuário
    ├── usuarioFree.java     # Regras para usuários gratuitos
    └── usuarioPremium.java  # Benefícios para usuários premium