package Controller;

import Model.clienteDAO;
import Model.productoDAO;

public interface configurable {
	public final productoDAO pdao=new productoDAO();
	public final clienteDAO cdao=new clienteDAO();
}
