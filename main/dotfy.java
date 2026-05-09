import java.util.ArrayList;
import java.util.Scanner;

import usuario.usuario;
import usuario.usuarioFree;

public class dotfy {
    
    // Agora usamos apenas a lista de Objetos 'Musica'
    private static ArrayList<Musica> bancoDeMusicas = new ArrayList<>();
    
    // Listas para a playlist
    private static ArrayList<playlist> bancoDePlaylists = new ArrayList<>();
    private static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica", "K-Pop", "Forró", "MPB", "R&B", "Funk"};
    private static Scanner scanner = new Scanner(System.in);
// Definimos um usuário padrão (Free) ao iniciar o sistema
    private static usuario usuarioLogado = new usuarioFree ("Julia", "julia@email.com");
    
    public static void main(String[] args) {
        adicionarMusicasTeste();
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("\n Obrigado por usar o Sistema Dotfy! Até logo! ");
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" DOTFY ");
        System.out.println("=".repeat(50));
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Criar/Adicionar à playlist");
        System.out.println("5. Gerenciar playlist");
        System.out.println("6. Exibir estatísticas");
        System.out.println("7. Remover Música");
        System.out.println("8. Editar Música");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Escolha uma opção: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarPorTitulo(); break;
            case 4: criarplaylist(); break;
            case 5: gerenciarplaylist(); break;
            case 6: exibirEstatisticas(); break;
            case 7: removerMusica(); break;
            case 8: menuEditarMusica(); break;
            case 0: break;
            default: System.out.println(" Opção inválida! Tente novamente.");
        }
    }

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        
        System.out.print("Título: ");
        String tituloDigitado = scanner.nextLine().trim();
        
        System.out.print("Artista: ");
        String artistaDigitado = scanner.nextLine().trim();
        
        System.out.print("Duração (em segundos): ");
        int duracaoDigitada;
        try {
            duracaoDigitada = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println(" Duração inválida!");
            return;
    }
        
        System.out.println("\nGêneros disponíveis:");
        for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS_VALIDOS[i]);
    }
        System.out.print("Escolha o gênero (1-" + GENEROS_VALIDOS.length + "): ");
        int opcaoGenero = Integer.parseInt(scanner.nextLine());
        
        String generoEscolhido = GENEROS_VALIDOS[opcaoGenero - 1];
        
        // Criando o objeto e salvando no banco
        Musica novaMusica = new Musica(tituloDigitado, artistaDigitado, duracaoDigitada, generoEscolhido);
        bancoDeMusicas.add(novaMusica);
        
        System.out.println(" Música cadastrada com sucesso!");
    }

    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        
        if (bancoDeMusicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }
        
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
            Musica m = bancoDeMusicas.get(i);
            System.out.print((i + 1) + ". ");
            m.exibirDetalhes(); // O Java decide sozinho se formata como Música ou como Podcast!
        }
    }
    
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");
        System.out.print("Digite o título (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();
    
        boolean encontrou = false;
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
        // 1. Mudamos para 'Audio' para aceitar qualquer coisa da nossa hierarquia
        audio m = bancoDeMusicas.get(i);
        
        if (m.getTitulo().toLowerCase().contains(busca)) {
            if (!encontrou) {
                System.out.println("\nItens encontrados: ");
                encontrou = true;
            }
            // 2. A MÁGICA DO POLIMORFISMO:
            // O Java descobre sozinho se deve usar o exibirDetalhes da Música ou do Podcast
            System.out.print("- ");
            m.exibirDetalhes();
        }
    }
    
    if (!encontrou) {
        System.out.println(" Nada encontrado com esse título.");
    }
}

    public static void criarplaylist() {

    System.out.println("\n--- GERENCIAR PLAYLISTS ---");
    System.out.print("Nome da Playlist: ");
    String nomeBusca = scanner.nextLine().trim();

    // 1. Busca se a playlist já existe no banco de objetos
    playlist playlistEncontrada = null;
    for (playlist p : bancoDePlaylists) {
        if (p.getNome().equalsIgnoreCase(nomeBusca)) {
            playlistEncontrada = p;
            break;
        }
    }

    // 2. Se não existe, cria uma nova e adiciona ao banco
    if (playlistEncontrada == null) {
        playlistEncontrada = new playlist(nomeBusca);
        bancoDePlaylists.add(playlistEncontrada);
        System.out.println(" Nova playlist '" + nomeBusca + "' criada!");
    }

    // 3. Validação de Limite usando o usuário logado
    if (playlistEncontrada.quantidadeItens() >= usuarioLogado.getLimitePlaylist()) {
        System.out.println(" Limite de " + usuarioLogado.getLimitePlaylist() + " itens atingido para sua conta!");
        return;
    }

    // 4. Lógica para buscar a música e adicionar (similar ao que você já faz)
    System.out.print("Título da música para adicionar: ");
    String titulo = scanner.nextLine().trim();
    
}


    public static void gerenciarplaylist() {

    System.out.println("\n--- GERENCIAR PLAYLISTS ---");
    
    if (bancoDePlaylists.isEmpty()) {
        System.out.println("Nenhuma playlist criada ainda.");
        return;
    }

    // Listar todas as playlists disponíveis
    for (int i = 0; i < bancoDePlaylists.size(); i++) {
        playlist p = bancoDePlaylists.get(i);
        System.out.println((i + 1) + ". " + p.getNome() + " (" + p.quantidadeItens() + " itens)");
    }

    System.out.print("\nDigite o número da playlist para ver os detalhes (ou 0 para voltar): ");
    int escolha = lerOpcao();

    if (escolha > 0 && escolha <= bancoDePlaylists.size()) {
        // Aqui está a mágica: o Main pede para o OBJETO playlist se mostrar
        bancoDePlaylists.get(escolha - 1).exibirPlaylistCompleta();
    }
}
    

    public static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");

        if (bancoDeMusicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }

        int totalMusicas = bancoDeMusicas.size();
        int duracaoTotal = 0;
        
        for (Musica m : bancoDeMusicas) {
            duracaoTotal += m.getDuracao();
        }

        int duracaoMedia = duracaoTotal / totalMusicas;

        String generoMaisComum = "";
        int maxContagem = 0;
        
        for (String generoValido : GENEROS_VALIDOS) {
            int contagem = 0;
            for (Musica m : bancoDeMusicas) {
                if (generoValido.equals(m.getGenero())) {
                    contagem++;
                }
            }
            if (contagem > maxContagem) {
                maxContagem = contagem;
                generoMaisComum = generoValido;
            }
        }

        System.out.println("Total de músicas: " + totalMusicas);
        System.out.println("Duração total do catálogo: " + formatarDuracao(duracaoTotal));
        System.out.println("Duração média: " + formatarDuracao(duracaoMedia));
        System.out.println("Gênero mais comum: " + generoMaisComum + " (" + maxContagem + " ocorrências)");
    }

    public static String formatarDuracao(int segundos) {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        return String.format("%d:%02d", minutos, segs);
    }

    public static void adicionarMusicasTeste() {
        // Agora adicionamos Objetos Musica diretamente!
        bancoDeMusicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        bancoDeMusicas.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
        bancoDeMusicas.add(new Musica("Smells Like Teen Spirit", "Nirvana", 301, "Rock"));
        bancoDeMusicas.add(new Musica("Confidencial", "Zé Vaqueiro", 180, "Forró"));
        bancoDeMusicas.add(new Musica("Thunderous", "Stray Kids", 190, "K-Pop"));
        bancoDeMusicas.add(new Musica("Folded", "Kelani", 200, "R&B"));
        bancoDeMusicas.add(new Musica("Faz Parte de mim", "Mc Tuto", 150, "Funk"));
        
    }


    public static void removerMusica() {
        System.out.println("\n--- DELETAR MÚSICA ---");
        System.out.print("Digite o título (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();

        boolean encontrou = false;
        
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
            Musica m = bancoDeMusicas.get(i);
            
            if (m.getTitulo().toLowerCase().contains(busca)) {
                System.out.println("✅ Música removida: " + m.getTitulo() + " (por " + m.getArtista() + ")");
                bancoDeMusicas.remove(i);
                encontrou = true;
                break;
            }
        }
        
        // O if (!encontrou)
        if (!encontrou){
            System.out.println("❌ Nenhuma música encontrada com esse nome.");
        }
    }

public static void menuEditarMusica() {
        System.out.println("\n --- O QUE DESEJA EDITAR ---");
        System.out.println("1- Editar titulo");
        System.out.println("2- Ediar Artista");
        System.out.println("3- Editar Duração");
        System.out.println("4- Ediar Genero");
        System.out.println("0- Voltar");
        System.out.println("Escolha: ");



        int opcao = lerOpcao();
        switch (opcao) {
            case 1: editarTitulo(); break;
            case 2: editarArtista(); break;
            case 3: editarDuracao(); break;
            case 4: editargenero(); break;
            case 0: break;
            default: System.out.println("❌ Opção inválida! Tente novamente.");
        }
    }


public static void editarTitulo() {
        System.out.println("\n--- EDITAR TITULO ---");
        System.out.print("Digite o título (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();

        boolean encontrou = false;
        
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
            Musica m = bancoDeMusicas.get(i);
            
            if (m.getTitulo().toLowerCase().contains(busca)) {
                
                System.out.println("musica encontrada: " + m.getTitulo());

                System.out.println("Digite o novo titulo: ");
                String novoTitulo = scanner.nextLine().trim();

                String tituloAntigo = m.getTitulo();

                m.setTitulo(novoTitulo);

                System.out.println("Música editada de : " + tituloAntigo + " (para " + novoTitulo + ")");
                
                encontrou = true;
                break;
            }
        }
        
        //  caso nao encontre a musica exuibir mensagem
        if (!encontrou){
            System.out.println("❌ Nenhuma música encontrada com esse nome.");
        }
    }

    public static void editarArtista() {
        System.out.println("\n--- EDITAR ARTISTA---");
        System.out.print("Digite o nome da musica para alterar o artista: ");
        String busca = scanner.nextLine().trim().toLowerCase();

        boolean encontrou = false;
        
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
            Musica m = bancoDeMusicas.get(i);
            
            if (m.getTitulo().toLowerCase().contains(busca)) {
                
                System.out.println("musica encontrada: " + m.getTitulo() + " | Artista atual:" + m.getArtista());

                System.out.println("Digite o novo Artista: ");
                String novoArtista = scanner.nextLine().trim();

                String artistaAntigo = m.getArtista();

                m.setArtista(novoArtista);

                System.out.println("Música editada de : " + artistaAntigo + " (para " + novoArtista + ")");
                
                encontrou = true;
                break;
            }
        }
        
        //  caso nao encontre a musica exuibir mensagem
        if (!encontrou){
            System.out.println("❌ Nenhuma Artista encontrado com esse nome.");
        }
    }


    public static void editarDuracao() {
    System.out.println("\n--- EDITAR DURAÇÃO ---");
    System.out.print("Digite o título (ou parte dele): ");
    String busca = scanner.nextLine().trim().toLowerCase();

    boolean encontrou = false;
    
    for (Musica m : bancoDeMusicas) {
        if (m.getTitulo().toLowerCase().contains(busca)) {
            encontrou = true;
            System.out.println("Música encontrada: " + m.getTitulo());
            System.out.println("Duração atual: " + formatarDuracao(m.getDuracao()));
            
            System.out.print("Digite a nova duração (em segundos): ");
            try {
                int novaDuracao = Integer.parseInt(scanner.nextLine().trim());
                // A MÁGICA ACONTECE AQUI:
                m.atualizarDuracao(novaDuracao);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Você deve digitar somente números inteiros.");
            }
            break;
        }
    }
    
    if (!encontrou) {
        System.out.println(" Nenhuma música encontrada com esse nome.");
    }
        }
        
        //  caso nao encontre a musica exuibir mensagem
        if (!encontrou){
            System.out.println("Nenhuma música encontrada com esse nome.");
        }
    }


    public static void editargenero() {
        System.out.println("\n--- EDITAR GENERO ---");
        System.out.print("Digite o titulo da musica: ");
        String busca = scanner.nextLine().trim().toLowerCase();

        boolean encontrou = false;
        
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
            Musica m = bancoDeMusicas.get(i);
            
            if (m.getTitulo().toLowerCase().contains(busca)) {
                
                System.out.println("musica encontrada: " + m.getTitulo() + "| genero atual:" +  m.getGenero());

                System.out.println("Digite o novo Genero: ");
                String novoGenero = scanner.nextLine().trim();

                String generoAntigo = m.getGenero();

                m.setGenero(novoGenero);

                System.out.println("Música editada de : " + generoAntigo + " (para " + novoGenero + ")");
                
                encontrou = true;
                break;
            }
        }
        
        //  caso nao encontre a musica exuibir mensagem
        if (!encontrou){
            System.out.println("❌ Nenhum genero encontrada com esse nome.");
        }
    }

}