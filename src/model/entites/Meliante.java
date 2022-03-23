package model.entites;

import java.io.InputStream;

public class Meliante {

	private String CPFMeliante;
	private String apelido;
	private String caracteristicasFisicas;
	private String delitos;
	private String faccao;
	private InputStream imagem;
	private String telefone;

	public Meliante() {

	}



	public Meliante(String cPFMeliante, String apelido, String caracteristicasFisicas, String delitos, String faccao,
			InputStream imagem, String telefone) {
		
		CPFMeliante = cPFMeliante;
		this.apelido = apelido;
		this.caracteristicasFisicas = caracteristicasFisicas;
		this.delitos = delitos;
		this.faccao = faccao;
		this.imagem = imagem;
		this.telefone = telefone;
	}



	public String getCPFMeliante() {
		return CPFMeliante;
	}

	public void setCPFMeliante(String cPFMeliante) {
		CPFMeliante = cPFMeliante;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getCaracteristicasFisicas() {
		return caracteristicasFisicas;
	}

	public void setCaracteristicasFisicas(String caracteristicasFisicas) {
		this.caracteristicasFisicas = caracteristicasFisicas;
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

	public InputStream getImagem() {
		return imagem;
	}

	public void setImagem(InputStream imagem) {
		this.imagem = imagem;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
