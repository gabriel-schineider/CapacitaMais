package app;

import modelo.*;

public class AppRoteiro {
	public static void main(String[] args) {
		

		
		System.out.println("\n\nFim dos testes...");
	}

/*
 		System.out.println("Testando a conclusão de curso");
		Curso cursoA = new Curso("A", Area.ADMINISTRACAO, 5);
		Professor professorA = new Professor("Professor A", "cpf", 40, "@mail", Area.EMPREENDEDORISMO);
		Aluno alunoA = new Aluno("Aluno A", "cpf", 20, "@mail");
		
		professorA.adicionarCurso(cursoA);
		alunoA.realizarInscricao(cursoA);
		cursoA.exibirInformacoes();
		
		alunoA.assistirCurso(cursoA, 5);
		alunoA.solicitarCertificado(cursoA);
		cursoA.exibirInformacoes();
		
		alunoA.exibirInformacoes();
		alunoA.ListarCertificados();
 */
	
/*
 		System.out.println("Testando o fechamento automático do curso");
		Curso cursoA = new Curso("A", Area.ADMINISTRACAO, 5);
		Professor professorA = new Professor("Professor A", "cpf", 40, "@mail", Area.EMPREENDEDORISMO);
		cursoA.exibirInformacoes(); System.out.println();
		professorA.adicionarCurso(cursoA);
		cursoA.exibirInformacoes(); System.out.println();
		professorA.removerCurso(cursoA);
		cursoA.exibirInformacoes();
*/


/*
		System.out.println("\nCriando curso...");
		Curso cursoProgramacao = new Curso("Programação 101", Area.TECNOLOGIA, 10, 20);
		//cursoProgramacao.exibirInformacoes();
		
		System.out.println("\nCriando professor...");
		Professor professorXavier= new Professor("Xavier", "000.000.000-00", 30, "x@uerj.br", Area.TECNOLOGIA);
		//professorXavier.exibirInformacoes();
		
		System.out.println("\nAdicionando curso ao professor e, consequentemente, professor ao curso...");
		professorXavier.adicionarCurso(cursoProgramacao);
		//cursoProgramacao.exibirInformacoes();
		//professorXavier.exibirInformacoes();
		
		System.out.println("\nCriando aluno...");
		Aluno alunoCamilo = new Aluno("Camilo", "999.999.999-99", 25, "c@cmail.com");
		//alunoCamilo.exibirInformacoes();
		
		System.out.println("\nAbrindo o curso...");
		cursoProgramacao.abrirCurso();
		
		System.out.println("\nAdicionando aluno ao curso...");
		//alunoCamilo.realizarInscricao(cursoProgramacao);
		//alunoCamilo.exibirInformacoes();
		//cursoProgramacao.exibirInformacoes();
		
		System.out.println("\nCriando comunidade...");
		Comunidade comunidadeCientificaJuridica = new Comunidade(Area.DIREITO, "Jurídicos da Ciência");
		//comunidadeCientificaJuridica.exibirInformacoes();
		
		System.out.println("\nCriando artigos na comunidade...");
		comunidadeCientificaJuridica.criarArtigo("Importância dos direitos humanos", "Os direitos humanos são muito importantes, mas não se esqueçam que a natureza também tem direitos e isso se extende para os animais.");
		comunidadeCientificaJuridica.criarArtigo("Vida e sociedade", "Estudem filosofia e sociologia. São temas importantes.");
		//comunidadeCientificaJuridica.exibirInformacoes();
		
		System.out.println("\nListando os artigos criados...");
		//comunidadeCientificaJuridica.listarTituloDeArtigos();
		
		System.out.println("\nEscolhendo um artigo para ler...");
		//comunidadeCientificaJuridica.buscarArtigo("Vida e sociedade");
		
		System.out.println("\nAdicionando professor à comunidade...");
		professorXavier.adicionarComunidade(comunidadeCientificaJuridica);
		//professorXavier.exibirInformacoes();
		//comunidadeCientificaJuridica.exibirInformacoes();

		System.out.println("\nAdicionando aluno à comunidade...");
		alunoCamilo.adicionarComunidade(comunidadeCientificaJuridica);
		//alunoCamilo.exibirInformacoes();
		//comunidadeCientificaJuridica.exibirInformacoes();
		/*
		System.out.println("\nDisparando notificações...");
		comunidadeCientificaJuridica.notificar("O que estão achando da nossa comunidade?");
		professorXavier.notificar("Essa é uma comunidade interessante!");
		alunoCamilo.notificar("Realmente, é bem curiosa!");
		comunidadeCientificaJuridica.notificar("Pedimos tristemente que saiam da comunidade, pois ela será descontinuada.");
		
		System.out.println("\nRemovendo professor de comunidade...");
		professorXavier.removerComunidade(comunidadeCientificaJuridica);
		professorXavier.exibirInformacoes();
		comunidadeCientificaJuridica.exibirInformacoes();
		
		System.out.println("\nRemovendo aluno da comunidade...");
		alunoCamilo.removerComunidade(comunidadeCientificaJuridica);
		alunoCamilo.exibirInformacoes();
		comunidadeCientificaJuridica.exibirInformacoes();
		
		System.out.println("\nTestando a dinâmica de assistir curso e solicitar certificado...");
		Aluno alunaKeyla = new Aluno("Keyla", "888.888.888-88", 27, "k@kmail.org");
		Curso cursoEmpreendedorismo = new Curso("Empreendedorismo inovador", Area.EMPREENDEDORISMO, 2, 10);
		Professor professoraTatiana = new Professor("Tatiana", "111.111.111-11", 35, "t@uff.br", Area.EMPREENDEDORISMO);
		professoraTatiana.adicionarCurso(cursoEmpreendedorismo);
		alunaKeyla.realizarInscricao(cursoEmpreendedorismo);
		alunaKeyla.assistirCurso(cursoEmpreendedorismo, 5);
		alunaKeyla.solicitarCertificado(cursoEmpreendedorismo);
		//alunaKeyla.exibirInformacoes();
		alunaKeyla.assistirCurso(cursoEmpreendedorismo, 15);
		alunaKeyla.solicitarCertificado(cursoEmpreendedorismo);
		//alunaKeyla.exibirInformacoes();
		//alunaKeyla.ListarCertificados();
		//System.out.println("\nPegando mais um certificado...");
		alunaKeyla.realizarInscricao(cursoProgramacao);
		alunaKeyla.assistirCurso(cursoProgramacao, 20);
		alunaKeyla.solicitarCertificado(cursoProgramacao);
		//alunaKeyla.ListarCertificados();

		System.out.println("#################################################################################################################################");
		System.out.println("\nCriando uma Adm...");
		Administradora admKarol = new Administradora("Karol");
		admKarol.exibirInformacoes();
		System.out.println("\nAdicionando usuarios ao escopo dessa Adm...");
		admKarol.adicionarUsuario(professorXavier);
		admKarol.adicionarUsuario(professoraTatiana);
		admKarol.adicionarUsuario(alunoCamilo);
		admKarol.adicionarUsuario(alunaKeyla);
		admKarol.exibirInformacoes();
		System.out.println("\nExibindo as informações de todos os usuarios dessa Adm...");
		admKarol.exibirInformacoesDosUsuarios();
		System.out.println("\nDesativando o cadastro de todos os usuarios...");
		admKarol.desativarUsuarios();
		System.out.println("\nVerificando se foi tudo desativado...");
		professorXavier.exibirInformacoes();
		professoraTatiana.exibirInformacoes();
		alunoCamilo.exibirInformacoes();
		alunaKeyla.exibirInformacoes();
		cursoProgramacao.exibirInformacoes();
		cursoEmpreendedorismo.exibirInformacoes();
		comunidadeCientificaJuridica.exibirInformacoes();
		System.out.println("#################################################################################################################################");

		System.out.println("\n\nFim dos testes...");
		*/
}