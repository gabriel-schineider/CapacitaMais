package modelo;

public class Inscricao {
	private Curso curso;
	private StatusInscricao status;
	private float notaFinal;
	private int tentativas;
	private static final int MAX_TENTATIVAS= 3;
	private static final float NOTA_MINIMA= 8.0f;
	
	//Construtor
	public Inscricao(Curso curso) {
		// Isso é uma agregação!
		this.curso = curso;
		this.notaFinal = 0;
		this.tentativas = 0;
		this.status = StatusInscricao.ATIVA;
		curso.registrarInscricaoAtiva();
	}
	
	//Métodos
	
/*	public boolean validarInscricao(Curso curso) {
		if(curso!=null && curso.isAberto() && curso.temVaga()) {
			curso.registrarInscricaoAtiva();
			return true;
		}
		return false;
	}*/
	
	 public StatusInscricao fazerProva(float nota){
		 if(status != StatusInscricao.ATIVA) {
			 return status;
		 }
		 if(tentativas>= MAX_TENTATIVAS) {
			 return status;
		 }
		 
		 float notaObtida= curso.aplicarProva(nota);
		 tentativas++;
		 
		 if(notaObtida >= notaFinal) {
			 notaFinal= notaObtida;
		 }
		 
		 if(notaFinal>= NOTA_MINIMA) {
			 status= StatusInscricao.CONCLUIDA;
			 curso.registrarTransicaoDeInscricaoAtivaParaConcluida();
		 } else {
			 if(tentativas >=MAX_TENTATIVAS) {
				 status= StatusInscricao.REPROVADA;
				 curso.registrarTransicaoDeInscricaoAtivaParaReprovada();
			 }
		 }
		 return status;
	 }
	 
	 public boolean isAtiva() {
		 return status == StatusInscricao.ATIVA;
	 }
	 
	 public boolean isConcluida() {
		 return status== StatusInscricao.CONCLUIDA;
	 }

	 public boolean isCancelada() {
		 return status== StatusInscricao.CANCELADA;
	 }
	 
	 public boolean isReprovada() {
		 return status== StatusInscricao.REPROVADA;
	 }
	 
	 public boolean cancelarInscricao() {
		 if(status == StatusInscricao.ATIVA) {
			 status = StatusInscricao.CANCELADA;
			 curso.registrarTransicaoDeInscricaoAtivaParaCancelada();
			 return true;
		 }
		 return false;
	 }
	 
	 public boolean reativarInscricao() {
		 if(curso.isAberto() && curso.temVaga()) {
			 if(status == StatusInscricao.CANCELADA) {
				 status= StatusInscricao.ATIVA;
				 curso.registrarTransicaoDeInscricaoCanceladaParaAtiva();
				 return true;
			 }
			 if(status == StatusInscricao.REPROVADA) {
				 notaFinal=0;
				 tentativas=0;
				 status= StatusInscricao.ATIVA;
				 curso.registrarTransicaoDeInscricaoReprovadaParaAtiva();
				 return true;
			 }
		 }
		 return false;
	 }
	 
	
	public Curso getCurso() {
		return curso;
	}
}