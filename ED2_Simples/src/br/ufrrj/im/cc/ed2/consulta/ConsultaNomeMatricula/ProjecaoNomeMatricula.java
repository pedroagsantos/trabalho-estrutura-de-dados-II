package br.ufrrj.im.cc.ed2.consulta.ConsultaNomeMatricula;

import java.util.TreeMap;

import br.ufrrj.im.cc.ed2.join.base.Projecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;

public class ProjecaoNomeMatricula {

	NomeMatriculaHashJoin1 Hj1;
	NomeMatriculaHashJoin2 Hj2;
	NomeMatriculaMergeJoin1 Mj1;
	NomeMatriculaMergeJoin2 Mj2;
	NomeMatriculaNestedLoopJoin1 Nlj1;
	NomeMatriculaNestedLoopJoin2 Nlj2;

	private String curso ;

	public ProjecaoNomeMatricula(String curso){

		this.curso = curso;

		Hj1 = new NomeMatriculaHashJoin1 (curso);		
		Hj2 = new NomeMatriculaHashJoin2 (curso);
		Mj1 = new NomeMatriculaMergeJoin1 (curso);			
		Mj2 = new NomeMatriculaMergeJoin2 (curso);		
		Nlj1 = new 	NomeMatriculaNestedLoopJoin1 (curso);
		Nlj2 = new NomeMatriculaNestedLoopJoin2 (curso);
	}

	public String custoFinal(){

		TreeMap< Long , String> custosFinais = new TreeMap<>();

		custosFinais.put(Hj1.calculaCusto(),"NomeMatriculaHashJoin1");
		//custosFinais.put(Hj2.calculaCusto(),"NomeMatriculaHashJoin2");
		custosFinais.put(Mj1.calculaCusto(),"NomeMatriculaMergeJoin1");
		//custosFinais.put(Mj2.calculaCusto(),"NomeMatriculaMergeJoin2");
		custosFinais.put(Nlj1.calculaCusto(),"NomeMatriculaNestedLoopJoin1");
		//custosFinais.put(Nlj2.calculaCusto(),"NomeMatriculaNestedLoopJoin2");
		
		
		return custosFinais.get(custosFinais.firstKey());

	}

	public void projeta (){

		if( custoFinal().equals("NomeMatriculaHashJoin1")){

			NomeMatriculaHashJoin1 Hj1= new NomeMatriculaHashJoin1 (curso);
			Projecao projecao = new Projecao (Hj1, "nome", "matricula");

			Tupla tupla;
			projecao.open();

			while ((tupla = (Tupla) projecao.next()) != null) {

				System.out.println("Nome: " +tupla.getValorCampo("nome") + " " +"[Matricula: " +tupla.getValorCampo("matricula") + "]");

			}
			projecao.close();
		}
		
		else if(custoFinal().equals("NomeMatriculaHashJoin2")){

			NomeMatriculaHashJoin2 Hj2= new NomeMatriculaHashJoin2 (curso);
			Projecao projecao = new Projecao (Hj2,"nome","matricula");

			Tupla tupla;
			projecao.open();

			while ((tupla = (Tupla) projecao.next()) != null) {

				System.out.println("Nome: " +tupla.getValorCampo("nome") + " " +"[Matricula: " +tupla.getValorCampo("matricula") + "]");

			}
			projecao.close();
		}
		
		else if(custoFinal().equals("NomeMatriculaMergeJoin1")){

			NomeMatriculaMergeJoin1 Mj1 = new NomeMatriculaMergeJoin1 (curso);
			Projecao projecao = new Projecao (Hj2,"nome","matricula");

			Tupla tupla;
			projecao.open();

			while ((tupla = (Tupla) projecao.next()) != null) {

				System.out.println("Nome: " +tupla.getValorCampo("nome") + " " +"[Matricula: " +tupla.getValorCampo("matricula") + "]");

			}
			projecao.close();
		}
		
		else if(custoFinal().equals("NomeMatriculaMergeJoin2")){

			NomeMatriculaMergeJoin2 Mj2= new NomeMatriculaMergeJoin2 (curso);
			Projecao projecao = new Projecao (Hj2,"nome","matricula");

			Tupla tupla;
			projecao.open();

			while ((tupla = (Tupla) projecao.next()) != null) {

				System.out.println("Nome: " +tupla.getValorCampo("nome") + " " +"[Matricula: " +tupla.getValorCampo("matricula") + "]");

			}
			projecao.close();
		}
		
		else if(custoFinal().equals("NomeMatriculaNestedLoopJoin1")){

			NomeMatriculaNestedLoopJoin1 Nlj1= new NomeMatriculaNestedLoopJoin1 (curso);
			Projecao projecao = new Projecao (Hj2,"nome","matricula");

			Tupla tupla;
			projecao.open();

			while ((tupla = (Tupla) projecao.next()) != null) {

				System.out.println("Nome: " +tupla.getValorCampo("nome") + " " +"[Matricula: " +tupla.getValorCampo("matricula") + "]");

			}
			projecao.close();
		}
		
		else if(custoFinal().equals("NomeMatriculaNestedLoopJoin2")){

			NomeMatriculaNestedLoopJoin1 Nlj2= new NomeMatriculaNestedLoopJoin1 (curso);
			Projecao projecao = new Projecao (Nlj2,"nome","matricula");

			Tupla tupla;
			projecao.open();

			while ((tupla = (Tupla) projecao.next()) != null) {

				System.out.println("Nome: " +tupla.getValorCampo("nome") + " " +"[Matricula: " +tupla.getValorCampo("matricula") + "]");

			}
			projecao.close();
		}


	}


}


