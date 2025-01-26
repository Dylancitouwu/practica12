package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class productoDAO {
	private producto p;
	private connectionBBDD conn=new connectionBBDD();
	public productoDAO() {
	super();
	}
	public productoDAO(producto p) {
		super();
		this.p=p;
	}
	public boolean addProduct() throws ClassNotFoundException, SQLException {
	    return conn.setQuery(String.format("INSERT INTO shop.product (name_product, description, stock, price, `create`, code, state) " +
	                                       "VALUES ('%s', '%s', %d, %s, '%s', '%s', true);",
	                                       p.getNameProduct(), p.getDescProduct(), p.getStock(),
	                                       p.getPrice(), p.getCreate().toString(), p.getCode()));
	}
	public boolean updateproduct() throws ClassNotFoundException, SQLException {
		return conn.setQuery(String.format("UPDATE shop.product set name_product= '%s', description='%s',stock=%d,price='%s' where code='%s';",p.getNameProduct(),p.getDescProduct(),p.getStock(),
																												String.valueOf(p.getPrice()),p.getCode()));
	}
	public boolean deleteProduct() throws ClassNotFoundException, SQLException {
	    System.out.println("Deleting product with code: " + p.getCode()); // Verificar el código
	    return conn.setQuery(String.format("UPDATE shop.product SET state=false WHERE code='%s';", p.getCode()));
	}
	public List<producto> allProducts() throws ClassNotFoundException, SQLException{
		List<producto> products=new ArrayList();
		ResultSet res=conn.getQuery("SELECT * FROM shop.product;");
		while(res.next()) {
			products.add(getProduct(res));
		}
		conn.closeConn();
		return products;
		
	}
	public synchronized producto searchProduct(String code) throws ClassNotFoundException, SQLException{
		ResultSet res=conn.getQuery(String.format("SELECT * FROM shop.product where code='%s';",code));
		if(res.next()) {
			p=getProduct(res);
		}else {
			p=null;
		}
		conn.closeConn();
		return p;
	}
	private producto getProduct(ResultSet res) throws SQLException {
		producto p=new producto();
		p.setIdProduct(res.getInt(1));
		p.setNameProduct(res.getString(2));
		p.setDescProduct(res.getString(3));
		p.setStock(res.getInt(4));
		p.setPrice(res.getDouble(5));
		p.setCreate(res.getDate(6).toLocalDate());
		p.setCode(res.getString(7));
		p.setState(res.getBoolean(8));
		return p;
	}
	
	public synchronized cliente searchClientByCedula(String cedula) throws ClassNotFoundException, SQLException {
        // Validar la cédula con una expresión regular
        if (!Pattern.matches("\\d{10}", cedula)) {
            throw new IllegalArgumentException("El número de cédula no es válido.");
        }

        ResultSet res = conn.getQuery(String.format("SELECT * FROM shop.client WHERE cedula='%s';", cedula));
        cliente client = null;
        if (res.next()) {
            client = new cliente();
            client.setIdClient(0);
            client.setNameClient(res.getString(2));
            client.setEmail(res.getString(3));
            client.setDni(res.getString(4));
            client.setPhone(res.getString(5));
            client.setAddress(res.getString(6));
            client.setStateC(res.getBoolean(7));
        }
        conn.closeConn();
        return client;
    }
	public synchronized boolean addToCart(String code, int quantity) throws ClassNotFoundException, SQLException {
        producto prod = searchProduct(code);
        if (prod == null || prod.getStock() < quantity) {
            throw new IllegalArgumentException("Producto no encontrado o stock insuficiente.");
        }

        // Reducir stock del producto
        prod.setStock(prod.getStock() - quantity);
        updateproduct();

        // Registrar en tabla temporal de factura
        Thread insertThread = new Thread(() -> {
            try {
                conn.setQuery(String.format("INSERT INTO shop.temp_invoice (product_code, quantity, price) VALUES ('%s', %d, %f);",
                                            prod.getCode(), quantity, prod.getPrice() * quantity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        insertThread.start();
        return true;
    }
	public synchronized boolean cancelPurchase(String code, int quantity) throws ClassNotFoundException, SQLException {
        producto prod = searchProduct(code);
        if (prod == null) {
            throw new IllegalArgumentException("Producto no encontrado.");
        }

        // Incrementar el stock
        prod.setStock(prod.getStock() + quantity);
        updateproduct();

        // Eliminar de la tabla temporal
        conn.setQuery(String.format("DELETE FROM shop.temp_invoice WHERE product_code='%s' AND quantity=%d;", code, quantity));
        return true;
    }
	
}
