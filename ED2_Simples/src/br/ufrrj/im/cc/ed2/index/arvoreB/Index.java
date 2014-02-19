package br.ufrrj.im.cc.ed2.index.arvoreB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.List;

import br.ufrrj.im.cc.ed2.catalogo.Catalogo;
import br.ufrrj.im.cc.ed2.join.base.Iterator;
import br.ufrrj.im.cc.ed2.join.base.Relacao;
import br.ufrrj.im.cc.ed2.join.base.Tupla;

public class Index implements Iterator  {
	
	private String nomeRelacao;
	private ArvoreB arvoreB;
	private String[] colunasChave;
	private List<Long> linhasArquivo = null;
	private String valorChaves;
	private FileOutputStream arquivo = null;
	private RandomAccessFile rel;
	private int count = 0;
	
	public Index (String nomeRelacao, String... colunasChave){
		this.nomeRelacao = nomeRelacao;
		this.colunasChave = colunasChave;
	}
	
	public void setValorChaves (String... chaves) {
		String chave = "";
		for (int i = 0; i < chaves.length; i++) {
			chave += chaves[i];
			if(!(i == colunasChave.length -1))
				chave += "-";
		}
		
		this.valorChaves = chave;
	}
	
	private void montaArvore(){
		Relacao indexado = new Relacao(this.nomeRelacao);
		indexado.open();
		Tupla tupla;
		arvoreB = new ArvoreB(2);
		int c = 0;
		while((tupla = (Tupla)indexado.next()) != null) {
			String chave = "";
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

	@Override
	public Iterator open() {
		montaArvore();
		try {
			salvaArvore("arvore");
			rel = new RandomAccessFile(Catalogo.getInstancia().recuperaNomeArquivo(this.nomeRelacao), "r");
		} catch(Exception e) {
			System.out.println("Problemas com a manipulação do arquivo !\n");
			System.out.println(e.toString());
		}
		return this;
	}

	@Override
	public Iterator next() {
		Long pos;
		if(this.valorChaves == null){
			System.out.println("Defina os valores para as chaves primeiro !");
			return null;
		}
		if(this.linhasArquivo == null){
			this.linhasArquivo = arvoreB.busca(valorChaves).getPosicaoArquivo();
		}
		if(count == linhasArquivo.size()){
			pos = null;
		} else 
			pos = linhasArquivo.get(count);
		if(pos != null){
			try {
				rel.seek(pos);
				Tupla tupla = new Tupla(rel.readLine(), nomeRelacao, pos);
				count++;
				return tupla;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;
	}

	@Override
	public Iterator close() {
		try {
			rel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		arvoreB = null;
		return this;
	}

	@Override
	public long calculaCusto() {
		return Catalogo.getInstancia().recuperaNumeroLinhas(nomeRelacao);
	}
	
	private void salvaArvore(String nome) throws FileNotFoundException,
		IOException {
		ObjectOutputStream objectOut;
		if(arquivo == null){
			arquivo = new FileOutputStream(nome);
			objectOut = new ObjectOutputStream(arquivo);
			objectOut.writeObject(arvoreB);
			objectOut.flush();
			objectOut.close();
			arquivo.flush();
			arquivo.close();
		}
	}

	public void CarregaArvoreArquivo(String nome) throws IOException, ClassNotFoundException {
		if(this.arquivo != null){	
			FileInputStream arquivoLeitura = new FileInputStream(nome);
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
			ArvoreB avb = (ArvoreB) objLeitura.readObject();
			objLeitura.close();
			arquivoLeitura.close();
			this.arvoreB = avb;
		} else {
			System.out.println("Salve a arvore primeiro !");
		}
	}
}
