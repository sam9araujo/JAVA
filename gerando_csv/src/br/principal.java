package br;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.sql.DataSource;

import br.com.sam9araujo.projetojava.dao.ConsultarDao;
import br.com.sam9araujo.projetojava.db.CreateOracleDataSource;
import br.com.sam9araujo.projetojava.exceptions.DataAccessException;
import br.com.sam9araujo.projetojava.vo.ConsultarTotalVo;


public class principal {

	public static void main(String[] args) throws DataAccessException, SQLException, IOException {
		// TODO Auto-generated method stub
		try {

			if (args.length != 1 ) {
				System.out.println("Parametros Invalidos");
				System.exit(1);
			}
		Date date = new Date();
     	SimpleDateFormat formato = new SimpleDateFormat("YYYYMMdd");
     	SimpleDateFormat formatoHora = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
	    System.out.println("Start: " +  formatoHora.format(date));		
		DataSource dataSource = CreateOracleDataSource.createPropertiesDataSource();
		// Getting dynamic parameters.
		
		Connection connection = dataSource.getConnection();
		ConsultarDao consultarDao = new ConsultarDao(connection);
		List<ConsultarTotalVo> listTot = new ArrayList<ConsultarTotalVo>();
		
		String id = args[0];
				
		PrintWriter writer = new PrintWriter(new FileWriter("./csv/report_"+ formato.format(date)+".csv"));
		writer.println("FIELD1;FIELD2;FIELD3");
					
		 listTot = consultarDao.getConsultarTotalizadorVO(id);
		 double total = 0;
		 double totalGeral = 0;
		 if(listTot.size() > 0){
			 for(int i = 0; i < listTot.size(); i++){
				 writer.println(listTot.get(i).getId() + ";"
						 + listTot.get(i).getTotal()+ ";"
						 +listTot.get(i).getValor() + ";"
						 );
				 total = total + Integer.parseInt(listTot.get(i).getTotal());
				 totalGeral = totalGeral + Double.parseDouble(listTot.get(i).getValor());
			 }		 
		 writer.println("");
		 writer.println("Total :;" + total +";"+ totalGeral);
		 
			
		}else{
			writer.println("No records found.");
		}
							
		writer.flush();
		writer.close();
		date = new Date();
		System.out.println("End: " +  formatoHora.format(date));	
		
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	
	}

}
