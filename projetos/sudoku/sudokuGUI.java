
import javax.swing.*;
import java.awt.*; 

public class sudokuGUI extends JFrame {

    private JTextField[][] campos = new JTextField[9][9];
    private int[][] tabuleiroInicial = new int[9][9];
    private boolean[][] eFixo = new boolean[9][9];

    public sudokuGUI(String[] args) {
        processarArgumentos(args);
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Bootcamp Sudoku - Projeto Final");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); 
    }

    private void inicializarComponentes() {
        JPanel painelTabuleiro = new JPanel(new GridLayout(9, 9));
        painelTabuleiro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                campos[i][j] = new JTextField();
                campos[i][j].setHorizontalAlignment(JTextField.CENTER);
                campos[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                
                int top = (i % 3 == 0) ? 2 : 1;
                int left = (j % 3 == 0) ? 2 : 1;
                int bottom = (i == 8) ? 2 : 1;
                int right = (j == 8) ? 2 : 1;
                campos[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                if (tabuleiroInicial[i][j] != 0) {
                    campos[i][j].setText(String.valueOf(tabuleiroInicial[i][j]));
                    campos[i][j].setEditable(false);
                    campos[i][j].setBackground(new Color(220, 220, 220)); 
                    eFixo[i][j] = true;
                }
                painelTabuleiro.add(campos[i][j]);
            }
        }

        JPanel painelMenu = new JPanel(new GridLayout(7, 1, 5, 5));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnNovo = new JButton("1. Iniciar/Reiniciar");
        JButton btnVerificar = new JButton("4. Verificar Jogo");
        JButton btnStatus = new JButton("5. Status do Jogo");
        JButton btnLimpar = new JButton("6. Limpar Usuário");
        JButton btnFinalizar = new JButton("7. Finalizar Jogo");

        btnNovo.addActionListener(e -> reiniciarJogo());
        btnVerificar.addActionListener(e -> visualizarSituacao());
        btnStatus.addActionListener(e -> mostrarStatus());
        btnLimpar.addActionListener(e -> limparCamposUsuario());
        btnFinalizar.addActionListener(e -> finalizar());

        painelMenu.add(new JLabel("MENU INTERATIVO", JLabel.CENTER));
        painelMenu.add(btnNovo);
        painelMenu.add(btnVerificar);
        painelMenu.add(btnStatus);
        painelMenu.add(btnLimpar);
        painelMenu.add(btnFinalizar);

        add(painelTabuleiro, BorderLayout.CENTER);
        add(painelMenu, BorderLayout.EAST);
    }

    private void processarArgumentos(String[] args) {
        for (String arg : args) {
            try {
                String[] partes = arg.split(",");
                int l = Integer.parseInt(partes[0]);
                int c = Integer.parseInt(partes[1]);
                int v = Integer.parseInt(partes[2]);
                if (l >= 0 && l < 9 && c >= 0 && c < 9 && v >= 1 && v <= 9) {
                    tabuleiroInicial[l][c] = v;
                }
            } catch (Exception e) { 
            }
        }
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!eFixo[i][j]) {
                    campos[i][j].setText("");
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Jogo reiniciado! Apenas os números iniciais foram mantidos.");
    }

    private void limparCamposUsuario() {
        reiniciarJogo(); 
    }

    private void visualizarSituacao() {
        if (regrasSudoku.contemErros(obterMatrizAtual())) {
            JOptionPane.showMessageDialog(this, "Atenção: O jogo contém erros (números conflitantes na linha, coluna ou bloco)!", "Verificação", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Tudo certo até agora! Não há números conflitantes.", "Verificação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void mostrarStatus() {
        boolean incompleto = false;
        int preenchidos = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (campos[i][j].getText().isEmpty()) {
                    incompleto = true;
                } else if (!eFixo[i][j]) {
                    preenchidos++;
                }
            }
        }
        String status = (preenchidos == 0) ? "Não Iniciado" : (incompleto ? "Incompleto" : "Completo");
        String erro = regrasSudoku.contemErros(obterMatrizAtual()) ? "Com Erros" : "Sem Erros";
        
        JOptionPane.showMessageDialog(this, "Status do preenchimento: " + status + "\nSituação atual: " + erro);
    }

    private void finalizar() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (campos[i][j].getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ação negada: Você deve preencher todos os espaços vazios primeiro!");
                    return;
                }
            }
        }
        if (regrasSudoku.contemErros(obterMatrizAtual())) {
            JOptionPane.showMessageDialog(this, "Jogo completo, mas contém ERROS. Corrija os números em conflito para finalizar.");
        } else {
            JOptionPane.showMessageDialog(this, "PARABÉNS! Você completou o Sudoku com sucesso!");
            System.exit(0);
        }
    }

    private int[][] obterMatrizAtual() {
        int[][] matrizAtual = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String texto = campos[i][j].getText();
                if (texto.matches("[1-9]")) {
                    matrizAtual[i][j] = Integer.parseInt(texto);
                } else {
                    matrizAtual[i][j] = 0;
                }
            }
        }
        return matrizAtual;
    }
}