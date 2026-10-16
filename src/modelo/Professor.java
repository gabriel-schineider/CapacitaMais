package modelo;

import java.util.ArrayList;

public class Professor extends Usuario implements Notificavel{
	private Area area;
	private ArrayList<Curso> cursos;
	private static final int LIMITE_CURSOS = 5;
	private ArrayList<Comunidade> comunidades;
	
	public Professor(String nome, String cpf, int idade, String email, Area area) {
		super(nome, cpf, idade, email);
		this.area = area;
		cursos = new ArrayList<Curso>(LIMITE_CURSOS);
		comunidades = new ArrayList<>();
	}
	
	private boolean podeReceberCurso() {
		if (cursos.size() < LIMITE_CURSOS) {
			return true;
		}
		return false;
	}
	
	private boolean contemCurso(Curso curso) {
		return cursos.contains(curso);
	}
	
	public boolean adicionarCurso(Curso curso) {
		if (podeReceberCurso()) {
			if (!contemCurso(curso)) {
				cursos.add(curso);
				curso.adicionarProfessor(this);
				return true;
			}
		}
		return false;
	}
	
	public boolean removerCurso(Curso curso) {
		if (contemCurso(curso)) {
			if (curso.removerProfessor(this)) { // Verificar se foi possível remover o professor lá no curso
				cursos.remove(curso); // Removendo o curso aqui no professor
				return true;
			}
		}
		return false;
	}
	
	public boolean trocarCurso(Curso cursoAntigo, Curso cursoNovo) {
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
	
	private boolean contemComunidade(Comunidade comunidade) {
		return comunidades.contains(comunidade);
	}
	
	public boolean adicionarComunidade(Comunidade comunidade) {
		if (!contemComunidade(comunidade)) {
			comunidades.add(comunidade);
			comunidade.adicionarProfessor();
			return true;
		}
		return false;
	}
	
	public boolean removerComunidade(Comunidade comunidade) {
		if (contemComunidade(comunidade)) {
			comunidade.removerProfessor();
			comunidades.remove(comunidade);
			return true;
		}
		return false;	
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
		System.out.println("Área: " + area);
		
		System.out.print("Cursos: { ");
		for (Curso curso : cursos) {
			System.out.printf("%s ", curso.getNome());
		} System.out.println("}");
	}
	
	@Override
	public void notificar(String mensagem) {
		System.out.printf("Mensagem de professor(a) %s:\n", nome);
		System.out.printf("   %s\n", mensagem);
		
	}
}
