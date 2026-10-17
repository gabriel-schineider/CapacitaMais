package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Curso {
	private String nome;
	private Area area;
	private int vagas;
	private int cargaHorariaHoras;
	private ArrayList<Professor> professores;
	private boolean aberto;
	private int inscricoesAtivas;
	private int inscricoesConcluidas;
	private int inscricoesCanceladas;
	private int inscricoesReprovadas;
	
	public Curso(String nome, Area area, int vagas, int cargaHorariaHoras) {		
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		
		this.area = area;
		
		if (vagas > 0) {
			this.vagas = vagas;	
		}
		
		if (cargaHorariaHoras > 0) {
			this.cargaHorariaHoras = cargaHorariaHoras;	
		}
		
		professores = new ArrayList<>();
		
		aberto = false;
			
		inscricoesAtivas = 0;
		inscricoesConcluidas = 0;
		inscricoesCanceladas = 0;
		inscricoesReprovadas = 0;
	}
	
	public boolean isAberto() {
		return aberto;
	}

	public void abrirCurso() {
		if (temProfessor() && !aberto) {
			aberto = true;
		}
	}
	
	boolean temProfessor() {
		if (professores.size() > 0) {
			return true;
		}
		return false;
	}

	public void fecharCurso() {
		if (aberto) {
			aberto = false;
		}
	}
	
	boolean seRemoverProfessorZeraProfessoresDoCurso() {
		return professores.size() == 1;
	}
	
	void adicionarProfessor(Professor professor) {  // Somente sincroniza. Por isso visibilidade package
		professores.add(professor); 
	}
	
	void removerProfessor(Professor professor) {  // Somente sincroniza. Por isso visibilidade package
		professores.remove(professor);
	}
	
	public boolean temVaga() {
		if (inscricoesAtivas < vagas) {
			return true;
		}
		return false;
	}
	
	public void alterarNumeroVagas(int vagasNovo) {
		if (vagasNovo > 0 && vagasNovo >= inscricoesAtivas) {
			vagas = vagasNovo;
		}
	}
	
	void registrarInscricaoAtiva() {
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
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Curso");
		System.out.println("Nome: " + nome);
		System.out.println("Área: " + area);
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
		return Objects.hash(area, nome);
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
		return area == other.area && Objects.equals(nome, other.nome);
	}
}
