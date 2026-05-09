package usuario;
public class usuarioFree extends usuario{
            public usuarioFree(String nome, String email) {
                super(nome, email);
            }

            @Override
            public int getLimitePlaylist() {
                return 5;
            }

        }