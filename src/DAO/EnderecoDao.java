package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import model.entites.Endereco;

public class EnderecoDao {

	private Connection con;

	public EnderecoDao() {
		this.con = DB.getConnection();
	}

	public boolean add(Endereco e) {
		String sql = "INSERT INTO endereco ( CPFMeliante, cidade, bairro, rua, estado, numero) VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, e.getCPFMeliante());
			pst.setString(2, e.getCidade());
			pst.setString(3, e.getBairro());
			pst.setString(4, e.getRua());
			pst.setString(5, e.getEstado());
			pst.setString(6, e.getNumero());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean update(Endereco e) {
		String sql = "UPDATE endereco SET CPFMeliante = ?, " + "cidade = ? , " + "bairro = ?, "
				+ "rua = ?, " + "estado = ?, " + "numero = ?" + "telefone = ? WHERE CPFMeliante = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, e.getCPFMeliante());
			pst.setString(2, e.getCidade());
			pst.setString(3, e.getBairro());
			pst.setString(4, e.getRua());
			pst.setString(5, e.getEstado());
			pst.setString(6, e.getNumero());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean delete(Endereco m) {
		String sql = "DELETE FROM endereco WHERE CPFMeliante = ?";

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

}
