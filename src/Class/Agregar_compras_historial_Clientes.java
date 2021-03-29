/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

/**
 *
 * @author Camilo
 */
public class Agregar_compras_historial_Clientes {
    static String id="";
        
    public static String obtener_id(String ID){
        id = ID;
        return ID;
    }
    
    public static Vector obtener_compras(){
        String contenido = "";
        String ruta_compras = "./compras.txt";
        Vector compras = new Vector();
       try {
            FileReader fr = new FileReader(ruta_compras);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                if (vecLinea[7].equalsIgnoreCase(id)) {
                    compras.add(vecLinea);
                }
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return compras;
    }
}