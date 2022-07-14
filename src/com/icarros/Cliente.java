package com.icarros;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Cliente {	
	private String ag;
	private String nome;
	private String email;
	private String telefone;
	private String conta;
	private String saldo;	
}