package modelo;

import java.util.Objects;

public class Curso {
	// Atributos
	private String nome;
	private int vagas;
	private int cargaHorariaHoras;
	private Professor professor;
	private boolean aberto;
	private int inscricoesAtivas;
	private int inscricoesConcluidas;
	private int inscricoesCanceladas;
	private int inscricoesReprovadas;
	
	// Construtor
	public Curso(String nome, int vagas, int cargaHorariaHoras, Professor professor) {
		
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		if (vagas > 0) {
			this.vagas = vagas;	
		}
		if (cargaHorariaHoras > 0) {
			this.cargaHorariaHoras = cargaHorariaHoras;	
		}
		if (professor != null) {
			this.professor = professor;	
		}
				
		aberto = true;
		inscricoesAtivas = 0;
		inscricoesConcluidas = 0;
		inscricoesCanceladas = 0;
		inscricoesReprovadas = 0;
	}
	
	public void abrirCurso() {
		if (!aberto) {
			aberto = true;
		}
	}
	
	public void fecharCurso() {
		if (aberto) {
			aberto = false;
		}
	}
	
	void trocarProfessor(Professor professorNovo) {
		// Vizibilidade package porque somente a Instituição deve validar e sincronizar as classes Professor e Curso. A Main não.
		professor = professorNovo;
	}
	
	public boolean temVaga() {
		if (inscricoesAtivas < vagas) {
			return true;
		}
		return false;
	}
	
	void alterarNumeroVagas(int vagasNovo) {
		if (vagasNovo >= inscricoesAtivas) {
			vagas = vagasNovo;
		}
	}
	
	public float aplicarProva(float nota) {
		return nota;
	}
	
	public void registrarInscricaoAtiva() {
		inscricoesAtivas++;
	}
	
	public void registrarTransicaoDeInscricaoAtivaParaConcluida() {
		inscricoesAtivas--;
		inscricoesConcluidas++;
	}
	
	public void registrarTransicaoDeInscricaoAtivaParaCancelada() {
		inscricoesAtivas--;
		inscricoesCanceladas++;
	}
	
	public void registrarTransicaoDeInscricaoAtivaParaReprovada() {
		inscricoesAtivas--;
		inscricoesReprovadas++;
	}
	
	public void registrarTransicaoDeInscricaoCanceladaParaAtiva() {
		inscricoesCanceladas--;
		inscricoesAtivas++;
	}
	
	public void registrarTransicaoDeInscricaoReprovadaParaAtiva() {
		inscricoesReprovadas--;
		inscricoesAtivas++;
	}
	
	public int calcularTotalInscricoes() {
		return inscricoesAtivas + inscricoesConcluidas + inscricoesCanceladas + inscricoesReprovadas;
	}
	
	public void exibirNome() {
		System.out.printf("%s", nome);
	}
	
	public void exibirInformacoes() {
		System.out.printf("--> Curso:\n\tNome: %s\n\tVagas: %d\n\tCarga horário em horas: %d\n\tProfessor: ", nome, vagas, cargaHorariaHoras);
		professor.exibirNome();
		System.out.printf("\n\tCurso aberto: %b\n\tTotal de inscrições: %d\n\tInscrições ativas: %d\n\tInscrições concluídas: %d\n\tInscrições canceladas: %d\n\tInscrições reprovadas: %d\n", aberto, calcularTotalInscricoes(), inscricoesAtivas, inscricoesConcluidas, inscricoesCanceladas, inscricoesReprovadas);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(nome, other.nome);
	}
}
