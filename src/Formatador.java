import com.icarros.Cliente;

public class Formatador {
	
	// usei tamanhos referenciados no PDF do projeto
	
	public static String formatarAgencia(String agencia) {
		// podemos ajustar o tamanho m�nimo onde zeros s�o inclusos aqui
		String zeroEsquerdaAg = "0000".substring(agencia.length()) + agencia;
		return zeroEsquerdaAg;
	}

	public static String formatarConta(String conta) {
		String digito = "-" + conta.substring(conta.length() - 1);
		String contaComDigito = conta.substring(0, conta.length() - 1) + digito;
		
		// mesma coisa aqui, se precisarmos mudar o tamanho m�nimo
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
	
	public static void formatar(Cliente cliente) {
		// formata todos os campos do cliente de uma vez
		cliente.setAg(formatarAgencia(cliente.getAg()));
		cliente.setConta(formatarConta(cliente.getConta()));
		cliente.setTelefone(formatarTelefone(cliente.getTelefone()));
		cliente.setSaldo(formatarSaldo(cliente.getSaldo()));
	}
}
