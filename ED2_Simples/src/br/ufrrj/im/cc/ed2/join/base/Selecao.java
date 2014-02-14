package br.ufrrj.im.cc.ed2.join.base;

import java.io.IOException;

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
		
		this.relacao = selecao;
		this.campo= campo;
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
		return null;
	}
	

	public Iterator close() {
		
		relacao.close();
		return null;
	}
	
	
	public long calculaCusto() {
		
		return ((Relacao) relacao).calculaCusto();
	
	}	
	
	
}
