/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Class.Agregar_productos_al_combo;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Class.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Camilo
 */
public class Interfaz_usuario extends javax.swing.JFrame {

    DefaultTableModel dtm;
    String usuario, id;
    int fila = 0;
    Actualizar_datos ad = new Actualizar_datos();
    String Fecha = Agregar_productos_al_carrito.agregar_fecha();
    Agregar_Carrito_al_archivo ag = new Agregar_Carrito_al_archivo();
    Agregar_productos_al_carrito ac = new Agregar_productos_al_carrito();
    Agregar_compras_historial_Clientes agregar_historial = new Agregar_compras_historial_Clientes();
    Modificar_historial_cliente modificar_historial = new Modificar_historial_cliente();
    int agregar_precio;

    public Interfaz_usuario(String usuario, String id) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.usuario = usuario;
        this.id = id;
        btnmodificarhistorial.setVisible(false);
        Cambiar_valores_en_el_archivo.setVisible(false);
        btnvolveracompras.setVisible(false);
        jLabel5.setVisible(false);
        jLabel7.setVisible(false);
        txtnuevonumerocompra.setVisible(false);
        jLabel8.setVisible(false);
        txtnuevocodpro.setVisible(false);
        jLabel9.setVisible(false);
        txtnuevoproducto.setVisible(false);
        jLabel11.setVisible(false);
        txtnuevoprecio4.setVisible(false);

        String titulos[] = new String[]{
            "No.Pedido",
            "Fecha de compra",
            "Codigo",
            "Producto",
            "Numero de productos",
            "Precio Total",
            "Cliente",
            "Id del cliente"
        };
        dtm = new DefaultTableModel(titulos, 1);
        jTableCarrito.setModel(dtm);
        jTableCarrito.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jTableCarrito.getColumnModel().getColumn(0).setPreferredWidth(48);
        jTableCarrito.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableCarrito.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTableCarrito.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTableCarrito.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTableCarrito.setValueAt(usuario, 0, 6); // Agrega el usuario
        jTableCarrito.setValueAt(id, 0, 7); // Agrega la id
        jTableCarrito.setValueAt(Fecha, 0, 1); // Agrega la fecha

        ac.obtener_siguiente_consecutivo();
        agregar_historial.obtener_id(jTableCarrito.getValueAt(0, 7).toString());

        Vector agregarProductos = new Vector();
        agregarProductos = Agregar_productos_al_combo.agregar_productos();

        for (Object valor : agregarProductos) {
            jComboproductos.addItem(valor.toString());
        }
    }

    public void agregar_carrito() {
        int num_productos = Integer.parseInt(txtnumerodeproductos.getText());
        if (num_productos > 0) {
            String ruta = "clientes.txt";
            File archivo = new File(ruta);
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String lea;
                while ((lea = br.readLine()) != null) {
                    String vecLinea[];
                    vecLinea = lea.split(",");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Vector agregarCodido = new Vector();
            String codigo = jComboproductos.getSelectedItem().toString();
            agregarCodido = Agregar_productos_al_carrito.agregar_codigo(codigo);

            for (Object valor : agregarCodido) {
                jTableCarrito.setValueAt(valor.toString(), fila, 2); // Agrega el codigo del producto
            }
            int numero_de_productos = Integer.parseInt(txtnumerodeproductos.getText());
            agregar_precio = Agregar_productos_al_carrito.agregar_Precios(numero_de_productos, 0, jComboproductos.getSelectedItem().toString());
            jTableCarrito.setValueAt(Integer.toString(agregar_precio), fila, 5);
            jTableCarrito.setValueAt(jComboproductos.getSelectedItem().toString(), fila, 3);//Agrega el nombre del producto
            jTableCarrito.setValueAt(txtnumerodeproductos.getText(), fila, 4); //Agrega el numero de producto
            jTableCarrito.setValueAt(usuario, fila, 6); // Agrega el usuario
            jTableCarrito.setValueAt(id, fila, 7); // Agrega la id
            jTableCarrito.setValueAt(Fecha, fila, 1); // Agrega la fecha
            jTableCarrito.setValueAt(Agregar_productos_al_carrito.agregar_consecutivo_carrito(), fila, 0);
            fila++;
        } else {
            JOptionPane.showMessageDialog(this, "Solo se admiten numeros mayores a 1");
        }
    }

    public void modificar() {
        if (jTableCarrito.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "debe Selecionar una fila con valores para poder editar");
        } else {
            jTableCarrito.setValueAt(txtnumerodeproductos.getText(), jTableCarrito.getSelectedRow(), 4);
            jTableCarrito.setValueAt(jComboproductos.getSelectedItem().toString(), jTableCarrito.getSelectedRow(), 3);
            int numero_de_productos = Integer.parseInt(txtnumerodeproductos.getText());
            agregar_precio = Agregar_productos_al_carrito.agregar_Precios(numero_de_productos, 0, jComboproductos.getSelectedItem().toString());
            jTableCarrito.setValueAt(Integer.toString(agregar_precio), jTableCarrito.getSelectedRow(), 5);
            Vector agregarCodido = new Vector();
            String codigo = jComboproductos.getSelectedItem().toString();
            agregarCodido = Agregar_productos_al_carrito.agregar_codigo(codigo);
            for (Object valor : agregarCodido) {
                jTableCarrito.setValueAt(valor.toString(), jTableCarrito.getSelectedRow(), 2); // Agrega el codigo del producto
            }
        }

    }

    public void modificar_historial() {
        if (jTableCarrito.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "debe Selecionar una fila con valores para poder editar");
        } else {
            jTableCarrito.setValueAt(txtnumerodeproductos.getText(), jTableCarrito.getSelectedRow(), 4);
            txtnumerodeproductos.setText(txtnumerodeproductos.getText());
            jTableCarrito.setValueAt(jComboproductos.getSelectedItem().toString(), jTableCarrito.getSelectedRow(), 3);
            txtnuevoproducto.setText(jComboproductos.getSelectedItem().toString());
            int numero_de_productos = Integer.parseInt(txtnumerodeproductos.getText());
            agregar_precio = Agregar_productos_al_carrito.agregar_Precios(numero_de_productos, 0, jComboproductos.getSelectedItem().toString());
            jTableCarrito.setValueAt(Integer.toString(agregar_precio), jTableCarrito.getSelectedRow(), 5);
            txtnuevoprecio4.setText(Integer.toString(agregar_precio));
            Vector agregarCodido = new Vector();
            String codigo = jComboproductos.getSelectedItem().toString();
            agregarCodido = Agregar_productos_al_carrito.agregar_codigo(codigo);
            for (Object valor : agregarCodido) {
                jTableCarrito.setValueAt(valor.toString(), jTableCarrito.getSelectedRow(), 2);
                txtnuevocodpro.setText(valor.toString());
            }
            txtnuevonumerocompra.setText(jTableCarrito.getValueAt(jTableCarrito.getSelectedRow(), 0).toString());
        }
    }

    public void eliminar() {
        if (jTableCarrito.getSelectedRow() == 0) {
            JOptionPane.showMessageDialog(this, "No es posible eliminar Esta fila debido a que debe de haber minimo un producto para realizar la compra");
        } else {
            dtm.removeRow(jTableCarrito.getSelectedRow());
            Agregar_productos_al_carrito.eliminar_consecutivo_carrito();
            fila--;
        }
    }

    public void realizar_compra() {
        int i;
        String no_pedido, fecha_de_compra, codigo, producto, numero, precio, cliente, id_;
        for (i = 0; i < jTableCarrito.getRowCount(); i++) {
            no_pedido = jTableCarrito.getValueAt(i, 0).toString();
            fecha_de_compra = jTableCarrito.getValueAt(i, 1).toString();
            codigo = jTableCarrito.getValueAt(i, 2).toString();
            producto = jTableCarrito.getValueAt(i, 3).toString();
            numero = jTableCarrito.getValueAt(i, 4).toString();
            precio = jTableCarrito.getValueAt(i, 5).toString();
            cliente = jTableCarrito.getValueAt(i, 6).toString();
            id_ = jTableCarrito.getValueAt(i, 7).toString();
            ag.agregar_al_archivo(no_pedido, fecha_de_compra, codigo, producto, numero, precio, cliente, id);
        }
    }

    public void actualizar_historial() {
        int seguridad_modificad = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea modificar este pedido", "Modificar pedido", JOptionPane.YES_NO_OPTION);
        if (seguridad_modificad == 0) {
            modificar_historial.Modificar_producto(txtnuevoproducto.getText(), txtnumerodeproductos.getText(), txtnuevoprecio4.getText(), txtnuevocodpro.getText(), txtnuevonumerocompra.getText());
            JOptionPane.showMessageDialog(this, "El producto ha sido modificado");
        } else {
            JOptionPane.showMessageDialog(this, "Continue Modificando su porducto");
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboproductos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnumerodeproductos = new javax.swing.JTextField();
        btnagregarcarrito = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCarrito = new javax.swing.JTable();
        btnmodificarcarrito = new javax.swing.JButton();
        btneliminarcarrito = new javax.swing.JButton();
        btnrealizarcompra = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnvolveracompras = new javax.swing.JButton();
        Cambiar_valores_en_el_archivo = new javax.swing.JButton();
        btnmodificarhistorial = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtnuevoprecio4 = new javax.swing.JTextField();
        txtnuevonumerocompra = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnuevocodpro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtnuevoproducto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Super tiendas y Almacenes DVMQ");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PRODUCTOS");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Seleccione el producto que desea comprar");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("¿Cuantos desea llevar?");

        txtnumerodeproductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumerodeproductosKeyTyped(evt);
            }
        });

        btnagregarcarrito.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnagregarcarrito.setText("AGREGAR AL CARRITO 🛒");
        btnagregarcarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarcarritoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboproductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 48, Short.MAX_VALUE))
                    .addComponent(txtnumerodeproductos))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(btnagregarcarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboproductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnumerodeproductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnagregarcarrito)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTableCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableCarritoMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCarrito);

        btnmodificarcarrito.setText("MODIFICAR");
        btnmodificarcarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarcarritoActionPerformed(evt);
            }
        });

        btneliminarcarrito.setText("ELIMINAR");
        btneliminarcarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarcarritoActionPerformed(evt);
            }
        });

        btnrealizarcompra.setText("REALIZAR COMPRA");
        btnrealizarcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrealizarcompraActionPerformed(evt);
            }
        });

        btnHistorial.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnHistorial.setText("VER EL HISTORIAL DE COMPRAS");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Configuara Perfil👤");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        btnvolveracompras.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnvolveracompras.setText("VOLVER A LA INTERFAZ DE COMPRAS");
        btnvolveracompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolveracomprasActionPerformed(evt);
            }
        });

        Cambiar_valores_en_el_archivo.setText("REALIZAR LOS CAMBIOS EN EL ARCHIVO");
        Cambiar_valores_en_el_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cambiar_valores_en_el_archivoActionPerformed(evt);
            }
        });

        btnmodificarhistorial.setText("MODIFICAR");
        btnmodificarhistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarhistorialActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("NUEVO PRECIO");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("NUMERO DE LA COMPRA");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("NUEVO CODIGO DE PRODUCTO");

        txtnuevoproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnuevoproductoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("NUEVO PRODUCTO");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CAMBIOS QUE SE REALIZARAN");

        jLabel12.setText("SELECIONE UN PRODUCTO AGREGE AL CARRITO Y TERMINE SU COMPRA 🎆");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(txtnuevonumerocompra, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(txtnuevoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(txtnuevocodpro, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(txtnuevoprecio4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnuevonumerocompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnuevocodpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtnuevoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnuevoprecio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnmodificarcarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btneliminarcarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnrealizarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnmodificarhistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Cambiar_valores_en_el_archivo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnvolveracompras)
                            .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnmodificarcarrito)
                                .addComponent(btneliminarcarrito))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnrealizarcompra)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cambiar_valores_en_el_archivo)
                            .addComponent(btnmodificarhistorial))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnvolveracompras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 58, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarcarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarcarritoActionPerformed
        dtm.setRowCount(fila + 1);
        agregar_carrito();
    }//GEN-LAST:event_btnagregarcarritoActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        dtm.setNumRows(0);
        Vector compras = new Vector();
        compras = agregar_historial.obtener_compras();
        for (Object valor : compras) {
            dtm.addRow((String[]) valor);
        }
        btnmodificarcarrito.setVisible(false);
        btneliminarcarrito.setVisible(false);
        btnrealizarcompra.setVisible(false);
        btnHistorial.setVisible(false);
        btnagregarcarrito.setVisible(false);
        btnmodificarhistorial.setVisible(true);
        btnvolveracompras.setVisible(true);
        jLabel5.setVisible(true);
        jLabel7.setVisible(true);
        txtnuevonumerocompra.setVisible(true);
        jLabel8.setVisible(true);
        txtnuevocodpro.setVisible(true);
        jLabel9.setVisible(true);
        txtnuevoproducto.setVisible(true);
        jLabel11.setVisible(true);
        txtnuevoprecio4.setVisible(true);
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        String[] botones = {"Editar perfil ", "Cerrar Sesion", "Cancelar"};
        int Mensaje = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Configuracion del perfil", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        switch (Mensaje) {
            case 0:
                ad.recibir_id(jTableCarrito.getValueAt(0, 7).toString());
                ad.leer_datos(ad.recibir_id(id));
                ad.llenar_datos();
                this.dispose();
                break;
            case 1:
                Login lg = new Login();
                lg.setVisible(true);
                this.dispose();
                break;
            case 2:
            default:
                break;
        }

    }//GEN-LAST:event_jLabel6MouseClicked

    private void btnrealizarcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrealizarcompraActionPerformed
        realizar_compra();
        JOptionPane.showMessageDialog(this, "La compra se ha realizado con exito, visite el historial para modificar");
        this.dispose();
        Interfaz_usuario ia = new Interfaz_usuario(usuario, id);
        ia.setVisible(true);
    }//GEN-LAST:event_btnrealizarcompraActionPerformed

    private void jTableCarritoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCarritoMouseReleased
        txtnumerodeproductos.setText(jTableCarrito.getValueAt(jTableCarrito.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_jTableCarritoMouseReleased

    private void btnmodificarcarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarcarritoActionPerformed
        modificar();
    }//GEN-LAST:event_btnmodificarcarritoActionPerformed

    private void btneliminarcarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarcarritoActionPerformed
        eliminar();
    }//GEN-LAST:event_btneliminarcarritoActionPerformed

    private void btnvolveracomprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolveracomprasActionPerformed
        this.dispose();
        Interfaz_usuario ia = new Interfaz_usuario(usuario, id);
        ia.setVisible(true);
    }//GEN-LAST:event_btnvolveracomprasActionPerformed

    private void Cambiar_valores_en_el_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cambiar_valores_en_el_archivoActionPerformed
        actualizar_historial();
    }//GEN-LAST:event_Cambiar_valores_en_el_archivoActionPerformed

    private void btnmodificarhistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarhistorialActionPerformed
        modificar_historial();
        Cambiar_valores_en_el_archivo.setVisible(true);
        Cambiar_valores_en_el_archivo.setEnabled(true);
    }//GEN-LAST:event_btnmodificarhistorialActionPerformed

    private void txtnuevoproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnuevoproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnuevoproductoActionPerformed

    private void txtnumerodeproductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumerodeproductosKeyTyped
        char validad = evt.getKeyChar();
        if (Character.isLetter(validad)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se adminten numeros mayores a 0");
        }
    }//GEN-LAST:event_txtnumerodeproductosKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz_usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz_usuario("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cambiar_valores_en_el_archivo;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnagregarcarrito;
    private javax.swing.JButton btneliminarcarrito;
    private javax.swing.JButton btnmodificarcarrito;
    private javax.swing.JButton btnmodificarhistorial;
    private javax.swing.JButton btnrealizarcompra;
    private javax.swing.JButton btnvolveracompras;
    private javax.swing.JComboBox<String> jComboproductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCarrito;
    private javax.swing.JTextField txtnuevocodpro;
    private javax.swing.JTextField txtnuevonumerocompra;
    private javax.swing.JTextField txtnuevoprecio4;
    private javax.swing.JTextField txtnuevoproducto;
    private javax.swing.JTextField txtnumerodeproductos;
    // End of variables declaration//GEN-END:variables
}
