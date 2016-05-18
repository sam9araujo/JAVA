package br.com.sam9araujo.projetojava.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sam9araujo.projetojava.exceptions.DataAccessException;
import br.com.sam9araujo.projetojava.util.Conexao;
import br.com.sam9araujo.projetojava.vo.ConsultarTotalVo;


public class ConsultarDao {
	
	private Connection connection;
	private ResultSet resultSet;	

	public ConsultarDao(Connection pConnection) {
		this.connection = pConnection;
	}
	
	public List<ConsultarTotalVo> getConsultarTotalizadorVO(String ID) throws DataAccessException {
		final String sqlTotalizador   = "SELECT * FROM WHERE Comercio = " + ID ;
				  
		PreparedStatement callableStatement = null;
		ConsultarTotalVo consultarVO = null;
		ArrayList<ConsultarTotalVo> lista = null;
		try {
			callableStatement = connection.prepareStatement(sqlTotalizador);
						
			ResultSet resultSet = callableStatement.executeQuery();
			
			lista = new ArrayList<ConsultarTotalVo>();
			while (resultSet.next()) {
				consultarVO = new ConsultarTotalVo();
					
				consultarVO.setId(resultSet.getString(1));			
				consultarVO.setTotal(resultSet.getString(2));
				consultarVO.setValor(resultSet.getString(3));
				
				
				lista.add(consultarVO);
				
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Error during process.", e);
		} finally {
			Conexao.close(callableStatement, resultSet);
		}
	}
	
}
