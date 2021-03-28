/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Camilo
 */
public class Agregar_Carrito_al_archivo {

    private String ruta_compras = "./compras.txt";

    public boolean agregar_al_archivo(String no_pedido, String fecha_de_compra, String codigo, String producto, String numero, String precio, String cliente,String id) {
        try {
            File archivo = new File(ruta_compras);
            String Contenido = no_pedido + "," + fecha_de_compra + "," + codigo +"," +producto+ "," + numero + "," + precio + "," + cliente + "," + id;
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Contenido);
            bw.newLine();
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
