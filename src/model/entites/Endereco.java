package model.entites;

public class Endereco {
	
	private String CPFMeliante;
	private String cidade;
	private String bairro;
	private String rua;
	private String estado;
	private String numero;
	
	public Endereco() {
		
	}
	
	public Endereco(String cPFMeliante, String cidade, String bairro, String rua, String estado, String numero) {
		super();
		CPFMeliante = cPFMeliante;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.estado = estado;
		this.numero = numero;
	}



	public String getCPFMeliante() {
		return CPFMeliante;
	}

	public void setCPFMeliante(String cPFMeliante) {
		CPFMeliante = cPFMeliante;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
