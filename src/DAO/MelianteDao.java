package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.entites.Meliante;

public class MelianteDao {

	private Connection con;

	public MelianteDao() {
		this.con = DB.getConnection();
	}

	public boolean add(Meliante m) {
		String sql = "INSERT INTO meliante ( CPFMeliante, apelido, caracteristicasFisicas, delitos, faccao, imagem, telefone) VALUES (?,?,?,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getCPFMeliante());
			pst.setString(2, m.getApelido());
			pst.setString(3, m.getCaracteristicasFisicas());
			pst.setString(4, m.getDelitos());
			pst.setString(5, m.getFaccao());
			pst.setBytes(6, m.getImagem());
			pst.setString(7, m.getTelefone());

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
		String sql = "UPDATE meliante SET CPFMeliante = ?, " + "apelido =? , " + "caracteristicasFisicas = ?, "
				+ "delitos = ?, " + "faccao = ?, " + "imagem = ?, " + "telefone = ? WHERE CPFMeliante = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getCPFMeliante());
			pst.setString(2, m.getApelido());
			pst.setString(3, m.getCaracteristicasFisicas());
			pst.setString(4, m.getDelitos());
			pst.setString(5, m.getFaccao());
			pst.setBytes(6, m.getImagem());
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

	public List<Meliante> getList() {
		List<Meliante> Meliantes = new ArrayList<Meliante>();
		String sql = "SELECT * FROM meliante";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Meliante u = new Meliante(rs.getString("CPFMeliante"), rs.getString("apelido"),
						rs.getString("caracteristicasFisicas"), rs.getString("delitos"), rs.getString("faccao"),
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

}
