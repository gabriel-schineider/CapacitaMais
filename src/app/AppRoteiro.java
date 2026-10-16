package app;

import modelo.*;

public class AppRoteiro {
	public static void main(String[] args) {
		
		System.out.println("\nCriando curso...");
		Curso cursoProgramacao = new Curso("Programação 101", Area.TECNOLOGIA, 10, 20);
		cursoProgramacao.exibirInformacoes();
		
		System.out.println("\nCriando professor...");
		Professor professorXavier= new Professor("Xavier", "000.000.000-00", 30, "x@uerj.br", Area.TECNOLOGIA);
		professorXavier.exibirInformacoes();
		
		System.out.println("\nAdicionando curso ao professor e, consequentemente, professor ao curso...");
		professorXavier.adicionarCurso(cursoProgramacao);
		cursoProgramacao.exibirInformacoes();
		professorXavier.exibirInformacoes();
		
		System.out.println("\nCriando aluno...");
		Aluno alunoCamilo = new Aluno("Camilo", "999.999.999-99", 25, "c@cmail.com");
		alunoCamilo.exibirInformacoes();
		
		System.out.println("\nAbrindo o curso...");
		cursoProgramacao.abrirCurso();
		
		System.out.println("\nAdicionando aluno ao curso...");
		alunoCamilo.realizarInscricao(cursoProgramacao);
		alunoCamilo.exibirInformacoes();
		cursoProgramacao.exibirInformacoes();
		
		System.out.println("\nCriando comunidade...");
		Comunidade comunidadeCientificaJuridica = new Comunidade(Area.DIREITO, "Jurídicos da Ciência");
		comunidadeCientificaJuridica.exibirInformacoes();
		
		System.out.println("\nCriando artigos na comunidade...");
		comunidadeCientificaJuridica.criarArtigo("Importância dos direitos humanos", "Os direitos humanos são muito importantes, mas não se esqueçam que a natureza também tem direitos e isso se extende para os animais.");
		comunidadeCientificaJuridica.criarArtigo("Vida e sociedade", "Estudem filosofia e sociologia. São temas importantes.");
		comunidadeCientificaJuridica.exibirInformacoes();
		
		System.out.println("\nListando os artigos criados...");
		comunidadeCientificaJuridica.listarTituloDeArtigos();
		
		System.out.println("\nEscolhendo um artigo para ler...");
		comunidadeCientificaJuridica.buscarArtigo("Vida e sociedade");
		
		System.out.println("\nAdicionando professor à comunidade...");
		professorXavier.adicionarComunidade(comunidadeCientificaJuridica);
		//professorXavier.exibirInformacoes();
		comunidadeCientificaJuridica.exibirInformacoes();

		System.out.println("\nAdicionando aluno à comunidade...");
		alunoCamilo.adicionarComunidade(comunidadeCientificaJuridica);
		//alunoCamilo.exibirInformacoes();
		comunidadeCientificaJuridica.exibirInformacoes();
		
		System.out.println("\nNotificações na comunidade...");
		professorXavier.notificar("Essa é uma comunidade interessante!");
		alunoCamilo.notificar("Realmente, é bem curiosa!");
		comunidadeCientificaJuridica.notificar("Pedimos tristemente que saiam da comunidade, pois ela será descontinuada.");
		
		System.out.println("\nRemovendo professor de comunidade...");
		professorXavier.removerComunidade(comunidadeCientificaJuridica);
		//professorXavier.exibirInformacoes();
		comunidadeCientificaJuridica.exibirInformacoes();

		System.out.println("\nRemovendo aluno da comunidade...");
		alunoCamilo.removerComunidade(comunidadeCientificaJuridica);
		//alunoCamilo.exibirInformacoes();
		comunidadeCientificaJuridica.exibirInformacoes();

		
		System.out.println("\n\nFim dos testes...");
	}
}
