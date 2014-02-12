package br.ufrrj.im.cc.ed2.index.arvoreB;

import java.util.SortedSet;
import java.util.TreeSet;

public class ArvoreB {
	
	private SortedSet<ElementoArvoreB> raiz;
	Integer ordem;
	
	public ArvoreB(Integer ordem){
		this.ordem = ordem;
		raiz = new TreeSet<ElementoArvoreB>();
		raiz.add(new ElementoArvoreB(String.valueOf(Double.POSITIVE_INFINITY)));
	}
	
	public void insere (ElementoArvoreB elemento){
		insere(elemento, this.raiz);
	}
	
	private void insere (ElementoArvoreB elemento, SortedSet<ElementoArvoreB> raiz){
		if(raiz.size() == 1)
		{
			raiz.add(elemento);
			return;
		} else {
			for (ElementoArvoreB elementoArvoreB : raiz) {
				if(elemento.compareTo(elementoArvoreB) > 0) {
					if(elemento.getMenores().size() == 1) 
						raiz.add(elemento);
					else
						insere(elemento, elemento.getMenores());
				}
			}
		}
		
		if(raiz.size() == 2 * ordem + 2){
			
		}
			
	}

}
