package Model;

import java.time.LocalDate;
import java.util.Date;

import lib_genericP65v0.generic;

public class producto {
	private generic<Integer,String> dt1_p;
	private generic<Double, String> dt2_p;
	private generic<LocalDate,Boolean> dt3_p;
	
	public producto() {
		dt1_p = new generic<>(0, 0, "", ""); // ID = 0, stock = 0, name = "", desc = ""
	    dt2_p = new generic<>(0.0, "");     // Price = 0.0, code = ""
	    dt3_p = new generic<>(LocalDate.now(), false);
	}
	public producto(int id_product,String nameProduct,String descProduct, int stock, double price,String code, LocalDate create, boolean state) {
		dt1_p=new generic<>(id_product,stock,nameProduct,descProduct);
		dt2_p=new generic<>(price,code);
		dt3_p=new generic<>(create,state);
	}
	public int getIdProduct() {
		if (dt1_p.getAttribute1() != null) {
	        return dt1_p.getAttribute1();
	    } else {
	        // Maneja el caso de null, tal vez lanzando una excepción o devolviendo un valor predeterminado.
	        throw new IllegalStateException("ID del producto no inicializado");
	    }
	}
	public void setIdProduct(int id_) {
		dt1_p.setAttribute1(id_);
	}
	public String getNameProduct() {
		return dt1_p.getAttribute3();
	}
	public void setNameProduct(String name) {
		dt1_p.setAttribute3(name);
	}
	public String getDescProduct() {
		return dt1_p.getAttribute4();
	}
	public void setDescProduct(String desc) {
		dt1_p.setAttribute4(desc);
	}
	public int getStock() {
		return dt1_p.getAttribute2();
		}
	public void setStock(int stock) {
			dt1_p.setAttribute2(stock);
		}
	public double getPrice() {
		return dt2_p.getAttribute1();
	}
	public void setPrice(double price) {
		dt2_p.setAttribute1(price);
	}
	public String getCode() {
		return dt2_p.getAttribute3();
	}
	public void setCode(String code) {
		dt2_p.setAttribute3(code);
	}
	public LocalDate getCreate() {
		return dt3_p.getAttribute1();
	}
	public void setCreate(LocalDate create) {
		dt3_p.setAttribute1(create);
	}
	public boolean getState() {
		return dt3_p.getAttribute3();
	}
	public void setState(boolean state) {
		dt3_p.setAttribute3(state);
	}
	@Override
	public String toString() {
		return "producto [getIdProduct()=" + getIdProduct() + ", getNameProduct()=" + getNameProduct()
				+ ", getDescProduct()=" + getDescProduct() + ", getStock()=" + getStock() + ", getPrice()=" + getPrice()
				+ ", getCode()=" + getCode() + ", getCreate()=" + getCreate() + ", getState()=" + getState() + "]";
	}
	
}
