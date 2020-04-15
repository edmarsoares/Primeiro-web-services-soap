package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name = "AutoricaoFault", messageName = "AutorizacaoFault")
public class AutorizacaoExcveption extends Exception {
	
	public AutorizacaoExcveption(String mensagem) {
		super(mensagem);
	}
	
	public FaultInfo getFaultInfo() {
		return new FaultInfo("Token inválido", new Date());
	}
}
