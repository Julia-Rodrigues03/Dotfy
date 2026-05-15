package usuario;

public abstract class usuario {
    protected String nome;
    protected String email;
    
    public usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public abstract int getLimitePlaylist();

    public void exibirPerfil() {
        // Exibir informações básicas do usuário
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        throw new UnsupportedOperationException("Unimplemented method 'exibirPerfil'");
    }
}
