package br.com.lojinhaguru.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.lojinhaguru.connection.ConnectionFactory;
import br.com.lojinhaguru.model.Clientes;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ClienteDao {

	private Connection con;
	
	public ClienteDao(){
		try{
			this.con = new ConnectionFactory().getConnection();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void incluiCliente(Clientes clientes){
		String sql = "insert into clientes (nome, endereco, numero, complemento, bairro, municipio, uf, cep, contato, email, dataNascimento)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement stm;
		try{
			stm = (PreparedStatement) con.clientPrepareStatement(sql);
			stm.setString(0, clientes.getNome());
			stm.setString(1, clientes.getEndereco());
			stm.setString(2, clientes.getNumero());
			stm.setString(3, clientes.getComplemento());
			stm.setString(4, clientes.getBairro());
			stm.setString(5, clientes.getMunicipio());
			stm.setString(6, clientes.getUf());
			stm.setString(7, clientes.getCep());
			stm.setString(8, clientes.getContato());
			stm.setString(9, clientes.getEmail());
			stm.setDate(10, new Date(clientes.getDataNascimento().getTimeInMillis()));
			
			stm.execute();
			con.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Clientes> lista(){
		
		try{
			List<Clientes> clientes = new ArrayList<Clientes>();
			PreparedStatement stm = (PreparedStatement) con.prepareStatement("select * from clientes");
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				clientes.add(populaClientes(rs));
			}
			
			rs.close();
			con.close();
			
			return clientes;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public Clientes populaClientes(ResultSet rs) throws SQLException{
		Clientes clientes = new Clientes();
		
		clientes.setIdCliente(rs.getLong("idCliente"));
		clientes.setNome(rs.getString("nome"));
		clientes.setNome(rs.getString("endereco"));
		clientes.setNome(rs.getString("numero"));
		clientes.setNome(rs.getString("complemento"));
		clientes.setNome(rs.getString("bairro"));
		clientes.setNome(rs.getString("municipio"));
		clientes.setNome(rs.getString("uf"));
		clientes.setNome(rs.getString("cep"));
		clientes.setNome(rs.getString("contato"));
		clientes.setNome(rs.getString("email"));
		
		Date data = rs.getDate("dataNascimento");
		if(data != null){
			Calendar dataNasc = Calendar.getInstance();
			dataNasc.setTime(data);
			clientes.setDataNascimento(dataNasc);
		}
		
		
		return clientes;
	}
	
}
