package br.ufrrj.im.cc.ed2.catalogo;

import java.util.LinkedList;
import java.util.List;

public class Catalogo {
	
	private static Catalogo instance;
	private List<ItemCatalogo> itens;
	private List<Coluna> colunasRelacaoLivro;
	private List<Coluna> colunasRelacaoCategoria;
	private List<Coluna> colunasRelacaoAutor;
	
//----------------------------------------------------	
	private Catalogo(){
		//os itens podem ser recuperados de um arquivo binario.
		itens = new LinkedList<>();
		colunasRelacaoLivro = new LinkedList<>();
		colunasRelacaoCategoria = new LinkedList<>();
		colunasRelacaoAutor = new LinkedList<>();

		criaColunasLivro("id", 0);
		criaColunasLivro("titulo", 1);
		criaColunasLivro("editora", 2);
		criaColunasLivro("id_categoria", 3);
		criaColunasLivro("id_autor", 4);
		 
		ItemCatalogo itemLivro = new ItemCatalogo("Livro", "livros.txt", colunasRelacaoLivro, 500001);
		itens.add(itemLivro);
		
		criaColunasCategoria("id", 0);
		criaColunasCategoria("descricao", 1);
		ItemCatalogo itemCategoria = new ItemCatalogo("Categoria", "categorias.txt", colunasRelacaoCategoria, 20);
		itens.add(itemCategoria);
		
		criaColunasAutor("id", 0);
		criaColunasAutor("nome", 1);
		ItemCatalogo itemAutor = new ItemCatalogo("Autor", "autores.txt", colunasRelacaoAutor, 100);
		itens.add(itemAutor);
		
	}

	private void criaColunasLivro(String nomeColuna, int ordem) {
		Coluna coluna = new Coluna(nomeColuna, "String", ordem);
		colunasRelacaoLivro.add(coluna);
	}
	
	private void criaColunasCategoria(String nomeColuna, int ordem) {
		Coluna coluna = new Coluna(nomeColuna, "String", ordem);
		colunasRelacaoCategoria.add(coluna);
	}
	
	private void criaColunasAutor(String nomeColuna, int ordem) {
		criaColunasAutor(nomeColuna, ordem, false);
	}
	
	private void criaColunasAutor(String nomeColuna, int ordem, boolean valoresUnicos) {
		Coluna coluna = new Coluna(nomeColuna, "String", ordem, null, valoresUnicos);
		colunasRelacaoAutor.add(coluna);
	}

	
	public static Catalogo getInstancia(){
		if(instance == null)
			instance = new Catalogo();
		return instance;
	}
//---------------------------------------------------

	public String recuperaNomeArquivo(String nomeRelacao) {
		for (ItemCatalogo item : itens) {
			if(item.comparaNomeRelacao(nomeRelacao))
				return item.getNomeArquivo();
		}
		return null;
	}

	public List<Coluna> recuperaColunas(String nomeRelacao) {
		for (ItemCatalogo item : itens) {
			if(item.comparaNomeRelacao(nomeRelacao))
				return item.getColunas();
		}
		return null;
	}

	public int recuperaNumeroLinhas(String nomeRelacao) {
		for (ItemCatalogo item : itens) {
			if(item.comparaNomeRelacao(nomeRelacao))
				return item.numeroLinhas();
		}
		return -1;
	}
	
	public int recuperaNumeroEstimadoDeLinhas(String nomeRelacao, String campo, String valorBuscado) {
		for (ItemCatalogo item : itens) {
			if(item.comparaNomeRelacao(nomeRelacao))
				return item.numeroEstimadoLinhas(campo, valorBuscado);
		}
		return -1;
	}


	public boolean campoComValorUnico(String nomeRelacao, String nomeCampo) {
		for (ItemCatalogo item : itens) {
			if(item.comparaNomeRelacao(nomeRelacao))
				return item.campoComValorUnico(nomeCampo);
		}
		return false;
	}
	
}