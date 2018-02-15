package model;

public class Usuario {

    public Usuario(String login, String nome, String email, String senha) {
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontos = 0;
    }
    
    public Usuario(String login, String nome, String email, String senha, Integer pontos) {
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontos = pontos;
    }

	private String login = "";
	private String nome = "";
	private String email = "";
	private String senha = "";
	private Integer pontos = 0;

	public String getLogin() {
		return login;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Integer getPontos() {
		return pontos;
	}
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
}
