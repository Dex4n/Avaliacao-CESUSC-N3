package transportadora.n3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ControleCRUD {

	private ArrayList<Cliente> clientes;
	private ArrayList<Fornecedor> fornecedores;

	private ControleAcessoBD meuControle = new ControleAcessoBD();

	public String[][] getListaClientes() {
		String[][] matAux;
		matAux = new String[clientes.size()][3];
		for (int j = 0; j < clientes.size(); j++) {
			String[] aux = new String[3];
			aux[0] = clientes.get(j).getIdCliente() + "";
			aux[1] = clientes.get(j).getNomeCliente();
			aux[2] = clientes.get(j).getCpfCliente();
			matAux[j] = aux;
		}
		return matAux;
	}

	public String[][] getListaFornecedores() {
		String[][] matAux = new String[fornecedores.size()][3];
		for (int j = 0; j < fornecedores.size(); j++) {
			String[] aux = new String[3];
			aux[0] = fornecedores.get(j).getIdFornecedor() + "";
			aux[1] = fornecedores.get(j).getNomeFornecedor();
			aux[2] = fornecedores.get(j).getEmailFornecedor();
			matAux[j] = aux;
		}
		return matAux;
	}

	public void cadastrarCliente(int idCliente, String nomeCliente, String cpfCliente, String emailCliente) {

		Connection conn = null;
		PreparedStatement pst = null;

		try {

			conn = Conexao.getConnection();

			pst = conn.prepareStatement(
					"INSERT INTO public.cliente(idcliente, nomecliente, cpfcliente, emailcliente)VALUES (?, ?, ?, ?)");

			pst.setInt(1, idCliente);
			pst.setString(2, nomeCliente);
			pst.setString(3, cpfCliente);
			pst.setString(4, emailCliente);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro durante o cadastro!");
		}
	}

	public void cadastrarFornecedor(int idFornecedor, String nomeFornecedor, String emailFornecedor) {

		Connection conn = null;
		PreparedStatement pst = null;

		try {

			conn = Conexao.getConnection();

			pst = conn.prepareStatement(
					"INSERT INTO public.fornecedor(idfornecedor, nomefornecedor, emailfornecedor)VALUES (?, ?, ?)");

			pst.setInt(1, idFornecedor);
			pst.setString(2, nomeFornecedor);
			pst.setString(3, emailFornecedor);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro durante o cadastro!");
		}
	}

	public void listarCadastroCliente() {

		Cliente newCliente = null;
		clientes = new ArrayList<Cliente>();

		try {

			ResultSet consultaClientes = meuControle.getResultSet("SELECT * FROM public.cliente");

			while (consultaClientes.next()) {

				newCliente = new Cliente();

				newCliente.setIdCliente(consultaClientes.getInt(1));
				newCliente.setNomeCliente(consultaClientes.getString(2));
				newCliente.setCpfCliente(consultaClientes.getString(3));
				newCliente.setEmailCliente(consultaClientes.getString(4));

				this.clientes.add(newCliente);
			}
			JOptionPane.showMessageDialog(null, "Listagem realizada com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro durante a listagem!");
			e.printStackTrace();
		}
	}

	public void listarCadastroFornecedor() {

		Fornecedor newFornecedor = null;
		fornecedores = new ArrayList<Fornecedor>();

		try {

			ResultSet consultaFornecedores = meuControle.getResultSet("SELECT * FROM public.fornecedor");

			while (consultaFornecedores.next()) {

				newFornecedor = new Fornecedor();

				newFornecedor.setIdFornecedor(consultaFornecedores.getInt(1));
				newFornecedor.setNomeFornecedor(consultaFornecedores.getString(2));
				newFornecedor.setEmailFornecedor(consultaFornecedores.getString(3));

				this.fornecedores.add(newFornecedor);
			}
			JOptionPane.showMessageDialog(null, "Listagem realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro durante a listagem!");
		}
	}

	public void alterarCliente(int idCliente, String nomeCliente) {

		Connection conn = null;
		PreparedStatement pst = null;

		for (int i = 0; i < clientes.size(); i++) {

			Cliente cadastroProvisorio = clientes.get(i);

			if (cadastroProvisorio.getNomeCliente().equals(nomeCliente)) {

				JOptionPane.showMessageDialog(null, "Cadastro encontrado!");

				try {

					conn = Conexao.getConnection();

					cadastroProvisorio.setNomeCliente(JOptionPane.showInputDialog("Digite um novo nome"));

					pst = conn.prepareStatement("UPDATE public.cliente SET nomecliente=? WHERE idcliente = ?");

					pst.setString(1, cadastroProvisorio.getNomeCliente());
					pst.setInt(2, idCliente);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro durante o cadastro!");
				}
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Cadastro não encontrado!");
			}
		}
	}

	public void alterarFornecedor(int idFornecedor, String nomeFornecedor) {

		Connection conn = null;
		PreparedStatement pst = null;

		for (int i = 0; i < fornecedores.size(); i++) {

			Fornecedor cadastroProvisorio = fornecedores.get(i);

			if (cadastroProvisorio.getNomeFornecedor().equals(nomeFornecedor)) {

				JOptionPane.showMessageDialog(null, "Cadastro encontrado!");

				try {

					conn = Conexao.getConnection();

					cadastroProvisorio.setNomeFornecedor(JOptionPane.showInputDialog("Digite um novo nome"));

					pst = conn.prepareStatement("UPDATE public.fornecedor SET nomefornecedor=? WHERE idfornecedor = ?");

					pst.setString(1, cadastroProvisorio.getNomeFornecedor());
					pst.setInt(2, idFornecedor);

					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro durante o cadastro");
				}
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Cadastro não encontrado!");
			}
		}
	}

	public void apagarCadastroCliente() {

		Connection connCliente = null;

		PreparedStatement pstCliente = null;

		try {

			connCliente = Conexao.getConnection();

			pstCliente = connCliente.prepareStatement("DELETE FROM cliente");

			pstCliente.executeUpdate();

			JOptionPane.showMessageDialog(null, "Cadastros apagados com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro durante a exclusão!");
		}

		clientes.clear();
	}

	public void apagarCadastroFornecedor() {

		Connection connFornecedor = null;

		PreparedStatement pstFornecedor = null;

		try {

			connFornecedor = Conexao.getConnection();

			pstFornecedor = connFornecedor.prepareStatement("DELETE FROM fornecedor");

			pstFornecedor.executeUpdate();

			JOptionPane.showMessageDialog(null, "Cadastros apagados com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro durante a exclusão!");
		}

		fornecedores.clear();
	}
}
