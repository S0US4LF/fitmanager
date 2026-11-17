
package br.com.fitmanager.service;

import br.com.fitmanager.dao.AlunoDAO;
import br.com.fitmanager.model.Aluno;
import br.com.fitmanager.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class AlunoService {

    public static void cadastrar(
            Usuario usuario,
            Aluno aluno
    ) throws SQLException {

        exigirPermissaoCadastro(usuario);

        if (aluno == null) {
            throw new IllegalArgumentException(
                "Os dados do aluno não foram informados."
            );
        }

        String nome = aluno.getNome() == null
            ? ""
            : aluno.getNome().trim();

        String cpf = aluno.getCpf() == null
            ? ""
            : aluno.getCpf().replaceAll("\\D", "");

        if (nome.isBlank()) {
            throw new IllegalArgumentException(
                "O nome do aluno é obrigatório."
            );
        }

        if (cpf.length() != 11) {
            throw new IllegalArgumentException(
                "O CPF deve possuir 11 números."
            );
        }

        String telefone = limparNumeros(aluno.getTelefone());

        if (telefone != null &&
            telefone.length() != 10 &&
            telefone.length() != 11) {

            throw new IllegalArgumentException(
                "O telefone deve possuir DDD e 10 ou 11 números."
            );
        }

        Aluno alunoValidado = new Aluno(
            aluno.getId(),
            nome,
            cpf,
            aluno.getDataNascimento(),
            telefone,
            limpar(aluno.getEmail()),
            limpar(aluno.getEndereco())
        );

        try {

            AlunoDAO.cadastrar(alunoValidado);

        } catch (SQLException erro) {

            String mensagem = erro.getMessage();

            if (mensagem != null &&
                mensagem.contains("UNIQUE constraint failed: alunos.cpf")) {

                throw new IllegalArgumentException(
                    "Já existe um aluno cadastrado com esse CPF."
                );
            }

            throw erro;
        }
    }

    public static List<Aluno> consultar(
            Usuario usuario,
            String filtro
    ) throws SQLException {

        exigirPermissaoConsulta(usuario);

        String texto = filtro == null ? "" : filtro.trim();

        String cpf = texto.replaceAll("\\D", "");

        String nome = texto;

        if (texto.isEmpty()) {
            nome = "";
            cpf = "";
        }

        return AlunoDAO.consultar(nome, cpf);
    }

    public static boolean excluir(
            Usuario usuario,
            Aluno aluno
    ) throws SQLException {

        exigirPermissaoExclusao(usuario);

        if (aluno == null || aluno.getId() <= 0) {
            throw new IllegalArgumentException(
                "Aluno inválido para exclusão."
            );
        }

        return AlunoDAO.excluir(aluno.getId());
    }

    private static void exigirPermissaoCadastro(Usuario usuario) {

        if (usuario == null ||
            !"ATENDENTE".equals(usuario.getPerfil())) {

            throw new SecurityException(
                "Somente o Atendente pode cadastrar alunos."
            );
        }
    }

    private static void exigirPermissaoConsulta(Usuario usuario) {

        if (usuario == null ||
            (!"ADMINISTRADOR".equals(usuario.getPerfil()) &&
             !"ATENDENTE".equals(usuario.getPerfil()))) {

            throw new SecurityException(
                "Você não possui permissão para consultar alunos."
            );
        }
    }

    private static void exigirPermissaoExclusao(Usuario usuario) {

        if (usuario == null ||
            !"ATENDENTE".equals(usuario.getPerfil())) {

            throw new SecurityException(
                "Somente o Atendente pode excluir alunos."
            );
        }
    }

    private static String limpar(String valor) {

        if (valor == null || valor.isBlank()) {
            return null;
        }

        return valor.trim();
    }

    private static String limparNumeros(String valor) {

        if (valor == null || valor.isBlank()) {
            return null;
        }

        return valor.replaceAll("\\D", "");
    }
}
