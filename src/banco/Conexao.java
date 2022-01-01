/**
 * 
 */
package banco;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author leo_dias
 *
 */
public class Conexao {

	private static Connection conexao = null;

	public static Connection Conectar() {
		if (conexao == null) {
			try {
				Properties props = lerArquivo();
				String url = props.getProperty("dburl");
				conexao = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}
		return conexao;
	}

	public static void desconectar() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}
	}

	private static Properties lerArquivo() {
		try (FileInputStream fis = new FileInputStream("banco.properties")) {
			Properties props = new Properties();
			props.load(fis);
			return props;
		} catch (IOException e) {
			throw new BancoException(e.getMessage());
		}
	}

	public static void FechamentoStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}
	}

	public static void FechamentoResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new BancoException(e.getMessage());
			}
		}
	}

}
