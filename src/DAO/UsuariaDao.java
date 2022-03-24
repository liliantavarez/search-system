package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionFactory;
import model.entites.Usuario;

public class UsuariaDao {

	private Connection con;

	public UsuariaDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	public boolean add(Usuario u) {
		String sql = "INSERT INTO usuario ( nome, CPFUsuario, senha, email, fnivel) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, u.getNome());
			pst.setString(2, u.getCPFUsuario());
			pst.setString(3, u.getSenha());
			pst.setString(4, u.getEmail());
			pst.setString(5, u.getfNivel());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean update(Usuario u) {
		String sql = "UPDATE usuario SET nome = ?, CPFUsuario = ?, email = ? WHERE id = ?;";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, u.getNome());
			pst.setString(2, u.getCPFUsuario());
			pst.setString(3, u.getEmail());
			pst.setInt(4, u.getId());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete(Usuario u) {

		String sql = "DELETE FROM usuario WHERE CPFUsuario = ?";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, u.getCPFUsuario());

			pst.execute();

			pst.close();
			con.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Usuario> getList(String busca) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuario where CPFUsuario = ? or email = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, busca);
			pst.setString(2, busca);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Usuario u = new Usuario(rs.getString("nome"), rs.getString("CPFUsuario"), rs.getString("senha"),
						rs.getString("email"), rs.getString("fnivel"));
				usuarios.add(u);
			}

			pst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro, lista não retornada");
			return null;
		}

		return usuarios;
	}

}
