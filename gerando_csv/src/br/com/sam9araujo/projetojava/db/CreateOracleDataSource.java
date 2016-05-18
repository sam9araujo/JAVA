package br.com.sam9araujo.projetojava.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.hpe.cardsacquier.util.TripleDesCriptor;
import oracle.jdbc.pool.OracleDataSource;

public class CreateOracleDataSource {

	public static DataSource createJndiDataSource() {
		InitialContext jndiCntx;
		DataSource ds;
		try {

			jndiCntx = new InitialContext();

			ds = (DataSource) jndiCntx.lookup("java:/GPWS_DS");
			return ds;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static DataSource createPropertiesDataSource() {
		OracleDataSource dataSource = null;
		try {

			Properties prop = new Properties();
			try {
				prop.load(CreateOracleDataSource.class.getResourceAsStream("/app.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			String url = prop.getProperty("jdbc.url");
			String user = prop.getProperty("jdbc.user");
			String passwd = prop.getProperty("jdbc.passwd");
			String hashedPassword = prop.getProperty("jdbc.hashedpasswd");

			//System.out.println("DataSource url: " + url);
			dataSource = new OracleDataSource();
			dataSource.setURL(url);
			dataSource.setUser(user);

			if (hashedPassword != null) {
				try {
					dataSource.setPassword(TripleDesCriptor.decifrar(hashedPassword));
				} catch (Exception ex) {
					System.out.println("Failed to decrypt password");
					ex.printStackTrace();
				}
			} else {
				dataSource.setPassword(passwd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataSource;
	}
}
