package Controller;

import java.sql.SQLException;

import View.view_main;

public class process_codigo extends Thread implements configurable {
	private String  codeProduct=""; 
	private  view_main vm;
	public process_codigo(view_main vm_,String msg) {
		super(msg);
		this.vm=vm_;
	}
	//metodo para generar numeros ramdomicos
	private int getValue() {
		return (int)(Math.random()*10);
	}
	public void run() {
		int aux=0;
		
		while(true) {
			if(aux==0)
				codeProduct=getName()+"-";
			codeProduct+=String.valueOf(getValue());
			aux++;
			if(aux==4) {
				try {
					if(pdao.searchProduct(codeProduct)==null) {
						//System.out.println(codeProduct);
						vm.txt_codigoProducto.setText(codeProduct);
						break;//Crea una interrupcion al Thread
					}else {
						aux=0;
									
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
}
