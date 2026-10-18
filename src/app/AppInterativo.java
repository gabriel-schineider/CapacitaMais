package app;

import modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AppInterativo {
	private static ArrayList<Aluno> alunos = new ArrayList<>();
	private static ArrayList<Professor> professores = new ArrayList<>();
	private static ArrayList<Curso> cursos = new ArrayList<>();
	private static ArrayList<Administradora> adms = new ArrayList<>();
	private static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String opcao;
		do {
			exibirMenuInicial();
			opcao = teclado.nextLine();
			switch (opcao) {
				case "0":
					System.out.println("Encerrando programa...");
					break;
				case "1":
					exibirMenuAluno();
					break;
				case "2":
					exibirMenuProfessor();
					break;
				case "3":
					exibirMenuCurso();
					break;
				case "4":
					exibirMenuAdministradoras();
					break;
				default:
					System.out.println("Opção inválida.");
					break;
			}
		} while (!opcao.equals("0"));
		
		teclado.close();
		return;
	}
	

// ########## MENUS ##########
	static void exibirMenuInicial() {
		System.out.println("\n--- Menu Inicial ---");
		System.out.println("[1] Alunos");
		System.out.println("[2] Professores");
		System.out.println("[3] Cursos");
		System.out.println("[4] Administradoras");
		System.out.println("[0] Sair");
		System.out.print("Digite a opção desejada: ");
	}
	
	static void exibirSubmenu(String titulo) {
		System.out.println("\n--- " + titulo + " ---");
		System.out.println("[1] Listar");
		System.out.println("[2] Criar");
		System.out.println("[3] Usar métodos");
		System.out.println("[0] Voltar");
		System.out.print("Digite a opção desejada: ");
	}
	
	static void exibirMenuAluno() {
		String opcao;
		do {
			exibirSubmenu("Alunos");
			opcao = teclado.nextLine();
			switch (opcao) {
				case "0":
					break;
				case "1":
					listarAlunos();
					break;
				case "2":
					criarAluno();
					break;
				case "3":
					System.out.println("Ainda não implementado.");
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (!opcao.equals("0"));
	}
	
	static void exibirMenuProfessor() {
		String opcao;
		do {
			exibirSubmenu("Professores");
			opcao = teclado.nextLine();
			switch (opcao) {
				case "0":
					break;
				case "1":
					listarProfessores();
					break;
				case "2":
					criarProfessor();
					break;
				case "3":
					System.out.println("Ainda não implementado.");
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (!opcao.equals("0"));
	}
 
	static void exibirMenuCurso() {
		String opcao;
		do {
			exibirSubmenu("Cursos");
			opcao = teclado.nextLine();
			switch (opcao) {
				case "0":
					break;
				case "1":
					listarCursos();
					break;
				case "2":
					criarCurso();
					break;
				case "3":
					System.out.println("Ainda não implementado.");
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (!opcao.equals("0"));
	}
 
	static void exibirMenuAdministradoras() {
		String opcao;
		do {
			exibirSubmenu("Administradoras");
			opcao = teclado.nextLine();
			switch (opcao) {
				case "0":
					break;
				case "1":
					listarAdministradoras();
					break;
				case "2":
					criarAdministradora();
					break;
				case "3":
					System.out.println("Ainda não implementado.");
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (!opcao.equals("0"));
	}
// ########## MÉTODOS DE CRIAÇÃO E LISTAGEM ##########
	static void criarAluno() {
		System.out.print("Nome: ");
		String nome = teclado.nextLine();
		
		System.out.print("CPF: ");
		String cpf = teclado.nextLine();
		
		System.out.print("Idade: ");
		int idade = teclado.nextInt();
		
		System.out.print("E-mail: ");
		String email = teclado.nextLine();
		
		Aluno aluno = new Aluno(nome, cpf, idade, email);
		if (alunos.contains(aluno)) { // equals compara o CPF
			System.out.println("Já existe um aluno com esse CPF.");
			return;
		}
		alunos.add(aluno);
		System.out.println("Aluno criado.");
	}
	static void listarAlunos() {
		if (alunos.isEmpty()) {
			System.out.println("Nenhum aluno cadastrado.");
		}
		else {
			for (int i = 0; i < alunos.size(); i++) {
				System.out.println("\n" + (i + 1) + ".");
				alunos.get(i).exibirInformacoes();
			}
		}
	}	
	
	static void criarProfessor() {
		System.out.print("Nome: ");
		String nome = teclado.nextLine();
 
		System.out.print("CPF: ");
		String cpf = teclado.nextLine();
 
		System.out.print("Idade: ");
		int idade = teclado.nextInt();
 
		System.out.print("E-mail: ");
		String email = teclado.nextLine();
 
		//System.out.print("Área: ");
		//String area = teclado.nextLine();
 
		Professor professor = new Professor(nome, cpf, idade, email, null);
		if (professores.contains(professor)) { // equals compara o CPF
			System.out.println("Já existe um professor com esse CPF.");
			return;
		}
		professores.add(professor);
		System.out.println("Professor criado.");
	}
 
	static void listarProfessores() {
		if (professores.isEmpty()) {
			System.out.println("Nenhum professor cadastrado.");
			return;
		}
		for (int i = 0; i < professores.size(); i++) {
			System.out.println("\n" + (i + 1) + ".");
			professores.get(i).exibirInformacoes();
		}
	}
		
	static void criarCurso() {
		System.out.print("Nome: ");
		String nome = teclado.nextLine();
 
		//System.out.print("Área: ");
		//String area = teclado.nextLine();
 
		System.out.print("Carga horária (em horas): ");
		int cargaHoraria = teclado.nextInt();
 
		Curso curso = new Curso(nome, null, cargaHoraria);
		if (cursos.contains(curso)) { // equals compara nome e área
			System.out.println("Já existe um curso com esse nome nessa área.");
			return;
		}
		cursos.add(curso);
		System.out.println("Curso criado.");
	}
 
	static void listarCursos() {
		if (cursos.isEmpty()) {
			System.out.println("Nenhum curso cadastrado.");
			return;
		}
		for (int i = 0; i < cursos.size(); i++) {
			System.out.println("\n" + (i + 1) + ".");
			cursos.get(i).exibirInformacoes();
		}
	}
	
	static void criarAdministradora() {
		System.out.print("Nome: ");
		String nome = teclado.nextLine();
 
		adms.add(new Administradora(nome));
		System.out.println("Administradora criada.");
	}
 
	static void listarAdministradoras() {
		if (adms.isEmpty()) {
			System.out.println("Nenhuma administradora cadastrada.");
			return;
		}
		for (int i = 0; i < adms.size(); i++) {
			System.out.println("\n" + (i + 1) + ".");
			adms.get(i).exibirInformacoes();
		}
	}
}