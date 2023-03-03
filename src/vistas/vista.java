package vistas;

import clases.Aula;
import clases.EstadoAula;
import clases.Ocupante;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ventanas.ventanaAulaEstado;
import ventanas.ventanaAulas;
import ventanas.ventanaMenuPrincipal;
import ventanas.ventanaNuevaAula;
import ventanas.ventanaNuevoOcupante;
import ventanas.ventanaOcupante;

/**
 *
 * @author ISMAEL
 */
public class vista {
    public static void MenuPrincipal(ventanaMenuPrincipal a){
        a.setVisible(true);
        
    }
    //vista de aula
    public static int mostrarRegistroAula(ventanaNuevaAula v, ventanaAulas v2, String op){
        int fila = -1;
        if("Nuevo".equals(op)){
            v.setVisible(true);
            v.jTextAula.setText("");
            v.jTextCapacidad.setText("");
            v.jTextDescripcion.setText("");
        }
        if("Editar".equals(op)){
            fila = v2.tablaAula.getSelectedRow();
            if(fila <= -1){
                JOptionPane.showMessageDialog(v2, "Debe seleccionar una fila");
            }
            if(fila > -1){
                v.setVisible(true);
                String aula = (String)v2.tablaAula.getValueAt(fila, 0);
                String can = (String)v2.tablaAula.getValueAt(fila, 1).toString();
                String des = (String)v2.tablaAula.getValueAt(fila, 2);
                v.jTextAula.setText(aula);
                v.jTextCapacidad.setText(can);
                v.jTextDescripcion.setText(des);
            }
        }
        return fila;
    }
    public static Object entregarRegistroAula(ventanaNuevaAula vistaNA, ventanaAulas v2, int fila){
        String au2 = "";
        if(fila > -1){
            au2 = (String)v2.tablaAula.getValueAt(fila, 0);
        }
        
        Object[] res = new Object[4];
        res[0] = vistaNA.jTextAula.getText().trim();
        res[1] = vistaNA.jTextCapacidad.getText().trim();
        res[2] = vistaNA.jTextDescripcion.getText().trim();
        res[3] = au2;
        
        return res;
    }
    public static void listarRegistrosAula(ventanaAulas a , List v){
        Aula aula = new Aula();
        DefaultTableModel modeloT = new DefaultTableModel();
        JTable tabla = a.tablaAula;
        modeloT = (DefaultTableModel)tabla.getModel();
        modeloT.setRowCount(0);
        List<Aula>lista = v;
        Object[] object = new Object[3];
        for(int i=0 ; i<lista.size() ; i++){
            object[0] = lista.get(i).getNombre();
            object[1] = lista.get(i).getCantidad();
            object[2] = lista.get(i).getDescripcion();
            modeloT.addRow(object);  
        }
        a.tablaAula.setModel(modeloT);
    }
    public static String eliminarRegistroAula(ventanaAulas a){
        int fila = a.tablaAula.getSelectedRow();
        if (fila > -1){
            String nom = (String)a.tablaAula.getValueAt(fila, 0);
            return nom;
        }else{
            JOptionPane.showMessageDialog(a, "Debe seleccionar una fila");
        }
        return null;  
    }
    //vista ocupante
    public static int mostrarRegistroOcupante(ventanaNuevoOcupante v, ventanaOcupante v2, String op){
        int fila = -1;
        if("Nuevo".equals(op)){
            v.setVisible(true);
            v.inputNombreOcupante.setText("");
            v.inputCarreraOcupante.setText("");
            v.inputCargoOcupante.setText("");
            v.inputCuilOcupante.setText("");
        }
        if("Editar".equals(op)){
            fila = v2.tablaOcupante.getSelectedRow();
            if(fila <= -1){
                JOptionPane.showMessageDialog(v2, "Debe seleccionar una fila");
            }
            if(fila > -1){
                v.setVisible(true);
                String nom = (String)v2.tablaOcupante.getValueAt(fila, 0);
                String carr = (String)v2.tablaOcupante.getValueAt(fila, 1);
                String cargo = (String)v2.tablaOcupante.getValueAt(fila, 2);
                String cuil = (String)v2.tablaOcupante.getValueAt(fila, 3).toString();
                v.inputNombreOcupante.setText(nom);
                v.inputCarreraOcupante.setText(carr);
                v.inputCargoOcupante.setText(cargo);
                v.inputCuilOcupante.setText(cuil);
            }
        }
        return fila;
    }
        public static void listarRegistrosOcupante(ventanaOcupante a , List v){
        Ocupante o = new Ocupante();
        DefaultTableModel modeloT = new DefaultTableModel();
        JTable tabla = a.tablaOcupante;
        modeloT = (DefaultTableModel)tabla.getModel();
        modeloT.setRowCount(0);
        List<Ocupante>lista = v;
        Object[] object = new Object[4];
        for(int i=0 ; i<lista.size() ; i++){
            object[0] = lista.get(i).getNombre();
            object[1] = lista.get(i).getCarrera();
            object[2] = lista.get(i).getCargo();
            object[3] = lista.get(i).getCuil();
            modeloT.addRow(object);  
        }
        a.tablaOcupante.setModel(modeloT);
    }
    public static Object entregarRegistroOcupante(ventanaNuevoOcupante vo, ventanaOcupante v2, int fila){
        int cuil2 = 0;
        if(fila > -1){
            cuil2 = Integer.parseInt((String)v2.tablaOcupante.getValueAt(fila, 3).toString());
        }
        
        Object[] res = new Object[5];
        res[0] = vo.inputNombreOcupante.getText().trim();
        res[1] = vo.inputCarreraOcupante.getText().trim();
        res[2] = vo.inputCargoOcupante.getText().trim();
        res[3] = vo.inputCuilOcupante.getText().trim();
        res[4] = cuil2;
        
        return res;
    }
    public static int eliminarRegistroOcupante(ventanaOcupante a){
        int fila = a.tablaOcupante.getSelectedRow();
        if (fila > -1){
            int num = Integer.parseInt((String)a.tablaOcupante.getValueAt(fila, 3).toString());
            return num;
        }else{    
            JOptionPane.showMessageDialog(a, "Debe seleccionar una fila");
        }
        return 0;  
    }
    
    //estado aula
    public static void listarEstadoAula(ventanaAulaEstado vea,List v,String op){
        List<EstadoAula>lista = v;
        DefaultTableModel modeloT = new DefaultTableModel();
        JTable tabla = vea.tablaEstadoAula;
        modeloT = (DefaultTableModel)tabla.getModel();
        modeloT.setRowCount(0);
        Object[] object = new Object[7];
        for(int i=0 ; i<lista.size() ; i++){
            object[0] = lista.get(i).getAula().getNombre();
            object[1] = lista.get(i).getAula().getCantidad();
            object[2] = lista.get(i).getAula().getDescripcion();
            object[3] = lista.get(i).getEstado();
            object[4] = "";
            object[5] = "";
           
            if(lista.get(i).getOcupante() != null){
                object[4] = lista.get(i).getOcupante().getCargo();
                object[5] = lista.get(i).getOcupante().getNombre();
                
            }
            if(op.equals("todo")){
                modeloT.addRow(object);
            }else if(object[3].equals(op)){
                modeloT.addRow(object);
            }
        }
        vea.tablaEstadoAula.setModel(modeloT);
    }
    public static String elegirAulaDesocupada(ventanaAulaEstado a){
        int fila = a.tablaEstadoAula.getSelectedRow();
        if (fila > -1){
            String nom = (String)a.tablaEstadoAula.getValueAt(fila, 0);
            return nom;
        }else{
            JOptionPane.showMessageDialog(a, "Debe seleccionar una fila");
        }
        return null;  
    }
    public static int elegirOcupanteAula(ventanaOcupante a){
        int fila = a.tablaOcupante.getSelectedRow();
        if (fila > -1){
            int cuil = Integer.parseInt((String)a.tablaOcupante.getValueAt(fila, 3).toString());
            return cuil;
        }else{
            JOptionPane.showMessageDialog(a, "Debe seleccionar una fila");
        }
        return -1;
    }
}