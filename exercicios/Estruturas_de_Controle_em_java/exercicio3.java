package exercicios.Estruturas_de_Controle_em_java;
import java.util.Scanner;

public class exercicio3 {
    /*
    Escreva um código que o usuário entre com um primeiro número, um segundo número maior que o primeiro e escolhe entre a opção par e impar, 
    com isso o código deve informar todos os números pares ou ímpares (de acordo com a seleção inicial) no intervalo de números informados, 
    incluindo os números informados e em ordem decrescente;
    */
   public static void imprimirLista(int x, int y, int escolha) {
        if (y <= x) {
            System.out.println("O segundo número deve ser maior que o primeiro.");
            return;
        }

        System.out.println("Resultado:");
        for (int i = y; i >= x; i--) {
            if ((escolha == 1 && i % 2 == 0) || (escolha == 2 && i % 2 != 0)) {
                System.out.println(i);
            }
        }
    }
   public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Escolha: 1-Par ou 2-Ímpar");
    int escolha = sc.nextInt();
    System.out.println("Digite o primeiro número:");
    int numero1 = sc.nextInt();
    System.out.println("Digite o segundo número:");
    int numero2 = sc.nextInt();

    switch (escolha) {
        case 1:
            imprimirLista(numero1, numero2, escolha);
            break;
        case 2:
            imprimirLista(numero1, numero2, escolha);
            break;
        default:
            System.out.println("Escolha inválida");;
    }
    sc.close();
   }
}
