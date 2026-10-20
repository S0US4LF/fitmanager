
package br.com.fitmanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label mensagem = new Label("FitManager - Sistema de Gerenciamento de Academia");

        StackPane layout = new StackPane(mensagem);
        Scene cena = new Scene(layout, 800, 500);

        stage.setTitle("FitManager");
        stage.setScene(cena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

