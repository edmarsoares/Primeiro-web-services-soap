package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoExcveption;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class EstoqueWs {
	
	private ItemDao dao = new ItemDao();
//	
//	@WebMethod(operationName = "todosOsItens")
//	@WebResult(name = "itens")
//	@ResponseWrapper(localName = "itens")
//	public ListaItens getItens(){
//		
//		System.out.println("chamando getItens");
//		
//		List<Item> lista = dao.todosItens();
//		return new ListaItens(lista);
//	}
	
	@WebMethod(operationName="todosOsItens")
	@RequestWrapper(localName = "listaItens")
	@ResponseWrapper(localName="itens")
	@WebResult(name="item")
	public List<Item> getItens() { 

	    System.out.println("Chamando getItens()");
	    return dao.todosItens();

	}
	
	@WebMethod(operationName="cadastrarItem")
	@WebResult(name="item")
	public Item cadastrarItem(@WebParam(name = "token", header = true)
	                          TokenUsuario token, 
	                          @WebParam(name = "item") Item item) throws AutorizacaoExcveption {
		
		System.out.println("cadastrando item : " + item + " com Token" + token);
		
		boolean ehValido = new TokenDao().ehValido(token);
		
		if (!ehValido) {
			throw new AutorizacaoExcveption("Autorização falhou !");
		}
		
		new ItemValidador(item).validate();
		this.dao.cadastrar(item);
		
		return item;
	}
	
	@Oneway
	@WebMethod(operationName="GerarRelatorio")
	public void gerarRelatorio() { 
	    // código omitido
	}
	
//	@WebMethod(operationName="todosOsItens")
//    @WebResult(name="itens")
//    public ListaItens getItens(@WebParam(name = "filtros") Filtros filtros) { //cuidado, plural
//        System.out.println("Chamando getItens()");
//        List<Filtro> lista = filtros.getLista();
//        List<Item> itensResultado = dao.todosItens(lista);
//        return new ListaItens(itensResultado);
//    }
}
