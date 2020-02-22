package transportadora.n3;

public class Fornecedor {
	
	private int idFornecedor;
	private String nomeFornecedor;
	private String emailFornecedor;
	
	public Fornecedor(int idFornecedor, String nomeFornecedor, String emailFornecedor) {
		this.idFornecedor = idFornecedor;
		this.nomeFornecedor = nomeFornecedor;
		this.emailFornecedor = emailFornecedor;
	}
	
	public Fornecedor() {
		
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getEmailFornecedor() {
		return emailFornecedor;
	}

	public void setEmailFornecedor(String emailFornecedor) {
		this.emailFornecedor = emailFornecedor;
	}

	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + ", nomeFornecedor=" + nomeFornecedor + ", emailFornecedor="
				+ emailFornecedor + "]";
	}
}
