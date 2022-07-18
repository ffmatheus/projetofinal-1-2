package com.icarros.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.icarros.Usuario;
import com.icarros.db.UsuarioDAO;
import com.icarros.global.StringsUtils;
import com.icarros.global.Validador;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 556, 342);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(128, 67, 73, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(128, 152, 73, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(211, 66, 197, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEntrar.setBounds(128, 197, 89, 50);
		frame.getContentPane().add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSair.setBounds(319, 197, 89, 50);
		frame.getContentPane().add(btnSair);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(211, 151, 197, 20);
		frame.getContentPane().add(txtSenha);
		
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtUsuario.getText();
				String password = txtSenha.getText();
				
				Validador validador = new Validador();
				validador.loginRequired(username, password);
				
				if(validador.estaValido()) {
					
					UsuarioDAO userDAO = new UsuarioDAO();
					Usuario usuario = new Usuario();
					usuario.setUsername(username);
					usuario.setSenha(password);
					
					Boolean userExiste = userDAO.verificaUser(usuario);
					
					if (userExiste) {
						
						Admin adminForm = new Admin();
						adminForm.setVisible(true);
						userDAO.close();
						frame.dispose();
						
					} else {
						
						JOptionPane.showMessageDialog(null, StringsUtils.ERROR_USER_NOT_FOUND);
						txtUsuario.requestFocusInWindow();
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null, StringsUtils.ERROR_REQUIRED_FIELDS);
					txtUsuario.requestFocusInWindow();
				}
			}
		});
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frame.dispose();
			}
		});
	}
}
