package br.com.sam9araujo.projetojava.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

	public static Connection getConexao() {
		Connection conn = null;
		try {
			PropertiesConexao props = PropertiesConexao.getInstance();
			
			props.load();
			Class.forName(props.getProperty("jdbcDriver"));

			conn = DriverManager.getConnection(
					props.getProperty("urlConnection"),							
					props.getProperty("userDB"),
					props.getProperty("passwordDB"));
			
			System.out.println("Conectou");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro não achou o driver: " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao se conectar na base de dados: "
					+ e.getMessage());
		} 
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar conexao: " + e.getMessage());
		}
	}

	public static void close(Connection conn, PreparedStatement pstm) {
		try {
			if (pstm != null) {
				pstm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar um preparedStatement"
					+ e.getMessage());
		} finally {
			close(conn);
		}
	}
	
	public static void close(Connection conn, CallableStatement cs) {
		try {
			if (cs != null) {
				cs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar um CallableStatement"
					+ e.getMessage());
		} finally {
			close(conn);
		}
	}

	public static void close(Connection conn, PreparedStatement pstm,
			ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("Erro ao fechar um ResultSet: " + e.getMessage());
		} finally {
			close(conn, pstm);
		}
	}

	public static void close(PreparedStatement pstm, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("Erro ao fechar um ResultSet: " + e.getMessage());
		} finally {
			close(pstm);
		}
	}
	
	public static void close(CallableStatement cs, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("Erro ao fechar um ResultSet: " + e.getMessage());
		} finally {
			close(cs);
		}
	}
	
	public static void close(Connection conn,CallableStatement cs, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("Erro ao fechar um ResultSet: " + e.getMessage());
		} finally {
			close(conn,cs);
		}
	}
	
	public static void close(CallableStatement rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar um CallableStatement"
					+ e.getMessage());
		}
	}	

	public static void close(PreparedStatement pstm) {
		try {
			if (pstm != null) {
				pstm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar um PreparedStatement"
					+ e.getMessage());
		}
	}
}
