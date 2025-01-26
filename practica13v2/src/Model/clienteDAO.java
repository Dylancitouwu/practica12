package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clienteDAO {
	private cliente c;
	private connectionBBDD conn=new connectionBBDD();
	public clienteDAO() {
		super();
	}
	public clienteDAO(cliente c) {
		super();
		this.c=c;		
	}
	public boolean addClient() throws ClassNotFoundException, SQLException {
	    return conn.setQuery(String.format(
	        "INSERT INTO shop.client (name_client, email, dni, phone, address, create_client, state) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', true);",
	        c.getNameClient(), c.getEmail(), c.getDni(), c.getPhone(), c.getAddress(), c.getCreateC().toString()
	    ));
	}
	public boolean updateClient() throws ClassNotFoundException, SQLException {
	    return conn.setQuery(String.format("UPDATE shop.client set name_client='%s', email='%s', phone='%s', address='%s' where dni='%s';",
	            c.getNameClient(), c.getEmail(), c.getPhone(), c.getAddress(), c.getDni()));
	}
	public boolean deleteClient() throws ClassNotFoundException, SQLException {
	    System.out.println("Deleting client with DNI: " + c.getDni()); // Verificar el DNI
	    return conn.setQuery(String.format("UPDATE shop.client SET state=false WHERE dni='%s';", c.getDni()));
	}
	public List<cliente> allCliente() throws ClassNotFoundException, SQLException{
		List<cliente> clientes=new ArrayList();
		ResultSet res=conn.getQuery("SELECT * FROM shop.client;");
		while(res.next()) {
			clientes.add(getClient(res));
		}
		conn.closeConn();
		return clientes;		
	}
	public synchronized cliente searchClient(String dni) throws ClassNotFoundException, SQLException {
	    ResultSet res = conn.getQuery(String.format("SELECT * FROM shop.client where dni='%s';", dni));
	    if (res.next()) {
	        c = getClient(res);
	    } else {
	        c = null; // Asegúrate de manejar el caso cuando no se encuentre ningún cliente
	    }
	    conn.closeConn();
	    return c;
	}
	private cliente getClient(ResultSet res) throws SQLException {
		cliente c=new cliente();
		c.setIdClient(res.getInt(1));
		c.setNameClient(res.getString(2));
		c.setEmail(res.getString(3));
		c.setDni(res.getString(4));
		c.setPhone(res.getString(5));
		c.setAddress(res.getString(6));
		c.setCreateC(res.getDate(7).toLocalDate());
		c.setStateC(res.getBoolean(8));		
		return c;
	}
}
