package modelo;

import java.util.ArrayList;

public class Professor extends Usuario {
	// Atributos
	private Especialidade especialidade;
	private ArrayList<Curso> cursos;
	private static final int LIMITE_CURSOS = 5;
	
	// Construtor
	public Professor(String nome, String cpf, int idade, String email, Especialidade especialidade) {
		super(nome, cpf, idade, email);
		
		this.especialidade = especialidade;
		cursos = new ArrayList<Curso>(LIMITE_CURSOS);
	}
	
	// Métodos
	
	// Métodos com vizibilidade package porque somente a Instituição deve validar e sincronizar as classes Professor e Curso. A Main não.
	boolean podeReceberCurso() {
		if (cursos.size() < LIMITE_CURSOS) {
			return true;
		}
		return false;
	}
	
	boolean contemCurso(Curso curso) {
		if (!cursos.contains(curso)) {
			cursos.add(curso);
			return true;
		}
		return false;
	}
	
	void adicionarCurso(Curso curso) {
		cursos.add(curso);
	}
	
	void removerCurso(Curso curso) {
		cursos.remove(curso);
	}
	
	public void exibirNome() {
		System.out.printf("%s", nome);
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Professor:");
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Idade: " + idade);
		System.out.println("E-mail: " + email);
		System.out.println("Especialidade: " + especialidade);
		System.out.print("Cursos: { ");
		for (Curso curso : cursos) {
			curso.exibirNome();
			System.out.printf(" ");
		}
		System.out.print("}\n");
	}
}
