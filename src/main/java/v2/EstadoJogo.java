package v2;

import java.io.*; // 
public class EstadoJogo {// classe para salvar e carregar jogo
    public static void salvar(Mapas mapas, String filename) {// metodo salvar() salva o objeto Mapas e nome do arquivo
        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(mapas);

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mapas carregar(String filename) { // metodo carregar() carrega Mapas, mas somente com a busca pelo nome do arquivo (filename)
        try {
            FileInputStream fi = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fi);

            Mapas mapas = (Mapas) oi.readObject();

            oi.close();
            fi.close();

            return mapas;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
