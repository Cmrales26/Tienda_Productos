/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 *
 * @author Camilo
 */
public class Agregar_productos_al_carrito {

    static String Fecha;
    static int Consecutivo;
    static int Consecutivo_final;

    public static Vector agregar_codigo(String Producto) { //METODO ENCARGADO DE AGREGAR EL CODIGO DEL PRODUCTO EN LA TABLAS
        String ruta_productos = "./productos.txt";
        Vector agregarcodigo = new Vector();

        try {
            FileReader fr = new FileReader(ruta_productos);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                if (vecLinea[0].equals(Producto)) {
                    agregarcodigo.add(vecLinea[2]);
                }
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return agregarcodigo;
    }

    public static String agregar_fecha() { // METODO ENCARGADO DE AGREGAR A LA TABLA LA FECHA ACUTUAL
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        int mesint = c.MONTH + 1;
        String año = Integer.toString(c.get(Calendar.YEAR));
        String mes = Integer.toString(mesint);
        return Fecha = (dia + " / " + mes + " / " + año);

    }

    public static int agregar_Precios(int cantidad, int total, String Producto) {
        String ruta_productos = "./productos.txt";
        Vector agregar_precio = new Vector();
        String precio;
        int precioint;
        try {
            FileReader fr = new FileReader(ruta_productos);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                if (vecLinea[0].equals(Producto)) {
                    agregar_precio.add(vecLinea[1]);
                    precio = vecLinea[1];
                    precioint = Integer.parseInt(precio);
                    total = precioint * cantidad;
                }

            }
            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public static boolean obtener_siguiente_consecutivo() {
        String ruta_compras = "./compras.txt";
        try {
            FileReader fr = new FileReader(ruta_compras);
            BufferedReader br = new BufferedReader(fr);
            String lineas;
            int i;
            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                Consecutivo = Integer.parseInt(vecLinea[0]);

                for (i = 0; i <= Consecutivo; i++) {
                    Consecutivo_final = i;
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String agregar_consecutivo_carrito() {
       Consecutivo_final++;
       String consecutivo_final_String = Integer.toString(Consecutivo_final);
       return consecutivo_final_String;
    }
}
