/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Frames.Interfaz_usuario;
import Frames.Modificar_cuenta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

/**
 *
 * @author Camilo
 */
public class Actualizar_datos {

    //HolamaineimisDianix
    
    static String usuario = "";
    static String nombre = "";
    static String apellido = "";
    static String email = "";
    static String id = "";
    static String telefono = "";
    static String pass = "";

    public static String recibir_id(String ID) {
        id = ID;
        return ID;
    }

    public static boolean leer_datos(String id) {
        try {
            String ruta_clientes = "./clientes.txt";
            FileReader fr = new FileReader(ruta_clientes);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = linea.split(",");
                if (vecLinea[5].equals(Actualizar_datos.id)) {
                    usuario = vecLinea[0];
                    nombre = vecLinea[1];
                    apellido = vecLinea[2];
                    email = vecLinea[3];
                    telefono = vecLinea[4];
                    id = Actualizar_datos.id;
                    pass = vecLinea[6];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean llenar_datos() {
        Modificar_cuenta mc = new Modificar_cuenta(usuario, nombre, apellido, email, telefono, id, pass);
        mc.setVisible(true);
        Interfaz_usuario iu = new Interfaz_usuario("", "");
        iu.setVisible(false);
        return true;
    }

    public boolean modificar_cliente_archivo(String usuarios, String nombres, String apellidos, String emails, String telefonos, String ids, String passs) {

        String contenido = "";
        String ruta_clientes = "./clientes.txt";
        Vector cliente = new Vector();
        try {
            FileReader fr = new FileReader(ruta_clientes);
            BufferedReader br = new BufferedReader(fr);
            String lineas;

            while ((lineas = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = lineas.split(",");
                if (vecLinea[5].equals(id)) {
                    vecLinea[0] = usuarios;
                    vecLinea[1] = nombres;
                    vecLinea[2] = apellidos;
                    vecLinea[3] = emails;
                    vecLinea[4] = telefonos;
                    vecLinea[6] = passs;
                    vecLinea[7] = vecLinea[7];
                }
                cliente.add(vecLinea);
            }
            br.close();
            fr.close();

            FileWriter fw = new FileWriter(ruta_clientes);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Object valor : cliente) {
                String vec[] = (String[]) valor;
                contenido += vec[0] + "," + vec[1] + "," + vec[2] + "," + vec[3] + "," + vec[4] + "," + vec[5] + "," + vec[6] + "," + vec[7] + "\n";
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
