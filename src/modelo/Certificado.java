package modelo;

import java.time.LocalDate;

public class Certificado {
	private String nomeAluno;
	private String nomeCurso;
	private int cargaHorariaHoras;
	private LocalDate dataEmissao;
	
	// Construtor
	public Certificado(String nomeAluno, String nomeCurso, int cargaHorariaHoras) {
		if (nomeAluno != null && !nomeAluno.equals("")) {
			this.nomeAluno = nomeAluno;
		}
		if (nomeCurso != null && !nomeCurso.equals("")) {
			this.nomeCurso = nomeCurso;
		}
		if (cargaHorariaHoras > 0) {
			this.cargaHorariaHoras = cargaHorariaHoras;
		}
		dataEmissao = LocalDate.now();
	}
	
	public void exibirCertificado() {
		System.out.printf("Certificamos que %s concluiu o curso %s de carga horária equivalente à %d horas no dia %s.\n\nAssinatura:  Capacita+\n\t    -----------\n", nomeAluno, nomeCurso, cargaHorariaHoras, dataEmissao);
	}
}
