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
    int agregar_precio;

    public Interfaz_usuario(String usuario, String id) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        panefactura.setVisible(false);
        panefactura.setEditable(false);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.usuario = usuario;
        this.id = id;
        String titulos[] = new String[]{
            "No.Pedido",
            "Fecha de compra",
            "Codigo",
            "Producto",
            "Numero de productos",
            "Precio Total",
            "Cliente",
            "Id del cliente"};

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

    public void eliminar() {
        dtm.removeRow(jTableCarrito.getSelectedRow());
        fila--;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        panefactura = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCarrito = new javax.swing.JTable();
        btnmodificarcarrito = new javax.swing.JButton();
        btneliminarcarrito = new javax.swing.JButton();
        btnrealizarcompra = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

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
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(txtnumerodeproductos))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
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
                .addGap(27, 27, 27)
                .addComponent(btnagregarcarrito)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panefactura);

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

        btnmodificarcarrito.setText("Modificar");
        btnmodificarcarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarcarritoActionPerformed(evt);
            }
        });

        btneliminarcarrito.setText("Eliminar");
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

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setText("VER EL HISTORIAL DE COMPRAS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Configuara Perfil👤");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(btnmodificarcarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btneliminarcarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnrealizarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnmodificarcarrito)
                            .addComponent(btneliminarcarrito)
                            .addComponent(btnrealizarcompra))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 91, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarcarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarcarritoActionPerformed
        dtm.setRowCount(fila + 1);
        agregar_carrito();
    }//GEN-LAST:event_btnagregarcarritoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ac.agregar_consecutivo_carrito();
//        Agregar_productos_al_carrito.mostrar();
        //        Historial_Compras hc = new Historial_Compras();
        //        hc.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
        panefactura.setVisible(true);
        panefactura.setText("AAAAAAAAAAAAAAAAAAAAAAAAA");
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
            java.util.logging.Logger.getLogger(Interfaz_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnagregarcarrito;
    private javax.swing.JButton btneliminarcarrito;
    private javax.swing.JButton btnmodificarcarrito;
    private javax.swing.JButton btnrealizarcompra;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboproductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCarrito;
    private javax.swing.JTextPane panefactura;
    private javax.swing.JTextField txtnumerodeproductos;
    // End of variables declaration//GEN-END:variables
}
