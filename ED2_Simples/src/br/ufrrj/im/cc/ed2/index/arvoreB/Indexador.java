package br.ufrrj.im.cc.ed2.index.arvoreB;

import java.util.List;

import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;

public class Indexador {
	
	private String nomeRelacao;
	private ArvoreB arvoreB;
	private String[] colunasChave;
	
	public Indexador (String nomeRelacao, String... colunasChave){
		this.nomeRelacao = nomeRelacao;
		this.colunasChave = colunasChave;
		this.montaArvore();
	}
	
	public List<Long> buscaRegistros (String... chaves) {
		String chave = "";
		for (int i = 0; i < chaves.length; i++) {
			chave += chaves[i];
			if(!(i == colunasChave.length -1))
				chave += "-";
		}
		return arvoreB.busca(chave).getPosicaoArquivo();
	}
	
	private void montaArvore(){
		Relacao indexado = new Relacao(this.nomeRelacao);
		indexado.open();
		Tupla tupla;
		String chave = "";
		arvoreB = new ArvoreB(2);
		int c = 0;
		while((tupla = (Tupla)indexado.next()) != null) {
			for (int i = 0; i < colunasChave.length; i++) {
				chave += tupla.getValorCampo(colunasChave[i]);
				if(!(i == colunasChave.length -1))
					chave += "-";
			}
			
			ElementoArvoreB novo = null;
			novo = arvoreB.busca(chave);
			if(novo == null) {
				novo = new ElementoArvoreB(chave);
				this.arvoreB.insere(novo);
			}
			novo.addPosicaoArquivo(tupla.getPosicao());
			System.out.println(c++);
		}

	}
}