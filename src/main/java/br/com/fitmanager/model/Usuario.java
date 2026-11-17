
package br.com.fitmanager.model;

public class Usuario {

    private final int id;
    private final String nome;
    private final String usuario;
    private final String perfil;

    public Usuario(int id, String nome, String usuario, String perfil) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPerfil() {
        return perfil;
    }
}
