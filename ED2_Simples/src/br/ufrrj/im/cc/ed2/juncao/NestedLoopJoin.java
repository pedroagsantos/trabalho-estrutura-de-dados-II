package br.ufrrj.im.cc.ed2.juncao;

import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;


public class NestedLoopJoin implements Iterator {


	private Relacao relacaoIn;
	private Iterator relacaoOut;
	private String campoRelacao1;
	private String campoRelacao2;
	Tupla tuplaOut = null;
	Tupla tuplaIn;



	public NestedLoopJoin(String relacao1, String campoRelacao1, String relacao2, String campoRelacao2) {

		this.relacaoIn = new Relacao(relacao1);
		this.relacaoOut = new Relacao(relacao2);
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;

	}

	public NestedLoopJoin(String relacao1, String campoRelacao1, Selecao selecao ,String campoRelacao2) {

		this.relacaoIn = new Relacao(relacao1);
		this.relacaoOut = selecao;
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}

	/*public NestedLoopJoin(Selecao selecao, String campoRelacao1, String relacao2 ,String campoRelacao2) {

		this.relacaoIn = selecao;
		this.relacaoOut = new Relacao(relacao2);
		this.campoRelacao1 = campoRelacao1;
		this.campoRelacao2 = campoRelacao2;
	}*/
	@Override
	public Iterator open() {

		relacaoIn.open();
		relacaoOut.open();

		return null;

	}
	@Override
	public Iterator next() {

		Tupla tuplaResultante = new Tupla(); 
		tuplaIn = new Tupla();

		String campo;

		if(tuplaOut == null){

			tuplaOut = (Tupla) relacaoOut.next();

		}
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
			relacaoIn.retornaLinha(0);
			campo = null;
		}
		while ((tuplaOut = (Tupla) relacaoOut.next()) != null);

		return null;

	}
	@Override
	public Iterator close() {

		relacaoIn.close();
		relacaoOut.close();

		return null;

	}
	@Override
	public long calculaCusto() {

		return relacaoIn.calculaCusto() * relacaoOut.calculaCusto();
	}


}