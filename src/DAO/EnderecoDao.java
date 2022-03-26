package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionFactory;
import model.entites.Endereco;

public class EnderecoDao {

	private Connection con;

	public EnderecoDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	public boolean add(Endereco e) {
		String sql = "INSERT INTO endereco ( cidade, estado, bairro, rua, numero) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, e.getCidade());
			pst.setString(2, e.getEstado());
			pst.setString(3, e.getBairro());
			pst.setString(4, e.getRua());
			pst.setString(5, e.getNumero());

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
		String sql = "UPDATE endereco SET cidade = ?, estado = ?, bairro = ?, rua = ?, numero = ? WHERE id = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, e.getCidade());
			pst.setString(2, e.getBairro());
			pst.setString(3, e.getRua());
			pst.setString(4, e.getEstado());
			pst.setString(5, e.getNumero());
			pst.setInt(6, e.getId());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean delete(Endereco e) {
		String sql = "DELETE FROM endereco WHERE id = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, e.getId());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Endereco buscaEndereco(Integer id) {
		Endereco endereco = new Endereco();
		String sql = "SELECT * FROM endereco where id = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Endereco e = new Endereco(rs.getInt("id"), rs.getString("cidade"), rs.getString("estado"),
						rs.getString("bairro"), rs.getString("rua"), rs.getString("numero"));
				endereco = e;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro, endereço não retornado.");
			return null;
		}
		return endereco;
	}

}
