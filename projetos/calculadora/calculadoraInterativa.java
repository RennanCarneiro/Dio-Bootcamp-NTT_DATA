package Dio;

import java.util.Scanner;

public class calculadoraInterativa extends operacoes {
    public static void main(String[] args) {
        int operador;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Esolha uma operação:");
            System.out.println("1- somar");
            System.out.println("2- subtração");
            System.out.println("3- multiplicação");
            System.out.println("4- divisão");
            System.out.println("5- potencia");
            System.out.println("6- sair");
            operador = sc.nextInt();
            if (operador == 6) {
                sair();
                break;
            }
            if (operador < 1 || operador > 6) {
                System.out.println("Operador inválido. Tente novamente.");
                continue; 
            }
            System.out.print("Digite o primeiro número: ");
            double numero1 = sc.nextDouble();
            System.out.print("Digite o segundo número: ");
            double numero2 = sc.nextDouble();
            switch (operador){
                case 1 -> somar(numero1, numero2, sc);
                case 2 -> subtrair(numero1, numero2, sc);
                case 3 -> multiplicar(numero1, numero2);
                case 4 -> dividir(numero1, numero2);
                case 5 -> elevar(numero1, numero2);
                case 6 -> sair();
                default -> {
                    System.out.println("Operador inválido");
                }
            };
        } while (operador!=6);
        
    }
}
