package usuario;
public class usuarioPremium extends usuario {
    public usuarioPremium(String nome, String email) {
        super(nome, email);
    }

    @Override
    public int getLimitePlaylist() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public void exibirPerfil() {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Plano: Premium (sem anúncio - Qualidade Alta)");
    }
}