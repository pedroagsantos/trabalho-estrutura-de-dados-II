package br.ufrrj.im.cc.ed2.consulta.ConsultaDisciplina;

import br.ufrrj.im.cc.ed2.index.arvoreB.Index;
import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.HashJoin;

public class DisciplinaHashJoin1 implements Iterator {

	//arvore 
	private Relacao relacao;
	private Selecao selecao;
	private HashJoin hashjoin;
	Tupla tuplaResultante;
	Index index;


	public DisciplinaHashJoin1(String MatriculaAluno) {
		
		//index 
	}

	@Override
	public Iterator open() {

		return null;
	}

	@Override
	public Iterator next() {

		return null;
	}

	@Override
	public Iterator close() {
		hashjoin.close();
		return null;
	}




	@Override
	public long calculaCusto() {
		// TODO Auto-generated method stub
		return 0;
	}
}