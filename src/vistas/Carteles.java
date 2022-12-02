/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ISMAEL
 */
public class Carteles {
    public static void cartelito(String cadena){
        JOptionPane.showMessageDialog(null, cadena);
    }
    public static int cartelitoTwo(String cadena){
        int input = JOptionPane.showConfirmDialog(null, cadena);
        return input;
    }

    public static void cartelito(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."+e); //To change body of generated methods, choose Tools | Templates.
    }
}
