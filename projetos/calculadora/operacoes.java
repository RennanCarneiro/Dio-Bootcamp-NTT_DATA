package Dio;

import java.util.Scanner;

public class operacoes {
    
    // Recebendo o Scanner como parâmetro
    public static void somar(double number1, double number2, Scanner sc) {
        double resultado = number1 + number2;
        double number;
        int escolha;

        do {
            System.out.println("Resultado atual: " + resultado);
            System.out.println("Escolha 1 para continuar somando ou 2 para encerrar.");
            escolha = sc.nextInt();

            if (escolha == 1) {
                System.out.print("Digite o proximo numero: ");
                number = sc.nextDouble();
                resultado += number;
            }
        } while (escolha == 1);
        
        System.out.println("Resultado final da soma: " + resultado);
    }

    public static void subtrair(double number1, double number2, Scanner sc) {
        double resultado = number1 - number2;
        double number;
        int escolha;

        do {
            System.out.println("Resultado atual: " + resultado);
            System.out.println("Escolha 1 para continuar subtraindo ou 2 para encerrar.");
            escolha = sc.nextInt();

            if (escolha == 1) {
                System.out.print("Digite o proximo numero: ");
                number = sc.nextDouble();
                resultado -= number;
            }
        } while (escolha == 1);
        
        System.out.println("Resultado final da subtração: " + resultado);
    }

    public static void multiplicar(double number1, double number2) {
        double resultado = number1 * number2;
        System.out.println(number1 + " X " + number2 + " = " + resultado);
    }
    
    public static void dividir(double number1, double number2) {
        if (number2 == 0) {
            System.out.println("Operação inválida: Divisão por zero não é permitida.");
            return;
        }
        double resultado = number1 / number2;
        double resto = number1 % number2;
        System.out.println(number1 + " / " + number2 + " = " + resultado);
        System.out.println("Resto da divisão: " + resto);
    }

    public static void elevar(double number1, double number2) {
        System.out.println(number1 + " ^ " + number2 + " = " + Math.pow(number1, number2));
    }

    public static void sair() {
        System.out.println("Encerrando calculadora.");
    }
}