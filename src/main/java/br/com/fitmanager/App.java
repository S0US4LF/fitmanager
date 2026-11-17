
package br.com.fitmanager;

import br.com.fitmanager.database.Database;
import br.com.fitmanager.util.ThemeManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Inicializa o banco de dados
        Database.initializeDatabase();

        // Carrega a tela de login
        FXMLLoader carregador = new FXMLLoader(
            getClass().getResource("/br/com/fitmanager/view/login.fxml")
        );

        Parent raiz = carregador.load();

        Scene cena = new Scene(raiz, 1000, 650);

        // Aplica o último tema escolhido
        ThemeManager.applyTheme(cena, ThemeManager.isDarkMode());

        stage.setTitle("FitManager - Sistema de Gerenciamento de Academia");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(cena);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
