package app;

import modelo.*;

public class AppRoteiro {
	public static void main(String[] args) {
		//Certificado c = new Certificado("Maria", "Desenvolvimento Web", 30);
		//c.exibirCertificado();
		//Aluno a = new Aluno("Maria", "00000000000", 16, "um@xmail.br");
		//a.exibirInformacoes();
		
		Instituicao i = new Instituicao("UERJ");
		i.exibirInformacoes();
		
		System.out.println();
		//Curso curso = new Curso("Programação", 10, 20, null);
		//curso.exibirInformacoes();
		i.criarCurso("Programação", 10, 20);
		i.listarCursos();
		
		System.out.println();
		Professor professor = new Professor("Xavier", "000.000.000-00", 20, "x@uerj.br", Especialidade.TECNOLOGIA);
		professor.exibirInformacoes();
		
		System.out.println();
		professor.adicionarCurso(i.getCurso("Programação"));
		
		System.out.println();
		i.listarCursos();
		professor.exibirInformacoes();
		
		System.out.println();
	}
}
