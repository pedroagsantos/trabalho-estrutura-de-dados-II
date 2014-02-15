package br.ufrrj.im.cc.ed2.index.arvoreB;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ElementoArvoreB implements Comparable<ElementoArvoreB> {
	
	private String chave; //aluno_id-ano-periodo
	private List<Long> posicaoArquivo;
	private SortedSet<ElementoArvoreB> menores;
	private SortedSet<ElementoArvoreB> pagPai;
	
	
	public ElementoArvoreB (String chave){
		this.chave = chave;
		this.menores = new TreeSet<ElementoArvoreB>();
		this.posicaoArquivo = new ArrayList<Long>();
		this.menores.add(new ElementoArvoreB(String.valueOf(Double.POSITIVE_INFINITY)));
	}
	
	@Override
	public int compareTo(ElementoArvoreB arg0) {
		if(arg0.getChave().equals(String.valueOf(Double.POSITIVE_INFINITY))){
			if(this.chave.equals(arg0.getChave()))
				return 0;
			return -1;
		}else if(this.chave.equals(String.valueOf(Double.POSITIVE_INFINITY)))
			return 1;
		else
			return this.chave.compareTo(arg0.getChave());
	}
	
	public void addPosicaoArquivo(Long valor){
		this.posicaoArquivo.add(valor);
	}
	
	public String getChave() {
		return chave;
	}

	public List<Long> getposicaoArquivo() {
		return posicaoArquivo;
	}
	
	public void addposicaoArquivo(Long posicaoArquivo){
		this.posicaoArquivo.add(posicaoArquivo);
	}
	
	public SortedSet<ElementoArvoreB> getMenores(){
		return this.menores;
	}

	public void setMenores(SortedSet<ElementoArvoreB> menores) {
		this.menores = menores;
	}

	public SortedSet<ElementoArvoreB> getPagPai() {
		return pagPai;
	}

	public void setPagPai(SortedSet<ElementoArvoreB> pagPai) {
		this.pagPai = pagPai;
	}

}

