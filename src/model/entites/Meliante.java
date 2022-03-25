package model.entites;

import java.io.InputStream;

public class Meliante {

	private Integer id;
	private String nome;
	private String apelido;
	private String CPFMeliante;
	private String caracteristicasFisicas;
	private String telefone;
	private InputStream imagem;
	private String delitos;
	private String faccao;

	public Meliante() {

	}

	public Meliante(Integer id, String nome, String apelido, String cPFMeliante, String caracteristicasFisicas,
			String telefone, InputStream imagem, String delitos, String faccao) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		CPFMeliante = cPFMeliante;
		this.caracteristicasFisicas = caracteristicasFisicas;
		this.telefone = telefone;
		this.imagem = imagem;
		this.delitos = delitos;
		this.faccao = faccao;
	}

	public Meliante(String nome, String apelido, String CPFMeliante, String caracteristicasFisicas, String telefone,
			InputStream imagem, String delitos, String faccao) {

		this.nome = nome;
		this.apelido = apelido;
		this.CPFMeliante = CPFMeliante;
		this.caracteristicasFisicas = caracteristicasFisicas;
		this.telefone = telefone;
		this.imagem = imagem;
		this.delitos = delitos;
		this.faccao = faccao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPFMeliante() {
		return CPFMeliante;
	}

	public void setCPFMeliante(String CPFMeliante) {
		this.CPFMeliante = CPFMeliante;
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
