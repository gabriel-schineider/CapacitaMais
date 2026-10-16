package modelo;

import java.util.ArrayList;

public class Professor extends Usuario implements Notificavel{
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
		return cursos.contains(curso);
	}
	
	boolean adicionarCurso(Curso curso) {
		if (podeReceberCurso()) {
			if (!contemCurso(curso)) {
				cursos.add(curso);
				return true;
			}
		}
		return false;
	}
	
	boolean removerCurso(Curso curso) {
		curso.removerProfessor();
		return cursos.remove(curso);
	}
	
	public boolean desativarCadastro() {
		if(cadastroAtivo) {
			for(Curso curso: cursos) {
				curso.removerProfessor();
				removerCurso(curso);
			}
			cadastroAtivo = false;
			return true;
		}
		return false;
	}
	
	public StatusInscricao lancarNota(Aluno aluno, Curso curso, float nota) {
		if(aluno== null || !contemCurso(curso)) {
			return null; //professor só pode lançar notas no curso dele
		}
		return aluno.receberNota(curso, nota);
	}
	
	public String getNome() {
		return nome;
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
			System.out.printf("%s ", curso.getNome());
		} System.out.println("}");
	}
	
	@Override
	public void notificar(String mensagem) {
		System.out.println(mensagem);
		
	}
}
