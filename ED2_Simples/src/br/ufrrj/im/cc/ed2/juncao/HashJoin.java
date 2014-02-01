package br.ufrrj.im.cc.ed2.juncao;

import java.util.Hashtable;

import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;

public class HashJoin implements Iterator {

	private Iterator relacaoConstrucao;
	private Iterator relacaoPoda;
	private String campoRelacao2;
	private String campoRelacao1;
	private Hashtable<String, Tupla> tabela;
	
	
	// junta os dois arquivos 
	public HashJoin(String relacao1, String campoRelacao1, String relacao2, String campoRelacao2) {
		
		this.relacaoConstrucao = new Relacao(relacao1);
		this.relacaoPoda = new Relacao(relacao2);
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}
	
	// arquivo e uma seleçao
	public HashJoin(String relacao1, String campoRelacao1, Iterator selecao, String campoRelacao2){
		
		this.relacaoConstrucao = new Relacao(relacao1);
		this.relacaoPoda = selecao;
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}
	
	//  seleçao e arquivo 
	public HashJoin(Selecao selecao, String campoRelacao1, String relacao2, String campoRelacao2){
		
		this.relacaoConstrucao = selecao;
		this.relacaoPoda = new Relacao(relacao2);
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}
	
	// duas seleçoes
	public HashJoin(Iterator selecao, String campoRelacao1, Iterator relacao2, String campoRelacao2){
		
		this.relacaoConstrucao = selecao;
		this.relacaoPoda = relacao2;
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}
	@Override
	public Iterator open() {
		tabela = new Hashtable<>();
		relacaoConstrucao.open();
		Tupla tupla;
		while ((tupla = (Tupla) relacaoConstrucao.next()) != null) {
			String valor = tupla.getValorCampo(campoRelacao1);
			tabela.put(valor, tupla);
		}
		relacaoPoda.open();
		return this;
	}

	@Override
	public Iterator next() {
		Tupla tupla = (Tupla) relacaoPoda.next();
		if (tupla == null) {
			return null;
		}
		Tupla tuplaCorrespondente = tabela.get(tupla.getValorCampo(campoRelacao2));
		if(tuplaCorrespondente == null){
			return new Tupla();
		}
		else{
			Tupla tuplaResultante = new Tupla();
			tuplaResultante.concatena(tupla);
			tuplaResultante.concatena(tuplaCorrespondente);
			return tuplaResultante;
		}
	}

	@Override
	public Iterator close() {
		relacaoConstrucao.close();
		relacaoPoda.close();
		return this;
	}
	
	@Override
	public long calculaCusto() {
		return relacaoConstrucao.calculaCusto() + relacaoPoda.calculaCusto();
	}

}
