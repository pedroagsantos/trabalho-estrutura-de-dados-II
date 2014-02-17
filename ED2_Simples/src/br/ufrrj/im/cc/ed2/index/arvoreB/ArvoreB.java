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
		if(raiz.isEmpty())
		{
			raiz.add(elemento);
			raiz.add(new ElementoArvoreB(String.valueOf(Double.POSITIVE_INFINITY)));
			return;
		} else {
			for (ElementoArvoreB elementoArvoreB : raiz) {
				if(elemento.compareTo(elementoArvoreB) == 0)
					return;
				if(elemento.compareTo(elementoArvoreB) < 0) {
					if(elemento.getMenores().isEmpty()) 
						raiz.add(elemento);
					else
						insere(elemento, elemento.getMenores());
				}
				
			}
		}
		
		if(raiz.size() == 2 * ordem + 2) {
			Object[] asArrayObj = raiz.toArray();
			ElementoArvoreB[] asArray = new ElementoArvoreB[asArrayObj.length];
			for (int i = 0; i < asArrayObj.length; i++) {
				asArray[i] = (ElementoArvoreB)asArrayObj[i];
			}
			SortedSet<ElementoArvoreB> novaPag = new TreeSet<>();	
			SortedSet<ElementoArvoreB> menores = new TreeSet<ElementoArvoreB>();
			SortedSet<ElementoArvoreB> maiores = new TreeSet<ElementoArvoreB>();
			if(asArray[ordem].getPagPai() == null){
				for(int i = 0; i < ordem;i++) {
					asArray[i].setPagPai(novaPag);
					menores.add(asArray[i]);
				}
				menores.add(new ElementoArvoreB(String.valueOf(Double.POSITIVE_INFINITY)));
				for(int i = ordem+1; i < asArray.length; i++) {
					asArray[i].setPagPai(novaPag);
					maiores.add(asArray[i]);
				}
				asArray[ordem].setMenores(menores);
				novaPag.add(asArray[ordem]);
				ElementoArvoreB infinito = new ElementoArvoreB(String.valueOf(Double.POSITIVE_INFINITY));
				infinito.setMenores(maiores);
				novaPag.add(infinito);
			} else {
				for(int i = 0; i < ordem;i++) {
					menores.add(asArray[i]);
				}
				menores.add(new ElementoArvoreB(String.valueOf(Double.POSITIVE_INFINITY)));
				for(int i = ordem+1; i < asArray.length; i++) {
					maiores.add(asArray[i]);
				}
				asArray[ordem].setMenores(menores);
				for (ElementoArvoreB elementoArvoreB : asArray[ordem].getPagPai()) {
					if(maiores.first().compareTo(elementoArvoreB) < 0)
						elementoArvoreB.setMenores(maiores);
				}
				asArray[ordem].getPagPai().add(asArray[ordem]);
				
			}
		}
			
	}

	public ElementoArvoreB busca(String chave){
		return busca(this.raiz, chave);
	}
	
	private ElementoArvoreB busca(SortedSet<ElementoArvoreB> raiz, String chave) {

		if (raiz.isEmpty()) {
			return null;
		}

		else {
			for (ElementoArvoreB elemento : raiz) {
				if (chave.equals(elemento.getChave())) {
					return elemento;
				} else if (chave.compareTo(elemento.getChave()) < 0) {
					return busca(elemento.getMenores(), chave);
				}
			}
		}
		return null;

	}

}
