package v2;

import java.io.Serializable;
import java.util.ArrayList;

public class Mapa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int tamanho = 10;// define que o mapa é de tamanho 10x10

    // ArrayList's necessárias para gerar o mapa e seus componentes
    private ArrayList<Navio> naviosPlayer; // dentro do mapa existirão navios 
    private ArrayList<Tiro> tirosPlayer;// tiros
    private final char AGUA = '.'; // e agua

    public Mapa(int tamanhoMapa) { // inicializa mapa
        this.inicializar(tamanhoMapa);
    }

    public void inicializar(int tamanhoMapa) { // metodo construtor mapa
        this.tamanho = tamanhoMapa;
        this.naviosPlayer = new ArrayList<>();
        this.tirosPlayer = new ArrayList<>();
    }
    
    public char[][] getMapaPlayer() { // puxa o mapa do jogador
    char[][] mapa = new char[tamanho][tamanho];

    for (int y = 0; y < tamanho; y++) {
        for (int x = 0; x < tamanho; x++) {
            Unidade navio = getNavioOnPosicao(x, y);
            if (navio != null && navio.isAtingido()) {
                mapa[y][x] = 'O'; // Parte do navio atingida
            } else if (navio != null) {
            mapa[y][x] = navio.desenha(); // Parte do navio não atingida
            } else {
        Tiro tiro = getTiroNaPosicao(x, y);
            if (tiro != null) {
            mapa[y][x] = tiro.desenha();
        } else {
            mapa[y][x] = AGUA;
            }
            }
        }
    }
        return mapa;
}



public char[][] getMapaInimigo() { // puxa mapa inimigo, escondendo seus navios, mostrando somente a parte atingida
    char[][] mapa = new char[tamanho][tamanho];

    for (int y = 0; y < tamanho; y++) {
        for (int x = 0; x < tamanho; x++) {
            Tiro tiro = getTiroNaPosicao(x, y);
            if (tiro != null) {
                mapa[y][x] = tiro.desenha();
                Unidade navio = getNavioOnPosicao(x, y);
                if (navio != null && navio.isAtingido()) {
                    mapa[y][x] = 'O'; // Exibe uma unidade inimigo atingido
                }
            } else {
                mapa[y][x] = AGUA; // Exibe água quando a posição estiver vazia
            }
        }
    }

    return mapa;
}
    public boolean isNavioNaPosicao(int x, int y) { // define se existe ou não navio na posição escolhida
        return getNavioOnPosicao(x, y) != null ? true : false;
    }

    public Navio getNavioOnPosicao(int x, int y) { // verifica se existe ou não navio na posição escolhida
        for(Navio navio : this.naviosPlayer) {
            for(int i=0; i < navio.getTamanho(); i++) {
                int baseEixo = navio.getOrientacao() == 0 ? navio.getY()+i : navio.getX() + i;

                if(navio.getOrientacao() == 0) {
                    if(navio.getX() == x && baseEixo == y) {
                        return navio;
                    }
                } else {
                    if(baseEixo == x && navio.getY() == y) {
                        return navio;
                    }
                }
            }
        }
        return null;// RETORNA NULO SE NÃO HOUVER NAVIO
    }

    public void posicionarNavio(int x, int y, int tamanho, int orientacao) { 
        // verifica se o posicionamento dos navios
        //respeita os limites do mapa, e se ja existe alguma parte de navio ocupando alguma  
        //das unidades(x,y) escolhidas, levando em consideração tamanho, orientação e localização(x,y)
    if ((orientacao == 0 && (y + tamanho > this.tamanho || x < 0)) || (orientacao == 1 && (x + tamanho > this.tamanho || y < 0))) {
        //verifica limites do mapa 
        throw new RuntimeException("Posição está fora dos limites do mapa [" + 10 + "][" + 10 + "]");
    }

    if (orientacao == 0) { // caso o usuario deseje que o navio esteja na vertical
        //verifica posicionamento "x" mais 2 abaixos(tamanho navio = 3) 
        for (int i = 0; i < tamanho; i++) {
            if (isNavioNaPosicao(x + i, y)) {
                throw new RuntimeException("Já existe um navio ocupando esta posição: [" + (x + i) + "][" + y + "]");
            }
        }
    } else {
        for (int i = 0; i < tamanho; i++) {//mesma logica, mas para horizontal (Y)
            if (isNavioNaPosicao(x, y + i)) {
                throw new RuntimeException("Já existe um navio ocupando esta posição: [" + x + "][" + (y + i) + "]");
            }
        }
    }

    this.naviosPlayer.add(new Navio(x, y, tamanho, orientacao)); // caso respeite os limites do mapa e não ocupe 
    // o mesmo espaço de outro navio, será posicionado
}

    public Tiro getTiroNaPosicao(int x, int y) {// puxa tiro na posição(X,Y)
        for(Tiro tiro : tirosPlayer) {
            if(tiro.getX() == x && tiro.getY() == y) {
                return tiro;// se houver ele retorna
            }
        }
        return null;//se não houve, não retorna nada
    }

    public void tiro(int x, int y) {// metodo tiro 
    tirosPlayer.add(new Tiro(x, y));
    Unidade navio = getNavioOnPosicao(x, y);//verifica se existe navio na posição 
    if (navio != null) {
        navio.setAtingido(true); // se houver, o navio será atingido
    }
}
}
