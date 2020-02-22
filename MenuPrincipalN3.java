package transportadora.n3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuPrincipalN3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCliente;
	private JTable tableFornecedor;
	private ControleCRUD controladorCRUD;

	private JTextField textFieldIdFornecedor;
	private JTextField textFieldNomeFornecedor;
	private JTextField textFieldEmailFornecedor;
	private JTextField textFieldIdCliente;
	private JTextField textFieldNomeCliente;
	private JTextField textFieldEmailCliente;
	private JTextField textFieldCPFCliente;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipalN3 frame = new MenuPrincipalN3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipalN3() throws ParseException {

		controladorCRUD = new ControleCRUD();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 564, 399);
		contentPane.add(tabbedPane);

		JPanel panelCadastrarCliente = new JPanel();
		tabbedPane.addTab("Cliente", null, panelCadastrarCliente, null);
		panelCadastrarCliente.setLayout(null);

		JButton btnAtualizarCliente = new JButton("Atualizar");
		btnAtualizarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				JOptionPane.showMessageDialog(null, "Informe o ID e o nome do cliente para procurá-lo!");

				// Habilitar os campos de ID e NOME do formulário.
				setEditableCamposCliente(true, true, false, false);
			}
		});

		JButton btnCadastrarCliente = new JButton("Cadastrar");
		btnCadastrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// Habilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposCliente(true, true, true, true);
			}
		});
		btnCadastrarCliente.setBounds(10, 11, 110, 23);
		panelCadastrarCliente.add(btnCadastrarCliente);
		btnAtualizarCliente.setBounds(10, 45, 110, 23);
		panelCadastrarCliente.add(btnAtualizarCliente);

		JButton btnDeletarCliente = new JButton("Deletar");
		btnDeletarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// Desabilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposCliente(false, false, false, false);
				controladorCRUD.apagarCadastroCliente();
			}
		});

		JButton btnListarCliente = new JButton("Listar");
		btnListarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				// Desabilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposCliente(false, false, false, false);

				DefaultTableModel meuModelTableClientes = new DefaultTableModel(new String[][] { { "", "", "" }, },
						new String[] { "Código Cliente", "Nome Cliente", "CPF Cliente" });

				tableCliente.setModel(meuModelTableClientes);

				controladorCRUD.listarCadastroCliente();

				tableCliente.setModel(new DefaultTableModel(controladorCRUD.getListaClientes(),
						new String[] { "Código Cliente", "Nome Cliente", "CPF Cliente" }));
			}
		});
		btnListarCliente.setBounds(10, 79, 110, 23);
		panelCadastrarCliente.add(btnListarCliente);
		btnDeletarCliente.setBounds(10, 113, 110, 23);
		panelCadastrarCliente.add(btnDeletarCliente);

		JScrollPane scrollPaneCliente = new JScrollPane();
		scrollPaneCliente.setBounds(130, 11, 419, 91);
		panelCadastrarCliente.add(scrollPaneCliente);

		tableCliente = new JTable();

		DefaultTableModel meuModelTableClientes = new DefaultTableModel(
				new String[][] { { "1", "Alexandre", "123.456.789-10" }, },
				new String[] { "Código Cliente", "Nome Cliente", "CPF Cliente" });

		tableCliente.setModel(meuModelTableClientes);

		scrollPaneCliente.setViewportView(tableCliente);

		JButton btnSairCliente = new JButton("Sair");
		btnSairCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		btnSairCliente.setBounds(460, 338, 89, 23);
		panelCadastrarCliente.add(btnSairCliente);

		JPanel panelClienteCadastrar = new JPanel();
		panelClienteCadastrar.setLayout(null);
		panelClienteCadastrar.setBounds(10, 147, 539, 180);
		panelCadastrarCliente.add(panelClienteCadastrar);

		JLabel lblIdCliente = new JLabel("ID Cliente");
		lblIdCliente.setBounds(10, 11, 100, 20);
		panelClienteCadastrar.add(lblIdCliente);

		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setBounds(10, 42, 100, 20);
		panelClienteCadastrar.add(lblNomeCliente);

		JLabel lblCpfCliente = new JLabel("CPF Cliente");
		lblCpfCliente.setBounds(10, 73, 100, 20);
		panelClienteCadastrar.add(lblCpfCliente);

		JLabel lblEmailCliente = new JLabel("E-mail Cliente");
		lblEmailCliente.setBounds(10, 104, 100, 20);
		panelClienteCadastrar.add(lblEmailCliente);

		textFieldIdCliente = new JTextField();
		textFieldIdCliente.setEditable(false);
		textFieldIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIdCliente.setColumns(10);
		textFieldIdCliente.setBounds(120, 11, 50, 20);
		panelClienteCadastrar.add(textFieldIdCliente);

		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setEditable(false);
		textFieldNomeCliente.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setBounds(120, 42, 259, 20);
		panelClienteCadastrar.add(textFieldNomeCliente);

		textFieldCPFCliente = new JTextField();
		textFieldCPFCliente.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldCPFCliente.setEditable(false);
		textFieldCPFCliente.setColumns(10);
		textFieldCPFCliente.setBounds(120, 73, 259, 20);
		panelClienteCadastrar.add(textFieldCPFCliente);

		textFieldEmailCliente = new JTextField();
		textFieldEmailCliente.setEditable(false);
		textFieldEmailCliente.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldEmailCliente.setColumns(10);
		textFieldEmailCliente.setBounds(120, 104, 259, 20);
		panelClienteCadastrar.add(textFieldEmailCliente);

		JButton btnSalvarCliente = new JButton("Salvar");
		btnSalvarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!validaFormularioCliente()) {
					JOptionPane.showMessageDialog(null,
							"Os campos de <Código, Nome, CPF e Email> obrigatoriamente devem estar preenchidos!");
				} else {

					int idCliente = getTextCliente().getIdCliente();
					String nomeCliente = getTextCliente().getNomeCliente();
					String cpfCliente = getTextCliente().getCpfCliente();
					String emailCliente = getTextCliente().getEmailCliente();

					controladorCRUD.cadastrarCliente(idCliente, nomeCliente, cpfCliente, emailCliente);

					limpaFormulario();

					tableCliente.setModel(new DefaultTableModel(controladorCRUD.getListaClientes(),
							new String[] { "Código Cliente", "Nome Cliente", "CPF Cliente" }));
					// Desabilita edição dos componentes JText para inserção dos dados.
					setEditableCamposCliente(false, false, false, false);
				}
			}
		});
		btnSalvarCliente.setBounds(290, 146, 89, 23);
		panelClienteCadastrar.add(btnSalvarCliente);

		JButton btnLimpaCliente = new JButton("Limpar");
		btnLimpaCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				limpaFormulario();
			}
		});
		btnLimpaCliente.setBounds(191, 146, 89, 23);
		panelClienteCadastrar.add(btnLimpaCliente);

		JButton btnConsultarClienteIdNome = new JButton("Consultar");
		btnConsultarClienteIdNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// Após habilitado o componente de JText através do botão Atualizar, este botão
				// é responsável por verificar se
				// o formulário está preenchido para que possa ser obtido os dados para passar
				// como parâmetro ao PreparedStatement
				// de update da classe ControleCRUD.

				if (!validaFormularioClienteUpdate()) {
					JOptionPane.showMessageDialog(null,
							"Os campos de <Código e Nome> obrigatoriamente devem estar preenchidos!");
				} else {
					int idProvisorio = Integer.parseInt(textFieldIdCliente.getText());
					String nomeProvisorio = textFieldNomeCliente.getText();

					controladorCRUD.alterarCliente(idProvisorio, nomeProvisorio);
					limpaFormulario();
				}
				// Desabilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposCliente(false, false, false, false);
			}
		});
		btnConsultarClienteIdNome.setBounds(279, 10, 100, 23);
		panelClienteCadastrar.add(btnConsultarClienteIdNome);

		JButton btnLimparTableCliente = new JButton("Limpar");
		btnLimparTableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				DefaultTableModel meuModelTableClientes = new DefaultTableModel(
						new String[][] { { "N/A", "N/A", "N/A" }, },
						new String[] { "Código Cliente", "Nome Cliente", "CPF Cliente" });

				tableCliente.setModel(meuModelTableClientes);
			}
		});
		btnLimparTableCliente.setBounds(460, 113, 89, 23);
		panelCadastrarCliente.add(btnLimparTableCliente);

		JPanel panelCadastrarFornecedor = new JPanel();
		tabbedPane.addTab("Fornecedor", null, panelCadastrarFornecedor, null);
		panelCadastrarFornecedor.setLayout(null);

		JButton btnCadastrarFornecedor = new JButton("Cadastrar");
		btnCadastrarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				// Habilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposFornecedor(true, true, true);
			}
		});
		btnCadastrarFornecedor.setBounds(10, 11, 110, 23);
		panelCadastrarFornecedor.add(btnCadastrarFornecedor);

		JButton btnListarFornecedor = new JButton("Listar");
		btnListarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				// Desabilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposFornecedor(false, false, false);

				DefaultTableModel meuModelTableFornecedor = new DefaultTableModel(new String[][] { { "", "", "" }, },
						new String[] { "Código Fornecedor", "Nome Fornecedor", "Email Fornecedor" });

				tableFornecedor.setModel(meuModelTableFornecedor);

				controladorCRUD.listarCadastroFornecedor();

				tableFornecedor.setModel(new DefaultTableModel(controladorCRUD.getListaFornecedores(),
						new String[] { "Código Fornecedor", "Nome Fornecedor", "Email Fornecedor" }));
			}
		});

		JButton btnAtualizarFornecedor = new JButton("Atualizar");
		btnAtualizarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				JOptionPane.showMessageDialog(null, "Informe o ID e o nome do fornecedor para procurá-lo!");

				// Habilitar os campos de ID e NOME do formulário.
				setEditableCamposFornecedor(true, true, false);
			}
		});
		btnAtualizarFornecedor.setBounds(10, 45, 110, 23);
		panelCadastrarFornecedor.add(btnAtualizarFornecedor);
		btnListarFornecedor.setBounds(10, 79, 110, 23);
		panelCadastrarFornecedor.add(btnListarFornecedor);

		JButton btnDeletarFornecedor = new JButton("Deletar");
		btnDeletarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				// Desabilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposFornecedor(false, false, false);

				controladorCRUD.apagarCadastroFornecedor();
			}
		});
		btnDeletarFornecedor.setBounds(10, 113, 110, 23);
		panelCadastrarFornecedor.add(btnDeletarFornecedor);

		JScrollPane scrollPaneFornecedor = new JScrollPane();
		scrollPaneFornecedor.setBounds(130, 11, 419, 91);
		panelCadastrarFornecedor.add(scrollPaneFornecedor);

		tableFornecedor = new JTable();

		DefaultTableModel meuModelTableFornecedores = new DefaultTableModel(
				new String[][] { { "1", "Tirol", "tirol@vendas.com.br" }, },
				new String[] { "Código Fornecedor", "Nome Fornecedor", "Email Fornecedor" });

		tableFornecedor.setModel(meuModelTableFornecedores);

		scrollPaneFornecedor.setViewportView(tableFornecedor);

		JButton btnSairFornecedor = new JButton("Sair");
		btnSairFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		btnSairFornecedor.setBounds(460, 338, 89, 23);
		panelCadastrarFornecedor.add(btnSairFornecedor);

		JPanel panelFornecedorCadastrar = new JPanel();
		panelFornecedorCadastrar.setBounds(10, 147, 539, 180);
		panelCadastrarFornecedor.add(panelFornecedorCadastrar);
		panelFornecedorCadastrar.setLayout(null);

		JLabel lblIdFornecedor = new JLabel("ID Fornecedor");
		lblIdFornecedor.setBounds(10, 11, 130, 20);
		panelFornecedorCadastrar.add(lblIdFornecedor);

		JLabel lblNomeFornecedor = new JLabel("Nome Fornecedor");
		lblNomeFornecedor.setBounds(10, 42, 130, 20);
		panelFornecedorCadastrar.add(lblNomeFornecedor);

		JLabel lblEmailFornecedor = new JLabel("E-mail Fornecedor");
		lblEmailFornecedor.setBounds(10, 73, 130, 20);
		panelFornecedorCadastrar.add(lblEmailFornecedor);

		textFieldIdFornecedor = new JTextField();
		textFieldIdFornecedor.setEditable(false);
		textFieldIdFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIdFornecedor.setBounds(150, 11, 50, 20);
		panelFornecedorCadastrar.add(textFieldIdFornecedor);
		textFieldIdFornecedor.setColumns(10);

		textFieldNomeFornecedor = new JTextField();
		textFieldNomeFornecedor.setEditable(false);
		textFieldNomeFornecedor.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldNomeFornecedor.setColumns(10);
		textFieldNomeFornecedor.setBounds(150, 42, 259, 20);
		panelFornecedorCadastrar.add(textFieldNomeFornecedor);

		textFieldEmailFornecedor = new JTextField();
		textFieldEmailFornecedor.setEditable(false);
		textFieldEmailFornecedor.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldEmailFornecedor.setColumns(10);
		textFieldEmailFornecedor.setBounds(150, 73, 259, 20);
		panelFornecedorCadastrar.add(textFieldEmailFornecedor);

		JButton btnSalvarFornecedor = new JButton("Salvar");
		btnSalvarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!validaFormularioFornecedor()) {
					JOptionPane.showMessageDialog(null,
							"Os campos de <Código, Nome e Email> obrigatoriamente devem estar preenchidos!");
				} else {

					int idFornecedor = getTextFornecedor().getIdFornecedor();
					String nomeFornecedor = getTextFornecedor().getNomeFornecedor();
					String emailFornecedor = getTextFornecedor().getEmailFornecedor();

					controladorCRUD.cadastrarFornecedor(idFornecedor, nomeFornecedor, emailFornecedor);

					limpaFormulario();

					tableFornecedor.setModel(new DefaultTableModel(controladorCRUD.getListaFornecedores(),
							new String[] { "Código Fornecedor", "Nome Fornecedor", "Email Fornecedor" }));
				}
			}
		});
		btnSalvarFornecedor.setBounds(290, 146, 89, 23);
		panelFornecedorCadastrar.add(btnSalvarFornecedor);

		JButton btnLimparFornecedor = new JButton("Limpar");
		btnLimparFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				limpaFormulario();
			}
		});
		btnLimparFornecedor.setBounds(191, 146, 89, 23);
		panelFornecedorCadastrar.add(btnLimparFornecedor);

		JButton btnConsultarFornecedorIdNome = new JButton("Consultar");
		btnConsultarFornecedorIdNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				// Após habilitado o componente de JText através do botão Atualizar, este botão
				// é responsável por verificar se
				// o formulário está preenchido para que possa ser obtido os dados para passar
				// como parâmetro ao PreparedStatement
				// de update da classe ControleCRUD.

				if (!validaFormularioFornecedorUpdate()) {
					JOptionPane.showMessageDialog(null,
							"Os campos de <Código e Nome> obrigatoriamente devem estar preenchidos!");
				} else {

					int idProvisorio = Integer.parseInt(textFieldIdFornecedor.getText());
					String nomeProvisorio = textFieldNomeFornecedor.getText();

					controladorCRUD.alterarFornecedor(idProvisorio, nomeProvisorio);

					limpaFormulario();

				}
				// Desabilitar edição dos componentes JText para inserção dos dados.
				setEditableCamposFornecedor(false, false, false);
			}
		});
		btnConsultarFornecedorIdNome.setBounds(309, 10, 100, 23);
		panelFornecedorCadastrar.add(btnConsultarFornecedorIdNome);

		JButton btnLimparTableFornecedor = new JButton("Limpar");
		btnLimparTableFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				DefaultTableModel meuModelTableFornecedor = new DefaultTableModel(
						new String[][] { { "N/A", "N/A", "N/A" }, },
						new String[] { "Código Fornecedor", "Nome Fornecedor", "Email Fornecedor" });

				tableFornecedor.setModel(meuModelTableFornecedor);
			}
		});
		btnLimparTableFornecedor.setBounds(460, 113, 89, 23);
		panelCadastrarFornecedor.add(btnLimparTableFornecedor);

	}

	// Desabilitar ou habilitar campos da guia cliente
	public void setEditableCamposCliente(boolean enableId, boolean enableNome, boolean enableCPF, boolean elableEmail) {
		textFieldIdCliente.setEditable(enableId);
		textFieldNomeCliente.setEditable(enableNome);
		textFieldCPFCliente.setEditable(enableCPF);
		textFieldEmailCliente.setEditable(elableEmail);
	}

	// Desabilitar ou habilitar campos da guia fornecedor
	public void setEditableCamposFornecedor(boolean enableId, boolean enableNome, boolean enableEmail) {
		textFieldIdFornecedor.setEditable(enableId);
		textFieldNomeFornecedor.setEditable(enableNome);
		textFieldEmailFornecedor.setEditable(enableEmail);
	}

	// Esvazia o conteúdo dos formulários
	public void limpaFormulario() {
		textFieldIdCliente.setText("");
		textFieldNomeCliente.setText("");
		textFieldCPFCliente.setText("");
		textFieldEmailCliente.setText("");

		textFieldIdFornecedor.setText("");
		textFieldNomeFornecedor.setText("");
		textFieldEmailFornecedor.setText("");
	}

	// Verifica o preenchimento de todos os campos do formulário de cliente
	public boolean validaFormularioCliente() {
		return (!textFieldIdCliente.getText().equals("") && !textFieldNomeCliente.getText().equals("")
				&& !textFieldCPFCliente.getText().equals("") && !textFieldEmailCliente.getText().equals(""));
	}

	// Verifica o preenchimento dos campos do formulário de cliente para update
	public boolean validaFormularioClienteUpdate() {
		return (!textFieldIdCliente.getText().equals("") && !textFieldNomeCliente.getText().equals(""));
	}

	// Verifica o preenchimento de todos os campos do formulário de fornecedor
	public boolean validaFormularioFornecedor() {
		return (!textFieldIdFornecedor.getText().equals("") && !textFieldNomeFornecedor.getText().equals("")
				&& !textFieldEmailFornecedor.getText().equals(""));
	}

	// Verifica o preenchimento dos campos do formulário de fornecedor para update
	public boolean validaFormularioFornecedorUpdate() {
		return (!textFieldIdFornecedor.getText().equals("") && !textFieldNomeFornecedor.getText().equals(""));
	}

	// Obtém os dados de cliente do formulário para passar como parâmetro ao método
	// de insert da classe ControleCRUD
	public Cliente getTextCliente() {

		Cliente newCliente = new Cliente();

		newCliente.setIdCliente(Integer.parseInt(textFieldIdCliente.getText()));
		newCliente.setNomeCliente(textFieldNomeCliente.getText());
		newCliente.setCpfCliente(textFieldCPFCliente.getText());
		newCliente.setEmailCliente(textFieldEmailCliente.getText());

		return newCliente;
	}

	// Obtém os dados de fornecedor do formulário para passar como parâmetro ao
	// método de insert da classe ControleCRUD
	public Fornecedor getTextFornecedor() {

		Fornecedor newFornecedor = new Fornecedor();

		newFornecedor.setIdFornecedor(Integer.parseInt(textFieldIdFornecedor.getText()));
		newFornecedor.setNomeFornecedor(textFieldNomeFornecedor.getText());
		newFornecedor.setEmailFornecedor(textFieldEmailFornecedor.getText());

		return newFornecedor;
	}
}
