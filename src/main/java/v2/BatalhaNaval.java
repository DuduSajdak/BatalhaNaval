package v2;

import java.util.Scanner;

public class BatalhaNaval {// o jogo de batalha naval tem os mapas(representação = unidades + navios + tiros(no navio ou agua)
    private Mapas mapas;
    private Menu menu;

    public BatalhaNaval() {// metodo construtor de um novo jogo
        mapas = new Mapas(10);
        menu = new Menu();
        
    }

    public void jogar() { // codigo "principal" que roda o jogo
    Scanner scanner = new Scanner(System.in);
    System.out.println("Bem-vindo ao jogo Batalha Naval!");

    int opcaoMenu = menu.exibeMenuPlayer(); // utiliza o primeiro menu da classe Menu
    while (opcaoMenu != 4) {// mostra as opçoes do menu
        //opção 4 é "Sair"
        System.out.println("Seu mapa:");
        exibirMapa(mapas.getMapaPlayer().getMapaPlayer());// o primeiro metodo getMapaPlayer() pertence a classe Mapa ou seja: char[][]
        // e o segundo é da classe Mapas

        System.out.println("Mapa inimigo:");
        exibirMapa(mapas.getMapaInimigo().getMapaInimigo()); // mesma lógica de cima para o mapa inimigo

        switch (opcaoMenu) { //"opcaoMenu é o return do segundo menu da classe Menu(exibeMenuPlayer())
            
            case 1: // caso queira "atirar"
                System.out.println("Digite as coordenadas para atirar (ou -1 para sair):");
                System.out.print("Coordenada X(coluna) (de 0 à 9): ");
                int x = scanner.nextInt();// define coluna do tiro
                if (x == -1) {//sair
                    break;
                }

                System.out.print("Coordenada Y(linha) (de 0 à 9): ");
                int y = scanner.nextInt();// define linha do tiro
                if (y == -1) {//sair
                    break;
                }

                mapas.getMapaInimigo().tiro(x, y); // usa a localização do plano cartesiano
                tiroInimigo();// metodo do tiro inimigo 
                break;

            case 2: // caso queira salvar o jogo
                System.out.print("Digite o nome do arquivo para salvar o jogo: ");
                String nomeArquivoSalvar = scanner.next(); // define o nome do arquivo
                salvar(nomeArquivoSalvar);// salva o arquivo com o respectivo nome (filename)
                break;

            case 3: // caso queira carregar um jogo salvo
                System.out.print("Digite o nome do arquivo para carregar o jogo: ");
                String nomeArquivoCarregar = scanner.next();// define o nome do arquivo a ser carregado
                carregar(nomeArquivoCarregar);//caso o arquivo exista, carrega 
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }

        opcaoMenu = menu.exibeMenuPlayer(); // a cada rodada o menu.exibeMenuPlayer() será chamado 
    }

    System.out.println("Obrigado por jogar!");
}


    public void salvar(String filename) {
        EstadoJogo.salvar(mapas, filename);// salva mapa atual e nome
    }

    public void carregar(String filename) {// carrega arquivo pelo nome
        mapas = EstadoJogo.carregar(filename); //chama metodo da classe EstadoJogo
        mapas.getMapaPlayer().getMapaPlayer();//puxa os mapas do arquivo
        mapas.getMapaInimigo().getMapaInimigo();
    }

    
private void exibirMapa(char[][] mapa) { // metodo para exibir os mapas atualizados
    for (int y = 0; y < mapa.length; y++) {
        for (int x = 0; x < mapa[y].length; x++) {
            System.out.print(mapa[y][x] + " ");
        }
        System.out.println();
    }
    System.out.println();
}
    
public void posicionarNavios() { // metodo chamado na classe Main, para que o jogador possa posicionar seus navios 
    // na classe Mapa existe o metodo posicionarNavio() que realmente DEFINE a posição do navio
    // esse metodo é a "interface" de posicionamento do navio para o jogador
    Scanner scanner = new Scanner(System.in);

    System.out.println("Posicione seus navios.");

    for (int i = 0; i < 3; i++) { // posiciona 3 navios de tamanho 3x1 
        int tamanho = 3;
        System.out.printf("Posicione o %dº navio (1 - horizontal, 0 - vertical):\n", i + 1);

        int x, y, orientacao;
        while (true) {
            System.out.print("Digite a coordenada X(coluna) (de 0 à 9): ");
            x = scanner.nextInt();// coluna "base" do navio (a mais acima)

            System.out.print("Digite a coordenada Y(linha) (de 0 à 9): ");
            y = scanner.nextInt();// linha "base" do navio (a mais à esquerda)

            System.out.print("Digite a orientação (1 - horizontal, 0 - vertical): ");
            // verifica se o tamanho do navio será aplicado em X ou Y
            orientacao = scanner.nextInt();

            try {
                if ((orientacao == 0 && (y + tamanho > 10 || x < 0)) || (orientacao == 1 && (x + tamanho > 10 || y < 0))) {
                    throw new RuntimeException("Posição está fora dos limites do mapa [10][10].");
                }

                for (int j = 0; j < tamanho; j++) {
                    if (orientacao == 0) {
                        if (mapas.getMapaPlayer().isNavioNaPosicao(x, y + j)) {
                            throw new RuntimeException("Já existe um navio ocupando esta posição: [" + x + "][" + (y + j) + "]");
                        }
                    } else {
                        if (mapas.getMapaPlayer().isNavioNaPosicao(x + j, y)) {
                            throw new RuntimeException("Já existe um navio ocupando esta posição: [" + (x + j) + "][" + y + "]");
                        }
                    }
                }

                mapas.getMapaPlayer().posicionarNavio(x, y, tamanho, orientacao);
                mapas.setMapaPlayer(mapas.getMapaPlayer()); // setMapaPlayer define o mapaPlayer(classe Mapas) como a variavel mapaPlayer da classe Mapa
                //posiciona e define o mapa do jogador 
                break;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        exibirMapa(mapas.getMapaPlayer().getMapaPlayer());//  exibe o mapa do jogador, a cada navio posicionado sera mostrado seu mapa atualizado
    }
}



public void posicionarNaviosInimigos() { // usa o Random para posicionar navios inimigos
    for (int i = 0; i < 3; i++) {
        int tamanho = 3;

        int x, y, orientacao;
        while (true) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
            orientacao = (int) (Math.random() * 2);

            try {
                mapas.getMapaInimigo().posicionarNavio(x, y, tamanho, orientacao);
                break;
            } catch (RuntimeException e) {
                // Se a posição já estiver ocupada, tente novamente
            }
        }
    }
}

public void tiroInimigo() { // usa também o Random para atirar
    int x, y; // coordenadas do tiro
    while (true) {
        x = (int) (Math.random() * 10);
        y = (int) (Math.random() * 10);

        if (mapas.getMapaPlayer().getTiroNaPosicao(x, y) == null) { // se ja houver atirado nessa posição
            //gera um novo numero aleatorio para que atire em alguma coordenada "nova"
            break;
        }
    }
    Unidade navio = mapas.getMapaPlayer().getNavioOnPosicao(x, y); // verifica se aquela coordenada esta ocupada com algum navio do jogador
        if (navio != null) {
        navio.isAtingido(); // define o navio como atingido
        navio.desenha(); // redefine o "simbolo" daquele local
        }
        mapas.getMapaPlayer().tiro(x, y); // caso o tiro acerte a agua
}
}



