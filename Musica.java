public class Musica {
    // Atributos privados
    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    // Construtor (Chamado na hora de criar a música pela primeira vez)
    public Musica(String titulo, String artista, int duracao, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.genero = genero;
    }

    
    // GETTERS Para LER os dados

    public String getTitulo() { return this.titulo; }
    public String getArtista() { return this.artista; }
    public int getDuracao() { return this.duracao; }
    public String getGenero() { return this.genero; }

    
    // SETTERS para ALTERAR os dados

    public void setTitulo(String novoTitulo) {
        //  substitui o que esta escrito pelo novo valor
        this.titulo = novoTitulo;
    }

    public void setArtista(String novoArtista) {
        this.artista = novoArtista;
    }

    public void setDuracao(int novaDuracao) {
        this.duracao = novaDuracao;
    }

    public void setGenero(String novoGenero) {
        this.genero = novoGenero;
    }
}