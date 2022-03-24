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

import db.DB;
import javafx.scene.image.Image;
import model.entites.Meliante;

public class MelianteDao {

	private Connection con;
	private Image image;


	public MelianteDao() {
		this.con = DB.getConnection();
	}

	public boolean add(Meliante m) {
		String sql = "INSERT INTO meliante ( nome, CPFMeliante, apelido, caracteristicasFisicas, delitos, faccao, imagem, telefone) VALUES (?,?,?,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getNome());
			pst.setString(2, m.getCPFMeliante());
			pst.setString(3, m.getApelido());
			pst.setString(4, m.getCaracteristicasFisicas());
			pst.setString(5, m.getDelitos());
			pst.setString(6, m.getFaccao());
			pst.setBinaryStream(7, m.getImagem());
			pst.setString(8, m.getTelefone());

			pst.execute();

			pst.close();
			//con.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean update(Meliante m) {
		String sql = "UPDATE meliante SET nome = ?, "
				+ "CPFMeliante = ?, " + "apelido = ?, " + "caracteristicasFisicas = ?, "
				+ "delitos = ?, " + "faccao = ?, " + "imagem = ?, " + "telefone = ? WHERE CPFMeliante = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getCPFMeliante());
			pst.setString(2, m.getApelido());
			pst.setString(3, m.getCaracteristicasFisicas());
			pst.setString(4, m.getDelitos());
			pst.setString(5, m.getFaccao());
			pst.setBinaryStream(6, m.getImagem());
			pst.setString(7, m.getTelefone());
			pst.setString(8, m.getCPFMeliante());

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
		String sql = "DELETE FROM meliante WHERE CPFMeliante = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getCPFMeliante());

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
		List<Meliante> Meliantes = new ArrayList<Meliante>();
		String sql = "SELECT * FROM meliante WHERE CPFMeliante = ? OR apelido = ? OR telefone = ? OR nome = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, busca);
			pst.setString(2, busca);
			pst.setString(3, busca);
			pst.setString(4, busca);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Meliante u = new Meliante(rs.getString("nome"),rs.getString("CPFMeliante"), rs.getString("apelido"),
						rs.getString("caracteristicasFisicas"), rs.getString("delitos"), rs.getString("faccao"), rs.getBinaryStream("imagem"),
						rs.getString("telefone"));
				Meliantes.add(u);
			}

			pst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro, lista não retornada");
			return null;
		}

		return Meliantes;
	}
	
	public Image visualizar(Meliante m) {

		String sql = "select * from meliante where CPFMeliante = ?";
		
		try {
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getCPFMeliante());
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
