package transportadora.n3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControleAcessoBD {

	static final String JDBC_Driver = "org.postgresql.Driver";
	static final String DATABASE_URL = "jdbc:postgresql://localhost/tapetemagicotransportadora";
	static final String DATABASE_USER = "postgres";
	static final String DATABASE_PASS = "ads";

	// Conecta na base de dados e habilita a possibilidade de executar comandos.
	public Statement getStatement() throws SQLException {
		Connection minhaConexao = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
		Statement meuStatement = minhaConexao.createStatement();
		return meuStatement;
	}

	// Retorna dados de uma consulta (linhas)
	public ResultSet getResultSet(String consulta) throws SQLException {
		ResultSet meuResultSet = getStatement().executeQuery(consulta);
		return meuResultSet;
	}

	// Não retorna linhas com dados
	public void runCommand(String comando) throws SQLException {
		getStatement().execute(comando);
	}
}