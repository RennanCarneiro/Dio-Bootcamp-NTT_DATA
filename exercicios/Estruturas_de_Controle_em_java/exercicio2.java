package exercicios.Estruturas_de_Controle_em_java;

import java.util.Scanner;

public class exercicio2 {
    /*
    Escreva um código onde o usuário entra com sua altura e peso, seja feito o calculo do seu IMC(IMC = peso/(altura * altura)) 
    e seja exibida a mensagem de acordo com o resultado:

    Se for menor ou igual a 18,5 "Abaixo do peso";
    se for entre 18,6 e 24,9 "Peso ideal";
    Se for entre 25,0 e 29,9 "Levemente acima do peso";
    Se for entre 30,0 e 34,9 "Obesidade Grau I";
    Se for entre 35,0 e 39,9 "Obesidade Grau II (Severa)";
    Se for maior ou igual a 40,0 "Obesidade III (Mórbida)";
    */
   public static float imc(float altura, float peso){
    return peso/(altura*altura);
   }
   public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    float peso = sc.nextFloat();
    float altura = sc.nextFloat();
    sc.close();

    if (imc(altura, peso) <= 18.5) {
        System.out.println("Abaixo do peso");
    }
    else if (imc(altura, peso) >= 18.6 && imc(altura, peso) <= 24.9) {
        System.out.println("Peso Ideal");
    }
    else if (imc(altura, peso) >= 25 && imc(altura, peso) <= 29.9) {
        System.out.println("Levemente acima do peso");
    }
    else if (imc(altura, peso) >= 30 && imc(altura, peso) <= 34.9) {
        System.out.println("Obesidade Grau I");
    }
    else if (imc(altura, peso) >= 35 && imc(altura, peso) <= 39.9) {
        System.out.println("Obesidade Grau II");
    }
    else{
        System.out.println("Obesidade III (Mórbida)");
    }
   }
}
