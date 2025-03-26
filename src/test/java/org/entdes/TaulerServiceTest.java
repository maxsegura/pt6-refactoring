package org.entdes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaulerServiceTest {

    private TaulerService tauler;

    @BeforeEach
    public void setUp() {
        tauler = new TaulerService();
    }

    @Test
    public void testInicialitzacio() {
        assertFalse(tauler.isGameOver(), "El joc no hauria d'estar acabat al iniciar");
        assertEquals("", tauler.getGuanyador(), "No hi hauria d'haver guanyador al iniciar");
    }

    @Test
    public void testJugadaBasica() {
        String resultat = tauler.tractarClicCasella(0, 0);
        assertEquals("X", resultat, "La primera jugada hauria de ser X");
        assertFalse(tauler.isGameOver(), "El joc no hauria d'acabar amb una sola jugada");

        resultat = tauler.tractarClicCasella(0, 1);
        assertEquals("O", resultat, "La segona jugada hauria de ser O");
    }

    @Test
    public void testVictoriaFila() {
        tauler.tractarClicCasella(0, 0); // X
        tauler.tractarClicCasella(1, 0); // O
        tauler.tractarClicCasella(0, 1); // X
        tauler.tractarClicCasella(1, 1); // O
        tauler.tractarClicCasella(0, 2); // X

        assertTrue(tauler.isGameOver(), "El joc hauria d'estar acabat");
        assertEquals("X", tauler.getGuanyador(), "X hauria de ser el guanyador");
    }

    @Test
    public void testVictoriaDiagonal() {
        tauler.tractarClicCasella(0, 0); // X
        tauler.tractarClicCasella(0, 1); // O
        tauler.tractarClicCasella(1, 1); // X
        tauler.tractarClicCasella(0, 2); // O
        tauler.tractarClicCasella(2, 2); // X

        assertTrue(tauler.isGameOver(), "El joc hauria d'estar acabat");
        assertEquals("X", tauler.getGuanyador(), "X hauria de ser el guanyador");
    }

    @Test
    public void testEmpat() {
        tauler.tractarClicCasella(0, 0); // X
        tauler.tractarClicCasella(0, 1); // O
        tauler.tractarClicCasella(0, 2); // X
        tauler.tractarClicCasella(1, 1); // O
        tauler.tractarClicCasella(1, 0); // X
        tauler.tractarClicCasella(1, 2); // O
        tauler.tractarClicCasella(2, 1); // X
        tauler.tractarClicCasella(2, 0); // O
        tauler.tractarClicCasella(2, 2); // X

        assertTrue(tauler.isGameOver(), "El joc hauria d'estar acabat");
        assertEquals("", tauler.getGuanyador(), "No hi hauria d'haver guanyador en un empat");
    }
}