package br.ufrrj.im.cc.ed2.join.base;

public class Projecao implements Iterator {

	private Iterator selecao;
	private Iterator relacao;
	private String tipoRelacao;
	private String coluna;
	private String coluna2;


	public Projecao(String tipoRelacao, String coluna) {
		
		this.relacao = new Relacao(tipoRelacao);
		this.coluna = coluna;
	}
	
	public Projecao(Iterator relacao, String coluna) {
		
		this.relacao = relacao;
		this.coluna = coluna;
		
	}

	public Projecao(Iterator relacao, String coluna,  String coluna2) {
		
		this.relacao = relacao;
		this.coluna = coluna;
		this.coluna2 = coluna2;

	}
	@Override
	public Iterator open() {
		relacao.open();

		return this;

	}


	@Override
	public Iterator next() {
		
		Tupla tupla = new Tupla();
		Tupla valor = new Tupla();
		ColunaTupla c;
		ColunaTupla c2;
		tupla = (Tupla) relacao.next();

		if(coluna2 == null){
			if(tupla != null){
				c = new ColunaTupla(coluna, tupla.getValorCampo(coluna));
				valor.adicionaColuna(c);
				return valor;
			}else
				return null;
		}else {
			if(tupla != null){
				c = new ColunaTupla(coluna, tupla.getValorCampo(coluna));
				c2 = new ColunaTupla(coluna2, tupla.getValorCampo(coluna2));
				valor.adicionaColuna(c);
				valor.adicionaColuna(c2);
				return valor;
			}else
				return null;
		}


	}


	@Override
	public Iterator close() {
		relacao.close();
		return null;
	}

	@Override
	public long calculaCusto() {
		// TODO Auto-generated method stub
		return 0;
	}

}
