package com.icarros.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conn_db {
	
			//Criando variaveis privadas
			private Connection connection = null;
			private Statement statement = null;
			private ResultSet resultset = null;
			
			//conectando ao banco
				
			public void connect() {
			
				//variaveis de conexao
				String servidor = "jdbc:mysql://localhost/correntista";	
				//user do banco
				String user = "root";
				//password
				String pass = "1234";
				//driver de conexao
				String driver = "com.mysql.cj.jdbc.Driver";
				
				//verificar conexao
				try {
					Class.forName(driver);
					this.connection = DriverManager.getConnection(servidor,user,pass);
					this.statement = this.connection.createStatement();
				}catch(Exception e) {
					System.out.println("Erro.:" + e.getMessage());
				}
			}


			public boolean connected() {
				if(this.connection != null) {
					return true;
				}else {
					return false;
				}
			}
			
			public Statement getStatement() {
				return this.statement;
			}
			
			public void close() {
				
				try {
					this.connection.close();
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
}
