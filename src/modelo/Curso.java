package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Curso {
	private String nome;
	private int vagas;
	private int cargaHorariaHoras;
	private ArrayList<Professor> professores;
	private boolean aberto;
	private int inscricoesAtivas;
	private int inscricoesConcluidas;
	private int inscricoesCanceladas;
	private int inscricoesReprovadas;
	
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
		
		professores = new ArrayList<>();
		if (professor != null) {
			professores.add(professor);
		}
		
		aberto = professores.size() == 1 ? true : false;
			
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
	
	private boolean contemProfessor(Professor professor) {
		return professores.contains(professor);
	}
	
	boolean adicionarProfessor(Professor professor) {
		if (professor != null) {
			if (!contemProfessor(professor)) {
				professores.add(professor);
				return true;
			}
		}
		return false;
	}
	
	boolean removerProfessor(Professor professor) {
		if (professores.size() == 1) { // Não pode deixar o curso sem professor
			return false;
		}
		else {
			return professores.remove(professor);
		}
	}
	
	boolean trocarProfessor(Professor professorAntigo, Professor professorNovo) {
		if (professorAntigo == null || professorNovo == null) {
			return false;
		}
		if (contemProfessor(professorAntigo) && !contemProfessor(professorNovo)) {
			adicionarProfessor(professorNovo); // Essa linha vem primeiro, pois é necessário ter pelo menos 2 professores para remover um
			removerProfessor(professorAntigo);
			return true;
		}
		return false;
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
	
	void registrarTransicaoDeInscricaoAtivaParaConcluida() {
		inscricoesAtivas--;
		inscricoesConcluidas++;
	}
	
	void registrarTransicaoDeInscricaoAtivaParaCancelada() {
		inscricoesAtivas--;
		inscricoesCanceladas++;
	}
	
	void registrarTransicaoDeInscricaoAtivaParaReprovada() {
		inscricoesAtivas--;
		inscricoesReprovadas++;
	}
	
	void registrarTransicaoDeInscricaoCanceladaParaAtiva() {
		inscricoesCanceladas--;
		inscricoesAtivas++;
	}
	
	void registrarTransicaoDeInscricaoReprovadaParaAtiva() {
		inscricoesReprovadas--;
		inscricoesAtivas++;
	}
	
	private int calcularTotalInscricoes() {
		return inscricoesAtivas + inscricoesConcluidas + inscricoesCanceladas + inscricoesReprovadas;
	}
	
	public String getNome() {
		return nome;
	}
    
	public int getCargaHorariaHoras() {
		return cargaHorariaHoras;
	}
	
	public ArrayList<Professor> getProfessor() {
		return professores;
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Curso:");
		System.out.println("Nome: " + nome);
		System.out.println("Vagas: " + vagas);
		System.out.println("Carga horário em horas: " + cargaHorariaHoras);
		
		System.out.print("Professores: { ");
		for (Professor professor : professores) {
			System.out.printf("%s ", professor.getNome());
		} System.out.println("}");
		
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
