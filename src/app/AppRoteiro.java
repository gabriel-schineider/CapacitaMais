package app;

import modelo.*;

public class AppRoteiro {
	public static void main(String[] args) {
		System.out.println("\n Criando 6 cursos diferentes...");
		Curso cursoA = new Curso("A", Area.ADMINISTRACAO, 10);
		Curso cursoB = new Curso("B", Area.TECNOLOGIA, 20);
		Curso cursoC = new Curso("C", Area.FINANCAS, 15);
		Curso cursoD = new Curso("D", Area.LOGISTICA, 10);
		Curso cursoE = new Curso("E", Area.PSICOLOGIA, 15);
		Curso cursoF = new Curso("F", Area.MARKETING, 10);
		
		System.out.println("\n Criando 2 professores diferentes...");
		Professor professorA = new Professor("Professor A", "000.000.000-00", 40, "professorA@uerj.br", Area.EMPREENDEDORISMO);
		Professor professorB = new Professor("Professor B", "111.111.111-11", 37, "professorB@uerj.br", Area.TECNOLOGIA);
		
		System.out.println("\n Criando 2 alunos diferentes...");
		Aluno alunoA = new Aluno("Aluno A", "222.222.222-22", 20, "alunoA@graduacao.uerj.br");
		Aluno alunoB = new Aluno("Aluno B", "333.333.333-33", 20, "alunoB@graduacao.uerj.br");
		
		System.out.println("\n Criando 1 adm...");
		Administradora adm = new Administradora("adm");
		
		System.out.println("\n Adicionando 2 usuários (1 professor e 1 aluno) ao escopo da adm...");
		adm.adicionarUsuario(professorA);
		adm.adicionarUsuario(alunoA);
		
		System.out.println("\n Exibindo as informações dos usuários da adm...");
		//adm.exibirInformacoesDosUsuarios(); // Polimorfismo
		
		System.out.println("\n Professores adicionando cursos (cursos sincronizam a adição de professores)...");
		professorA.adicionarCurso(cursoA);
		professorA.adicionarCurso(cursoB);
		professorA.adicionarCurso(cursoC);
		professorA.adicionarCurso(cursoD);
		professorA.adicionarCurso(cursoE);
		professorA.adicionarCurso(cursoF); // Vai falhar, pois excede o limite de cursos
		professorB.adicionarCurso(cursoA);
		professorB.adicionarCurso(cursoB);
		//professorA.exibirInformacoes();
		//professorB.exibirInformacoes();
		//cursoA.exibirInformacoes();
		//cursoB.exibirInformacoes();
		//cursoC.exibirInformacoes();
		//cursoD.exibirInformacoes();
		//cursoE.exibirInformacoes();
		//cursoF.exibirInformacoes();
		
		System.out.println("\n Alunos realizando inscrição em cursos...");
		alunoA.realizarInscricao(cursoA);
		alunoA.realizarInscricao(cursoB);
		alunoA.realizarInscricao(cursoC);
		alunoA.realizarInscricao(cursoD); // Vai falhar, pois já atingiu o limite de cursos ativos
		alunoB.realizarInscricao(cursoA);
		alunoB.realizarInscricao(cursoF); // Vai falhar, pois não tem nenhum professor nesse curso
		//alunoA.exibirInformacoes(); System.out.println();
		//alunoB.exibirInformacoes();
		
		System.out.println("\n aluno A assistindo cursos B e A e solicitando a emissão de certificado...");
		alunoA.assistirCurso(cursoB, 20);
		alunoA.solicitarCertificado(cursoB);
		alunoA.assistirCurso(cursoA, 7);
		alunoA.solicitarCertificado(cursoA); // Vai falhar, pois não completou a carga horária do curso
		alunoA.assistirCurso(cursoA, 3);
		alunoA.solicitarCertificado(cursoA);
		alunoA.assistirCurso(cursoB, 5);
		
		System.out.println("\n Interface Notificavel em ação...");
		alunoA.notificar("Adorei o curso A! Olha aqui meus certificados em ordem alfabética:");
		alunoA.ListarCertificados();
		professorA.notificar("Que bom! Fico contente.");
		alunoB.notificar("Eu vou precisar cancelar minha inscrição no curso A...");
		professorB.notificar("Poxa, que pena!");
		
		System.out.println("\n aluno B cancelando inscrição em curso A...");
		alunoB.cancelarInscricao(cursoA);
		
		/*
		System.out.println("\n Vendo as informações dos cursos...");
		cursoA.exibirInformacoes();
		cursoB.exibirInformacoes();
		cursoC.exibirInformacoes();
		cursoD.exibirInformacoes();
		cursoE.exibirInformacoes();
		cursoF.exibirInformacoes();
		*/
		
		System.out.println("\n Adm desativando todos os usuários do próprio escopo...");
		adm.desativarUsuarios();
		
		System.out.println("\n Vendo as informações dos professores...");
		professorA.exibirInformacoes();
		professorB.exibirInformacoes();
		
		System.out.println("\n Vendo as informações dos alunos...");
		alunoA.exibirInformacoes();
		alunoB.exibirInformacoes();
		
		System.out.println("\n Vendo as informações dos cursos...");
		cursoA.exibirInformacoes();
		cursoB.exibirInformacoes();
		cursoC.exibirInformacoes();
		cursoD.exibirInformacoes();
		cursoE.exibirInformacoes();
		cursoF.exibirInformacoes();
		
		
		System.out.println("\n\nFim dos testes...");
	}
}