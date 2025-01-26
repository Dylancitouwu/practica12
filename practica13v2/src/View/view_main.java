package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import Controller.logic_view;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class view_main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTabbedPane tpane_pestañas;
    public JButton btn_Cliente;
    public JButton btn_Producto;
    public JButton btn_Compra;
    public JTextField txt_nombre;
    public JTextField txt_email;
    public JTextField txt_cedula;
    public JTextField txt_telefono;
    public JButton btn_guardar1;
    public JButton btn_escribir1;
    public JButton btn_borrar1;
    public JTextField txt_producto;
    public JTextField txt_costo;
    public JTable table;
    public JTextArea txt_descripcion;
    public JSpinner sp_unidades;
    public JTextField txt_cedulasearch;
    public JTextArea textArea;
    public JButton btn_guardar;
    public JButton btn_escribir;
    public JButton btn_borrar;
    public JTextField textField_1;
    public JTextField txt_codigoProducto;
    public JButton btn_product_add;
    public JButton btn_product_update;
    public JTextArea txt_direccion;
    public JButton btn_client_add;
    public JButton btn_pagar;
    public JButton btn_cancelar;
    public JButton btn_buscarProd;
    public JButton btn_buscarDniCl;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    view_main frame = new view_main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public view_main() {
    	setTitle("Tienda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 713, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Panel de botones a la izquierda
        JPanel pn_btns = new JPanel();
        pn_btns.setBounds(10, 10, 130, 340);
        contentPane.add(pn_btns);
        pn_btns.setLayout(null);

        btn_Cliente = new JButton("Cliente");
        btn_Cliente.setBounds(10, 50, 110, 25);
        pn_btns.add(btn_Cliente);

        btn_Producto = new JButton("Producto");
        btn_Producto.setBounds(10, 150, 110, 25);
        pn_btns.add(btn_Producto);

        btn_Compra = new JButton("Compra");
        btn_Compra.setBounds(10, 250, 110, 25);
        pn_btns.add(btn_Compra);

        // JTabbedPane a la derecha
        tpane_pestañas = new JTabbedPane(JTabbedPane.TOP);
        tpane_pestañas.setBounds(150, 10, 420, 340);
        tpane_pestañas.setVisible(false);
        contentPane.add(tpane_pestañas);

        JPanel panelProducto = new JPanel();
        panelProducto.setBorder(new TitledBorder(null, "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tpane_pestañas.addTab("Producto", panelProducto);
        panelProducto.setLayout(null);
        
        JLabel lblNewLabel_5 = new JLabel("Producto:");
        lblNewLabel_5.setBounds(21, 27, 59, 14);
        panelProducto.add(lblNewLabel_5);
        
        txt_producto = new JTextField();
        txt_producto.setBounds(90, 24, 236, 20);
        panelProducto.add(txt_producto);
        txt_producto.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Descripcion:");
        lblNewLabel_6.setBounds(21, 63, 94, 14);
        panelProducto.add(lblNewLabel_6);
        
        txt_descripcion = new JTextArea();
        txt_descripcion.setBounds(89, 58, 237, 66);
        panelProducto.add(txt_descripcion);
        
        JLabel lblNewLabel_7 = new JLabel("Unidades:");
        lblNewLabel_7.setBounds(21, 136, 59, 14);
        panelProducto.add(lblNewLabel_7);
        
        sp_unidades = new JSpinner();
        sp_unidades.setBounds(90, 135, 79, 20);
        panelProducto.add(sp_unidades);
        
        JLabel lblNewLabel_8 = new JLabel("Costo:");
        lblNewLabel_8.setBounds(191, 135, 46, 14);
        panelProducto.add(lblNewLabel_8);
        
        txt_costo = new JTextField();
        txt_costo.setBounds(240, 133, 86, 20);
        panelProducto.add(txt_costo);
        txt_costo.setColumns(10);
        
        table = new JTable();
        table.setBounds(21, 194, 367, 107);
        panelProducto.add(table);
        
        btn_guardar = new JButton("Guardar");
        btn_guardar.setBounds(334, 23, 59, 23);
        panelProducto.add(btn_guardar);
        
        btn_escribir = new JButton("Escribir");
        btn_escribir.setBounds(336, 59, 57, 23);
        panelProducto.add(btn_escribir);
        
        btn_borrar = new JButton("Borrar");
        btn_borrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_borrar.setBounds(336, 93, 59, 23);
        panelProducto.add(btn_borrar);
        
        JLabel lblNewLabel_11 = new JLabel("Codigo del producto:");
        lblNewLabel_11.setBounds(21, 169, 134, 14);
        panelProducto.add(lblNewLabel_11);
        
        txt_codigoProducto = new JTextField();
        txt_codigoProducto.setBounds(133, 166, 96, 20);
        panelProducto.add(txt_codigoProducto);
        txt_codigoProducto.setColumns(10);
        
        btn_product_add = new JButton("Añadir");
        btn_product_add.setBounds(336, 132, 59, 23);
        panelProducto.add(btn_product_add);
        
        btn_product_update = new JButton("Añadir");
        btn_product_update.setBounds(334, 160, 59, 23);
        panelProducto.add(btn_product_update);

        JPanel panelCompra = new JPanel();
        panelCompra.setBorder(new TitledBorder(null, "Compra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tpane_pestañas.addTab("Compra", panelCompra);
        panelCompra.setLayout(null);
        
        JLabel lblNewLabel_9 = new JLabel("Cedula:");
        lblNewLabel_9.setBounds(34, 35, 46, 14);
        panelCompra.add(lblNewLabel_9);
        
        txt_cedulasearch = new JTextField();
        txt_cedulasearch.setBounds(91, 32, 96, 20);
        panelCompra.add(txt_cedulasearch);
        txt_cedulasearch.setColumns(10);
        
        btn_buscarDniCl = new JButton("Buscar");
        btn_buscarDniCl.setBounds(197, 31, 89, 23);
        panelCompra.add(btn_buscarDniCl);
        
        JLabel lblNewLabel_10 = new JLabel("Producto:");
        lblNewLabel_10.setBounds(34, 78, 69, 14);
        panelCompra.add(lblNewLabel_10);
        
        textArea = new JTextArea();
        textArea.setBounds(34, 155, 345, 110);
        panelCompra.add(textArea);
        
        textField_1 = new JTextField();
        textField_1.setBounds(91, 75, 96, 20);
        panelCompra.add(textField_1);
        textField_1.setColumns(10);
        
        btn_buscarProd = new JButton("Buscar");
        btn_buscarProd.setBounds(197, 74, 89, 23);
        panelCompra.add(btn_buscarProd);
        
        JLabel lblNewLabel_12 = new JLabel("Cantidad:");
        lblNewLabel_12.setBounds(34, 111, 48, 14);
        panelCompra.add(lblNewLabel_12);
        
        JSpinner spinner = new JSpinner();
        spinner.setBounds(91, 108, 69, 20);
        panelCompra.add(spinner);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(296, 30, 109, 98);
        panelCompra.add(textArea_1);
        
        btn_pagar = new JButton("Pagar");
        btn_pagar.setBounds(290, 280, 89, 23);
        panelCompra.add(btn_pagar);
        
        btn_cancelar = new JButton("Cancelar");
        btn_cancelar.setBounds(34, 281, 89, 23);
        panelCompra.add(btn_cancelar);
        

         JPanel panelCliente = new JPanel();
        panelCliente.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tpane_pestañas.addTab("Cliente", panelCliente);
        panelCliente.setLayout(null);
                
        JLabel lblNewLabel = new JLabel("Nombres:");
        lblNewLabel.setBounds(35, 49, 69, 14);
        panelCliente.add(lblNewLabel);
                
        txt_nombre = new JTextField();
        txt_nombre.setBounds(104, 46, 232, 20);
        panelCliente.add(txt_nombre);
        txt_nombre.setColumns(10);
                
        JLabel lblNewLabel_1 = new JLabel("Email:");
        lblNewLabel_1.setBounds(35, 86, 46, 14);
        panelCliente.add(lblNewLabel_1);
                
        txt_email = new JTextField();
        txt_email.setColumns(10);
        txt_email.setBounds(104, 77, 232, 20);
        panelCliente.add(txt_email);
                
        JLabel lblNewLabel_2 = new JLabel("Cedula:");
        lblNewLabel_2.setBounds(35, 121, 46, 14);
        panelCliente.add(lblNewLabel_2);
                
        txt_cedula = new JTextField();
        txt_cedula.setColumns(10);
        txt_cedula.setBounds(104, 118, 232, 20);
        panelCliente.add(txt_cedula);
                
        JLabel lblNewLabel_3 = new JLabel("Telefono:");
        lblNewLabel_3.setBounds(35, 158, 59, 14);
        panelCliente.add(lblNewLabel_3);
                
        txt_telefono = new JTextField();
        txt_telefono.setColumns(10);
               txt_telefono.setBounds(104, 155, 232, 20);
                panelCliente.add(txt_telefono);
                
                JLabel lblNewLabel_4 = new JLabel("Direccion:");
                lblNewLabel_4.setBounds(35, 197, 69, 14);
                panelCliente.add(lblNewLabel_4);
                
                txt_direccion = new JTextArea();
                txt_direccion.setBounds(104, 192, 232, 51);
                panelCliente.add(txt_direccion);
                
                btn_borrar1 = new JButton("Borrar");
                btn_borrar1.setBounds(316, 264, 89, 23);
                panelCliente.add(btn_borrar1);
                
                btn_escribir1 = new JButton("Escribir");
                btn_escribir1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	}
                });
                btn_escribir1.setBounds(200, 264, 89, 23);
                panelCliente.add(btn_escribir1);
                
                btn_guardar1 = new JButton("Guardar");
                btn_guardar1.setBounds(101, 264, 89, 23);
                panelCliente.add(btn_guardar1);
                
                btn_client_add = new JButton("Añadir");
                btn_client_add.setBounds(10, 264, 82, 23);
                panelCliente.add(btn_client_add);
                // Deshabilitar las pestañas al inicio
                for (int i = 0; i < tpane_pestañas.getTabCount(); i++) {
                    tpane_pestañas.setEnabledAt(i, false);
                }
                
                logic_view lv=new logic_view(this);
    }
}
