/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

/**
 *
 * @author Camilo
 */
public class Modificar_historial_cliente {
    public static boolean Modificar_producto(String nombre,String cantidad, String precio,String cod_producto, String consecutivo) {
        String contenido = "";
        String ruta_productos = "./compras.txt";
        Vector productos = new Vector();
        try {
            FileReader fr = new FileReader(ruta_productos);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                if (vecLinea[0].equals(consecutivo)) {
                    vecLinea[1] = vecLinea[1];
                    vecLinea[2] = cod_producto;
                    vecLinea[3] = nombre;
                    vecLinea[4] = cantidad;
                    vecLinea[5] = precio;
                    vecLinea[6] = vecLinea[6];
                    vecLinea[7] = vecLinea[7];
                   
                }

                productos.add(vecLinea);
            }
            br.close();
            fr.close();

            FileWriter fw = new FileWriter(ruta_productos);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Object valor : productos) {
                String vec[] = (String[]) valor;
                contenido += vec[0] + "," + vec[1] + "," + vec[2] + "," + vec[3] + "," + vec[4] + "," + vec[5] + "," + vec[6] + "," + vec[7] +"\n";
            }

            bw.write(contenido);
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
