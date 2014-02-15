import br.ufrrj.im.cc.ed2.join.base.Projecao;
import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.HashJoin;
import br.ufrrj.im.cc.ed2.juncao.NestedLoopJoin;


public class Main {


	public static void testaJoin(){


		//NestedLoop Plano A
		//NestedLoopJoin relation = new NestedLoopJoin ("Cursos", "id", "Alunos", "curso_id"); 
		//Selecao  relation2 = new Selecao(relation, "nome_curso", "MATEM햀ICA");
		//Projecao relation1 = new Projecao(relation2, "nome");

		/*Selecao  relation = new Selecao("Cursos", "nome_curso", "MATEM햀ICA");
		NestedLoopJoin relation2 = new NestedLoopJoin ("Alunos", "curso_id", relation, "id");
		Projecao relation1 = new Projecao(relation2, "nome");*/

		//Plano A pode
		Selecao  relation = new Selecao("Cursos", "nome_curso", "MATEM햀ICA");
		//HashJoin relation2 = new HashJoin(relation, "id", "Alunos", "curso_id");
		HashJoin relation2 = new HashJoin(relation, "id", "Alunos", "curso_id");
		Projecao relation1 = new Projecao(relation2, "nome");
		
		//Plano B
		/*HashJoin relation = new HashJoin("Cursos", "id", "Alunos", "curso_id");
		Selecao relation2 = new Selecao(relation, "nome_curso", "MATEM햀ICA");
		Projecao relation1 = new Projecao(relation2, "nome");*/
		 
		
		relation1.open();
		Tupla tupla;
		int i = 0;
		while ((tupla = (Tupla) relation1.next()) != null) {
			i++;
			if(tupla.getValorCampo("nome") != null)
			System.out.println(tupla.getValorCampo("nome") + " " + i);
			//System.out.println("["+tupla.getValorCampo("nome")+"] : "+tupla.getValorCampo("nome_curso") + " " + i);
		
			
		}
		relation1.close();
		
		//System.out.println(relation2.calculaCusto());
		//System.out.println(relation.calculaCusto());
		//System.out.println(relation2.calculaCusto() + relation.calculaCusto());


		//=============================================================================================
	}


	public static void main(String[] args) {
		testaJoin();

		//System.out.println("aqui");

	}

}