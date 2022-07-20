package com.icarros.form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.icarros.Correntista;
import com.icarros.db.CorrentistaDAO;
import com.icarros.global.Formatador;

public class CreateForm extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTextField email;
	CorrentistaDAO correntista_dao = new CorrentistaDAO();

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public CreateForm() throws ParseException {
		setTitle("Cadastro de cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agencia");
		lblNewLabel.setBounds(10, 11, 55, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 67, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		nome = new JTextField();
		nome.setBounds(10, 92, 339, 20);
		contentPane.add(nome);
		nome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Conta");
		lblNewLabel_2.setBounds(201, 11, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setBounds(10, 123, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		email = new JTextField();
		email.setBounds(10, 148, 339, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setBounds(10, 179, 72, 14);
		contentPane.add(lblNewLabel_4);
		
		JFormattedTextField telefone = new JFormattedTextField();
		MaskFormatter mask_tel = new MaskFormatter("(##)#####-####");
		mask_tel.install(telefone);
		telefone.setBounds(10, 204, 344, 20);
		contentPane.add(telefone);
		
		JFormattedTextField conta = new JFormattedTextField();
		MaskFormatter mask_conta = new MaskFormatter("#####-#");
		mask_conta.install(conta);
		conta.setBounds(201, 36, 148, 20);
		contentPane.add(conta);
		
		JFormattedTextField agencia = new JFormattedTextField();
		MaskFormatter mask_ag = new MaskFormatter("####");
		mask_ag.install(agencia);
		agencia.setBounds(10, 36, 148, 20);
		contentPane.add(agencia);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Correntista correntista = new Correntista();
				String agencia_int = Formatador.toNumberString(agencia.getText());
				if(!nome.getText().isBlank() && !nome.getText().isEmpty()) {
					correntista.setAg(Integer.parseInt(agencia_int));
				}
				String conta_int = Formatador.toNumberString(conta.getText());
				if(!nome.getText().isBlank() && !nome.getText().isEmpty()) {
					correntista.setConta(Integer.parseInt(conta_int));
				}
				if(!nome.getText().isBlank() && !nome.getText().isEmpty()) {
					correntista.setNome(nome.getText());
				}
				if(!email.getText().isBlank() && !email.getText().isEmpty()) {
					correntista.setEmail(email.getText());
				}
				String tel_int = telefone.getText().replaceAll("[^\\.0123456789]","");
				if(!telefone.getText().isBlank() && !telefone.getText().isEmpty()){
					correntista.setTelefone(tel_int);
				}
				correntista.setSaldo((double) 0);
				try {
					correntista_dao.create(correntista);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				JOptionPane.showMessageDialog(null, "Correntista criado!");
				setVisible(false);
			
			}
		});
		btnNewButton.setBounds(133, 253, 114, 23);
		contentPane.add(btnNewButton);
	}
}
