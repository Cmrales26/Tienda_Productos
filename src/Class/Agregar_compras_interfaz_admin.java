/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class Agregar_compras_interfaz_admin {

    public static Vector AgregarCompras() {
        String ruta = "./compras.txt";
        Vector AC = new Vector();
        try {
            FileReader FileR = new FileReader(ruta);
            BufferedReader BufferedR = new BufferedReader(FileR);

            String line;

            while ((line = BufferedR.readLine()) != null) {
                String Vecline[];
                Vecline = line.split(",");
                AC.add(Vecline);

            }

            FileR.close();
            BufferedR.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return AC;
    }

}
