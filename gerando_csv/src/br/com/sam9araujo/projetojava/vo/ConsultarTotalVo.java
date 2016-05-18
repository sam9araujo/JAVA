package br.com.sam9araujo.projetojava.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ConsultarTotalVo", namespace = "br.com.sam9araujo.projetojava.vo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultarTotalVo implements Serializable {
	
	private static final long serialVersionUID = -2878527749984184206L;
	
	@XmlElement(name = "id", namespace = "br.com.sam9araujo.projetojava.vo")
	private String id;

	@XmlElement(name = "total", namespace = "br.com.sam9araujo.projetojava.vo")
	private String total;
	
	@XmlElement(name = "valor", namespace = "br.com.sam9araujo.projetojava.vo")
	private String valor;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
