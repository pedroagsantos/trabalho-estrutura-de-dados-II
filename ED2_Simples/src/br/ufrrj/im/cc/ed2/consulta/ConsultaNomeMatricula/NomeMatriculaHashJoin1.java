package br.ufrrj.im.cc.ed2.consulta.ConsultaNomeMatricula;

import br.ufrrj.im.cc.ed2.join.base.ColunaTupla;
import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.HashJoin;

public class NomeMatriculaHashJoin1 implements Iterator{

	private Relacao relacao;
	private Selecao selecao;
	private HashJoin hashjoin;
	Tupla tuplaResultante;


	public  NomeMatriculaHashJoin1(String curso) {

		//Selecao  relation = new Selecao("Cursos", "nome_curso", "MATEMÁTICA");
		//HashJoin relation2 = new HashJoin(relation, "id", "Alunos", "curso_id");
		//HashJoin relation2 = new HashJoin(relation, "id", "Alunos", "curso_id");
		//Projecao relation1 = new Projecao(relation2, "nome");
		//Projecao relation1 = new Projecao(relation2, "nome", "matricula");

		this.relacao = new Relacao("Cursos");
		this.selecao = new Selecao("Cursos", "nome_curso", curso);
		this.hashjoin = new HashJoin(selecao, "id", "Alunos", "curso_id");

	}

	@Override
	public Iterator open() {
		hashjoin.open();
		return this;
	}

	@Override
	public Iterator next() {
		Tupla tupla;

		ColunaTupla colunaA;
		ColunaTupla colunaB;

		while ((tupla = (Tupla) hashjoin.next()) != null) {

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
		hashjoin.close();
		return null;
	}




	@Override
	public long calculaCusto() {
		// TODO Auto-generated method stub
		return 0;
	}

}



