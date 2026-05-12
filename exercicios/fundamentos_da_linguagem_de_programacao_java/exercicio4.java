package exercicios.fundamentos_da_linguagem_de_programacao_java;

public class exercicio4 {
    public static void diferencaIdade(String nome1, String nome2, int idade1, int idade2){
        int diferenca = Math.abs(idade1 - idade2);
        System.out.println("A diferenca de idade entre " + nome1 + " e " + nome2 + " são: " + diferenca);
    }
    public static void main(String[] args) {
        diferencaIdade("Rennan", "Pedro", 23, 27);
    }
}
