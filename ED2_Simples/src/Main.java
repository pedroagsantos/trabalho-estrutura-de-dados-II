import br.ufrrj.im.cc.ed2.join.base.Selecao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;
import br.ufrrj.im.cc.ed2.juncao.HashJoin;


public class Main {
	
	
	public static void testaHashJoin(){
		long tempo = System.currentTimeMillis();
		
		HashJoin relacao1 = new HashJoin("Autor", "id", "Livro", "id_autor");
		Selecao relacao2 = new Selecao(relacao1, "nome", "Luis Fernando Orleans");

		
		HashJoin relation = new HashJoin("Autor", "id", "Livro", "id_autor");
		relation.open();
		Tupla tupla;
		while ((tupla = (Tupla) relation.next()) != null) {
//			System.out.println("["+tupla.getValorCampo("nome")+"] : "+tupla.getValorCampo("titulo"));
		}
		relation.close();
		tempo = System.currentTimeMillis() - tempo;
		System.out.println("Junção com Tabela de Dispersão processada em "+tempo+"ms");
	}
	
	
	public static void main(String[] args) {
		testaHashJoin();
		
	}
	
}
