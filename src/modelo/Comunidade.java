package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Comunidade implements Notificavel {
	private Area area;
	private String nome;
	private int qtdAlunos;
	private int qtdProfessores;
	private ArrayList<Artigo> artigos;
	
	public Comunidade(Area area, String nome) {
		this.area = area;
		if(nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		qtdAlunos = 0;
		qtdProfessores = 0;
		artigos = new ArrayList<>();
	}

	public boolean criarArtigo(String titulo, String conteudo) {
		for (Artigo artigo : artigos) {
			if (artigo.getTitulo().equals(titulo)) {
				return false;
			}
		}
		// Composição
		Artigo artigo = new Artigo(area, titulo, conteudo);
		artigos.add(artigo);
		return true;
	}
	
	public void listarTituloDeArtigos() {
		System.out.printf("Artigos disponíveis na comunidade %s: \n", nome);
		int contador = 1;
		for (Artigo artigo : artigos) {
			System.out.printf("%d. ", contador);
			contador++;
			System.out.println(artigo.getTitulo());
		}
	}
	
	public void buscarArtigo(String titulo) {
		for (Artigo artigo : artigos) {
			if (artigo.getTitulo().equals(titulo)) {
				artigo.exibirArtigo();
				return;
			}
		}
		System.out.println("Busca não encontrada...");
	}
	
	public boolean excluirArtigo(String titulo) {
		for (Artigo artigo : artigos) {
			if (artigo.getTitulo().equals(titulo)) {
				artigos.remove(artigo);
				return true;
			}
		}
		return false;
	}
	
	void adicionarAluno() {
		qtdAlunos++;
	}
	
	void removerAluno() {
		if (qtdAlunos > 0) {
			qtdAlunos--;
		}
	}
	
	void adicionarProfessor() {
		qtdProfessores++;
	}
	
	void removerProfessor() {
		if (qtdProfessores > 0) {
			qtdProfessores--;
		}
	}
	
	private int calcularTotalMembros() {
		return qtdAlunos + qtdProfessores;
	}
	
	private int calcularQtdArtigos() {
		return artigos.size();
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Comunidade:");
		System.out.println("Área: " + area);
		System.out.println("Nome: " + nome);
		System.out.println("Total de membros: " + calcularTotalMembros());
		System.out.println("Quantidade de alunos: " + qtdAlunos);
		System.out.println("Quantidade de professores: " + qtdProfessores);
		System.out.println("Quantidade de artigos criados: " + calcularQtdArtigos());
	}
	
	@Override
	public void notificar(String mensagem) {
		System.out.printf("Mensagem da comunidade %s:\n", nome);
		System.out.printf("   %s\n", mensagem);		
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
		Comunidade other = (Comunidade) obj;
		return area == other.area && Objects.equals(nome, other.nome);
	}
}
