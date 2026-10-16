package modelo;

import java.util.Objects;

public class Curso {
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
	
	public boolean isAberto() {
		return aberto;
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
		if (professorNovo != null) {
			professor = professorNovo;	
		}
	}
	
	void removerProfessor() {
		professor = null;
		fecharCurso();
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
	
	public String getNome() {
		return nome;
	}
    
	public int getCargaHorariaHoras() {
		return cargaHorariaHoras;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Curso:");
		System.out.println("Nome: " + nome);
		System.out.println("Vagas: " + vagas);
		System.out.println("Carga horário em horas: " + cargaHorariaHoras);
		System.out.println("Professor: " + professor.getNome());
		System.out.println("Curso aberto: " + aberto);
		System.out.println("Total de inscrições: " + calcularTotalInscricoes());
		System.out.println("Inscrições ativas: " + inscricoesAtivas);
		System.out.println("Inscrições concluídas: " + inscricoesConcluidas);
		System.out.println("Inscrições canceladas: " + inscricoesCanceladas);
		System.out.println("Inscrições reprovadas: " + inscricoesReprovadas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
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
