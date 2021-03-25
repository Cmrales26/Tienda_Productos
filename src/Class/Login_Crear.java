/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Frames.InterfazAdmin;
import Frames.Interfaz_usuario;
import Frames.Login;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Camilo
 */
public class Login_Crear {

    private String ruta_clientes = "./clientes.txt";
    InterfazAdmin ia;
    Interfaz_usuario iu;
    public String Usuario;
    public String Id = "";
    int fila = 0;

    public boolean registrar(String usuario, String nombre, String apellido, String email, String telefono, String id, String pass, String tipo) {

        try {

            String Contenido = usuario + "," + nombre + "," + apellido + "," + email + "," + telefono + "," + id + "," + pass + "," + tipo;

            File archivo = new File(ruta_clientes);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            if (!usuarioexiste(usuario, id)) {
                FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(Contenido);
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

    public boolean usuarioexiste(String usuario, String id) {
        try {
            FileReader fr = new FileReader(ruta_clientes);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = linea.split(",");
                if (vecLinea[0].equalsIgnoreCase(usuario) || vecLinea[4].equals(id)) {
                    Id = vecLinea[4];
                    Usuario = vecLinea[0];
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

    public boolean ingresar_usuario(String usuario, String pass, String tipo) {
        try {
            FileReader fr = new FileReader(ruta_clientes);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = linea.split(",");
                if (vecLinea[0].equalsIgnoreCase(usuario) && vecLinea[6].equals(pass) && vecLinea[7].equalsIgnoreCase(tipo)) {
                    Id = vecLinea[5];
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

    public boolean ingresar(String usuario, String pass) {

        if (ingresar_usuario(usuario, pass, "cliente")) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario);
            Interfaz_usuario iu = new Interfaz_usuario(usuario, Id);
            iu.setVisible(true);
            return true;
        } else if (ingresar_usuario(usuario, pass, "administrador")) {
            String[] botones = {"Ingresar como cliente", "Ingresar como administrador"};
            int Mensaje = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Ingreso a Interfaz", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
            switch (Mensaje) {
                case 0:
                    Interfaz_usuario iu = new Interfaz_usuario(usuario, Id);
                    JOptionPane.showMessageDialog(null, "Haz ingresado como cliente");
                    iu.setVisible(true);
                    break;
                case 1:
                    InterfazAdmin ia = new InterfazAdmin();
                    JOptionPane.showMessageDialog(null, "Haz ingresado como Administrador");
                    ia.setVisible(true);
                case 2:
                    
                default:
                    break;
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña no existentes");
        }
        return false;
    }

        /*public boolean ingresar_admin(String usuario, String pass, String tipo) {
        try {
            FileReader fr = new FileReader(ruta_clientes);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String vecLinea[];
                vecLinea = linea.split(",");
                    if (vecLinea[0].equalsIgnoreCase(usuario) && vecLinea[6].equalsIgnoreCase(pass) && vecLinea[7].equalsIgnoreCase(tipo)) {
                        br.close();
                        fr.close();
                        return true;
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/
}
