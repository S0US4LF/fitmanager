
package br.com.fitmanager.controller;

import br.com.fitmanager.dao.UsuarioDAO;
import br.com.fitmanager.model.Usuario;
import br.com.fitmanager.util.ThemeManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoSenhaVisivel;

    @FXML
    private Button botaoEntrar;

    @FXML
    private Button botaoTema;

    @FXML
    private Button botaoMostrarSenha;

    private boolean senhaVisivel = false;

    @FXML
    private void initialize() {

        // Os dois campos compartilham o mesmo texto.
        campoSenha.textProperty().bindBidirectional(
            campoSenhaVisivel.textProperty()
        );

        campoSenha.setVisible(true);
        campoSenha.setManaged(true);

        campoSenhaVisivel.setVisible(false);
        campoSenhaVisivel.setManaged(false);

        atualizarTextoBotaoTema();

        campoUsuario.requestFocus();
    }

    @FXML
    private void handleAlternarVisibilidadeSenha() {

        int posicaoCursor;

        if (senhaVisivel) {
            posicaoCursor = campoSenhaVisivel.getCaretPosition();
        } else {
            posicaoCursor = campoSenha.getCaretPosition();
        }

        senhaVisivel = !senhaVisivel;

        campoSenha.setVisible(!senhaVisivel);
        campoSenha.setManaged(!senhaVisivel);

        campoSenhaVisivel.setVisible(senhaVisivel);
        campoSenhaVisivel.setManaged(senhaVisivel);

        if (senhaVisivel) {

            botaoMostrarSenha.setText("◎");

            campoSenhaVisivel.requestFocus();

            campoSenhaVisivel.positionCaret(
                Math.min(posicaoCursor, campoSenhaVisivel.getText().length())
            );

        } else {

            botaoMostrarSenha.setText("◉");

            campoSenha.requestFocus();

            campoSenha.positionCaret(
                Math.min(posicaoCursor, campoSenha.getText().length())
            );
        }
    }

    @FXML
    private void handleAlternarTema() {

        boolean novoTema = !ThemeManager.isDarkMode();

        ThemeManager.setDarkMode(novoTema);

        Scene cena = botaoTema.getScene();

        ThemeManager.applyTheme(cena, novoTema);

        atualizarTextoBotaoTema();
    }

    private void atualizarTextoBotaoTema() {

        if (ThemeManager.isDarkMode()) {
            botaoTema.setText("☀ Modo claro");
        } else {
            botaoTema.setText("☾ Modo escuro");
        }
    }

    @FXML
    private void handleEntrar() {

        String usuario = campoUsuario.getText().trim();
        String senha = campoSenha.getText();

        if (usuario.isEmpty()) {

            mostrarAlerta(
                "Atenção",
                "Digite seu usuário."
            );

            campoUsuario.requestFocus();

            return;
        }

        if (senha == null || senha.isEmpty()) {

            mostrarAlerta(
                "Atenção",
                "Digite sua senha."
            );

            if (senhaVisivel) {
                campoSenhaVisivel.requestFocus();
            } else {
                campoSenha.requestFocus();
            }

            return;
        }

        try {

            Usuario usuarioAutenticado =
                UsuarioDAO.autenticar(usuario, senha);

            if (usuarioAutenticado == null) {

                mostrarAlerta(
                    "Acesso negado",
                    "Usuário ou senha inválidos, ou usuário inativo."
                );

                campoSenha.clear();

                if (senhaVisivel) {
                    campoSenhaVisivel.requestFocus();
                } else {
                    campoSenha.requestFocus();
                }

                return;
            }

            abrirPainel(usuarioAutenticado);

        } catch (SQLException | GeneralSecurityException | IOException erro) {

            erro.printStackTrace();

            mostrarAlerta(
                "Erro",
                "Não foi possível realizar o login."
            );
        }
    }

    private void abrirPainel(Usuario usuario) throws IOException {

        FXMLLoader carregador = new FXMLLoader(
            getClass().getResource("/br/com/fitmanager/view/dashboard.fxml")
        );

        Parent raiz = carregador.load();

        DashboardController controller = carregador.getController();

        controller.setUsuario(usuario);

        // Aproveita a mesma Scene, preservando a tela cheia.
        Scene cenaAtual = botaoEntrar.getScene();

        Stage janela = (Stage) cenaAtual.getWindow();

        cenaAtual.setRoot(raiz);

        janela.setTitle("FitManager - Painel de Controle");
    }

    private void mostrarAlerta(String titulo, String mensagem) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.showAndWait();
    }
}
