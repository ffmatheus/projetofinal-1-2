package com.icarros.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.icarros.Usuario;
import com.icarros.global.Encrypter;
import com.icarros.global.QueryUtils;

public class UsuarioDAO {

	conn_db con;
	ResultSet resultSet;
	
	public UsuarioDAO() {
		this.con = new conn_db();
		con.connect();
		
		if (!con.connected()) {
			System.out.println("Erro ao se conectar com o banco de dados!");
		}
	}
	
	public boolean verificaUser(Usuario usuario) {
		
		String senhaCripto = Encrypter.encrypt(usuario.getSenha());
		String query = String.format(QueryUtils.QUERY_GET_USUARIO, usuario.getUsername(), senhaCripto);
		boolean existe = false;
		
		try {
		
			resultSet = con.getStatement().executeQuery(query);
			
			if(resultSet.next()) {
				existe = true;
			} else {
				existe = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public void close() {
		this.con.close();
	}
}
