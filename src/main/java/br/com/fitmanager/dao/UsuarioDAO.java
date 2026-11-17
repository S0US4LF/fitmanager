
package br.com.fitmanager.dao;

import br.com.fitmanager.database.Database;
import br.com.fitmanager.model.Usuario;
import br.com.fitmanager.util.PasswordUtil;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public static void criarAdministradorInicial()
            throws SQLException, GeneralSecurityException {

        String consulta = "SELECT COUNT(*) FROM usuarios";

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando = conexao.prepareStatement(consulta);
             ResultSet resultado = comando.executeQuery()) {

            resultado.next();

            if (resultado.getInt(1) > 0) {
                System.out.println("Já existem usuários cadastrados.");
                return;
            }
        }

        String usuario = "admin";
        String senha = "Admin@123";
        String salt = PasswordUtil.gerarSalt();
        String hash = PasswordUtil.gerarHash(senha, salt);

        String sql = """
            INSERT INTO usuarios
            (nome, usuario, senha_hash, salt, perfil)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, "Administrador");
            comando.setString(2, usuario);
            comando.setString(3, hash);
            comando.setString(4, salt);
            comando.setString(5, "ADMINISTRADOR");

            comando.executeUpdate();

            System.out.println("Administrador inicial criado com sucesso!");
        }
    }

    // MÉTODO TEMPORÁRIO PARA CRIAR A CONTA DE TESTE
    public static void criarAtendenteTemporarioParaTeste()
            throws SQLException, GeneralSecurityException {

        String usuario = "atendente.teste";

        String consulta = """
            SELECT COUNT(*)
            FROM usuarios
            WHERE usuario = ?
            """;

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando = conexao.prepareStatement(consulta)) {

            comando.setString(1, usuario);

            try (ResultSet resultado = comando.executeQuery()) {

                resultado.next();

                if (resultado.getInt(1) > 0) {
                    System.out.println("Atendente de teste já existe.");
                    return;
                }
            }
        }

        String senha = "Atendente@123";
        String salt = PasswordUtil.gerarSalt();
        String hash = PasswordUtil.gerarHash(senha, salt);

        String sql = """
            INSERT INTO usuarios
            (nome, usuario, senha_hash, salt, perfil)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, "Atendente de Teste");
            comando.setString(2, usuario);
            comando.setString(3, hash);
            comando.setString(4, salt);
            comando.setString(5, "ATENDENTE");

            comando.executeUpdate();

            System.out.println("Atendente temporário criado com sucesso!");
        }
    }

    public static Usuario autenticar(String usuario, String senha)
            throws SQLException, GeneralSecurityException {

        String sql = """
            SELECT id, nome, usuario, senha_hash, salt, perfil, ativo
            FROM usuarios
            WHERE usuario = ?
            LIMIT 1
            """;

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario);

            try (ResultSet resultado = comando.executeQuery()) {

                if (!resultado.next()) {
                    return null;
                }

                if (resultado.getInt("ativo") != 1) {
                    return null;
                }

                String hashArmazenado =
                    resultado.getString("senha_hash");

                String salt =
                    resultado.getString("salt");

                boolean senhaValida = PasswordUtil.verificarSenha(
                    senha,
                    salt,
                    hashArmazenado
                );

                if (!senhaValida) {
                    return null;
                }

                return new Usuario(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("usuario"),
                    resultado.getString("perfil")
                );
            }
        }
    }
}
