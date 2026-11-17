
# Diagrama de Classes — FitManager

Este diagrama representa a estrutura atual do sistema, incluindo as camadas de apresentação, serviço, acesso a dados, modelos e utilitários.

```mermaid
classDiagram
direction TB

class App {
    +start(Stage stage)
    +main(String[] args)
}

class Usuario {
    -int id
    -String nome
    -String usuario
    -String perfil
    +getId() int
    +getNome() String
    +getUsuario() String
    +getPerfil() String
}

class Aluno {
    -int id
    -String nome
    -String cpf
    -String dataNascimento
    -String telefone
    -String email
    -String endereco
    +getId() int
    +getNome() String
    +getCpf() String
    +getDataNascimento() String
    +getTelefone() String
    +getEmail() String
    +getEndereco() String
}

class LoginController {
    -TextField campoUsuario
    -PasswordField campoSenha
    -TextField campoSenhaVisivel
    -boolean senhaVisivel
    +initialize()
    +handleAlternarVisibilidadeSenha()
    +handleAlternarTema()
    +handleEntrar()
    -abrirPainel(Usuario usuario)
}

class DashboardController {
    -Usuario usuarioAtual
    +setUsuario(Usuario usuario)
    +handleAlternarTema()
    +handleAbrirAlunos()
    +handleSair()
}

class AlunosController {
    -Usuario usuarioAtual
    -ObservableList listaAlunos
    +initialize()
    +setUsuario(Usuario usuario)
    +handleCadastrar()
    +handleExcluir()
    +handlePesquisar()
    +handleLimparPesquisa()
    +handleAlternarTema()
    +handleVoltar()
    -carregarAlunos(String filtro)
    -limparFormulario()
}

class UsuarioDAO {
    +criarAdministradorInicial()
    +autenticar(String usuario, String senha) Usuario
}

class AlunoDAO {
    +cadastrar(Aluno aluno)
    +consultar(String filtroNome, String filtroCpf) List~Aluno~
    +excluir(int id) boolean
}

class AlunoService {
    +cadastrar(Usuario usuario, Aluno aluno)
    +consultar(Usuario usuario, String filtro) List~Aluno~
    +excluir(Usuario usuario, Aluno aluno) boolean
    -exigirPermissaoCadastro(Usuario usuario)
    -exigirPermissaoConsulta(Usuario usuario)
    -exigirPermissaoExclusao(Usuario usuario)
}

class Database {
    -String URL
    +getConnection() Connection
    +initializeDatabase()
}

class PasswordUtil {
    +gerarSalt() String
    +gerarHash(String senha, String salt) String
    +verificarSenha(String senha, String salt, String hash) boolean
}

class ThemeManager {
    -Preferences PREFERENCES
    -String DARK_MODE_KEY
    +isDarkMode() boolean
    +setDarkMode(boolean darkMode)
    +applyTheme(Scene scene, boolean darkMode)
}

App ..> Database : inicializa
App ..> ThemeManager : aplica tema

LoginController ..> UsuarioDAO : autentica
LoginController ..> Usuario : utiliza
LoginController ..> DashboardController : abre painel
LoginController ..> ThemeManager : alterna tema

DashboardController ..> Usuario : utiliza
DashboardController ..> AlunosController : abre módulo
DashboardController ..> ThemeManager : alterna tema

AlunosController ..> AlunoService : solicita operações
AlunosController ..> Aluno : manipula
AlunosController ..> Usuario : verifica perfil
AlunosController ..> DashboardController : retorna ao painel
AlunosController ..> ThemeManager : alterna tema

AlunoService ..> AlunoDAO : acessa dados
AlunoService ..> Aluno : valida dados
AlunoService ..> Usuario : verifica permissões

AlunoDAO ..> Database : obtém conexão
AlunoDAO ..> Aluno : persiste e consulta

UsuarioDAO ..> Database : obtém conexão
UsuarioDAO ..> Usuario : autentica usuário
UsuarioDAO ..> PasswordUtil : verifica senha

Database ..> UsuarioDAO : cria administrador inicial
