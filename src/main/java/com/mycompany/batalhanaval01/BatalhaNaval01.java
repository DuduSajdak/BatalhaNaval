package com.mycompany.batalhanaval01;

import java.util.Scanner;
import v2.BatalhaNaval;
import v2.Menu;

public class BatalhaNaval01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Batalha");

        Menu menu = new Menu();
        BatalhaNaval batalhaNaval = new BatalhaNaval();

        switch (menu.exibeMenu()) {
            case 1:
                batalhaNaval.posicionarNavios();
                batalhaNaval.posicionarNaviosInimigos();
                batalhaNaval.jogar();
                break;

            case 2:
                System.out.println("Digite o nome do arquivo para salvar o jogo: ");
                String nomeJogo = scanner.next(); // define o nome do arquivo a ser salvo
                batalhaNaval.salvar(nomeJogo); // salva o jogo
                break;

            case 3:
                System.out.println("Digite o nome do arquivo para carregar o jogo: ");
                String nomeJogoCarregar = scanner.next(); // define o nome do arquivo a ser carregado 
                batalhaNaval.carregar(nomeJogoCarregar); // carrega o jogo
                batalhaNaval.jogar(); // joga o jogo carregado 
                break;

            case 4:
                System.out.println("Até mais!"); // fecha programa
                break;
                
            default: 
                System.out.println("Tente novamente");
        }
    }
}

