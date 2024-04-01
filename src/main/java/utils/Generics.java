/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Models.Cliente;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Generics {
    
    
    public static Object[][] convertListClientsToMatrix2D(List<Cliente> lista){
        
        Object[][] matriz = new Object[lista.size()][5];
         for (int i = 0; i < lista.size(); i++) {
            Cliente persona = lista.get(i);
            matriz[i][0] = persona.getId();
            matriz[i][1] = persona.getNombre();
            matriz[i][2] = persona.getApellido();
            matriz[i][3] = persona.getEmail();
            matriz[i][4] = persona.getFechaCreacion();
        }
         
         return matriz;
        
    }
    
}
