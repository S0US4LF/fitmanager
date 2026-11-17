
package br.com.fitmanager.dao;

import br.com.fitmanager.database.Database;
import br.com.fitmanager.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static void cadastrar(Aluno aluno) throws SQLException {

        String sql = """
            INSERT INTO alunos
            (nome, cpf, data_nascimento, telefone, email, endereco)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, aluno.getNome());
            comando.setString(2, aluno.getCpf());
            comando.setString(3, aluno.getDataNascimento());
            comando.setString(4, aluno.getTelefone());
            comando.setString(5, aluno.getEmail());
            comando.setString(6, aluno.getEndereco());

            comando.executeUpdate();
        }
    }

    public static List<Aluno> consultar(
            String filtroNome,
            String filtroCpf
    ) throws SQLException {

        StringBuilder sql = new StringBuilder("""
            SELECT id, nome, cpf, data_nascimento,
                   telefone, email, endereco
            FROM alunos
            """);

        boolean pesquisarNome =
            filtroNome != null && !filtroNome.isBlank();

        boolean pesquisarCpf =
            filtroCpf != null && !filtroCpf.isBlank();

        if (pesquisarNome && pesquisarCpf) {
            sql.append("""
                WHERE nome LIKE ? COLLATE NOCASE
                   OR cpf LIKE ?
                """);
        } else if (pesquisarNome) {
            sql.append("WHERE nome LIKE ? COLLATE NOCASE");
        } else if (pesquisarCpf) {
            sql.append("WHERE cpf LIKE ?");
        }

        sql.append(" ORDER BY nome COLLATE NOCASE");

        List<Aluno> alunos = new ArrayList<>();

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando =
                 conexao.prepareStatement(sql.toString())) {

            int parametro = 1;

            if (pesquisarNome && pesquisarCpf) {
                comando.setString(1, "%" + filtroNome + "%");
                comando.setString(2, "%" + filtroCpf + "%");

            } else if (pesquisarNome) {
                comando.setString(parametro, "%" + filtroNome + "%");

            } else if (pesquisarCpf) {
                comando.setString(parametro, "%" + filtroCpf + "%");
            }

            try (ResultSet resultado = comando.executeQuery()) {

                while (resultado.next()) {

                    Aluno aluno = new Aluno(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("data_nascimento"),
                        resultado.getString("telefone"),
                        resultado.getString("email"),
                        resultado.getString("endereco")
                    );

                    alunos.add(aluno);
                }
            }
        }

        return alunos;
    }

    public static boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conexao = Database.getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            return comando.executeUpdate() > 0;
        }
    }
}
