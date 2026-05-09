
public class podcast extends audio {
    private String apresentador;

    public podcast(String titulo, String apresentador, int duracao) {
        super(titulo, duracao);
        this.apresentador = apresentador;
    }

    public String getApresentador() { return apresentador; }
    public void setApresentador(String apresentador) { this.apresentador = apresentador; }

    // Sobrescrita de métodos (Overriding) específica para Podcast
    @Override
    public void exibirDetalhes() {
        System.out.print("[PODCAST] ");
        super.exibirDetalhes();
        System.out.println("| Apresentador: " + apresentador);
    }
}
