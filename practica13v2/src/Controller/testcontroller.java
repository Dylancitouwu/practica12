package Controller;

import View.view_main;

public class testcontroller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		view_main vm=new view_main();
		Thread hilo=new process_codigo(vm,"000");
		hilo.start();
	}

}
