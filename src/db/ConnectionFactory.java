package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {

		try {
		String nomeUsuario = "lilian";
		String senhaUsuario = "12345678";
		String enderecoServidor = "search-system.ch2i953gu7o2.us-east-1.rds.amazonaws.com";
		String nomeBanco = "SS";
		
			return DriverManager.getConnection("jdbc:mysql://"+enderecoServidor+"/"+nomeBanco, nomeUsuario, senhaUsuario);
		} catch (SQLException e) {
			System.out.println("Erro na conexão do banco de dados");
			throw new RuntimeException();
		}
	}

}
