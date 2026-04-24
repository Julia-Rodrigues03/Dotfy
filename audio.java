//
public class audio {
    protected String titulo;
    protected int duracao;

    public audio(String titulo, int duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }

    /**
     * Método genérico que será modificado pelas subclasses.
     */
    public void exibirDetalhes() {
        System.out.print("Título: " + titulo + " | Duração: " + duracao + "s ");
    }
}