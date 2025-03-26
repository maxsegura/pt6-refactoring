package org.entdes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TresEnRatlla extends Application {
    private TaulerService taulerService;
    private Button[][] caselles = new Button[3][3];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pantalla) {
        GridPane tauler = new GridPane();
        for (int i = 0; i < 3; i++) {
        
            for (int j = 0; j < 3; j++) {
                Button casella = new Button();
                casella.setMinSize(200, 200);
                final int col = j;
                final int fila = i;
                casella.setOnAction(e -> tractarClic(fila, col));
                casella.setStyle("-fx-font-size: 72px;");
                caselles[fila][col] = casella;
                tauler.add(caselles[fila][col], col, fila);
            }
        }
        Scene scene = new Scene(tauler, 600, 600);
        pantalla.setScene(scene);
        pantalla.setTitle("Tres en ratlla");
        taulerService = new TaulerService();
        pantalla.show();
    }

    private void tractarClic(int fila, final int col) {
        String jugada = taulerService.tractarClicCasella(fila, col);
        if (!jugada.isEmpty()) {
            caselles[fila][col].setText(jugada);
        }

        if (taulerService.isGameOver()) {

            if (taulerService.getGuanyador().isEmpty()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText(null);
                alert.setContentText("Empat");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText(null);
                alert.setContentText("Guanyador: " + taulerService.getGuanyador());
                alert.showAndWait();
            }
        }
    }
}