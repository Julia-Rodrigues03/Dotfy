
// Conceito de herança e Palavra-chave extends
public class Musica extends audio {
    private String artista;
    private String genero;

    // Construtor completo
    public Musica(String titulo, String artista, int duracao, String genero) {
        // ✅ Palavra-chave super: Chama o construtor da classe pai (Audio)
        super(titulo, duracao);
        this.artista = artista;
        this.genero = genero;
    }

    // ✅ Sobrecarga de métodos (Overloading):
    // Temos dois construtores com parâmetros diferentes.
    // Se não passarem o gênero, ele vira "Desconhecido" por padrão.
    public Musica(String titulo, String artista, int duracao) {
        super(titulo, duracao);
        this.artista = artista;
        this.genero = "Desconhecido";
    }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    // ✅ Sobrescrita de métodos (Overriding):
    // Modificamos o comportamento do método da classe pai.
    @Override
    public void exibirDetalhes() {
        System.out.print("[MÚSICA] ");
        super.exibirDetalhes(); // Reutiliza a impressão do título e duração da classe pai
        System.out.println("| Artista: " + artista + " | Gênero: " + genero);
    }
}