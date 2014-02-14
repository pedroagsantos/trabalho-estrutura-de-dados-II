package br.ufrrj.im.cc.ed2.join.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import br.ufrrj.im.cc.ed2.catalogo.Catalogo;



public class Relacao implements Iterator {

	private String nomeRelacao;
	private RandomAccessFile arquivo;

	public Relacao(String nomeRelacao){

		this.nomeRelacao = nomeRelacao;
	}

	@Override
	public Iterator open() {

		String nomeArquivo = Catalogo.getInstancia().recuperaNomeArquivo(nomeRelacao);
		

		try {
			arquivo = new RandomAccessFile(new File(nomeArquivo), "r");
		} 
		catch (FileNotFoundException e) {

			geraExcecao("Erro ao abrir o arquivo "+nomeArquivo+" para a relacao "+nomeRelacao+".", e);
		}

		return this;
	}


	@Override
	public Iterator next() {
		//le a proxima linha
		String linha = null;
		long posicao = 0;

		try {
			posicao = arquivo.getFilePointer();
			linha = arquivo.readLine();
			if(linha == null) return null;

			//	retorna a tupla
			Tupla tuplaRetorno = new Tupla(linha, nomeRelacao, posicao);
			return tuplaRetorno;
		}
		catch(IOException e){

			geraExcecao("Erro", e);
			return null;
		}
	}

	@Override
	public Iterator close() {

		try {

			arquivo.close();
		} catch (IOException e) {

			geraExcecao("Erro ao fechar a rela��o "+nomeRelacao+".", e);
		}
		return null;
	}

	private void geraExcecao(String mensagem, Exception e) {
		e.printStackTrace();
		throw new RuntimeException(mensagem, e);
	}

	public int getNumeroLinhas() {

		return Catalogo.getInstancia().recuperaNumeroLinhas(nomeRelacao);
	}
	
	public Iterator voltaLinha(long linha) {
		try {
			arquivo.seek(linha);
			return this;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public long calculaCusto() {

		return Catalogo.getInstancia().recuperaNumeroLinhas(nomeRelacao);
	}


}
