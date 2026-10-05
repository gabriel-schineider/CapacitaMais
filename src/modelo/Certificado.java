package modelo;

import java.time.LocalDate;

public class Certificado implements Comparable<Certificado> {
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

	// Ordenação
	@Override
	public int compareTo(Certificado outro) {
		return nomeCurso.compareTo(outro.nomeCurso);
	}
	
	public void exibirCertificado() {
		System.out.printf("Certificamos que %s concluiu o curso %s de carga horária equivalente à %d horas no dia %s.\n\nAssinatura:  Capacita+\n\t    -----------\n", nomeAluno, nomeCurso, cargaHorariaHoras, dataEmissao);
	}
}
