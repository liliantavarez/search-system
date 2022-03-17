package model.entites;

public class Meliante {
	
	private String fisicas;
	private String cidade;
	private String uf;
	private String bairro;
	private String rua;
	private String numero;
	private String delitos;
	private String faccao;
	private String telefone;
	public Meliante() {
		super();
	}
	public Meliante(String fisicas, String cidade, String uf, String bairro, String rua, String numero, String delitos,
			String faccao, String telefone) {
		this.fisicas = fisicas;
		this.cidade = cidade;
		this.uf = uf;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.delitos = delitos;
		this.faccao = faccao;
		this.telefone = telefone;
	}
	
	public String getFisicas() {
		return fisicas;
	}
	
	public void setFisicas(String fisicas) {
		this.fisicas = fisicas;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getDelitos() {
		return delitos;
	}
	
	public void setDelitos(String delitos) {
		this.delitos = delitos;
	}
	
	public String getFaccao() {
		return faccao;
	}
	
	public void setFaccao(String faccao) {
		this.faccao = faccao;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
