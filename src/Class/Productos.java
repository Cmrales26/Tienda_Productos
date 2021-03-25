/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;
import javax.swing.JOptionPane;
import jdk.jfr.events.FileWriteEvent;

/**
 *
 * @author Camilo
 */
public class Productos {

    public static boolean guardar_productos(Vector productos, String id) {
        try {
            String contenido = "";
            String ruta = "./productos.txt";
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            if (!producto_existe(id)) {
                FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for (Object valor : productos) {
                    if (contenido.equals("")) {
                        contenido = valor.toString();
                    } else {
                        contenido = contenido + "," + valor.toString();
                    }
                }
                bw.write(contenido);
                bw.newLine();
                bw.close();
                fw.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean producto_existe(String cod) {
        String ruta = "./productos.txt";
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = linea.split(",");
                if (vecLinea[2].equalsIgnoreCase(cod)) {
                    br.close();
                    fr.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Vector obtener_productos() {
        String contenido = "";
        String ruta_productos = "./productos.txt";
        Vector productos = new Vector();

        try {
            FileReader fr = new FileReader(ruta_productos);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                productos.add(vecLinea);
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    public static boolean Modificar_producto(String nombre, String precio, String codigo) {
        String contenido = "";
        String ruta_productos = "./productos.txt";
        Vector productos = new Vector();
        try {
            FileReader fr = new FileReader(ruta_productos);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                if (vecLinea[2].equals(codigo)) {
                    vecLinea[0] = nombre;
                    vecLinea[1] = precio;
                }

                productos.add(vecLinea);
            }
            br.close();
            fr.close();

            FileWriter fw = new FileWriter(ruta_productos);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Object valor : productos) {
                String vec[] = (String[]) valor;
                contenido += vec[0] + "," + vec[1] + "," + vec[2] + "\n";
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
