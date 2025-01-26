package Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class testModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//producto p=new producto(1,"Teclado","Teclado inalimbrico",15,8.5,"125-125",LocalDate.now(),true);
		//System.out.println(p.toString());
		//connectionBBDD q=new connectionBBDD();
		//try {
			//q.setQuery(String.format("insert into shop.client values(null,'%s','%s','%s','%s','%s','%s',true);",
		//					"Tomas Alvarez", "tomas251@hotmai.com", "1716975578", "0991457496", "Chillogallo", "2025-01-17"));
		//} catch (ClassNotFoundException e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.err.println("Erro:"+e);
		//}
		//productoDAO pdao=new productoDAO(new producto(1,"Monitor Curvo","Monitor de 29 pulgadas marca DELL",5,399.99,"12-147",LocalDate.now(),true));
		productoDAO pdao1=new productoDAO();
		try {
			//pdao.deleteProduct();
			//pdao.addProduct();
			//pdao.updateproduct();
			producto p=pdao1.searchProduct("12-147");
			if(p!=null) {
				System.out.println(p.getNameProduct()+"\t"+p.getPrice());
			}else  {
				System.out.println("Producto no existente");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
