package exercicios.fundamentos_da_linguagem_de_programacao_java;

public class exercicio1 {
    //Escreva um código que receba o nome e o ano de 
    //nascimento de alguém e imprima na tela a seguinte mensagem: "Olá 'Fulano' você tem 'X' anos"

    public static void mensagem(int nascimento, String nome){
        int idade = 2026 - nascimento;
        System.out.println("Olá " + nome + " você tem " + idade + " anos");
    }
    public static void main(String[] args) {
        mensagem(2003, "Rennan");
    }
}
