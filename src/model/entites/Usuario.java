package model.entites;

public class Usuario {
	
	private String nome;
	private String usuario;
	private String senha;
	private String confSenha;
	private String email;
	private Integer tipousuario;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String usuario, String senha, String confSenha, String email, Integer tipousuario) {
		
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.confSenha = confSenha;
		this.email = email;
		this.tipousuario = tipousuario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	public Integer getTipousuario() {
		return tipousuario;
	}
	
	public void setTipousuario(Integer tipousuario) {
		this.tipousuario = tipousuario;
	}
	
	
}
