package com.icarros.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icarros.Fluxo_caixa;
import com.icarros.global.QueryUtils;

public class Fluxo_caixaDAO {

	conn_db con;
	ResultSet resultSet;
	
	public Fluxo_caixaDAO() {
		this.con = new conn_db();
		con.connect();
		
		if (!con.connected()) {
			System.out.println("Erro ao se conectar com o banco de dados!");
		}
	}

	public void create (Fluxo_caixa fluxo) {
		
		try {
			
			String insert = String.format(QueryUtils.INSERT_FLUXO_CAIXA
					, fluxo.getAg(), fluxo.getConta(), fluxo.getFluxo()
					, fluxo.getEntrada().toString().replace(",", ".")
					, fluxo.getSaida().toString().replace(",", "."));
			
			con.getStatement().executeUpdate(insert);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Fluxo_caixa> read() {
		// precisa ser implementado
		return null;
	}

	public void update() {
		// precisa ser implementado
	}

	public void delete() {
		// precisa ser implementado
	}
	
	public void close() {
		this.con.close();
	}
}
