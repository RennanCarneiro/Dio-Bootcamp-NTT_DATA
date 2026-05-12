package exercicios.Estruturas_de_Controle_em_java;

import java.util.Scanner;

public class exercicio4 {
    /*
    Escreva um código onde o usuário informa um número inicial, posteriormente irá informar outros N números, 
    a execução do código irá continuar até que o número informado dividido pelo primeiro número tenha resto diferente de 0 na divisão, 
    números menores que o primeiro número devem ser ignorados
    */
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int divisor = sc.nextInt();
        int numero;
        
        do {
            System.out.print("Informe o próximo número: ");
            numero = sc.nextInt();

            if (numero < divisor) {
                System.out.println("Número menor que o inicial. Ignorado.");
                continue; 
            }

            if (numero % divisor != 0) {
                System.out.println("Resto diferente de zero. Encerrando.");
            }

        } while (numero % divisor == 0 || numero < divisor);

        sc.close();
    }
}
