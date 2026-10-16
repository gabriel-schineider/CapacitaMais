package modelo;

import java.util.ArrayList;

public class Instituicao {
	private String nome;
	private ArrayList<Professor> professores;
	private ArrayList<Curso> cursos;
	
	public Instituicao(String nome) {
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		professores = new ArrayList<>();
		cursos = new ArrayList<>();
	}
	
	private boolean contemProfessor(Professor professor) {
		return professores.contains(professor);
	}	

	public boolean adicionarProfessor(Professor professor) {
		if (!contemProfessor(professor)) {
			professores.add(professor);
			return true;
		}
		return false;
	}
	
	public boolean removerProfessor(Professor professor) {
		return professores.remove(professor);
	}
	
	private boolean contemCurso(Curso curso) {
		return cursos.contains(curso);
	}
	
	public boolean adicionarCurso(Curso curso) {
		if (!contemCurso(curso)) {
			cursos.add(curso);
			return true;
		}
		return false;
	}
	
	public boolean removerCurso(Curso curso) {
		return removerCurso(curso);
	}
	
	public void listarCursos() {
		System.out.printf("--> Cursos da Instituição %s:\n", nome);
		for (Curso curso : cursos) {
			curso.exibirInformacoes();
			System.out.println("--------------------------------");
		}
	}
	
	public Curso getCurso(String nome) {
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
		
		System.out.print("Professores: { ");
		for (Professor professor : professores) {
			System.out.printf("%s ", professor.getNome());
		} System.out.println("}");
		
		System.out.print("Cursos: { ");
		for (Curso curso : cursos) {
			System.out.printf("%s ", curso.getNome());
		} System.out.println("}");
		
		System.out.println("Total de cursos: " + cursos.size());
	}
}
