package usuario;

public abstract class usuario {
    protected String nome;
    protected String email;
    
    public usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public abstract int getLimitePlaylist();
}
