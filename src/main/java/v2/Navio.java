package v2;

import java.io.Serializable;

public class Navio extends Unidade implements Serializable {
    // navio é um conjunto de unidades
    private static final long serialVersionUID = 2L;
    private int orientacao; //0 - Horizontal, 1 = Vertical
    private boolean isDestruido;// verifica se o navio foi destruido
    
    public Navio(int x, int y, int tam, int ori) {// metodo construtor
        super(x, y, tam);
        orientacao = ori;
        isDestruido = false;
        //saber o tamanho e a orientação do navio é importante
        //se a orientação for vertical, o tamanho do navio é aplicado somente para "X"(plano cartesiano)
    }
    //getter e setters de orientação e verificação de destruido
    public int getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(int orientacao) {
        this.orientacao = orientacao;
    }

    public boolean isDestruido() {
        return isDestruido;
    }

    public void setDestruido(boolean destruido) {
        isDestruido = destruido;
    }
    @Override
    //"%" representa o navio
    //"O" representa o navio atingido
    public char desenha() {
        return isAtingido() ? 'O' : '%';
    }  
}
