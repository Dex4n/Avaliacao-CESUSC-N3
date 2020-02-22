package transportadora.n3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Conexao {

	private static Connection conn = null;
	static final String JDBC_Driver = "org.postgresql.Driver";

	public static Connection getConnection() {
		if (conn == null) {
			try {

				String url = "jdbc:postgresql://localhost:5432/tapetemagicotransportadora";
				String usuario = "postgres";
				String senha = "ads";

				conn = DriverManager.getConnection(url, usuario, senha);
				JOptionPane.showMessageDialog(null, "Conexao realizada com sucesso!");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public PreparedStatement prepareStatement(String pst) {
		return null;
	}

}