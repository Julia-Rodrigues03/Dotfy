package usuario;
public class usuarioPremium extends usuario {
            public usuarioPremium(String nome, String email) {
                super(nome, email;)
            }

            @Override
            public int getLimitePlaylist() {
                return Integer.MAX_VALUE;
            }
            
            @Override
            public void exibirPerfil() {
                super.exibirPerfil();
                System.out.println("Plano: Premium (sem anuncio - Qualidade Alta");
            }

        }