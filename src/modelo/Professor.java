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
	
	boolean podeReceberCurso() {
		if (cadastroAtivo) {
			if (cursos.size() < LIMITE_CURSOS) {
				return true;
			}	
		}
		return false;
	}
	
	private boolean contemCurso(Curso curso) {
		return cursos.contains(curso);
	}
	
	public boolean adicionarCurso(Curso curso) {
		if (cadastroAtivo && podeReceberCurso()) {
			if (curso != null && !contemCurso(curso)) {
				boolean cursoNaoTinhaProfessor = !curso.temProfessor();
				cursos.add(curso);
				curso.adicionarProfessor(this);
				if (cursoNaoTinhaProfessor) {
					curso.abrirCurso();
				}
				return true;
			}
		}
		return false;
	}
	
	void removerCurso(Curso curso) {
		if (cadastroAtivo) {
			if (curso != null && contemCurso(curso)) {
				if(curso.seRemoverProfessorZeraProfessoresDoCurso()) {
					curso.fecharCurso();
				}
				curso.removerProfessor(this);
				cursos.remove(curso);
			}
		}
	}
	
	private boolean contemComunidade(Comunidade comunidade) {
		return comunidades.contains(comunidade);
	}
	
	public boolean adicionarComunidade(Comunidade comunidade) {
		if(cadastroAtivo) {
			if (comunidade != null && !contemComunidade(comunidade)) {
				comunidades.add(comunidade);
				comunidade.adicionarProfessor();
				return true;
			}			
		}
		return false;
	}
	
	public boolean removerComunidade(Comunidade comunidade) {
		if (comunidade != null && contemComunidade(comunidade)) {
			comunidade.removerProfessor();
			comunidades.remove(comunidade);
			return true;
		}
		return false;	
	}
	
	public boolean desativarCadastro() {
		if(cadastroAtivo) {
			for (Comunidade comunidade : new ArrayList<>(comunidades)) {
				comunidade.removerProfessor();
				comunidades.remove(comunidade);
			}
			for (Curso curso : new ArrayList<>(cursos)) {
				curso.removerProfessor(this);
				cursos.remove(curso);
			}
			cadastroAtivo = false;
			return true;
		}
		return false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Professor");
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Idade: " + idade);
		System.out.println("E-mail: " + email);
		System.out.println("Área: " + area);
		
		System.out.print("Cursos que leciona: { ");
		for (Curso curso : cursos) {
			System.out.printf("%s ", curso.getNome());
		} System.out.println("}");
		
		System.out.print("Comunidades que participa: { ");
		for (Comunidade comunidade : comunidades) {
			System.out.printf("%s ", comunidade.getNome());
		} System.out.println("}");
	}
	
	@Override
	public void notificar(String mensagem) {
		System.out.printf("Mensagem de professor(a) %s:\n", nome);
		System.out.printf("   %s\n", mensagem);
		
	}
}
