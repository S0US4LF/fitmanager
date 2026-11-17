
package br.com.fitmanager.model;

public class Aluno {

    private final int id;
    private final String nome;
    private final String cpf;
    private final String dataNascimento;
    private final String telefone;
    private final String email;
    private final String endereco;

    public Aluno(
            int id,
            String nome,
            String cpf,
            String dataNascimento,
            String telefone,
            String email,
            String endereco
    ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }
}
