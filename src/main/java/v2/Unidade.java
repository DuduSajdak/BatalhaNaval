package v2;

import java.io.Serializable;

public abstract class Unidade implements Serializable {
    private static final long serialVersionUID = 4L;
    // define os atributos da Unidade (que representam cada local do mapa)
    private int X;//coluna  
    private int Y;//linha

    private int tamanho;//tamanho do mapa
    private boolean atingido;//local atingido

    public Unidade(int x, int y, int tam) { //metodo construtor
        X = x;
        Y = y;
        tamanho = tam;
        atingido =  false;
        
    }

    // getters e setters dos atributos
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    public boolean isAtingido(){
        return atingido;
    }

    public void setAtingido(boolean atingido){
        this.atingido = atingido;
    }
    
    public char desenha() {
        return isAtingido() ? '$' : 'O';
    }
}
