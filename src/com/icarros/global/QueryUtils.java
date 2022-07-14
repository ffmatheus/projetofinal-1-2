package com.icarros.global;

public class QueryUtils {

	public static final String QUERY_GET_ALL_CORRENTISTA = "SELECT * FROM correntista.correntista";

	public static final String QUERY_GET_CORRENTISTA_BY_ID = "SELECT * FROM correntista.correntista where id = %d";
	
	public static final String INSERT_FLUXO_CAIXA = "INSERT INTO correntista.fluxo_caixa VALUES"
													+ " (%d, %d, %d, %s, %s);";
	
	public static final String UPDATE_CORRENTISTA = "UPDATE correntista.correntista"
													+ " SET nome = '%s', email = '%s', telefone = '%s', saldo = '%s'"
													+ " WHERE ag = %d AND conta = %d;";
	
	public static final String QUERY_CREATE_CORRENTISTA = "INSERT into correntista.correntista (ag, conta, nome, email, telefone, saldo)"
													+ " values (%d, %d, '%s', '%s', '%s', '%s');";
	
	public static final String QUERY_DELETE_CORRENTISTA = "DELETE from correntista.correntista c where "
													+ " c.ag = %d and c.conta = %d";
	
	public static final String QUERY_GET_USUARIO = "SELECT * FROM correntista.usuario WHERE username = '%s' and senha = '%s';";
}
