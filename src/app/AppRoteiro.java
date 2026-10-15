package app;

import modelo.*;

public class AppRoteiro {
	public static void main(String[] args) {
		Certificado c = new Certificado("Maria", "Desenvolvimento Web", 30);
		c.exibirCertificado();
		
		Aluno a = new Aluno("Maria", "00000000000", 16, "um@xmail.br");
		a.exibirInformacoes();
	}
}
