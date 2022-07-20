package com.icarros.global;

import java.util.Scanner;

// contï¿½m todas as funï¿½ï¿½es para validar as entradas de usuï¿½rio
public class Validador {

	private boolean valido;
	private String mensagemErro;

	// Regex para cada campo
	// apenas UM nï¿½mero entre 1 e 5
	private String escolhaRegex = "^[1-5]{1}$";
	// um ou mais nï¿½meros entre 0 e 9
	private String numRegex = "^[0-9]+$";
	// no mï¿½nimo um @ no meio de qualquer caracter pros dois lados
	private String emailRegex = "^\\S+@\\S+$";
	// filtra qualquer nï¿½mero
	private String nomeRegex = "^\\D+$";

	public Validador() {
		this.valido = true;
		this.mensagemErro = "";
	}

	public void validarMenu(String escolha) {

		if (!escolha.matches(this.escolhaRegex)) {
			this.valido = false;
			this.mensagemErro += "Escolha uma opção do menu.\n";
		}
	}

	// para campos que nï¿½o aceitam nï¿½meros
	public void validarNome(String nome) {

		if (!nome.matches(this.nomeRegex)) {
			this.valido = false;
			this.mensagemErro += "Não é permitido números.\n";
		}
	}

	// para campos como conta e agï¿½ncia
	public void validarApenasNumeros(String campo) {

		if (!campo.matches(this.numRegex)) {
			this.valido = false;
			this.mensagemErro += "Entrada invalida. Informe somente números.\n";
		}
	}

	// apenas verifico estrutura bï¿½sica: xxxx@xxxx
	public void validarEmail(String email) {

		if (!email.matches(this.emailRegex)) {
			this.valido = false;
			this.mensagemErro += "Entre com um email válido.\n";
		}
	}

	public void validarSaldo(String saldo) {

		try {
			Double.parseDouble(Formatador.toDoubleString(saldo));
		} catch (Exception e) {
			this.valido = false;
			this.mensagemErro += "Saldo inválido. informe apenas valores.\n";
		}

	}
	
	public void loginRequired(String user, String password) {
		
		if(user.isBlank() || password.isBlank()) {
			this.mensagemErro = StringsUtils.ERROR_REQUIRED_FIELDS;
			this.valido = false;
		}
	}
	
// funÃ§Ã£o para fazer o loop de entrada caso campo seja vazio
	public boolean campoVazio(String campo) {
		
		if (campo.isBlank()) {
			System.out.println("Campo não pode ser vazio, favor digitar novamente:\n");
			return true;
		} else {
			return false;
		}
	}
	
	// retorna estado de validaÃ§Ã£o e mensagem de erro caso falso	public boolean estaValido() {
	public boolean estaValido() {
		if (!this.valido) {
			System.out.println(this.mensagemErro);
		}

		return this.valido;
	}

	public String empty(String validar, String mensagem) {

		boolean check = true;
		String valor = null;
		Scanner s = new Scanner(System.in);
		System.out.println(StringsUtils.INFO_INPUT_SCANN + validar + "\n");
		while (check == true) {
			if (validar.isBlank()) {
				
				System.out.println(StringsUtils.INFO_INPUT_SCANN + mensagem);
				
				valor = s.next();
				
				if (valor.isBlank()) {
					check = true;
					
					// nova instancia para nÃ£o trazer sujeira 
					s = new Scanner(System.in);
				} else {
					check = false;
				}
				
			} else {
				check = false;
			}
		}

		return valor;

	}

	public String getErro() {
		return this.mensagemErro;
	}
}
