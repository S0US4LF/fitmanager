
package br.com.fitmanager.database;

import br.com.fitmanager.dao.UsuarioDAO;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:fitmanager.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {

        String sqlUsuarios = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                usuario TEXT NOT NULL UNIQUE,
                senha_hash TEXT NOT NULL,
                salt TEXT NOT NULL,
                perfil TEXT NOT NULL
                    CHECK (perfil IN (
                        'ADMINISTRADOR',
                        'ATENDENTE',
                        'INSTRUTOR',
                        'ALUNO'
                    )),
                ativo INTEGER NOT NULL DEFAULT 1,
                criado_em TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
            );
            """;

        String sqlAlunos = """
            CREATE TABLE IF NOT EXISTS alunos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                cpf TEXT NOT NULL UNIQUE,
                data_nascimento TEXT,
                telefone TEXT,
                email TEXT,
                endereco TEXT,
                criado_em TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
            );
            """;

        try (Connection conexao = getConnection();
             Statement comando = conexao.createStatement()) {

            comando.execute(sqlUsuarios);
            comando.execute(sqlAlunos);

            System.out.println("Banco de dados inicializado com sucesso!");

        } catch (SQLException erro) {

            System.err.println("Erro ao inicializar o banco de dados:");
            erro.printStackTrace();
            return;
        }

        try {

            UsuarioDAO.criarAdministradorInicial();

            // CHAMADA TEMPORÁRIA: REMOVER DEPOIS DO TESTE
            UsuarioDAO.criarAtendenteTemporarioParaTeste();

        } catch (SQLException | GeneralSecurityException erro) {

            System.err.println("Erro ao criar usuários iniciais:");
            erro.printStackTrace();
        }
    }
}
