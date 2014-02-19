package br.ufrrj.im.cc.ed2.consulta.ConsultaNomeMatricula;

import br.ufrrj.im.cc.ed2.join.base.ColunaTupla;
import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.MergeJoin;

public class NomeMatriculaMergeJoin1 implements Iterator {


	private Relacao relacao;
	private Selecao selecao;
	private MergeJoin mergejoin;
	Tupla tuplaResultante;

	public NomeMatriculaMergeJoin1(String curso) {
		// TODO Auto-generated constructor stub

		this.relacao = new Relacao("Cursos");
		this.selecao = new Selecao("Cursos", "nome_curso", curso);
		this.mergejoin = new MergeJoin( "Alunos", "curso_id",selecao, "id");


	}

	@Override
	public Iterator open() {
		mergejoin.open();
		return null;
	}

	@Override
	public Iterator next() {
		// TODO Auto-generated method stub
		Tupla tupla;

		ColunaTupla colunaA;
		ColunaTupla colunaB;

		while ((tupla = (Tupla) mergejoin.next()) != null) {

			if(tupla.getValorCampo("nome_curso") != null){

				tuplaResultante = new Tupla();

				colunaA = new ColunaTupla("nome_curso", tupla.getValorCampo("nome_curso"));
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
	
		mergejoin.close();
		return null;
	}

	@Override
	public long calculaCusto() {
		
		return relacao.calculaCusto() + selecao.calculaCusto() + mergejoin.calculaCusto();
		
	}

}
