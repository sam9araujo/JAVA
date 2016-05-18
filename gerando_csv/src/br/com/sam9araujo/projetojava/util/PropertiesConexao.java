package br.com.sam9araujo.projetojava.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConexao extends Properties {

	private static final long serialVersionUID = -7230233318673977012L;

	private static class Singleton {
		private static final PropertiesConexao thisInstance = new PropertiesConexao();
	}

	public static PropertiesConexao getInstance() {
		return PropertiesConexao.Singleton.thisInstance;
	}

	public void load() {
		try {
			InputStream inputStream = this.getClass().getResourceAsStream("/conexao.properties");
			load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro não acha o arquivo de propriedade de conexao.");
		}
	}
}
