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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.icarros.Correntista;
import com.icarros.Fluxo_caixa;
import com.icarros.db.CorrentistaDAO;
import com.icarros.db.Fluxo_caixaDAO;

public class FluxoForm extends JFrame {

	private JPanel contentPane;
	CorrentistaDAO correntista = new CorrentistaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FluxoForm frame = new FluxoForm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public FluxoForm(Correntista correntista_fluxo) throws ParseException {
		setTitle("Fluxo de caixa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFormattedTextField credito = new JFormattedTextField();
		credito.setBounds(105, 44, 192, 20);
		contentPane.add(credito);
		
		JFormattedTextField debito = new JFormattedTextField();
		debito.setBounds(105, 135, 192, 20);
		contentPane.add(debito);
		
		JLabel lblNewLabel = new JLabel("Credito");
		lblNewLabel.setBounds(28, 47, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Debito");
		lblNewLabel_1.setBounds(28, 138, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fluxo_caixaDAO fluxodb = new Fluxo_caixaDAO();
				Fluxo_caixa fluxo = new Fluxo_caixa();
				if(!credito.getText().isBlank() && !credito.getText().isEmpty()) {
					Double credito_form = Double.parseDouble(credito.getText());
					fluxo.setEntrada(credito_form);
					}
				if(!debito.getText().isBlank() && !debito.getText().isEmpty()) {
					Double debito_form = Double.parseDouble(debito.getText());
					fluxo.setSaida(debito_form);
				}
				fluxo.setAg(1);
				fluxo.setConta(1);
				try {
					fluxodb.create(fluxo);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				JOptionPane.showMessageDialog(null, "Transacao registrada!");
				setVisible(false);
			}
		});
		btnNewButton.setBounds(123, 204, 89, 23);
		contentPane.add(btnNewButton);
	}
}
