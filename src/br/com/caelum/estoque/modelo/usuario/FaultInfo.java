package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class FaultInfo {

	private Date dataErro;
	private String mensagem;

	public FaultInfo(String mensagem, Date dataErro) {
	    this.mensagem = mensagem;
	    this.dataErro = dataErro;
	}

	// JAX-B precisa
	FaultInfo() {
	}
}
