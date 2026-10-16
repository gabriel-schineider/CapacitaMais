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

	public boolean criarCurso(String nome, int vagas, int cargaHorariaHoras) {		
		// Verificando se o curso já existe
		for (Curso curso : cursos) {
			if (curso.getNome().equals(nome)) {
				return false;
			}
		}
		// Não existe, então vamos criar
		Curso curso = new Curso(nome, vagas, cargaHorariaHoras);
		cursos.add(curso);
		return true;
	}

// ACHO MELHOR NÃO FAZER ESSE MÉTODO ABAIXO E CRIAR UMA REGRA DE NEGÓCIO QUE IMPEÇA A EXCLUSÃO DE CURSOS.
/*	public boolean excluirCurso(Curso curso) { 
		if (contemCurso(curso)) {
			if (curso.temProfessor()) {
				
			}
			curso.fecharCurso(); // COMO FICAM AS INSCRIÇÕES ATIVAS? PRECISARIA ALTERAR TODA A LÓGICA DE ATRIBUTOS DA CLASSE INSCRIÇÃO PARA RESOLVER ISSO
		}
		return false;
	}*/
	
/*	public boolean removerCurso(Curso curso) {
		return removerCurso(curso);
	}*/
	
	public void listarCursos() {
		System.out.printf("--> Cursos da Instituição %s:\n", nome);
		for (Curso curso : cursos) {
			curso.exibirInformacoes();
			System.out.println("--------------------------------");
		}
	}
	
	public Curso getCurso(String nome) { // Serve tanto para aluno se inscrever, quanto para professor se vincular
		for(Curso curso : cursos) {
			if(curso.getNome().equals(nome)) {
				return curso;
			}
		}
		return null; //não achou
	}
	
	public void listarProfessores() {
		System.out.printf("--> Professores da Instituição %s:\n", nome);
		for (Professor professor : professores) {
			professor.exibirInformacoes();
			System.out.println("--------------------------------");
		}
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
