
public class regrasSudoku {

    public static boolean contemErros(int[][] matriz) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valor = matriz[i][j];
                if (valor != 0 && !isValido(matriz, i, j, valor)) {
                    return true; 
                }
            }
        }
        return false;
    }

    private static boolean isValido(int[][] matriz, int linha, int col, int valor) {
        for (int i = 0; i < 9; i++) {
            if (i != col && matriz[linha][i] == valor) return false;
            if (i != linha && matriz[i][col] == valor) return false;
        }
        int inicioLinhaBloco = linha - (linha % 3);
        int inicioColunaBloco = col - (col % 3);
        for (int i = inicioLinhaBloco; i < inicioLinhaBloco + 3; i++) {
            for (int j = inicioColunaBloco; j < inicioColunaBloco + 3; j++) {
                if (i != linha && j != col && matriz[i][j] == valor) {
                    return false;
                }
            }
        }
        return true; 
    }
}