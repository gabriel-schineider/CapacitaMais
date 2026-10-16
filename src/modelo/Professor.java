package modelo;

import java.util.ArrayList;

public class Professor extends Usuario implements Notificavel{
	// Atributos
	private Especialidade especialidade;
	private ArrayList<Curso> cursos;
	private final int LIMITE_CURSOS = 5;
	
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
			return cursos.contains(curso);
	}
		
	void adicionarCurso(Curso curso) {
			if (!contemCurso(curso)) {
				cursos.add(curso);
			}
	}
					
	void removerCurso(Curso curso) {
			if (contemCurso(curso)) {
				cursos.remove(curso);
			}
	}
	
	public StatusInscricao lancarNota(Aluno aluno, Curso curso, float nota) {
			if(aluno== null || !contemCurso(curso)) {
				return null; //professor só pode lançar notas no curso dele
			}
			return aluno.receberNota(curso, nota);
	}
	
	public void exibirNome() {
		System.out.printf("%s", nome);
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Professor:");
		super.exibirInformacoes();
		System.out.printf("\tEspecialidade: %s\n\tCursos: { ", especialidade);
		for (Curso curso : cursos) {
			curso.exibirNome();
			System.out.printf(" ");
		}
		System.out.printf("}\n");
	}

	@Override
		public void notificar(String mensagem) {
			System.out.println(mensagem);
			
		}
}
