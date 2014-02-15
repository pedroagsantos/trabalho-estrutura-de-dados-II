package br.ufrrj.im.cc.ed2.consulta.ConsultaNomeMatricula;

import br.ufrrj.im.cc.ed2.join.base.ColunaTupla;
import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.HashJoin;
import br.ufrrj.im.cc.ed2.juncao.NestedLoopJoin;

public class NomeMatriculaNestedLoopJoin2 implements Iterator {



	private Relacao relacao;
	private Selecao selecao;
	private NestedLoopJoin nestedloopjoin;
	Tupla tuplaResultante;

	public NomeMatriculaNestedLoopJoin2(String curso) {
	
		this.nestedloopjoin = new NestedLoopJoin("Curso", "id", "Alunos", "curso_id");
		this.selecao = new Selecao(nestedloopjoin, "nome_curso", curso);//"MATEMÁTICA"
		
	}

	@Override
	public Iterator open() {
		selecao.open();
		return null;
	}

	@Override
	public Iterator next() {
		// TODO Auto-generated method stub
		Tupla tupla;

		ColunaTupla colunaA;
		ColunaTupla colunaB;

		while ((tupla = (Tupla) selecao.next()) != null) {
			
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
		selecao.close();
		return null;
	}

	@Override
	public long calculaCusto() {
		// TODO Auto-generated method stub
		return 0;
	}

}



