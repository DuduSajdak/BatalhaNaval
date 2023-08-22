package v2;

import java.io.Serializable;

public class Tiro extends Unidade implements Serializable  {

    private static final long serialVersionUID = 3L;

    // o tiro nada mais é que uma unidade atingida
    public Tiro(int x, int y) {
        super(x, y, 1);
    }

    @Override
    // caso acerte a agua:
    public char desenha() {
        return 'X';
    }    
}
