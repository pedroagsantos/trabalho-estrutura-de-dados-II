package br.ufrrj.im.cc.ed2.join.base;

public class Projecao implements Iterator {

	private Iterator selecao;
	private Iterator relacao;
	private String tipoRelacao;
	private String coluna;


	public Projecao(String tipoRelacao, String coluna) {
		
		this.relacao = new Relacao(tipoRelacao);
		this.coluna = coluna;
	}

	public Projecao(Iterator relacao, String coluna) {
		
		this.relacao = relacao;
		this.coluna = coluna;

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
		tupla = (Tupla) relacao.next();

		if(tupla != null){
			c = new ColunaTupla(coluna, tupla.getValorCampo(coluna));
			valor.adicionaColuna(c);
			return valor;
		}else
			return null;


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
