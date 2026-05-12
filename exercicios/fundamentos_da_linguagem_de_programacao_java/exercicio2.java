package exercicios.fundamentos_da_linguagem_de_programacao_java;

public class exercicio2 {
    /*
    Escreva um código que receba o tamanho do lado de um quadrado, calcule sua área e exiba na tela fórmula: área=lado X lado
     */
    public static int areaQuadrado(int lado){
        return lado * lado;
    }
    public static void main(String[] args) {
        System.out.println(areaQuadrado(5));
    }
}
