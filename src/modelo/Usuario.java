package modelo;

import java.util.Objects;

public abstract class Usuario {
	protected String nome;
	protected String cpf;
	protected int idade;
	private static final int idadeMinima = 16;
	protected String email;
	protected boolean cadastroAtivo;
	
	// Construtor
	public Usuario(String nome, String cpf, int idade, String email) {
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		if (cpf != null && !cpf.equals("")) {
			this.cpf = cpf;
		}
		if (idade >= idadeMinima) {
			this.idade = idade;
		}
		if (email != null && !email.equals("")) {
			this.email = email;
		}
		cadastroAtivo = true;
	}
	
	// Métodos
	public boolean atualizarEmail(String emailNovo) {
		if (emailNovo != null && !emailNovo.equals("") && !emailNovo.equals(email)) {
			email = emailNovo;
			return true;
		}
		return false;
	}
	
	public void ativarCadastro() {
		if(!cadastroAtivo) {
			cadastroAtivo = true;
		}
	}
	
	public boolean desativarCadastro() {
		if(cadastroAtivo) {
			cadastroAtivo = false;
			return true;
		}
		return false;
	}
	
	public boolean isCadastroAtivo() {
		return cadastroAtivo;
	}
	
	public abstract void exibirInformacoes();

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf);
	}
}
