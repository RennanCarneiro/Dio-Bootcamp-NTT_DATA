package exercicios.Estruturas_de_Controle_em_java;
import java.util.Scanner;

public class exercicio1 {
    //Escreva um código onde o usuário entra com um número e seja gerada a tabuada de 1 até 10 desse número;
    public static void tabuada(int x){
        for(int i = 0; i <= 10; i++){
            int resultado = i * x;
            System.out.println(x + " X " + i + " = " + resultado);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero = sc.nextInt();
        sc.close();
        tabuada(numero);
    }
}
