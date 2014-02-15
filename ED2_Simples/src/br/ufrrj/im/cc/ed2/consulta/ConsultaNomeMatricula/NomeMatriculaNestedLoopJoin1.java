package br.ufrrj.im.cc.ed2.consulta.ConsultaNomeMatricula;

import br.ufrrj.im.cc.ed2.join.base.ColunaTupla;
import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.NestedLoopJoin;

public class NomeMatriculaNestedLoopJoin1 implements Iterator {



	private Relacao relacao;
	private Selecao selecao;
	private NestedLoopJoin nestedloopjoin;
	Tupla tuplaResultante;

	public NomeMatriculaNestedLoopJoin1(String curso) {
		// TODO Auto-generated constructor stub

		this.relacao = new Relacao("Cursos");
		this.selecao = new Selecao("Cursos", "nome_curso", curso);
		this.nestedloopjoin = new NestedLoopJoin( "Alunos", "curso_id",selecao, "id");
		

	}

	@Override
	public Iterator open() {
		nestedloopjoin.open();
		return null;
	}

	@Override
	public Iterator next() {
		// TODO Auto-generated method stub
		Tupla tupla;

		ColunaTupla colunaA;
		ColunaTupla colunaB;

		while ((tupla = (Tupla) nestedloopjoin.next()) != null) {
			
			if(tupla.getValorCampo("nome_curso") != null){
				
				tuplaResultante = new Tupla();
				
				colunaA = new ColunaTupla("nome_curso", tupla.getValorCampo("nome_curso"));
				colunaB = new ColunaTupla("matricula", tupla.getValorCampo("matricula"));
				tuplaResultante.adicionaColuna(colunaA);
				tuplaResultante.adicionaColuna(colunaB);
				
				return tuplaResultante;

			}
		}
		return null;
	}

	@Override
	public Iterator close() {
		// TODO Auto-generated method stub
		nestedloopjoin.close();
		return null;
	}

	@Override
	public long calculaCusto() {
		// TODO Auto-generated method stub
		return 0;
	}

}



