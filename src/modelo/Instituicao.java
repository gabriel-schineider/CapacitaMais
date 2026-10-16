package modelo;

import java.util.ArrayList;

public class Instituicao {
	// Atributos
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
		totalDeCursos = 0;
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
			professores.add(professor);
			return true;
		}
		return false;
	}
	
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
	
	public void exibirInformacoes() {
		System.out.printf("--> Instituição\n\tNome: %s\n\tProfessores: { ", nome);
		for (Professor professor : professores) {
			professor.exibirNome();
			System.out.printf(" ");
		}
		System.out.printf("}\n\tCursos: { ");
		for (Curso curso : cursos) {
			curso.exibirNome();
			System.out.printf(" ");
		}
		System.out.printf("}\n\tTotal de cursos: %d\n", cursos.size());
	}
}
