package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Curso {
	private String nome;
	private Area area;
	private int cargaHorariaHoras;
	private ArrayList<Professor> professores;
	private boolean aberto;
	private int inscricoesAtivas;
	private int inscricoesConcluidas;
	private int inscricoesCanceladas;
	
	public Curso(String nome, Area area, int cargaHorariaHoras) {		
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		
		this.area = area;
		
		if (cargaHorariaHoras > 0) {
			this.cargaHorariaHoras = cargaHorariaHoras;	
		}
		
		professores = new ArrayList<>();
		
		aberto = false;
			
		inscricoesAtivas = 0;
		inscricoesConcluidas = 0;
		inscricoesCanceladas = 0;
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
	
	void registrarTransicaoDeInscricaoCanceladaParaAtiva() {
		inscricoesCanceladas--;
		inscricoesAtivas++;
	}
	
	private int calcularTotalInscricoes() {
		return inscricoesAtivas + inscricoesConcluidas + inscricoesCanceladas;
	}
	
	public String getNome() { // Usado para exibir o nome dos Cursos no Professor e para emitir o certificado
		return nome;
	}
    
	public int getCargaHorariaHoras() { // Usada para validar a conclusão de curso e para emitir o certificado
		return cargaHorariaHoras;
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Curso");
		System.out.println("Nome: " + nome);
		System.out.println("Área: " + area);
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
