package br.ufrrj.im.cc.ed2.consulta.ConsultaNomeMatricula;


import br.ufrrj.im.cc.ed2.join.base.ColunaTupla;
import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.HashJoin;

public class NomeMatriculaHashJoin2 implements Iterator{

	private Relacao relacao;
	private Selecao selecao;
	private HashJoin hashjoin;
	Tupla tuplaResultante;


	public  NomeMatriculaHashJoin2(String curso) {
		
		//Plano B
		/*HashJoin relation = new HashJoin("Cursos", "id", "Alunos", "curso_id");
		Selecao relation2 = new Selecao(relation, "nome_curso", "MATEMÁTICA");
		Projecao relation1 = new Projecao(relation2, "nome");*/
		
		this.hashjoin = new HashJoin("Curso", "id", "Alunos", "curso_id");
		this.selecao = new Selecao(hashjoin, "nome_curso", curso );//"MATEMÁTICA"

	}
		@Override
		public Iterator open() {
			selecao.open();
			return this;
		}

	@Override
	public Iterator next() {
		Tupla tupla;

		ColunaTupla colunaA;
		ColunaTupla colunaB;

		while ((tupla = (Tupla) selecao.next()) != null) {

			if(tupla.getValorCampo("nome_curso") != null){

				tuplaResultante = new Tupla();
				
				colunaA = new ColunaTupla("nome", tupla.getValorCampo("nome"));
				colunaB = new ColunaTupla("matricula", tupla.getValorCampo("matricula"));
				
				tuplaResultante.adicionaColuna(colunaA);
				tuplaResultante.adicionaColuna(colunaB);
				//System.out.println(tuplaResultante);

				return tuplaResultante;

			}
		}
		return null;
	}

	@Override
	public Iterator close() {
		selecao.close();
		return null;
	}


	@Override
	public long calculaCusto() {
		
		 return   hashjoin.calculaCusto() + selecao.calculaCusto() ;
	}

}

