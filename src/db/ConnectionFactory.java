package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {

		try {
		String nomeUsuario = "root";
		String senhaUsuario = "1234567";
		String enderecoServidor = "localhost";
		String nomeBanco = "search-system";
		
			return DriverManager.getConnection("jdbc:mysql://"+enderecoServidor+"/"+nomeBanco, nomeUsuario, senhaUsuario);
		} catch (SQLException e) {
			System.out.println("Erro na conexão do banco de dados");
			throw new RuntimeException();
		}
	}

}
