/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static Class.Productos.producto_existe;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

/**
 *
 * @author Camilo
 */
public class Agregar_productos_al_combo {
    
    public static Vector agregar_productos() { //METODO ENCARGADO DE AGREGAR EL CODIGO DEL PRODUCTO EN LA TABLAS
        String contenido = "";
        String ruta_productos = "./productos.txt";
        Vector agregarProductos = new Vector();

        try {
            FileReader fr = new FileReader(ruta_productos);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                if (!vecLinea[0].equals(null)) {
                    agregarProductos.add(vecLinea[0]);    
                }
                
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return agregarProductos;
    }
}
