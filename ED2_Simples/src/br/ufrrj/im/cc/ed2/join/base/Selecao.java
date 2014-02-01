package br.ufrrj.im.cc.ed2.join.base;

public class Selecao implements Iterator {
	
	String nome;
	String campo;
	String valor;
	Iterator relacao;

	public Selecao(String nome, String campo, String valor) {
		
		this.campo= campo;
		this.nome= nome;
		this.valor = valor;
		relacao = new Relacao(nome);
		
	}
	public Selecao(Iterator selecao, String campo, String valor ){
		
		this.relacao = relacao;
		this.nome= nome;
		this.valor = valor;			
	}
	
	public Iterator open() {
		
		relacao.open();
		return this;
	}
	
	public Iterator next() {
		
		Tupla tupla = new Tupla();
		
		while((tupla = (Tupla) relacao.next()) != null){
			
			if(tupla.getValorCampo(campo).equals(valor)){
				return tupla;
			}	
			
		}
		return tupla;
	}
	

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
