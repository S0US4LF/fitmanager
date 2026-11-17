
package br.com.fitmanager.controller;

import br.com.fitmanager.model.Usuario;
import br.com.fitmanager.util.ThemeManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    private Usuario usuarioAtual;

    @FXML
    private Label tituloPainel;

    @FXML
    private Label mensagemBoasVindas;

    @FXML
    private Label perfilUsuario;

    @FXML
    private Button botaoTema;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botaoAlunos;

    public void setUsuario(Usuario usuario) {

        this.usuarioAtual = usuario;

        String nomePerfil = switch (usuario.getPerfil()) {
            case "ADMINISTRADOR" -> "Administrador";
            case "ATENDENTE" -> "Atendente";
            case "INSTRUTOR" -> "Instrutor";
            case "ALUNO" -> "Aluno";
            default -> "Usuário";
        };

        tituloPainel.setText("Painel do " + nomePerfil);

        mensagemBoasVindas.setText(
            "Bem-vindo(a), " + usuario.getNome() + "!"
        );

        perfilUsuario.setText(
            "Perfil de acesso: " + nomePerfil
        );

        boolean podeConsultarAlunos =
            "ADMINISTRADOR".equals(usuario.getPerfil()) ||
            "ATENDENTE".equals(usuario.getPerfil());

        botaoAlunos.setVisible(podeConsultarAlunos);
        botaoAlunos.setManaged(podeConsultarAlunos);

        if ("ADMINISTRADOR".equals(usuario.getPerfil())) {
            botaoAlunos.setText("CONSULTAR ALUNOS");
        } else {
            botaoAlunos.setText("GERENCIAR ALUNOS");
        }
    }

    @FXML
    private void initialize() {
        atualizarTextoBotaoTema();
    }

    @FXML
    private void handleAlternarTema() {

        boolean novoTema = !ThemeManager.isDarkMode();

        ThemeManager.setDarkMode(novoTema);

        ThemeManager.applyTheme(
            botaoTema.getScene(),
            novoTema
        );

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
    private void handleAbrirAlunos() {

        if (usuarioAtual == null) {
            return;
        }

        try {

            FXMLLoader carregador = new FXMLLoader(
                getClass().getResource("/br/com/fitmanager/view/alunos.fxml")
            );

            Parent raiz = carregador.load();

            AlunosController controller = carregador.getController();

            controller.setUsuario(usuarioAtual);

            // Mantém a mesma Scene e preserva a tela cheia.
            Scene cenaAtual = botaoAlunos.getScene();

            Stage janela = (Stage) cenaAtual.getWindow();

            cenaAtual.setRoot(raiz);

            janela.setTitle("FitManager - Gerenciamento de Alunos");

        } catch (IOException erro) {

            erro.printStackTrace();

            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro",
                "Não foi possível abrir o gerenciamento de alunos."
            );
        }
    }

    @FXML
    private void handleSair() {

        try {

            FXMLLoader carregador = new FXMLLoader(
                getClass().getResource("/br/com/fitmanager/view/login.fxml")
            );

            Parent raiz = carregador.load();

            // Retorna ao login sem criar outra Scene.
            Scene cenaAtual = botaoSair.getScene();

            Stage janela = (Stage) cenaAtual.getWindow();

            cenaAtual.setRoot(raiz);

            janela.setTitle(
                "FitManager - Sistema de Gerenciamento de Academia"
            );

        } catch (IOException erro) {

            erro.printStackTrace();

            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro",
                "Não foi possível retornar à tela de login."
            );
        }
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensagem
    ) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.showAndWait();
    }
}
