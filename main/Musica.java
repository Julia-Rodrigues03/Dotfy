package main;

public class Musica extends audio {
    private String artista;
    private String genero;

    // Construtor completo
    public Musica(String titulo, String artista, int duracao, String genero) {
        //
        super(titulo, duracao);
        this.artista = artista;
        this.genero = genero;
    }

    //
    //
    public Musica(String titulo, String artista, int duracao) {
        super(titulo, duracao);
        this.artista = artista;
        this.genero = "Desconhecido";
    }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    //
    @Override
    public void exibirDetalhes() {
        System.out.print("[MÚSICA] ");
        super.exibirDetalhes(); // Reutiliza a impressão do título e duração da classe pai
        System.out.println("| Artista: " + artista + " | Gênero: " + genero);
    }
}