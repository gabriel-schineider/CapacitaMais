package modelo;

import java.util.ArrayList;

public class Professor extends Usuario implements Notificavel{
	private Especialidade especialidade;
	private ArrayList<Curso> cursos;
	private static final int LIMITE_CURSOS = 5;
	
	public Professor(String nome, String cpf, int idade, String email, Especialidade especialidade) {
		super(nome, cpf, idade, email);
		
		this.especialidade = especialidade;
		cursos = new ArrayList<Curso>(LIMITE_CURSOS);
	}
	
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
				curso.adicionarProfessor(this);
				return true;
			}
		}
		return false;
	}
	
	boolean removerCurso(Curso curso) {
		if (contemCurso(curso)) {
			if (curso.removerProfessor(this)) { // Verificar se foi possível remover o professor lá no curso
				cursos.remove(curso); // Removendo o curso aqui no professor
				return true;
			}
		}
		return false;
	}
	
	boolean trocarCurso(Curso cursoAntigo, Curso cursoNovo) {
		if (cursoAntigo != null && cursoNovo != null) {
			if (contemCurso(cursoAntigo) && !contemCurso(cursoNovo)) {
				if (removerCurso(cursoAntigo) == true) { // Verificando se foi possível remover o professor do curso antigo
					adicionarCurso(cursoNovo); // Removendo o antigo antes de adicionar o novo para não quebrar a regra de LIMITE_CURSOS
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean desativarCadastro() {
		if(cadastroAtivo) {
			for(Curso curso: cursos) {
				if (removerCurso(curso) == false) { // Verificando se não foi possível remover o professor de algum curso
					return false; // Não pode deixar o curso sem professor
				}
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
