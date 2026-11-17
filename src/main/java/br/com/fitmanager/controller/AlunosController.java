
package br.com.fitmanager.controller;

import br.com.fitmanager.model.Aluno;
import br.com.fitmanager.model.Usuario;
import br.com.fitmanager.service.AlunoService;
import br.com.fitmanager.util.ThemeManager;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunosController {

    private Usuario usuarioAtual;

    private final ObservableList<Aluno> listaAlunos =
        FXCollections.observableArrayList();

    @FXML private VBox formularioCadastro;

    @FXML private TextField campoNome;
    @FXML private TextField campoCpf;
    @FXML private DatePicker campoDataNascimento;
    @FXML private TextField campoTelefone;
    @FXML private TextField campoEmail;

    @FXML private TextField campoRua;
    @FXML private TextField campoNumero;
    @FXML private TextField campoBairro;
    @FXML private TextField campoCep;

    @FXML private TextField campoPesquisa;

    @FXML private TableView<Aluno> tabelaAlunos;

    @FXML private TableColumn<Aluno, String> colunaNome;
    @FXML private TableColumn<Aluno, String> colunaCpf;
    @FXML private TableColumn<Aluno, String> colunaNascimento;
    @FXML private TableColumn<Aluno, String> colunaTelefone;
    @FXML private TableColumn<Aluno, String> colunaEmail;
    @FXML private TableColumn<Aluno, String> colunaEndereco;

    @FXML private Label mensagemStatus;

    @FXML private Button botaoTema;
    @FXML private Button botaoVoltar;

    @FXML
    private void initialize() {

        colunaNome.setCellValueFactory(
            new PropertyValueFactory<>("nome")
        );

        colunaCpf.setCellValueFactory(dado ->
            new ReadOnlyStringWrapper(
                formatarCpf(dado.getValue().getCpf())
            )
        );

        colunaNascimento.setCellValueFactory(
            new PropertyValueFactory<>("dataNascimento")
        );

        colunaTelefone.setCellValueFactory(dado ->
            new ReadOnlyStringWrapper(
                formatarTelefone(dado.getValue().getTelefone())
            )
        );

        colunaEmail.setCellValueFactory(
            new PropertyValueFactory<>("email")
        );

        colunaEndereco.setCellValueFactory(dado ->
            new ReadOnlyStringWrapper(
                dado.getValue().getEndereco()
            )
        );

        tabelaAlunos.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );

        colunaEndereco.setCellFactory(coluna ->
            new TableCell<Aluno, String>() {

                @Override
                protected void updateItem(String endereco, boolean vazio) {

                    super.updateItem(endereco, vazio);

                    if (vazio || endereco == null || endereco.isBlank()) {
                        setText(null);
                        setTooltip(null);
                    } else {
                        setText(endereco);
                        setTooltip(new Tooltip(endereco));
                    }
                }
            }
        );

        tabelaAlunos.setItems(listaAlunos);

        configurarMascara(
            campoCpf,
            11,
            AlunosController::formatarCpf
        );

        configurarMascara(
            campoTelefone,
            11,
            AlunosController::formatarTelefone
        );

        configurarMascara(
            campoCep,
            8,
            AlunosController::formatarCep
        );

        configurarSomenteNumeros(campoNumero);

        atualizarTextoBotaoTema();
    }

    private void configurarMascara(
            TextField campo,
            int limite,
            java.util.function.Function<String, String> formatador
    ) {

        campo.textProperty().addListener((observavel, anterior, atual) -> {

            String numeros = atual.replaceAll("\\D", "");

            if (numeros.length() > limite) {
                numeros = numeros.substring(0, limite);
            }

            String formatado = formatador.apply(numeros);

            if (!formatado.equals(atual)) {
                campo.setText(formatado);
                campo.positionCaret(formatado.length());
            }
        });
    }

    private void configurarSomenteNumeros(TextField campo) {

        campo.textProperty().addListener((observavel, anterior, atual) -> {

            String numeros = atual.replaceAll("\\D", "");

            if (!numeros.equals(atual)) {
                campo.setText(numeros);
                campo.positionCaret(numeros.length());
            }
        });
    }

    private static String formatarCpf(String valor) {

        String numeros = valor == null
            ? ""
            : valor.replaceAll("\\D", "");

        if (numeros.length() > 9) {
            return numeros.replaceFirst(
                "(\\d{3})(\\d{3})(\\d{3})(\\d{1,2})",
                "$1.$2.$3-$4"
            );
        }

        if (numeros.length() > 6) {
            return numeros.replaceFirst(
                "(\\d{3})(\\d{3})(\\d{1,3})",
                "$1.$2.$3"
            );
        }

        if (numeros.length() > 3) {
            return numeros.replaceFirst(
                "(\\d{3})(\\d{1,3})",
                "$1.$2"
            );
        }

        return numeros;
    }

    private static String formatarTelefone(String valor) {

        String numeros = valor == null
            ? ""
            : valor.replaceAll("\\D", "");

        if (numeros.length() > 11) {
            numeros = numeros.substring(0, 11);
        }

        if (numeros.length() == 11) {
            return numeros.replaceFirst(
                "(\\d{2})(\\d{5})(\\d{4})",
                "($1) $2-$3"
            );
        }

        if (numeros.length() > 6) {
            return numeros.replaceFirst(
                "(\\d{2})(\\d{4})(\\d{1,4})",
                "($1) $2-$3"
            );
        }

        if (numeros.length() > 2) {
            return numeros.replaceFirst(
                "(\\d{2})(\\d+)",
                "($1) $2"
            );
        }

        if (!numeros.isEmpty()) {
            return "(" + numeros;
        }

        return "";
    }

    private static String formatarCep(String valor) {

        String numeros = valor == null
            ? ""
            : valor.replaceAll("\\D", "");

        if (numeros.length() > 5) {
            return numeros.replaceFirst(
                "(\\d{5})(\\d{1,3})",
                "$1-$2"
            );
        }

        return numeros;
    }

    public void setUsuario(Usuario usuario) {

        this.usuarioAtual = usuario;

        if (!podeConsultarAlunos()) {
            throw new SecurityException(
                "Você não possui permissão para consultar alunos."
            );
        }

        boolean podeCadastrar =
            "ATENDENTE".equals(usuarioAtual.getPerfil());

        formularioCadastro.setVisible(podeCadastrar);
        formularioCadastro.setManaged(podeCadastrar);

        carregarAlunos("");
    }

    private boolean podeConsultarAlunos() {

        return usuarioAtual != null &&
            ("ADMINISTRADOR".equals(usuarioAtual.getPerfil()) ||
             "ATENDENTE".equals(usuarioAtual.getPerfil()));
    }

    private boolean podeCadastrarAlunos() {

        return usuarioAtual != null &&
            "ATENDENTE".equals(usuarioAtual.getPerfil());
    }

    @FXML
    private void handleCadastrar() {

        if (!podeCadastrarAlunos()) {
            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Acesso negado",
                "Somente o Atendente pode cadastrar alunos."
            );
            return;
        }

        String nome = campoNome.getText();
        String cpf = campoCpf.getText();

        String dataNascimento =
            campoDataNascimento.getValue() == null
                ? null
                : campoDataNascimento.getValue().toString();

        String telefone = campoTelefone.getText();
        String email = campoEmail.getText();

        List<String> partesEndereco = new ArrayList<>();

        adicionarEndereco(partesEndereco, campoRua.getText());
        adicionarEndereco(partesEndereco, campoNumero.getText());
        adicionarEndereco(partesEndereco, campoBairro.getText());

        String cep = campoCep.getText();

        if (cep != null && !cep.isBlank()) {
            partesEndereco.add("CEP " + cep);
        }

        String endereco = partesEndereco.isEmpty()
            ? null
            : String.join(", ", partesEndereco);

        Aluno novoAluno = new Aluno(
            0,
            nome,
            cpf,
            dataNascimento,
            telefone,
            email,
            endereco
        );

        try {

            AlunoService.cadastrar(usuarioAtual, novoAluno);

            mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Cadastro realizado",
                "Aluno cadastrado com sucesso!"
            );

            limparFormulario();

            carregarAlunos(campoPesquisa.getText());

        } catch (IllegalArgumentException | SecurityException erro) {

            mostrarAlerta(
                Alert.AlertType.WARNING,
                "Atenção",
                erro.getMessage()
            );

        } catch (SQLException erro) {

            erro.printStackTrace();

            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro no banco de dados",
                "Não foi possível cadastrar o aluno."
            );
        }
    }

    private void adicionarEndereco(List<String> partes, String valor) {

        if (valor != null && !valor.isBlank()) {
            partes.add(valor.trim());
        }
    }

    @FXML
    private void handleExcluir() {

        Aluno alunoSelecionado =
            tabelaAlunos.getSelectionModel().getSelectedItem();

        if (alunoSelecionado == null) {

            mostrarAlerta(
                Alert.AlertType.WARNING,
                "Nenhum aluno selecionado",
                "Selecione um aluno na tabela antes de excluí-lo."
            );

            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacao.setTitle("Confirmar exclusão");
        confirmacao.setHeaderText("Excluir aluno?");
        confirmacao.setContentText(
            "Tem certeza de que deseja excluir o cadastro de "
            + alunoSelecionado.getNome()
            + "?\n\nEssa operação não poderá ser desfeita."
        );

        Optional<ButtonType> resposta = confirmacao.showAndWait();

        if (resposta.isEmpty() ||
            resposta.get() != ButtonType.OK) {
            return;
        }

        try {

            boolean excluido =
                AlunoService.excluir(usuarioAtual, alunoSelecionado);

            if (excluido) {

                mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Exclusão realizada",
                    "O cadastro do aluno foi excluído com sucesso."
                );

                carregarAlunos(campoPesquisa.getText());

            } else {

                mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Cadastro não encontrado",
                    "O aluno não foi encontrado no banco de dados."
                );

                carregarAlunos(campoPesquisa.getText());
            }

        } catch (IllegalArgumentException | SecurityException erro) {

            mostrarAlerta(
                Alert.AlertType.WARNING,
                "Atenção",
                erro.getMessage()
            );

        } catch (SQLException erro) {

            erro.printStackTrace();

            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro no banco de dados",
                "Não foi possível excluir o aluno."
            );
        }
    }

    @FXML
    private void handlePesquisar() {

        if (!podeConsultarAlunos()) {
            return;
        }

        carregarAlunos(campoPesquisa.getText());
    }

    @FXML
    private void handleLimparPesquisa() {

        if (!podeConsultarAlunos()) {
            return;
        }

        campoPesquisa.clear();
        carregarAlunos("");
    }

    private void carregarAlunos(String filtro) {

        if (!podeConsultarAlunos()) {
            return;
        }

        try {

            List<Aluno> encontrados =
                AlunoService.consultar(usuarioAtual, filtro);

            listaAlunos.setAll(encontrados);

            mensagemStatus.setText(
                encontrados.size() + " aluno(s) encontrado(s)."
            );

        } catch (SQLException | SecurityException erro) {

            erro.printStackTrace();

            listaAlunos.clear();

            mensagemStatus.setText(
                "Não foi possível carregar os alunos."
            );

            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro na consulta",
                "Ocorreu um problema ao consultar os alunos. "
                + "Verifique o console para mais detalhes."
            );
        }
    }

    private void limparFormulario() {

        campoNome.clear();
        campoCpf.clear();
        campoDataNascimento.setValue(null);
        campoTelefone.clear();
        campoEmail.clear();

        campoRua.clear();
        campoNumero.clear();
        campoBairro.clear();
        campoCep.clear();

        campoNome.requestFocus();
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
    private void handleVoltar() {

        try {

            FXMLLoader carregador = new FXMLLoader(
                getClass().getResource("/br/com/fitmanager/view/dashboard.fxml")
            );

            Parent raiz = carregador.load();

            DashboardController controller = carregador.getController();

            controller.setUsuario(usuarioAtual);

            Scene cenaAtual = botaoVoltar.getScene();

            Stage janela = (Stage) cenaAtual.getWindow();

            cenaAtual.setRoot(raiz);

            janela.setTitle("FitManager - Painel de Controle");

        } catch (IOException erro) {

            erro.printStackTrace();

            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro",
                "Não foi possível retornar ao painel."
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
