import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de Streaming de Música - CP1
 * VERSÃO CONCLUÍDA
 */
public class  dotfy {
    
    // ArrayLists para armazenar os dados das músicas
    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();
    static ArrayList<String> musicas = new ArrayList<>();
    static ArrayList<String> playlist = new ArrayList<>();


    // Gêneros válidos
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        adicionarMusicasTeste();
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("\n🎵 Obrigado por usar o Sistema de Dotfy! Até logo! 🎵");
        scanner.close();
    }
    
    /**
     * FORNECIDO: Exibe o menu principal
     */
    public static void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=".repeat(0) +" DOTFY");
        System.out.println("=".repeat(50));
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlist");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * FORNECIDO: Lê a opção do usuário com tratamento de erro
     */
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * FORNECIDO: Processa a opção escolhida
     */
    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarPorTitulo(); break;
            case 4: criarplaylist(); break;
            case 5: gerenciarplaylist(); break;
            case 6: exibirEstatisticas(); break;
            case 0: break;
            default: System.out.println("❌ Opção inválida! Tente novamente.");
        }
    }
    
    /**
     * FORNECIDO: Cadastra uma nova música
     */
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        
        if (titulo.isEmpty()) {
            System.out.println("❌ Título não pode ser vazio!");
            return;
        }
        
        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();
        
        if (artista.isEmpty()) {
            System.out.println("❌ Artista não pode ser vazio!");
            return;
        }
        
        System.out.print("Duração (em segundos): ");
        int duracao;
        try {
            duracao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Duração inválida!");
            return;
        }
        
        if (duracao <= 0) {
            System.out.println("❌ Duração deve ser maior que 0!");
            return;
        }
        
        System.out.println("\nGêneros disponíveis:");
        for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS_VALIDOS[i]);
        }
        
        System.out.print("Escolha o gênero (1-" + GENEROS_VALIDOS.length + "): ");
        int opcaoGenero;
        try {
            opcaoGenero = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Opção inválida!");
            return;
        }
        
        if (opcaoGenero < 1 || opcaoGenero > GENEROS_VALIDOS.length) {
            System.out.println("❌ Gênero inválido!");
            return;
        }
        
        String genero = GENEROS_VALIDOS[opcaoGenero - 1];
        
        titulos.add(titulo);
        artistas.add(artista);
        duracoes.add(duracao);
        generos.add(genero);
        
        System.out.println("✅ Música cadastrada com sucesso!");
    }
    
    /**
     * FORNECIDO: Lista todas as músicas
     */
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        
        if (titulos.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }
        
        for (int i = 0; i < titulos.size(); i++) {
            System.out.printf("%d. Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
                (i + 1),
                titulos.get(i),
                artistas.get(i),
                formatarDuracao(duracoes.get(i)),
                generos.get(i)
            );
        }
        
        System.out.println("\nTotal: " + titulos.size() + " música(s)");
    }
    
    /**
     * FORNECIDO: Busca músicas por título
     */
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");
        
        System.out.print("Digite o título (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();
        
        boolean encontrou = false;
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).toLowerCase().contains(busca)) {
                if (!encontrou) {
                    System.out.println("\nMúsicas encontradas:");
                    encontrou = true;
                }
                System.out.printf("- %s | %s | %s | %s%n",
                    titulos.get(i),
                    artistas.get(i),
                    formatarDuracao(duracoes.get(i)),
                    generos.get(i)
                );
            }
        }
        
        if (!encontrou) {
            System.out.println("❌ Nenhuma música encontrada com esse título.");
        }
    }
    
    /**
     * IMPLEMENTADO: Busca músicas por artista
     */
    public static void criarplaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        
        System.out.print("Digite o nome da Playlist: ");
        String nomePlaylist = scanner.nextLine().trim();
        
        if (nomePlaylist.isEmpty()) {
            System.out.println(" não pode ser vazio!");
            return;
        }
        
        System.out.print("Título da Música: ");
        String titulo = scanner.nextLine().trim();
        
        if (titulo.isEmpty()) {
            System.out.println("❌ Título não pode ser vazio!");
            return;
        }
        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();
        
        if (artista.isEmpty()) {
            System.out.println("❌ Artista não pode ser vazio!");
            return;
        }

        String novaMusica = titulo + "( por " + artista + ")";
// chama o boolean para verificar se a playlist ja existe
        boolean encontrou = false;

        for(int i = 0; i < playlist.size(); i++){
            //equal procura e compara as plavras ignorando letras maiuscula e minusculas
            if (playlist.get(i).equalsIgnoreCase(nomePlaylist)) {

                String musicasAntigas = musicas.get(i);
                // aqui guardamos a musica nova, junto com as antigas 
                musicas.set(i, musicasAntigas + "," + novaMusica);
                encontrou = true;
                System.out.println(" musica adicionada á playlist " + nomePlaylist + " já existente");
                break;
            }
        }
// se a playlist nao foi achada criamos uma nova a partir daqui 
        if (!encontrou){
        playlist.add(nomePlaylist);
        musicas.add(novaMusica);
        //musicas.add(titulo + " (por " + artista + ")");//


        System.out.println(" Playlist '" + nomePlaylist + "' criada com sucesso!");
        
        }
    }
    
    /**
     * IMPLEMENTADO: Busca músicas por gênero
     */
    public static void gerenciarplaylist() {
        System.out.println("\n--- GERENCIAR PLAYLIST---");
        // verifica se esta vazio
        if (playlist.isEmpty()){
            System.out.println("nenhuma playlist criada ainda.");
            return;
        }

        System.out.println("PLAYLIST DISPONIVEIS:");
// percorre pela playlist 
        for (int i = 0; i < playlist.size(); i++){
        System.out.println((i + 1) + ". Nome: " + playlist.get(i) + " | Contém a música: " + musicas.get(i));
        }

        
    }
    
    /**
     * IMPLEMENTADO: Exibe estatísticas do sistema
     */
    public static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        
        if (titulos.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }
        
        int totalMusicas = titulos.size();
        
        // Calcular duração total
        int duracaoTotal = 0;
        for (int duracao : duracoes) {
            duracaoTotal += duracao;
        }
        
        // Calcular duração média
        int duracaoMedia = duracaoTotal / totalMusicas;
        
        // Encontrar gênero mais comum
        String generoMaisComum = "";
        int maxContagem = 0;
        
        for (String generoValido : GENEROS_VALIDOS) {
            int contagem = 0;
            for (String genero : generos) {
                if (generoValido.equals(genero)) {
                    contagem++;
                }
            }
            if (contagem > maxContagem) {
                maxContagem = contagem;
                generoMaisComum = generoValido;
            }
        }
        
        // Exibir estatísticas
        System.out.println("Total de músicas: " + totalMusicas);
        System.out.println("Duração total do catálogo: " + formatarDuracao(duracaoTotal));
        System.out.println("Duração média das músicas: " + formatarDuracao(duracaoMedia));
        System.out.println("Gênero mais comum: " + generoMaisComum + " (" + maxContagem + " ocorrências)");
    }
    
    /**
     * FORNECIDO: Formata duração de segundos para MM:SS
     */
    public static String formatarDuracao(int segundos) {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        return String.format("%d:%02d", minutos, segs);
    }
    
    /**
     * FORNECIDO: Adiciona músicas de teste
     */
    public static void adicionarMusicasTeste() {
        titulos.add("Bohemian Rhapsody");
        artistas.add("Queen");
        duracoes.add(354);
        generos.add("Rock");
        
        titulos.add("Billie Jean");
        artistas.add("Michael Jackson");
        duracoes.add(293);
        generos.add("Pop");
        
        titulos.add("Smells Like Teen Spirit");
        artistas.add("Nirvana");
        duracoes.add(301);
        generos.add("Rock");

        titulos.add("Confidencial");
        artistas.add("Zé vaqueiro");
        duracoes.add(180);
        generos.add("Forró");

        titulos.add("Thundercolds");
        artistas.add("Stray Kids");
        duracoes.add(190);
        generos.add("K-pop");
    }

public static void removerMusica(){
    System.out.println("\n--- DELETAR MUSICA ---");
        
        System.out.print("Digite o título (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();

        boolean encontrou = false;
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).toLowerCase().contains(busca)) {
                if (!encontrou) {
                    System.out.println("\nMúsicas encontradas e removida:" + titulos.get(i) + " por " + artistas.get(i) + ")");
//removendo  todas as caraciteristicas da musica 
                    titulos.remove(i);
                    artistas.remove(i);
                    duracoes.remove(i);
                    generos.remove(i);

                    encontrou = true;

                    break;

                }
            }
            if (!encontrou){
                System.out.println("nenhuma musica encontrada com esse nome ");
            }
        }

    }
}





