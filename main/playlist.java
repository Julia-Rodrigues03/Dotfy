package main;
import java.util.ArrayList;

public class playlist {
    private String nome;
    private ArrayList<audio> itens; // Pode guardar Musica ou Podcast (Polimorfismo!)

    public playlist(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
    }

    // limitação de quantidade
    public String getNome() { return nome; }
    
    public ArrayList<audio> getItens() { return itens; }

    public void adicionarItem(audio item) {

        this.itens.add(item);
    }

    public int quantidadeItens() {
        return itens.size();
    }

    public void exibirPlaylistCompleta() {
    System.out.println("\n--- Playlist: " + this.nome + " ---");
    if (itens.isEmpty()) {
        System.out.println("Esta playlist está vazia.");
    } else {
        for (int i = 0; i < itens.size(); i++) {
            System.out.print((i + 1) + ". ");
            // O polimorfismo acontece aqui: o Java decide se chama
            // exibirDetalhes de Musica ou de Podcast!
            itens.get(i).exibirDetalhes();
        }
    }
}
}