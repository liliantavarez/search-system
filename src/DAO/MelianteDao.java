package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionFactory;
import javafx.scene.image.Image;
import model.entites.Meliante;

public class MelianteDao {

	private Connection con;
	private Image image;


	public MelianteDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	public boolean add(Meliante m) {
		String sql = "INSERT INTO meliante ( nome, apelido, CPFMeliante, caracteristicasFisicas, telefone, imagem, delitos, faccao) VALUES (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getNome());
			pst.setString(2, m.getApelido());
			pst.setString(3, m.getCPFMeliante());
			pst.setString(4, m.getCaracteristicasFisicas());
			pst.setString(5, m.getTelefone());
			pst.setBinaryStream(6, m.getImagem());
			pst.setString(7, m.getDelitos());
			pst.setString(8, m.getFaccao());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean update(Meliante m) {
		String sql = "UPDATE meliante SET nome = ?, apelido = ?, CPFMeliante = ?, caracteristicasFisicas = ?, telefone = ?, imagem = ?, delitos = ?, faccao = ? WHERE id = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getNome());
			pst.setString(2, m.getApelido());
			pst.setString(3, m.getCPFMeliante());
			pst.setString(4, m.getCaracteristicasFisicas());
			pst.setString(5, m.getTelefone());
			pst.setBinaryStream(6, m.getImagem());
			pst.setString(7, m.getDelitos());
			pst.setString(8, m.getFaccao());
			pst.setInt(9, m.getId());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete(Meliante m) {
		String sql = "DELETE FROM meliante WHERE id = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, m.getId());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public List<Meliante> getList(String busca) {
		List<Meliante> meliantes = new ArrayList<Meliante>();
		String sql = "SELECT * FROM meliante where nome = ? or apelido = ? or CPFMeliante = ? or telefone = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, busca);
			pst.setString(2, busca);
			pst.setString(3, busca);
			pst.setString(4, busca);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Meliante m = new Meliante(rs.getInt("id"),rs.getString("nome"),rs.getString("apelido"), rs.getString("CPFMeliante"),
						rs.getString("caracteristicasFisicas"), rs.getString("telefone"), rs.getBinaryStream("imagem"), rs.getString("delitos"), rs.getString("faccao"));
				meliantes.add(m);
			}

			pst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro, lista não retornada");
			return null;
		}

		return meliantes;
	}
	
	public Meliante buscaMeliante(String busca) {
		Meliante meliante = new Meliante();
		String sql = "SELECT * FROM meliante where nome = ? or apelido = ? or CPFMeliante = ? or telefone = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, busca);
			pst.setString(2, busca);
			pst.setString(3, busca);
			pst.setString(4, busca);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Meliante m = new Meliante(rs.getInt("id"),rs.getString("nome"),rs.getString("apelido"), rs.getString("CPFMeliante"),
						rs.getString("caracteristicasFisicas"), rs.getString("telefone"), rs.getBinaryStream("imagem"), rs.getString("delitos"), rs.getString("faccao"));
				meliante = m;
			}
			
			
			pst.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro, lista não retornada");
			return null;
		}
		
		return meliante;
	}
	
	public Image visualizar(Meliante m) {

		String sql = "select * from meliante where id = ?";
		
		try {
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, m.getId());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
							
				InputStream is = rs.getBinaryStream("imagem");
				OutputStream os = new FileOutputStream(new File("photo.jpg"));
				
				byte[] content = new byte[1024];
				int size = 0;
				
				while ((size = is.read(content)) != -1) {
					os.write(content, 0, size);
				}
				os.close();
				is.close();
				
				image = new Image("file:photo.jpg", true);
			}
			
			pst.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
