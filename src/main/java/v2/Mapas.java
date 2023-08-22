package v2;
import java.io.Serializable;

public class Mapas implements Serializable {
    private static final long serialVersionUID = 1L;
    // classe criada unica e exclusivamente para salvarmos e carregarmos os dois mapas do jogo
    // Java não permite return de dois objetos
    private Mapa mapaPlayer;
    private Mapa mapaInimigo;

    public Mapas(int tamanhoMapa) {
        this.mapaPlayer = new Mapa(tamanhoMapa);
        this.mapaInimigo = new Mapa(tamanhoMapa);
    }

    // getter e setters
    public Mapa getMapaPlayer() {
        return mapaPlayer;
    }

    public Mapa getMapaInimigo() {
        return mapaInimigo;
    }

    public void setMapaPlayer(Mapa mapaPlayer) {
        this.mapaPlayer = mapaPlayer;
    }

    public void setMapaInimigo(Mapa mapaInimigo) {
        this.mapaInimigo = mapaInimigo;
    }
}

