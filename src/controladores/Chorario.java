package controladores;

import clases.Horario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.Mhorario;
import vistas.Carteles;
import ventanas.vistaHorarios;
import ventanas.vistaNuevoHorario;

/**
 * @author ISMAEL
 */
public class Chorario implements ActionListener{
    vistaHorarios vistaGH = new vistaHorarios();
    vistaNuevoHorario vistaNH = new vistaNuevoHorario();
    Mhorario mh = new Mhorario();
    
    String op = "";
    
    public Chorario(String hacer){
        vistaGH.setVisible(true);
        vistaHorarios.listarHorario(vistaGH, mh.listar());
        if("new".equals(hacer)){
            vistaGH.btnElejirHorario.setEnabled(false);
        }
        //btn de la gestion
        vistaGH.btnEditarHorario.addActionListener(this);
        vistaGH.btnElejirHorario.addActionListener(this);
        vistaGH.btnEliminarHorario.addActionListener(this);
        vistaGH.btnNuevoHorario.addActionListener(this);
        vistaGH.btnSalirHorario.addActionListener(this);
        
        //btn del registro
        vistaNH.btnCancelarHorario.addActionListener(this);
        vistaNH.btnGuardarHorario.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaGH.btnEditarHorario){
            int fila = vistaHorarios.modificamos(vistaGH, vistaNH);
            if(fila > -1){
                op = "Editar";
            } 
        }
        if(e.getSource() == vistaGH.btnElejirHorario){
        
        }
        if(e.getSource() == vistaGH.btnEliminarHorario){
            int input = Carteles.cartelitoTwo("Seguro de eliminar");
            // 0=yes, 1=no, 2=cancel
            if(input == 0){
                int id = vistaHorarios.eliminarRegistro(vistaGH);
                if(id >= 0){
                    int r = mh.Eliminar(id);
                    if(r == 1){
                        Carteles.cartelito("Se elimino correctamente");
                    }
                }
                vistaHorarios.listarHorario(vistaGH,mh.listar());
            }
        }
        if(e.getSource() == vistaGH.btnNuevoHorario){
            op = "Nuevo";
            vistaNuevoHorario.mostrarRegitroNuevo(vistaNH, op);
        }
        if(e.getSource() == vistaGH.btnSalirHorario){
            vistaGH.dispose();
            conrtrollerPrincipal vistaMenu = new conrtrollerPrincipal();
        }
        
        if(e.getSource() == vistaNH.btnCancelarHorario){
            vistaNH.dispose();
        }
        if(e.getSource() == vistaNH.btnGuardarHorario){
            if("Nuevo".equals(op)){
                Object[] res = (Object[]) vistaHorarios.entregarRegistro(vistaNH);
                Horario horario = new Horario();
                horario.setHora_i((String) res[0]);
                horario.setHora_f((String) res[1]);
                horario.setSusecion((int) res[2]);
                int r = mh.agregar(horario);
                if(r == 1){
                    Carteles.cartelito("Se agrego correctamente");
                }
                vistaHorarios.listarHorario(vistaGH,mh.listar());
                vistaNH.dispose();   
            }
            if("Editar".equals(op)){
                Object[] res = (Object[])vistaHorarios.modificarRegistro(vistaNH);
                Horario horario = new Horario();
                horario.setId((int) res[0]);
                horario.setHora_i((String)res[1]);
                horario.setHora_f((String)res[2]);
                horario.setSusecion((int)res[3]);
                int r = mh.Actualizar(horario);
                if(r == 1){
                    Carteles.cartelito("Actualizacion exitosa");
                }
                vistaHorarios.listarHorario(vistaGH,mh.listar());
                vistaNH.dispose();
            }
        }
    }
    
}
