import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final String ARQUIVO_DADOS = "dados_jogo_memoria.json";
    private static final Scanner sc = new Scanner(System.in);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static BancoDados db = new BancoDados();

    public static void main(String[] args) {
        carregarDados();

        int opcao = 0;
        do {
            System.out.println("\n===== JOGO DA MEMÓRIA =====");
            System.out.println("1 - Criar uma coleção de cartas");
            System.out.println("2 - Iniciar um jogo");
            System.out.println("3 - Continuar um jogo");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> criarColecao();
                case 2 -> iniciarJogo();
                case 3 -> continuarJogo();
                case 4 -> {
                    salvarDados();
                    System.out.println("Dados salvos. Encerrando o jogo...");
                }
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private static void criarColecao() {
        System.out.print("\nNome da coleção: ");
        String nome = sc.nextLine();

        System.out.print("Quantidade total de cartas (deve ser um número PAR): ");
        int qtdCartas;
        try {
            qtdCartas = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido.");
            return;
        }

        if (qtdCartas % 2 != 0 || qtdCartas <= 0) {
            System.out.println("Erro: A quantidade de cartas deve ser par e maior que zero.");
            return;
        }

        Colecao colecao = new Colecao(nome, qtdCartas);
        db.colecoes.add(colecao);
        salvarDados();
        System.out.println("Coleção '" + nome + "' criada com sucesso!");
    }

    private static void iniciarJogo() {
        if (db.colecoes.isEmpty()) {
            System.out.println("Nenhuma coleção disponível. Crie uma primeiro.");
            return;
        }

        System.out.println("\n--- Coleções Disponíveis ---");
        for (int i = 0; i < db.colecoes.size(); i++) {
            System.out.println((i + 1) + ". " + db.colecoes.get(i).nome + " (" + db.colecoes.get(i).qtdCartas + " cartas)");
        }
        System.out.print("Escolha o número da coleção: ");
        
        try {
            int escolha = Integer.parseInt(sc.nextLine()) - 1;
            if (escolha >= 0 && escolha < db.colecoes.size()) {
                Colecao col = db.colecoes.get(escolha);
                System.out.print("Digite um nome para este " +
                        "jogo (para salvar): ");
                String nomeJogo = sc.nextLine();
                
                Jogo novoJogo = new Jogo(nomeJogo, col);
                db.jogosPausados.add(novoJogo);
                salvarDados();
                loopJogo(novoJogo);
            } else {
                System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void continuarJogo() {
        if (db.jogosPausados.isEmpty()) {
            System.out.println("Nenhum jogo pausado encontrado.");
            return;
        }

        System.out.println("\n--- Jogos Pausados ---");
        for (int i = 0; i < db.jogosPausados.size(); i++) {
            Jogo j = db.jogosPausados.get(i);
            System.out.println((i + 1) + ". " + j.nomeJogo + " (Coleção: " + j.colecaoOrigem + ") - Lances: " + j.lances);
        }
        System.out.print("Escolha o número do jogo para continuar: ");

        try {
            int escolha = Integer.parseInt(sc.nextLine()) - 1;
            if (escolha >= 0 && escolha < db.jogosPausados.size()) {
                Jogo jogoRetomado = db.jogosPausados.get(escolha);
                
                // Requisito Opcional 1 -> Embaralhar as cartas ao carregar o jogo
                Collections.shuffle(jogoRetomado.tabuleiro);
                System.out.println("\n[!] As cartas foram reembaralhadas conforme regra opcional!");
                
                loopJogo(jogoRetomado);
            } else {
                System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void loopJogo(Jogo jogo) {
        boolean jogando = true;

        while (jogando) {
            imprimirTabuleiro(jogo);

            if (jogo.paresEncontrados == jogo.tabuleiro.size() / 2) {
                System.out.println("\nPARABÉNS! Você completou o jogo em " + jogo.lances + " lances!");
                db.jogosPausados.remove(jogo); // Remove o jogo concluído
                salvarDados();
                break;
            }

            System.out.println("\n--- SUBMENU DO JOGO ---");
            System.out.println("1 - Virar 2 cartas");
            System.out.println("2 - Pausar Jogo");
            System.out.print("Escolha: ");
            
            String opcaoStr = sc.nextLine();
            
            if (opcaoStr.equals("2")) {
                salvarDados();
                System.out.println("Jogo pausado. Retornando ao menu principal...");
                jogando = false;
            } else if (opcaoStr.equals("1")) {
                virarCartas(jogo);
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private static void virarCartas(Jogo jogo) {
        try {
            System.out.print("Digite a posição da 1ª carta (1 a " + jogo.tabuleiro.size() + "): ");
            int pos1 = Integer.parseInt(sc.nextLine()) - 1;
            
            System.out.print("Digite a posição da 2ª carta (1 a " + jogo.tabuleiro.size() + "): ");
            int pos2 = Integer.parseInt(sc.nextLine()) - 1;

            if (pos1 < 0 || pos2 < 0 || pos1 >= jogo.tabuleiro.size() || pos2 >= jogo.tabuleiro.size() || pos1 == pos2) {
                System.out.println("Posições inválidas! Tente novamente.");
                return;
            }

            Carta c1 = jogo.tabuleiro.get(pos1);
            Carta c2 = jogo.tabuleiro.get(pos2);

            if (c1.encontrada || c2.encontrada) {
                System.out.println("Uma dessas cartas já foi encontrada! Escolha cartas viradas para baixo.");
                return;
            }

            // Exibir as cartas viradas
            System.out.println("\nCarta 1: [" + c1.conteudo + "]");
            System.out.println("Carta 2: [" + c2.conteudo + "]");

            jogo.lances++; // Conta o lance independente de acertar ou errar

            if (c1.conteudo.equals(c2.conteudo)) {
                System.out.println("-> Você encontrou um par!");
                c1.encontrada = true;
                c2.encontrada = true;
                jogo.paresEncontrados++;
            } else {
                System.out.println("-> Não formam um par.");
            }

            System.out.println("Pressione ENTER para continuar...");
            sc.nextLine();

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
        }
    }

    private static void imprimirTabuleiro(Jogo jogo) {
        System.out.println("\n=== Tabuleiro (" + jogo.nomeJogo + ") ===");
        System.out.println("Lances até agora: " + jogo.lances);
        
        for (int i = 0; i < jogo.tabuleiro.size(); i++) {
            Carta c = jogo.tabuleiro.get(i);
            if (c.encontrada) {
                System.out.print("  [ " + c.conteudo + " ]  ");
            } else {
                System.out.print("  [ X" + (i + 1) + " ]  "); 
            }
            
            if ((i + 1) % 4 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private static void carregarDados() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (arquivo.exists()) {
            try (FileReader reader = new FileReader(arquivo)) {
                db = gson.fromJson(reader, BancoDados.class);
                if (db == null) db = new BancoDados();
            } catch (IOException e) {
                System.out.println("Erro ao carregar os dados: " + e.getMessage());
            }
        }
    }

    private static void salvarDados() {
        try (FileWriter writer = new FileWriter(ARQUIVO_DADOS)) {
            gson.toJson(db, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    static class BancoDados {
        List<Colecao> colecoes = new ArrayList<>();
        List<Jogo> jogosPausados = new ArrayList<>(); // Requisito Opcional 2 -> N jogos pausados
    }

    static class Colecao {
        String nome;
        int qtdCartas;

        public Colecao(String nome, int qtdCartas) {
            this.nome = nome;
            this.qtdCartas = qtdCartas;
        }
    }

    static class Carta {
        String conteudo;
        boolean encontrada;

        public Carta(String conteudo) {
            this.conteudo = conteudo;
            this.encontrada = false;
        }
    }

    static class Jogo {
        String nomeJogo;
        String colecaoOrigem;
        List<Carta> tabuleiro;
        int lances;
        int paresEncontrados;

        public Jogo(String nomeJogo, Colecao colecao) {
            this.nomeJogo = nomeJogo;
            this.colecaoOrigem = colecao.nome;
            this.lances = 0;
            this.paresEncontrados = 0;
            this.tabuleiro = new ArrayList<>();

            int numPares = colecao.qtdCartas / 2;
            for (int i = 1; i <= numPares; i++) {
                tabuleiro.add(new Carta(String.valueOf(i))); // Carta A do par
                tabuleiro.add(new Carta(String.valueOf(i))); // Carta B do par
            }
            Collections.shuffle(tabuleiro); // Embaralha inicialmente
        }
    }
}