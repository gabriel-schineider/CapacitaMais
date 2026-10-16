package modelo;

import java.util.ArrayList;

public class Instituicao {
	private String nome;
	private ArrayList<Professor> professores;
	private ArrayList<Curso> cursos;
	
	// Construtor
	public Instituicao(String nome) {
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		professores = new ArrayList<>();
		cursos = new ArrayList<>();
	}
	
	// Métodos
	
	private boolean contemProfessor(Professor professor) {
		return professores.contains(professor);
	}
	
	private boolean contemCurso(Curso curso) {
		return cursos.contains(curso);
	}
	
	public boolean adicionarProfessor(Professor professor) {
		if (!contemProfessor(professor)) {
			if (professor.isCadastroAtivo()) {
				professores.add(professor);
				return true;
			}
		}
		return false;
	}
	
	public boolean trocarProfessor(Curso curso, Professor novoProfessor) {
		if (contemCurso(curso) && contemProfessor(novoProfessor)) {
			for (Professor professor : professores) {
				if (professor.equals(curso.getProfessor())) {
					professor.removerCurso(curso);
					curso.trocarProfessor(novoProfessor);
					return true;
				}
			}
		}
		return false;
	}
	
	// TREINAR O "removerProfessor()" E O "removerCurso()". PODE SER QUE ELA PEÇA ISSO...
	
	public boolean criarCurso(String nome, int vagas, int cargaHorariaHoras, Professor professor) {
		// A criação de Curso em Instuição é uma composição!
		if (contemProfessor(professor)) {
			Curso curso = new Curso(nome, vagas, cargaHorariaHoras, professor);
			professor.adicionarCurso(curso);
			// Essa sincronização bidirecional de "Professor <-> Curso" realizada acima é uma agregação!
			cursos.add(curso);
			return true;
		}
		else {
			return criarCurso(nome, vagas, cargaHorariaHoras);
		}
	}
	
	public boolean criarCurso(String nome, int vagas, int cargaHorariaHoras) {
		for (Professor professor : professores) {
			if (professor.isCadastroAtivo())	
				if (professor.podeReceberCurso()) {
					// A criação de Curso em Instuição é uma composição!
					Curso curso = new Curso(nome, vagas, cargaHorariaHoras, professor);
					professor.adicionarCurso(curso);
					// Essa sincronização bidirecional de "Professor <-> Curso" realizada acima é uma agregação!
					cursos.add(curso);
					return true;
				}	
		}
		return false;
	}
	
	public void listarCursos() {
		System.out.printf("--> Cursos da Instituição %s:\n", nome);
		for (Curso curso : cursos) {
			curso.exibirInformacoes();
		}
	}
	
	public Curso escolherCurso(String nome) {
		for(Curso curso : cursos) {
			if(curso.getNome().equals(nome)) {
				return curso;
			}
		}
		return null; //não achou
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Instituição");
		System.out.println("Nome: " + nome);
		
		System.out.println("Professores: { ");
		for (Professor professor : professores) {
			System.out.printf("%s ", professor.getNome());
		} System.out.println("}");
		
		System.out.println("Cursos: { ");
		for (Curso curso : cursos) {
			System.out.printf("%s ", curso.getNome());
		} System.out.println("}");
		
		System.out.println("Total de cursos: " + cursos.size());
	}
}
