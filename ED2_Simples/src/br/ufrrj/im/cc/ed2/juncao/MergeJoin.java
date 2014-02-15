package br.ufrrj.im.cc.ed2.juncao;

import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;


public class MergeJoin implements Iterator {

	private Relacao relacaoIn;
	private Iterator relacaoOut;
	private String campoRelacao1;
	private String campoRelacao2;

	Tupla tuplaIn;
	Tupla tuplaOut;

	public MergeJoin(String relacao1, String campoRelacao1, String relacao2, String campoRelacao2) {

		this.relacaoIn = new Relacao(relacao1);
		this.relacaoOut = new Relacao(relacao2);
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}
	
	public MergeJoin(String relacao1, String campoRelacao1, Selecao selecao ,String campoRelacao2) {

		this.relacaoIn = new Relacao(relacao1);
		this.relacaoOut = selecao;
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}

	@Override
	public Iterator open() {

		relacaoOut.open();
		relacaoIn.open();
		// chama o ordena

		return null;

	}

	@Override
	public Iterator next() {

		Tupla tuplaResultante = new Tupla();
		tuplaOut = new Tupla(); 
		tuplaIn = new Tupla();
		
		String campo;
		
	
		do {
			campo = tuplaOut.getValorCampo(campoRelacao2);
			
			while ((tuplaIn = (Tupla) relacaoIn.next()) != null) {
				
				tuplaResultante = new Tupla();
				
				if(tuplaIn.getValorCampo(campoRelacao1).equals(campo)){
					
					tuplaResultante.concatena(tuplaOut);
					tuplaResultante.concatena(tuplaIn);
					return (tuplaResultante);
				}
			}
			campo = null;
		} while ((tuplaOut = (Tupla) relacaoOut.next()) != null);
		
		return null;
	}

	@Override
	public Iterator close() {

		relacaoIn.close();
		relacaoOut.close();

		return null;
	}


	public long calculaCusto() {

		return relacaoIn.getNumeroLinhas() * 10;

	}


}
