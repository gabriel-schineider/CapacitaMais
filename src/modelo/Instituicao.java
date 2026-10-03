package modelo;

import java.util.ArrayList;

public class Instituicao {
	// Atributos
	private String nome;
	private ArrayList<Professor> professores;
	private ArrayList<Curso> cursos;
	private int totalDeCursos;
	
	// Construtor
	public Instituicao(String nome) {
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		professores = new ArrayList<>();
		cursos = new ArrayList<>();
	}
	
	// Métodos
	public boolean adicionarProfessor(Professor professor) {
		if (!professores.contains(professor)) {
			professores.add(professor);
			return true;
		}
		return false;
	}
	
	public boolean criarCurso(String nome, int vagas, int cargaHorariaHoras, Professor professor) {
		Curso curso = new Curso(nome, vagas, cargaHorariaHoras, professor);
		if (!cursos.contains(curso)) {
			cursos.add(curso);
			totalDeCursos++;
			return true;
		}
		return false;
	}
	public boolean criarCurso(String nome, int vagas, int cargaHorariaHoras) {
		for (Professor professor : professores) {
			if (professor.isCadastroAtivo())	
				if (professor.podeReceberCurso()) {
					Curso curso = new Curso(nome, vagas, cargaHorariaHoras, professor);
					cursos.add(curso);
					totalDeCursos++;
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
		System.out.printf("}\n\tTotal de cursos: %d\n", totalDeCursos);
	}
}
