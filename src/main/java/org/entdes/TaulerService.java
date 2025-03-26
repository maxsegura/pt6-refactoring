package org.entdes;

public class TaulerService {

    private static final int MIDA_TAULER = 3;
    private static final String JUGADOR_X = "X";
    private static final String JUGADOR_O = "O";
    private static final String CASELLA_BUIDA = "";

    private String[][] caselles;
    private boolean esTornX = true;
    private boolean gameOver = false;
    private String guanyador = CASELLA_BUIDA;

    public TaulerService() {
        this.caselles = new String[][]{
            {CASELLA_BUIDA, CASELLA_BUIDA, CASELLA_BUIDA},
            {CASELLA_BUIDA, CASELLA_BUIDA, CASELLA_BUIDA},
            {CASELLA_BUIDA, CASELLA_BUIDA, CASELLA_BUIDA}
        };
    }

    public String tractarClicCasella(int fila, int columna) {
        if (!esJugadaValida(fila, columna)) {
            return caselles[fila][columna];
        }

        marcarCasella(fila, columna);
        alternarTorn();
        determinarGuanyador();
        comprovarEmpat();

        return caselles[fila][columna];
    }

    private boolean esJugadaValida(int fila, int columna) {
        return !gameOver && CASELLA_BUIDA.equals(caselles[fila][columna]); // Canvi aquí
    }

    private void marcarCasella(int fila, int columna) {
        caselles[fila][columna] = esTornX ? JUGADOR_X : JUGADOR_O;
    }

    private void alternarTorn() {
        esTornX = !esTornX;
    }

    private void determinarGuanyador() {
        guanyador = comprovarFiles();
        if (guanyador.isEmpty()) {
            guanyador = comprovarColumnes();
        }
        if (guanyador.isEmpty()) {
            guanyador = comprovarDiagonals();
        }
        if (!guanyador.isEmpty()) {
            gameOver = true;
        }
    }

    private String comprovarFiles() {
        for (int fila = 0; fila < MIDA_TAULER; fila++) {
            if (esLiniaGuanyadora(caselles[fila][0], caselles[fila][1], caselles[fila][2])) {
                return caselles[fila][0];
            }
        }
        return CASELLA_BUIDA;
    }

    private String comprovarColumnes() {
        for (int col = 0; col < MIDA_TAULER; col++) {
            if (esLiniaGuanyadora(caselles[0][col], caselles[1][col], caselles[2][col])) {
                return caselles[0][col];
            }
        }
        return CASELLA_BUIDA;
    }

    private String comprovarDiagonals() {
        if (esLiniaGuanyadora(caselles[0][0], caselles[1][1], caselles[2][2])) {
            return caselles[0][0];
        }
        if (esLiniaGuanyadora(caselles[2][0], caselles[1][1], caselles[0][2])) {
            return caselles[2][0];
        }
        return CASELLA_BUIDA;
    }

    private boolean esLiniaGuanyadora(String c1, String c2, String c3) {
        return !CASELLA_BUIDA.equals(c1) && c1.equals(c2) && c2.equals(c3); // Canvi aquí
    }

    private void comprovarEmpat() {
        if (!gameOver && taulerPle()) {
            gameOver = true;
        }
    }

    private boolean taulerPle() {
        for (int fila = 0; fila < MIDA_TAULER; fila++) {
            for (int col = 0; col < MIDA_TAULER; col++) {
                if (CASELLA_BUIDA.equals(caselles[fila][col])) { // Canvi aquí
                    return false;
                }
            }
        }
        return true;
    }

    public String getGuanyador() {
        return guanyador;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
