package model.entites;

public class Usuario {

	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String nome;
	private String CPFUsuario;
	private String senha;
	private String confSenha;
	private String email;
	private String fNivel;

	public Usuario() {

	}
	
	public Usuario(String nome, String CPFUsuario, String senha, String email, String fNivel) {
		this.nome = nome;
		this.CPFUsuario = CPFUsuario;
		this.senha = senha;
		this.email = email;
		this.fNivel = fNivel;
	}
	public Usuario(int id, String CPFUsuario, String nome, String email) {
		this.id = id;
		this.CPFUsuario = CPFUsuario;
		this.nome = nome;
		this.email = email;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPFUsuario() {
		return CPFUsuario;
	}

	public void setCPFUsuario(String CPFUsuario) {
		this.CPFUsuario = CPFUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfSenha() {
		return confSenha;
	}

	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfNivel() {
		return fNivel;
	}

	public void setfNivel(String fNivel) {
		this.fNivel = fNivel;
	}
	


}
