package com.icarros.form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.icarros.Correntista;
import com.icarros.db.CorrentistaDAO;

public class UpdateForm extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTextField email;
	private JTextField saldo;
	private JTextField telefone;
	CorrentistaDAO correntista = new CorrentistaDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateForm frame = new UpdateForm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateForm(Correntista correntista_update) {
		setTitle("Atualizar Correntista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nome = new JTextField();
		nome.setBounds(108, 27, 248, 20);
		contentPane.add(nome);
		nome.setColumns(10);
		
		email = new JTextField();
		email.setBounds(108, 58, 248, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		saldo = new JTextField();
		saldo.setBounds(108, 89, 248, 20);
		contentPane.add(saldo);
		saldo.setColumns(10);
		
		telefone = new JTextField();
		telefone.setBounds(108, 120, 248, 20);
		contentPane.add(telefone);
		telefone.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(44, 30, 54, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail");
		lblNewLabel_1.setBounds(44, 61, 54, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Saldo");
		lblNewLabel_2.setBounds(44, 92, 54, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setBounds(44, 123, 54, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nome.getText().isBlank() && !nome.getText().isEmpty()) {
				correntista_update.setNome(nome.getText());
				}
				if(!email.getText().isBlank() && !email.getText().isEmpty()) {
				correntista_update.setEmail(email.getText());
				}
				if(!telefone.getText().isBlank() && !telefone.getText().isEmpty()){
				correntista_update.setTelefone(telefone.getText());
				}
				if(!saldo.getText().isBlank() && !saldo.getText().isEmpty()) {
				correntista_update.setSaldo(Double.parseDouble(saldo.getText()));
				}
				correntista.update(correntista_update);
				JOptionPane.showMessageDialog(null, "Correntista atualizado!");
				setVisible(false);
			}
		});
		btnNewButton.setBounds(148, 173, 137, 53);
		contentPane.add(btnNewButton);
	}
}
