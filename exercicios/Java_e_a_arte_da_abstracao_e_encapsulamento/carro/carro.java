package exercicios.Java_e_a_arte_da_abstracao_e_encapsulamento.carro;

public class carro {
 private boolean ligado;
 private int velocidade;
 private int marcha;

    public carro(){
        this.ligado = false;
        this.velocidade = 0;
        this.marcha = 0;
    }
    public void ligarCarro(){
        if(!ligado){
            ligado = true;
            System.out.println("Carro ligado");
        }
        else{
            System.out.println("Carro já está ligado");
        } 
    }
    public void desligarCarro(){
        if(ligado){
            ligado = false;
            System.out.println("Carro desligado");
        }
        else{
            System.out.println("Carro já está desligado");
        }
    }
}