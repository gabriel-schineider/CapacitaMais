package modelo;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Set;

public class Aluno extends Usuario implements Notificavel{
	private ArrayList<Inscricao> inscricoes;
	private static final int MAX_INSCRICOES_ATIVAS = 3;
	private ArrayList<Certificado> certificados;
	private ArrayList<Comunidade> comunidades;

	public Aluno(String nome, String cpf, int idade, String email) {
		super(nome, cpf, idade, email);
		inscricoes = new ArrayList<Inscricao>();
		certificados = new ArrayList<Certificado>();
		comunidades = new ArrayList<Comunidade>();
	}

	private int contarInscricoesAtivas() {
		int qtdAtivas = 0;
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.isInscricaoAtiva()) {
				qtdAtivas++;
			}
		}
		return qtdAtivas;
	}
	
	public boolean realizarInscricao(Curso curso) {
		if (!isCadastroAtivo()) {
			return false;
		}
		if (curso == null || !curso.isAberto() || !curso.temVaga()) {
			return false;
		}
		if (contarInscricoesAtivas() == MAX_INSCRICOES_ATIVAS) {
			return false;
		}
		// Verificando se já existe uma inscrição para esse curso
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getCurso().equals(curso)) {
				if (inscricao.isInscricaoAtiva() || inscricao.isInscricaoConcluida()) {
					return false;
				}
				if (inscricao.isInscricaoCancelada() || inscricao.isInscricaoReprovada()) {
					inscricao.reativarInscricao();
					return true;
				}
			}
		}
		// Criando uma nova inscrição para esse curso
		Inscricao novaInscricao = new Inscricao(curso);
		inscricoes.add(novaInscricao);
		return true;
	}
	
	public boolean cancelarInscricao(Curso curso) {
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getCurso().equals(curso)) {
				if (inscricao.isInscricaoAtiva()) {
					inscricao.cancelarInscricao();
					return true;
				}
			}
		}
		return false;
	}

	public StatusInscricao receberNota(Curso curso, float nota) {
		for (Inscricao inscricao: inscricoes) {
			if(inscricao.getCurso().equals(curso) && inscricao.isInscricaoAtiva()) {
				StatusInscricao resultado= inscricao.fazerProva(nota);
				if(resultado == StatusInscricao.CONCLUIDA) {
					solicitarCertificado(curso);
				}
				return resultado;
			}
		}
		return null; //não existe inscricao ativa nesse curso
	}
	
	private boolean contemCertificado(Certificado certificado) {
		return certificados.contains(certificado);
	}
	
	public void adicionarCertificado(Certificado certificado) {
		if(certificado != null && !contemCertificado(certificado)) {
			certificados.add(certificado);
		}
	}
	
	boolean solicitarCertificado(Curso curso) {
		for(Inscricao inscricao: inscricoes) {
			if(inscricao.getCurso().equals(curso) && inscricao.isInscricaoConcluida()) {
				Certificado certificado= inscricao.solicitarCertificado(this.nome);
				adicionarCertificado(certificado);
				return true;
			}
		}
		return false;
	}

	//ordenação e exibição da lista certificados
	public void ListarCertificados() {
		Collections.sort(certificados); // usa o compareTo por nome do curso
		for(Certificado certificado: certificados) {
			certificado.exibirCertificado();
		}
	}
	
	private boolean contemComunidade(Comunidade comunidade) {
		return comunidades.contains(comunidade);
	}
	
	public boolean adicionarComunidade(Comunidade comunidade) {
		if (comunidade != null && !contemComunidade(comunidade)) {
			comunidades.add(comunidade);
			comunidade.adicionarAluno();
			return true;
		}
		return false;
	}
	
	public boolean removerComunidade(Comunidade comunidade) {
		if (comunidade != null && contemComunidade(comunidade)) {
			comunidade.removerAluno();
			comunidades.remove(comunidade);
			return true;
		}
		return false;	
	}
	
	public boolean desativarCadastro() {
		if(cadastroAtivo) {
			ArrayList<Comunidade> comunidadesCopia = comunidades;
			for(Comunidade comunidade : comunidadesCopia) {
				comunidade.removerAluno();
				comunidades.remove(comunidade);
			}
			for(Inscricao inscricao: inscricoes) {
				if(inscricao.isInscricaoAtiva()) {
					inscricao.cancelarInscricao();
				}
			}
			cadastroAtivo = false;
			return true;
		}
		return false;
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Aluno");
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Idade: " + idade);
		System.out.println("E-mail: " + email);
		System.out.println("Inscrições ativas: " + contarInscricoesAtivas());
		System.out.println("Quantidade de certificados: " + certificados.size());
	}
	
	@Override
	public void notificar(String mensagem) {
		System.out.printf("Mensagem de aluno(a) %s:\n", nome);
		System.out.printf("   %s\n", mensagem);
	}
}