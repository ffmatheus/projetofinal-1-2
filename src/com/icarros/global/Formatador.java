package com.icarros.global;
import com.icarros.Cliente;

public class Formatador {
	
	// usei tamanhos referenciados no PDF do projeto
	
	public static String formatarAgencia(String agencia) {
		// podemos ajustar o tamanho mínimo onde zeros são inclusos aqui
		String zeroEsquerdaAg = "0000".substring(agencia.length()) + agencia;
		return zeroEsquerdaAg;
	}

	public static String formatarConta(String conta) {
		String digito = "-" + conta.substring(conta.length() - 1);
		String contaComDigito = conta.substring(0, conta.length() - 1) + digito;
		
		// mesma coisa aqui, se precisarmos mudar o tamanho mínimo
		String zeroEsquerdaCt = "0000000".substring(contaComDigito.length()) + contaComDigito;
		return zeroEsquerdaCt;
	}
	
	public static String formatarTelefone(String tel) {
		// usando o String.replaceFirst(), posso retornar a string formatada usando Regex
		return tel.replaceFirst("(\\d{2})(\\d{5})(\\d+)", "($1) $2-$3");
	}
	
	public static String formatarSaldo(String saldo) {
		String saldoComMoeda = "R$ " + saldo;
		return saldoComMoeda;
	}
	
	// formata todos os campos do cliente de uma vez
	public static void formatar(Cliente cliente) {
		cliente.setAg(formatarAgencia(cliente.getAg()));
		cliente.setConta(formatarConta(cliente.getConta()));
		cliente.setTelefone(formatarTelefone(cliente.getTelefone()));
		cliente.setSaldo(formatarSaldo(cliente.getSaldo()));
	}
	
	// retorna apenas os numeros da str
	public static String toNumberString(String num) {
		return num.replaceAll("[^0123456789]", "");
	}
	
	// retorna apenas numeros da str, separado em formato de moeda ou double
	public static String toDoubleString(String num) {
		return num.replaceAll("[^\\.\\,0123456789]", "");
	}
}
