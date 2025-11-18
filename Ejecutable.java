import java.io.*;
import java.nio.file.*;

import GUI.ColCombos;
import GUI.Combo;
import GUI.GUIResto;
import GUI.Resto;


public class Ejecutable {
    
    // << Main >>
    public static void main(String args []){
        System.setProperty("sun.java2d.uiScale", "1"); 
        ColCombos combos = leerMenu("db/combos.csv");
        Resto resto= new Resto(12, combos);
        new GUIResto(resto);
    }
    
    private static ColCombos leerMenu(String path){
        ColCombos salida =null;
        String str;
        
        try {   
            //Cuenta la cantidad de combos
            BufferedReader in = new BufferedReader(new FileReader(path));
            int cant = 0;
            cant = (int) Files.lines(Paths.get(path)).count();
                
            //Carga las combos en el Menu
            String [] valores;
            salida = new ColCombos(cant);
            int precio,cantidad;
            String nombre;
            String descr;
                
            str = in.readLine();
            while (str!=null){
                valores = str.split(";");
                nombre = valores[0];
                precio = Integer.parseInt(valores[1]);
                cantidad = Integer.parseInt(valores[2]);                   
                descr = valores[3];
                salida.agregarCombo(new Combo(nombre, precio,cantidad,descr));
                str = in.readLine();
            }
            in.close();
        }
        catch (IOException err) {
            System.out.println("Se produjo un error al leer el archivo");
        }
        
        return salida;
    }
}
