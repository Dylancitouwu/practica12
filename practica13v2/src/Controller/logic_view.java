package Controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import Model.cliente;
import Model.clienteDAO;
import Model.producto;
import Model.productoDAO;
import View.view_main;

public class logic_view implements ActionListener , configurable{
    private view_main vm;
	private int type;//defne el tipo de guardado 
    private int typeC;
    public logic_view(view_main vm) {
        super();
        this.vm = vm;

        // Agregar ActionListener a los botones
        this.vm.btn_borrar.addActionListener(this);
        this.vm.btn_product_add.addActionListener(this);//boton add de producto
        this.vm.btn_Cliente.addActionListener(this);
        this.vm.btn_Compra.addActionListener(this);
        this.vm.btn_escribir.addActionListener(this);
        this.vm.btn_guardar.addActionListener(this);//guardar productos
        this.vm.btn_Producto.addActionListener(this);
        this.vm.btn_borrar1.addActionListener(this);
        this.vm.btn_escribir1.addActionListener(this);
        this.vm.btn_guardar1.addActionListener(this);
        this.vm.btn_client_add.addActionListener(this);
        this.vm.btn_product_update.addActionListener(this);
        stateFields(false,1);//bloquear los campos para producto
        stateFields(false,2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar la fuente del evento y mostrar un mensaje
    	if (e.getSource() == vm.btn_Cliente) {
    		 vm.tpane_pestañas.setVisible(true);
            activarPestaña(2); // Activar pestaña "Cliente"
        } else if (e.getSource() == vm.btn_Producto) {
        	vm. tpane_pestañas.setVisible(true);
            activarPestaña(0); // Activar pestaña "Producto"
        } else if (e.getSource() == vm.btn_Compra) {
        	 vm.tpane_pestañas.setVisible(true);
            activarPestaña(1); // Activar pestaña "Compra"
        }else if(e.getSource()==vm.btn_product_add) {
        	//call
        	Thread generarCode=new process_codigo(vm,"000");
        	generarCode.start();
        	 stateFields(true,1);//desbloquear los campos para producto
        	 type=1;
        }else if(e.getSource()==vm.btn_guardar){
        	//logica para guardar        	
        	
				if(saveProduct(type)) {
					JOptionPane.showMessageDialog(vm, "Proceso Exitoso", "Registro de Porducto", JOptionPane.INFORMATION_MESSAGE);
					stateFields(false,1);
				}else {
					JOptionPane.showMessageDialog(vm, "Proceso fallido", "Registro de Porducto", JOptionPane.INFORMATION_MESSAGE);
				}
			
        }else if(e.getSource() == vm.btn_product_update || e.getSource()==vm.btn_borrar) {
        	//p=pdao.searchProduct(getValueSearch("CÓDIGO del producto"));
        	if(e.getSource()==vm.btn_product_update)
        	type=2;//update Product 
        	try {
				loadDataFieldsProduct(pdao.searchProduct(getValueSearch("Codigo del producto?")));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(e.getSource()==vm.btn_borrar)type=3; saveProduct(type); 
        }else if(e.getSource() == vm.btn_client_add){
            stateFields(true, 2);
            typeC = 1;        	
        }else if(e.getSource() == vm.btn_guardar1) {
        
        	if(saveClient(typeC)) {
        		JOptionPane.showMessageDialog(vm, "Proceso Exitoso", "Registro del Cliente", JOptionPane.INFORMATION_MESSAGE);
				stateFields(false,2);
        	}else {
				JOptionPane.showMessageDialog(vm, "Proceso fallido", "Registro del Cliente", JOptionPane.INFORMATION_MESSAGE);
			}
        }else if(e.getSource() == vm.btn_escribir1 || e.getSource()==vm.btn_borrar1) {
        	
        	if(e.getSource()==vm.btn_escribir1) typeC=2;
        	try {
        		loadDataFieldsClient(cdao.searchClient(getValueSearch("Cédula del Cliente?")));
        		
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(e.getSource() == vm.btn_borrar1) typeC=3;saveClient(typeC); 
        }
    }
    private void activarPestaña(int index) {
        vm.tpane_pestañas.setEnabledAt(index, true); // Habilitar pestaña
        vm.tpane_pestañas.setSelectedIndex(index);  // Seleccionar pestaña
        System.out.println("Pestaña activada: " + vm.tpane_pestañas.getTitleAt(index));
    }
    //GUARDAR DATOS
    private boolean saveProduct(int type2) {
        producto p = loaldFieldProduct();
        if (p == null) {
            JOptionPane.showMessageDialog(vm, "Error al cargar los datos del producto. Verifica los campos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        productoDAO pdaoRegister = new productoDAO(p);

        try {
            switch (type) {
                case 1:
                    return pdaoRegister.addProduct(); // Agregar producto
                case 2:
                    return pdaoRegister.updateproduct(); // Actualizar producto
                case 3:
                    return pdaoRegister.deleteProduct(); // Eliminar producto
                default:
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vm, "Ocurrió un error al guardar los datos del producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private boolean saveClient(int type3) {
        cliente c = loadFieldClient();
        if (c == null) {
            JOptionPane.showMessageDialog(vm, "Error al cargar los datos del cliente. Verifica los campos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        clienteDAO cdaoRegister = new clienteDAO(c);

        try {
            switch (typeC) {
                case 1:
                    return cdaoRegister.addClient(); // Agregar cliente
                case 2:
                    return cdaoRegister.updateClient(); // Actualizar cliente
                case 3:
                    return cdaoRegister.deleteClient(); // Eliminar cliente
                default:
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vm, "Ocurrió un error al guardar los datos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

	
    /*
     * permite bloquear los campos
     * state sirve true -> activa los campos y false se desactiva
     * type sirve para saber que opcion se esta ejecutando type=1 (producto) type=2(client)
     */
    private void stateFields(boolean state, int type) {
        switch(type) {
            case 1: // Producto
                System.out.println("Configurando campos de producto: " + state);
                vm.txt_producto.setEnabled(state);
                vm.txt_costo.setEnabled(state);
                vm.sp_unidades.setEnabled(state);
                vm.txt_descripcion.setEnabled(state);
                vm.btn_guardar.setEnabled(state);
                cleanFields(1);
                break;
            case 2: // Cliente
                System.out.println("Configurando campos de cliente: " + state);
                vm.txt_nombre.setEnabled(state);
                vm.txt_email.setEnabled(state);
                vm.txt_cedula.setEnabled(state);
                vm.txt_telefono.setEnabled(state);
                vm.txt_direccion.setEnabled(state);
                vm.btn_guardar1.setEnabled(state);
                cleanFields(2);
                break;
        }
    }
    
    private producto loaldFieldProduct() {
        producto p = new producto();
        
        // Verificar y asignar valores de los campos
        p.setCode(vm.txt_codigoProducto.getText() != null ? vm.txt_codigoProducto.getText() : "");
        p.setCreate(LocalDate.now());
        p.setDescProduct(vm.txt_descripcion.getText() != null ? vm.txt_descripcion.getText() : "");
        p.setNameProduct(vm.txt_producto.getText() != null ? vm.txt_producto.getText() : "");
        
        // Manejar la conversión a Double para txt_costo
        try {
            String costoText = vm.txt_costo.getText();
            if (costoText != null && !costoText.isEmpty()) {
                p.setPrice(Double.parseDouble(costoText));
            } else {
                p.setPrice(0.0); // Valor por defecto
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vm, "El costo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Manejar la conversión a Integer para sp_unidades
        try {
            Object unidadesValue = vm.sp_unidades.getValue();
            if (unidadesValue instanceof Integer) {
                p.setStock((Integer) unidadesValue);
            } else {
                p.setStock(0); // Valor por defecto
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vm, "El stock debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return p;
    }
    private cliente loadFieldClient() {
        cliente c = new cliente();

        // Verificar y asignar valores de los campos
        c.setNameClient(vm.txt_nombre.getText() != null ? vm.txt_nombre.getText() : "");
        c.setEmail(vm.txt_email.getText() != null ? vm.txt_email.getText() : "");
        c.setPhone(vm.txt_telefono.getText() != null ? vm.txt_telefono.getText() : "");
        c.setAddress(vm.txt_direccion.getText() != null ? vm.txt_direccion.getText() : "");
        c.setDni(vm.txt_cedula.getText() != null ? vm.txt_cedula.getText() : "");

        return c;
    }
	
    	
    	/*
    }
     * metodo que permite limpiar los campos segun el tipo a ejecutar
     * cuanto type = 1 se limpian los campos de producto
     * cuendo sea 2 se limpia de cliente
     */
    private void cleanFields(int type) {
    	switch(type) {
    	case 1:
    		vm.txt_codigoProducto.setText("");
    		vm.txt_descripcion.setText("");
    		vm.txt_producto.setText("");
    		vm.txt_costo.setText("");
    		vm.sp_unidades.setValue(0);    		
    		break;
    	case 2:
    		vm.txt_nombre.setText("");    		
    		vm.txt_email.setText("");
    		vm.txt_cedula.setText("");
    		vm.txt_telefono.setText("");
    		vm.txt_direccion.setText("");
    		break;
    	default:
    		break;
    	}
    }
    /*
     * Metodo que permite obtener la cadena de caracteres que ingrese el usuario sea para comodigo de prpducto o para numero de cedula 
     */
    private String getValueSearch(String msg) {
    	return JOptionPane.showInputDialog(vm,msg,"Consultar información",JOptionPane.INFORMATION_MESSAGE);
    }
    /*
     * Este metood permite cargar los datos consultados de un  producto en los campos de la GUI
     */
    private void loadDataFieldsProduct(producto p) {
    	if(p!=null) {
    	stateFields(true,1);
    	vm.txt_producto.setText(p.getNameProduct());
    	vm.txt_descripcion.setText(p.getDescProduct());
    	vm.txt_costo.setText(String.valueOf(p.getPrice()));
    	vm.sp_unidades.setValue(p.getStock());
    	vm.txt_codigoProducto.setText(p.getCode());
    
    	}else {
    		JOptionPane.showMessageDialog(vm, "Producto no existente","Consultar Informacion",JOptionPane.ERROR_MESSAGE);
    	}
    }
    private void loadDataFieldsClient(cliente c) {
        if (c != null) {
            stateFields(true, 2);
            System.out.println("Cargando datos del cliente: ");
            System.out.println("Nombre: " + c.getNameClient());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Cédula: " + c.getDni());
            System.out.println("Teléfono: " + c.getPhone());
            System.out.println("Dirección: " + c.getAddress());

            vm.txt_nombre.setText(c.getNameClient());
            vm.txt_email.setText(c.getEmail());
            vm.txt_cedula.setText(c.getDni());
            vm.txt_telefono.setText(c.getPhone());
            vm.txt_direccion.setText(c.getAddress());
        } else {
            JOptionPane.showMessageDialog(vm, "Cliente no existente", "Consultar Información", JOptionPane.ERROR_MESSAGE);
        }
    }
}