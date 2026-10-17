package modelo;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Set;

public class Aluno extends Usuario implements Notificavel{
	private ArrayList<Inscricao> inscricoes;
	private static final int MAX_INSCRICOES_ATIVAS = 3;
	private ArrayList<Certificado> certificados;

	public Aluno(String nome, String cpf, int idade, String email) {
		super(nome, cpf, idade, email);
		inscricoes = new ArrayList<>();
		certificados = new ArrayList<>();
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
		if (curso == null || !curso.isAberto()) {
			return false;
		}
		if (contarInscricoesAtivas() == MAX_INSCRICOES_ATIVAS) {
			return false;
		}
		// Verificando se já existe uma inscrição para esse curso
		for (Inscricao inscricao : inscricoes) {
			if (curso.equals(inscricao.getCurso())) {
				if (inscricao.isInscricaoAtiva() || inscricao.isInscricaoConcluida()) {
					return false;
				}
				if (inscricao.isInscricaoCancelada()) {
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
	
	public void assistirCurso(Curso curso, int qtdHorasAssistidas) {
		if (qtdHorasAssistidas > 0) {
			for (Inscricao inscricao : inscricoes) {
				if (curso.equals(inscricao.getCurso())) {
					inscricao.assistirCurso(qtdHorasAssistidas);
				}
			}
		}
	}
	
	private boolean contemCertificado(Certificado certificado) {
		return certificados.contains(certificado);
	}
	
	public boolean solicitarCertificado(Curso curso) {
		for(Inscricao inscricao: inscricoes) {
			if(curso.equals(inscricao.getCurso()) && inscricao.isInscricaoConcluida()) {
				Certificado certificado = inscricao.solicitarCertificado(this.nome);
				return adicionarCertificado(certificado);	
			}
		}
		return false;
	}
	private boolean adicionarCertificado(Certificado certificado) {
		if(certificado != null && !contemCertificado(certificado)) {
			certificados.add(certificado);
			return true;
		}
		return false;
	}

	public void ListarCertificados() { // ordenação e exibição da lista certificados
		Collections.sort(certificados); // usa o compareTo por nome do curso
		int contador = 1;
		System.out.println("    --> Certificados de " + nome);
		for(Certificado certificado: certificados) {
			System.out.printf("%d. ", contador);
			contador++;
			certificado.exibirCertificado();
		}
	}
	
	public boolean desativarCadastro() {
		if(cadastroAtivo) {
			for(Inscricao inscricao: inscricoes) {
				if(inscricao.isInscricaoAtiva()) {
					inscricao.cancelarInscricao();
				}
			}
			// Certificados continuam armazenados
			cadastroAtivo = false;
			return true;
		}
		return false;
	}
	
	public void exibirInformacoes() {
		System.out.println("    --> Informações de Aluno");
		System.out.println("Cadastro ativo: " + cadastroAtivo);
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Idade: " + idade);
		System.out.println("E-mail: " + email);
		System.out.println("Quantidade de certificados: " + certificados.size());
		System.out.println("Inscrições ativas: " + contarInscricoesAtivas());
		
		System.out.print("Cursos que está fazendo: { ");
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.isInscricaoAtiva()) {
				System.out.printf("%s ", inscricao.getCurso().getNome());
			}
		} System.out.println("}");
	}
	
	@Override
	public void notificar(String mensagem) {
		System.out.printf("Mensagem de aluno(a) %s:\n", nome);
		System.out.printf("   %s\n", mensagem);
	}
}