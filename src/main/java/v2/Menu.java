package v2;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    //classe menu para automatizar a interface do jogo
    public int exibeMenu() {
        // é exibido somente no inicio do jogo
        System.out.println("Batalha naval: ");

        System.out.println("menu:");
        System.out.println("1) Novo jogo");
        System.out.println("2) Salvar jogo");
        System.out.println("3) Carregar jogo");
        System.out.println("4) Fechar");

        System.out.print("Digite uma opcao: ");
        return scanner.nextInt();
    }
    
    public int exibeMenuPlayer(){
        // é exibido á cada rodada do jogador
        System.out.println("1) atirar");
        System.out.println("2) Salvar jogo");
        System.out.println("3) Carregar jogo");
        System.out.println("4) Sair");
        
        System.out.println("Digite uma opcao: ");
        return scanner.nextInt();
    }
}
