package com.icarros;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.icarros.global.StringsUtils;
import com.icarros.global.Validador;


/**
 * @apiNote Inicio da aplicação 
 * @author renan
 * @author caio
 * @author matheus
 *  
 */
public class Main {

	public static void main(String[] args) throws IOException{
 		
		/* 
		 * Variaveis utilizadas no scanner, Ag, Conta, Nome, Email, Telefone, Saldo
		 * troquei os tipos para evitar estouro de exception
		 */
		String ag,nome,email,telefone,conta,saldo,msg,opcao = "";
		
		Scanner s = new Scanner(System.in);
		
		Cliente cliente; 
		
 		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		while (!opcao.equals("5")) {
			
			Validador validador = new Validador();
			
			// mostra opÃ§Ãµes do menu
			System.out.println(StringsUtils.SHOW_MENU_APP);
			
			opcao = s.nextLine();

			validador.validarMenu(opcao);
			
			// ao verificar, se entrada conter erro, exibe erro e forï¿½a o loop
			if (!validador.estaValido()) {
				continue;
			}			
			switch(opcao){
				//Cadastrar clientes	
				case "1":
					cliente = new Cliente();
					
					//adicionar validacao para cada caso ?
					try {
						// validar e formatar ?
						System.out.println(StringsUtils.REQUEST_AG);
						msg = StringsUtils.REQUEST_AG;
						s.reset();
						ag = s.nextLine();
 						validador.empty(ag, msg);
						validador.validarApenasNumeros(ag);
						if (!validador.estaValido()) {
							continue;
						}
						cliente.setAg(ag);
						
						//validar se nao tem caracteres, estipular limite ?
						System.out.println(StringsUtils.REQUEST_NUM_CONTA);
						msg = StringsUtils.REQUEST_NUM_CONTA;
						s.reset();
						conta = s.nextLine();
						validador.empty(conta, msg);
						validador.validarApenasNumeros(conta);
						if (!validador.estaValido()) {
							continue;
						}
						cliente.setConta(conta);
						
						//validar se nao tem numeros ?
						System.out.println(StringsUtils.REQUEST_NOME);
						msg = StringsUtils.REQUEST_NOME; 
						nome = s.nextLine();
						validador.empty(nome, msg);
						validador.validarNome(nome);						
						if (!validador.estaValido()) {
							continue;
						}
						cliente.setNome(nome);
						
						//validar se tem o @mail.com ?
						System.out.println(StringsUtils.REQUEST_EMAIL);
						msg = StringsUtils.REQUEST_EMAIL;
						email = s.nextLine();
						validador.empty(email, msg);
						validador.validarEmail(email);
						if (!validador.estaValido()) {
							continue;
						}
						cliente.setEmail(email);
						
						//validar e formatar ? (99)99999-9999
						System.out.println(StringsUtils.REQUEST_CELLPHONE);
						msg = StringsUtils.REQUEST_CELLPHONE;
						telefone = s.nextLine();
						validador.empty(telefone, msg);
						validador.validarApenasNumeros(telefone);
						if (!validador.estaValido()) {
							continue;
						}
						cliente.setTelefone(telefone);
						
						//validar formato e formatar com R$ ?
						System.out.println(StringsUtils.REQUEST_BALANCE);
						msg = StringsUtils.REQUEST_BALANCE;
						saldo = s.nextLine();
						validador.empty(saldo, msg);
						validador.validarSaldo(saldo);
						if (!validador.estaValido()) {
							continue;
						}						
						cliente.setSaldo(saldo);
						
						clientes.add(cliente);
						
						System.out.println(StringsUtils.SUCCESS_REGISTRY);						
					}
					catch (Exception e){
						System.out.println(e.getCause() + e.getMessage());
					}
					break;
					
				//Listar clientes	
				case "2":
					System.out.println(StringsUtils.INFO_TITLE_REGISTRED_CLIENTS);
					int ind = 0;
					for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
						Cliente ShowCliente = (Cliente) iterator.next();
						System.out.println(++ind+" --> "+ShowCliente.toString());
					}
					System.out.println("\n");
					break;
					
				//Gravar arquivo
				case "3":
					try {
						//estabelecer path
						FileWriter file = new FileWriter(StringsUtils.NAME_FILE);
						
						//variavel para gravacao em arquivo
						PrintWriter gravarArquivo = new PrintWriter(file);
						
						// Gravando informacoes
						for (int i=0; i<clientes.size(); i++) {
							gravarArquivo.printf(clientes.get(i)+"\n");
						}
						
						//fecha arquivo
						file.close();
						System.out.println(StringsUtils.SUCCESS_WRITE_FILE);
						break;
					}
					catch(Exception e) {
						System.out.println(StringsUtils.ERROR_WRITE_FILE + e.getCause() + e.getMessage());
					}
			
			    //Ler arquivo
				case "4":
					File file = new File(StringsUtils.NAME_FILE);
					FileReader fileWriter = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileWriter);
					if(bufferedReader.ready())
					{
						System.out.println(StringsUtils.PRINT_INFO_WHITHIN_FILE);
						while(true)
						{
							String stringLine = bufferedReader.readLine();
							if(stringLine == null) break;
							System.out.println("--> "+stringLine);
						}
					}
					break;
					
			    //Sair
				case "5":
					s.close(); // evitando memory leak
					System.out.println(StringsUtils.EXIT);
					break;
					
			}// switch			
		}// while	
	}// main
}// class Main
