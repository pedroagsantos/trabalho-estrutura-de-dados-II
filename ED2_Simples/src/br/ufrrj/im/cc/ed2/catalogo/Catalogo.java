package br.ufrrj.im.cc.ed2.catalogo;

import java.util.LinkedList;
import java.util.List;

public class Catalogo {

	private static Catalogo instance;
	private List<ItemCatalogo> itens;
	private List<Coluna> colunasRelacaoAluno;
	private List<Coluna> colunasRelacaoCurso;
	private List<Coluna> colunasRelacaoDisciplina;
	private List<Coluna> colunasRelacaoDisciplinaHistorico;


	// ----------------------------------------------------
	private Catalogo() {
		// os itens podem ser recuperados de um arquivo binario.
		itens = new LinkedList<>();
		colunasRelacaoAluno = new LinkedList<>();
		colunasRelacaoCurso = new LinkedList<>();
		colunasRelacaoDisciplina = new LinkedList<>();
		colunasRelacaoDisciplinaHistorico = new LinkedList<>();

		criaColunasAluno("id", 0);
		criaColunasAluno("curso_id", 1);
		criaColunasAluno("matricula", 2);
		criaColunasAluno("nome", 3);

		ItemCatalogo itemAluno = new ItemCatalogo("Alunos", "Alunos.txt", colunasRelacaoAluno, 2000);
		itens.add(itemAluno);

		criaColunasCurso("id", 0);
		//Mudei o nome do curso de nome para nome_curso 12/02
		criaColunasCurso("nome_curso", 1);
		ItemCatalogo itemCurso = new ItemCatalogo("Cursos", "Cursos.txt", colunasRelacaoCurso, 7);
		itens.add(itemCurso);

		criaColunasDisciplina("id", 0);
		criaColunasDisciplina("nome", 1);
		criaColunasDisciplina("curso_id", 2);
		ItemCatalogo itemDisciplina = new ItemCatalogo("Disciplinas", "Disciplinas.txt", colunasRelacaoDisciplina, 303);
		itens.add(itemDisciplina);

		criaColunasDisciplinaHistorico("id", 0);
		criaColunasDisciplinaHistorico("aluno_id", 1);
		criaColunasDisciplinaHistorico("disciplina_id", 2);
		criaColunasDisciplinaHistorico("nota", 3);
		criaColunasDisciplinaHistorico("ano", 4);
		criaColunasDisciplinaHistorico("periodo", 5);
		criaColunasDisciplinaHistorico("situacao", 6);
		ItemCatalogo itemDisciplinaHistorico = new ItemCatalogo("DisciplinaHistorico", "DisciplinaHistorico.txt", colunasRelacaoDisciplinaHistorico, 120056);
		itens.add(itemDisciplinaHistorico);


	}

	private void criaColunasAluno(String nomeColuna, int ordem) {

		if(nomeColuna.equals("id"))	{

			Coluna coluna = new Coluna(nomeColuna, "String", ordem, null, true);
			colunasRelacaoAluno.add(coluna);
		}
		else if(nomeColuna.indexOf("id") > -1){

			String[] relacaoChaveEstrangeira = nomeColuna.split("_");
			String nomeRelacao = relacaoChaveEstrangeira[0].replaceFirst(String.valueOf(nomeColuna.charAt(0)), String.valueOf(nomeColuna.charAt(0)).toUpperCase());
			Coluna coluna = new  Coluna(nomeColuna, "String", ordem, null, false, nomeRelacao);
			colunasRelacaoAluno.add(coluna);
		}
		else{
			Coluna coluna = new Coluna(nomeColuna, "String", ordem);
			colunasRelacaoAluno.add(coluna);
		}
	}

	private void criaColunasCurso(String nomeColuna, int ordem) {

		if(nomeColuna.equals("id"))	{

			Coluna coluna = new Coluna(nomeColuna, "String", ordem, null, true);
			colunasRelacaoCurso.add(coluna);
		}
		else if(nomeColuna.indexOf("id") > -1){

			String[] relacaoChaveEstrangeira = nomeColuna.split("_");
			String nomeRelacao = relacaoChaveEstrangeira[0].replaceFirst(String.valueOf(nomeColuna.charAt(0)), String.valueOf(nomeColuna.charAt(0)).toUpperCase());
			Coluna coluna = new  Coluna(nomeColuna, "String", ordem, null, false, nomeRelacao);
			colunasRelacaoCurso.add(coluna);
		}
		else{
			Coluna coluna = new Coluna(nomeColuna, "String", ordem);
			colunasRelacaoCurso.add(coluna);
		}
	}

	private void criaColunasDisciplina(String nomeColuna, int ordem) {

		if(nomeColuna.equals("id"))	{
			Coluna coluna = new Coluna(nomeColuna, "String", ordem, null, true);
			colunasRelacaoDisciplina.add(coluna);
		}
		else if(nomeColuna.indexOf("id") > -1){

			String[] relacaoChaveEstrangeira = nomeColuna.split("_");
			String nomeRelacao = relacaoChaveEstrangeira[0].replaceFirst(String.valueOf(nomeColuna.charAt(0)), String.valueOf(nomeColuna.charAt(0)).toUpperCase());
			Coluna coluna = new  Coluna(nomeColuna, "String", ordem, null, false, nomeRelacao);
			colunasRelacaoDisciplina.add(coluna);
		}
		else{
			Coluna coluna = new Coluna(nomeColuna, "String", ordem);
			colunasRelacaoDisciplina.add(coluna);
		}
	}

	private void criaColunasDisciplinaHistorico(String nomeColuna, int ordem) {

		if(nomeColuna.equals("id"))	{

			Coluna coluna = new Coluna(nomeColuna, "String", ordem, null, true);
			colunasRelacaoDisciplinaHistorico.add(coluna);
		}
		else if(nomeColuna.indexOf("id") > -1){

			String[] relacaoChaveEstrangeira = nomeColuna.split("_");
			String nomeRelacao = relacaoChaveEstrangeira[0].replaceFirst(String.valueOf(nomeColuna.charAt(0)), String.valueOf(nomeColuna.charAt(0)).toUpperCase());
			Coluna coluna = new  Coluna(nomeColuna, "String", ordem, null, false, nomeRelacao);
			colunasRelacaoDisciplinaHistorico.add(coluna);
		}
		else{
			Coluna coluna = new Coluna(nomeColuna, "String", ordem);
			colunasRelacaoDisciplinaHistorico.add(coluna);
		}
	}

	public static Catalogo getInstancia() {
		if (instance == null)
			instance = new Catalogo();
		return instance;
	}

	// ---------------------------------------------------

	public String recuperaNomeArquivo(String nomeRelacao) {
		for (ItemCatalogo item : itens) {
			if (item.comparaNomeRelacao(nomeRelacao))
				return item.getNomeArquivo();
		}
		return null;
	}

	public List<Coluna> recuperaColunas(String nomeRelacao) {
		for (ItemCatalogo item : itens) {
			if (item.comparaNomeRelacao(nomeRelacao))
				return item.getColunas();
		}
		return null;
	}

	public int recuperaNumeroLinhas(String nomeRelacao) {
		for (ItemCatalogo item : itens) {
			if (item.comparaNomeRelacao(nomeRelacao))
				return item.numeroLinhas();
		}
		return -1;
	}

	public int recuperaNumeroEstimadoDeLinhas(String nomeRelacao, String campo, String valorBuscado) {
		for (ItemCatalogo item : itens) {
			if (item.comparaNomeRelacao(nomeRelacao))
				return item.numeroEstimadoLinhas(campo, valorBuscado);
		}
		return -1;
	}

	public boolean campoComValorUnico(String nomeRelacao, String nomeCampo) {
		for (ItemCatalogo item : itens) {
			if (item.comparaNomeRelacao(nomeRelacao))
				return item.campoComValorUnico(nomeCampo);
		}
		return false;
	}

}
