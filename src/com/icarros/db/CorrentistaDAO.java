package com.icarros.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icarros.Correntista;
import com.icarros.global.QueryUtils;

public class CorrentistaDAO {

	conn_db con;
	ResultSet resultSet;
	
	public CorrentistaDAO() {
		this.con = new conn_db();
		con.connect();
		
		if (!con.connected()) {
			System.out.println("Erro ao se conectar com o banco de dados!");
		}
	}
	
	public void create(Correntista correntista) {
		
		String query = "";
		query = String.format(QueryUtils.QUERY_CREATE_CORRENTISTA, 
				correntista.getAg(),
				correntista.getConta(),
				correntista.getNome(),
				correntista.getEmail(),
				correntista.getTelefone(),
				correntista.getSaldo());
		System.out.println(query);
		
		try {

			con.getStatement().executeUpdate(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
	}
	
	public ArrayList<Correntista> read(Integer Id) {
	
		String query = "";
		if (Id == null) {
			query = String.format(QueryUtils.QUERY_GET_ALL_CORRENTISTA);
		} else {
			query = String.format(QueryUtils.QUERY_GET_CORRENTISTA_BY_ID);
		}
		
		ArrayList<Correntista> correntistas = new ArrayList<Correntista>();
		
		try {

			resultSet = con.getStatement().executeQuery(query);
			
			while (resultSet.next()) {
				Correntista correntista = new Correntista();
				
				correntista.setAg(resultSet.getInt("ag"));
				correntista.setConta(resultSet.getInt("conta"));
				correntista.setNome(resultSet.getString("nome"));
				correntista.setEmail(resultSet.getString("email"));
				correntista.setTelefone(resultSet.getString("telefone"));
				correntista.setSaldo(resultSet.getDouble("saldo"));
				
				correntistas.add(correntista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return correntistas;
	}

	public void update(Correntista correntista) {
		
		String script = String.format(QueryUtils.UPDATE_CORRENTISTA
				, correntista.getNome(), correntista.getEmail(), correntista.getTelefone()
				, correntista.getSaldo().toString().replace(",", ".")
				, correntista.getAg(), correntista.getConta());
		
		try {
			
			con.getStatement().executeLargeUpdate(script);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int ag, int conta) {
		
		String query = "";
		query = String.format(
				QueryUtils.QUERY_DELETE_CORRENTISTA, 
				ag,
				conta);

		System.out.println(query);
		
		try {

			con.getStatement().executeLargeUpdate(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
	
	}
}
