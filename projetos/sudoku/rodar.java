
import javax.swing.SwingUtilities;

public class rodar {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            sudokuGUI telaJogo = new sudokuGUI(args);
            telaJogo.setVisible(true);
        });
    }
}
