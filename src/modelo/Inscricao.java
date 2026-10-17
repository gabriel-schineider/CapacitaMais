package modelo;

public class Inscricao {
	private Curso curso;
	private Certificado certificado;
	private StatusInscricao status;
	private float notaFinal;
	private int tentativas;
	private static final int MAX_TENTATIVAS= 3;
	private static final float NOTA_MINIMA= 8.0f;
	
	Inscricao(Curso curso) {
		this.curso = curso;
		this.notaFinal = 0;
		this.tentativas = 0;
		this.status = StatusInscricao.ATIVA;
		curso.registrarInscricaoAtiva();
	}
	
	 public StatusInscricao fazerProva(float nota){
		 if(!isInscricaoAtiva() || tentativas>= MAX_TENTATIVAS) {
			 return status;
		 }
		 
		 if(nota<0 || nota>10) {
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
			 if(tentativas == MAX_TENTATIVAS) {
				 status= StatusInscricao.REPROVADA;
				 curso.registrarTransicaoDeInscricaoAtivaParaReprovada();
			 }
		 }
		 return status;
	 }
	 
	 public boolean isInscricaoAtiva() {
		 return status == StatusInscricao.ATIVA;
	 }
	 
	 public boolean isInscricaoConcluida() {
		 return status== StatusInscricao.CONCLUIDA;
	 }

	 public boolean isInscricaoCancelada() {
		 return status== StatusInscricao.CANCELADA;
	 }
	 
	 public boolean isInscricaoReprovada() {
		 return status== StatusInscricao.REPROVADA;
	 }
	 
	 public boolean cancelarInscricao() {
		 if(isInscricaoAtiva()) {
			 status = StatusInscricao.CANCELADA;
			 curso.registrarTransicaoDeInscricaoAtivaParaCancelada();
			 return true;
		 }
		 return false;
	 }
	 
	 public boolean reativarInscricao() {
		 if(curso.isAberto() && curso.temVaga()) {
			 if(isInscricaoCancelada()) {
				 status= StatusInscricao.ATIVA;
				 curso.registrarTransicaoDeInscricaoCanceladaParaAtiva();
				 return true;
			 }
			 if(isInscricaoReprovada()) {
				 notaFinal=0;
				 tentativas=0;
				 status= StatusInscricao.ATIVA;
				 curso.registrarTransicaoDeInscricaoReprovadaParaAtiva();
				 return true;
			 }
		 }
		 return false;
	 }
	 
	public Certificado solicitarCertificado(String nomeALuno) {
			 if(!isInscricaoConcluida()) { //impedir que o aluno crie o mesmo certificado duas ou mais vezes
				 return null;
			 }
			 if(certificado==null) {
				 certificado= new Certificado(nomeALuno, curso.getNome(), curso.getCargaHorariaHoras());
			 }
			 return certificado;
		 }
	
	public Curso getCurso() {
		return curso;
	}
}
