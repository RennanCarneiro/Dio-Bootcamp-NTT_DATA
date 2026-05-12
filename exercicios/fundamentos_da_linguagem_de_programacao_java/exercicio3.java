package exercicios.fundamentos_da_linguagem_de_programacao_java;

public class exercicio3 {
    /*
    Escreva um código que receba a base e a alturade um retângulo, calcule sua área e exiba na tela fórmula: área=base X altura
    */
   public static int areaRetangulo(int base, int altura){
    return base * altura;
   }
   public static void main(String[] args) {
    System.out.println(areaRetangulo(15, 5));
   }
}
