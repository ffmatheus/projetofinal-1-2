package com.icarros.form;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.icarros.Correntista;
import com.icarros.db.CorrentistaDAO;
import com.icarros.global.Formatador;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	DefaultTableModel model;
	CorrentistaDAO correntista = new CorrentistaDAO();
	

	/**
	 * Create the frame.
	 */
	public Admin() {
		setTitle("Gerenciamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 86, 466, 355);
		contentPane.add(scrollPane);
		
		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//cria variavel para capturar o index do array
				int index = tabela.getSelectedRow();
			}
		});
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ag", "conta", "nome", "email", "telefone", "saldo"
				});
		tabela.setModel(model);
		JLabel lblNewLabel = new JLabel("Correntistas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(190, 35, 379, 40);
		contentPane.add(lblNewLabel);
		
		//Exibindo os valores na tabela
		JButton btnNewButton = new JButton("Exibir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Formatador format = new Formatador();
				model.setRowCount(0);
				ArrayList<Correntista> value = correntista.read(null);
				int n = 0;
				for(int i = 0; i < value.size(); i++) {	
					System.out.println(value.get(i));
					Correntista atual = value.get(i);
					String telefone = format.formatarTelefone(atual.getTelefone());
					String conta = format.formatarConta(Integer.toString(atual.getConta()));
					String saldo = format.formatarSaldo(Double.toString(atual.getSaldo()));
					Object[] row = new Object[]{
							Integer.toString(atual.getAg()),
							conta,
							atual.getNome(),
							atual.getEmail(),
							telefone,
							saldo};
					model.addRow(row);
					n++;
				}
				if(n < 1){
					JOptionPane.showMessageDialog(null, "Nao existem registros!","Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(42, 55, 89, 52);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Deletar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(
							null, 
							"Nao existem registros ou nenhum foi selecionado!",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int ag = Integer.parseInt((String) model.getValueAt(row, 0));
				int conta = Integer.parseInt((String) model.getValueAt(row, 1));
				correntista.delete(ag, conta);
				model.removeRow(row);				
			}
		});
		btnNewButton_1.setBounds(42, 162, 89, 52);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Atualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(
							null, 
							"Nao existem registros ou nenhum foi selecionado!",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int ag = Integer.parseInt((String) model.getValueAt(row, 0));
				int conta = Integer.parseInt((String) model.getValueAt(row, 1));
				String nome = (String) model.getValueAt(row, 2);
				String email = (String) model.getValueAt(row, 3);
				String telefone = (String) model.getValueAt(row, 4);
				Double saldo = Double.parseDouble((String) model.getValueAt(row, 5));
				Correntista correntista_update = new Correntista();
				correntista_update.setAg(ag);
				correntista_update.setConta(conta);
				correntista_update.setNome(nome);
				correntista_update.setEmail(email);
				correntista_update.setTelefone(telefone);
				correntista_update.setSaldo(saldo);
				UpdateForm updateForm = new UpdateForm(correntista_update);
				updateForm.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(42, 269, 89, 52);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Inserir");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateForm createForm = null;
				try {
					createForm = new CreateForm();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				createForm.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(42, 376, 89, 52);
		contentPane.add(btnNewButton_3);
	}
}
