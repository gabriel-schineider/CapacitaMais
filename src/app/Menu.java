package modelo;
 
import java.util.Scanner;
 
public class Menu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
 
		// Criando os objetos
		Professor[] professores = new Professor[2];
		professores[0] = new Professor("Professor A", "000.000.000-00", 40, "professorA@uerj.br", Area.TECNOLOGIA);
		professores[1] = new Professor("Professor B", "111.111.111-11", 37, "professorB@uerj.br", Area.MARKETING);
 
		Aluno[] alunos = new Aluno[2];
		alunos[0] = new Aluno("Aluno A", "222.222.222-22", 20, "alunoA@graduacao.uerj.br");
		alunos[1] = new Aluno("Aluno B", "333.333.333-33", 20, "alunoB@graduacao.uerj.br");
 
		Curso[] cursos = new Curso[3];
		cursos[0] = new Curso("Curso A", Area.ADMINISTRACAO, 10);
		cursos[1] = new Curso("Curso B", Area.TECNOLOGIA, 20);
		cursos[2] = new Curso("Curso C", Area.FINANCAS, 15);
 
		Administradora adm = new Administradora("Adm");
		adm.adicionarUsuario(professores[0]);
		adm.adicionarUsuario(professores[1]);
		adm.adicionarUsuario(alunos[0]);
		adm.adicionarUsuario(alunos[1]);
 
		int opcao = -1;
 
		while (opcao != 0) {
			System.out.println();
			System.out.println("===== CAPACITA+ =====");
			System.out.println("1 - Professor adiciona curso");
			System.out.println("2 - Professor remove curso");
			System.out.println("3 - Aluno se inscreve em curso");
			System.out.println("4 - Aluno cancela inscrição");
			System.out.println("5 - Aluno assiste curso");
			System.out.println("6 - Aluno solicita certificado");
			System.out.println("7 - Listar certificados do aluno");
			System.out.println("8 - Notificar");
			System.out.println("9 - Adm desativa os usuários");
			System.out.println("10 - Exibir informações");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");
			opcao = sc.nextInt();
			System.out.println();
 
			if (opcao == 1) {
				System.out.print("Professor (1 ou 2): ");
				int p = sc.nextInt();
				System.out.print("Curso (1, 2 ou 3): ");
				int c = sc.nextInt();
				boolean deuCerto = professores[p - 1].adicionarCurso(cursos[c - 1]);
				if (deuCerto) {
					System.out.println("Curso adicionado!");
				} else {
					System.out.println("Não foi possível adicionar o curso.");
				}
			}
			else if (opcao == 2) {
				System.out.print("Professor (1 ou 2): ");
				int p = sc.nextInt();
				System.out.print("Curso (1, 2 ou 3): ");
				int c = sc.nextInt();
				professores[p - 1].removerCurso(cursos[c - 1]);
				System.out.println("Pronto!");
			}
			else if (opcao == 3) {
				System.out.print("Aluno (1 ou 2): ");
				int a = sc.nextInt();
				System.out.print("Curso (1, 2 ou 3): ");
				int c = sc.nextInt();
				boolean deuCerto = alunos[a - 1].realizarInscricao(cursos[c - 1]);
				if (deuCerto) {
					System.out.println("Inscrição realizada!");
				} else {
					System.out.println("Não foi possível realizar a inscrição.");
				}
			}
			else if (opcao == 4) {
				System.out.print("Aluno (1 ou 2): ");
				int a = sc.nextInt();
				System.out.print("Curso (1, 2 ou 3): ");
				int c = sc.nextInt();
				boolean deuCerto = alunos[a - 1].cancelarInscricao(cursos[c - 1]);
				if (deuCerto) {
					System.out.println("Inscrição cancelada!");
				} else {
					System.out.println("Não foi possível cancelar a inscrição.");
				}
			}
			else if (opcao == 5) {
				System.out.print("Aluno (1 ou 2): ");
				int a = sc.nextInt();
				System.out.print("Curso (1, 2 ou 3): ");
				int c = sc.nextInt();
				System.out.print("Quantas horas assistiu? ");
				int horas = sc.nextInt();
				alunos[a - 1].assistirCurso(cursos[c - 1], horas);
				System.out.println("Pronto!");
			}
			else if (opcao == 6) {
				System.out.print("Aluno (1 ou 2): ");
				int a = sc.nextInt();
				System.out.print("Curso (1, 2 ou 3): ");
				int c = sc.nextInt();
				boolean deuCerto = alunos[a - 1].solicitarCertificado(cursos[c - 1]);
				if (!deuCerto) {
					System.out.println("Não foi possível emitir o certificado.");
				}
			}
			else if (opcao == 7) {
				System.out.print("Aluno (1 ou 2): ");
				int a = sc.nextInt();
				alunos[a - 1].ListarCertificados();
			}
			else if (opcao == 8) {
				System.out.println("Quem vai receber a mensagem?");
				System.out.println("1 - Professor A");
				System.out.println("2 - Professor B");
				System.out.println("3 - Aluno A");
				System.out.println("4 - Aluno B");
				System.out.println("5 - Adm");
				int quem = sc.nextInt();
				sc.nextLine(); // limpa o Enter que ficou no Scanner
				System.out.print("Mensagem: ");
				String mensagem = sc.nextLine();
				if (quem == 1) {
					professores[0].notificar(mensagem);
				} else if (quem == 2) {
					professores[1].notificar(mensagem);
				} else if (quem == 3) {
					alunos[0].notificar(mensagem);
				} else if (quem == 4) {
					alunos[1].notificar(mensagem);
				} else {
					adm.notificar(mensagem);
				}
			}
			else if (opcao == 9) {
				adm.desativarUsuarios();
				System.out.println("Usuários desativados!");
			}
			else if (opcao == 10) {
				adm.exibirInformacoes();
				System.out.println();
				adm.exibirInformacoesDosUsuarios();
				for (int i = 0; i < cursos.length; i++) {
					cursos[i].exibirInformacoes();
					System.out.println();
				}
			}
			else if (opcao == 0) {
				System.out.println("Até logo!");
			}
			else {
				System.out.println("Opção inválida.");
			}
		}
 
		sc.close();
	}
}
 
