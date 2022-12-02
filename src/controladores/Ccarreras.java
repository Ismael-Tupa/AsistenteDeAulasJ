package controladores;

/**
 *
 * @author ISMAEL
 */
import clases.Carrera;
import ventanas.vistaCarreras;
import ventanas.vistaNuevaCarrera;

import modelos.Mcarrera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.Carteles;

public class Ccarreras implements ActionListener{
    vistaCarreras vistaGC = new vistaCarreras();
    vistaNuevaCarrera vistaNC = new vistaNuevaCarrera();
    Mcarrera mc = new Mcarrera();
    
    String op = "";
    
    public Ccarreras(String hacer){
        
        vistaGC.setVisible(true);
        vistaCarreras.listarCarrera(vistaGC, mc.listar());
        if("new".equals(hacer)){
            vistaGC.btnSeleccionCarrera.setEnabled(false);
        }
        //btn del inicio de gestion
        vistaGC.btnSeleccionCarrera.addActionListener(this);
        vistaGC.jButtonNuevo.addActionListener(this);
        vistaGC.jButtonEditar.addActionListener(this);
        vistaGC.jButtonEliminar.addActionListener(this);
        vistaGC.jButtonSalir.addActionListener(this);
        
        //btn del registro
        vistaNC.cancelarRegistro.addActionListener(this);
        vistaNC.guardarRegistro.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Event de inicio de gestion
        if(e.getSource() == vistaGC.jButtonNuevo){
            op = "Nuevo";
            vistaNuevaCarrera.mostrarRegitroNuevo(vistaNC, op);
        }
        if(e.getSource() == vistaGC.jButtonEditar){
            int fila = vistaCarreras.modificamos(vistaGC, vistaNC);
            if(fila > -1){
                op = "Editar";
            } 
        }
        if(e.getSource() == vistaGC.jButtonEliminar){
            int input = Carteles.cartelitoTwo("Seguro de eliminar");
            // 0=yes, 1=no, 2=cancel
            if(input == 0){
                int id = vistaCarreras.eliminarRegistro(vistaGC);
                if(id >= 0){
                    int r = mc.Eliminar(id);
                    if(r == 1){
                        Carteles.cartelito("Se elimino correctamente");
                    }
                }
                vistaCarreras.listarCarrera(vistaGC,mc.listar());
            }
        }
        if(e.getSource() == vistaGC.jButtonSalir){
            vistaGC.dispose();
            conrtrollerPrincipal vistaMenu = new conrtrollerPrincipal();
        }
        //Event del registro
        if(e.getSource() == vistaNC.cancelarRegistro){
            vistaNC.dispose();
        }
        if(e.getSource() == vistaNC.guardarRegistro){
            if("Nuevo".equals(op)){
                Object[] res = (Object[]) vistaCarreras.entregarRegistro(vistaNC);
                Carrera carrera = new Carrera();
                carrera.setNombre((String) res[0]);
                carrera.setTipo((String) res[1]);
                carrera.setDuracion((int) res[2]);
                int r = mc.agregar(carrera);
                if(r == 1){
                    Carteles.cartelito("Se agrego correctamente");
                }
                vistaCarreras.listarCarrera(vistaGC,mc.listar());
                vistaNC.dispose();   
            }
            if("Editar".equals(op)){
                Object[] res = (Object[])vistaCarreras.modificarRegistro(vistaNC);
                Carrera carrera = new Carrera();
                carrera.setId((int) res[0]);
                carrera.setNombre((String)res[1]);
                carrera.setTipo((String)res[2]);
                carrera.setDuracion((int)res[3]);
                int r = mc.Actualizar(carrera);
                if(r == 1){
                    Carteles.cartelito("Actualizacion exitosa");
                }
                vistaCarreras.listarCarrera(vistaGC,mc.listar());
                vistaNC.dispose();
            }
        }
    }
}
