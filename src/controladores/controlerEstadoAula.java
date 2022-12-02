package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.modelo;
import modelos.MestadoAula;
import ventanas.ventanaAulaEstado;

/**
 *
 * @author ISMAEL
 */
public class controlerEstadoAula implements ActionListener{
    
    ventanaAulaEstado vistaAE = new ventanaAulaEstado();
    modelo ma = new modelo();
    MestadoAula mea = new MestadoAula();
    
    public controlerEstadoAula(String op){
        vistaAE.setVisible(true);
        ventanaAulaEstado.listarEstadoAula(vistaAE, ma.listarAula(), mea.listar(),"todo");
        if(op.equals("1")){
            vistaAE.btnDesocuparAula.setEnabled(false);
            vistaAE.btnOcuparAula.setEnabled(false);
        }
        //btn de estados 
        vistaAE.btnAulaDisponible.addActionListener(this);
        vistaAE.btnAulaOcupada.addActionListener(this);
        vistaAE.btnDesocuparAula.addActionListener(this);
        vistaAE.btnEstadoAula.addActionListener(this);
        vistaAE.btnOcuparAula.addActionListener(this);
        vistaAE.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaAE.btnAulaDisponible){
            //vistaAulaEstado.listarEstadoAula(vistaAE, ma.listar(), mea.listar(),"Desocupado");
            vistaAE.btnOcuparAula.setEnabled(true);
            vistaAE.btnDesocuparAula.setEnabled(false);
        }
        if(e.getSource() == vistaAE.btnAulaOcupada){
            //vistaAulaEstado.listarEstadoAula(vistaAE, ma.listar(), mea.listar(),"Ocupado");
            vistaAE.btnDesocuparAula.setEnabled(true);
            vistaAE.btnOcuparAula.setEnabled(false);
        } 
        if(e.getSource() == vistaAE.btnEstadoAula){
            //vistaAulaEstado.listarEstadoAula(vistaAE, ma.listar(), mea.listar(),"todo");
            vistaAE.btnDesocuparAula.setEnabled(false);
            vistaAE.btnOcuparAula.setEnabled(false);
        }
        if(e.getSource() == vistaAE.btnOcuparAula){
            int id = ventanaAulaEstado.modificamos(vistaAE);
            if(id >= 0){
                mea.CambioEstado(id, "Ocupado");
                //vistaAulaEstado.listarEstadoAula(vistaAE, ma.listar(), mea.listar(),"Desocupado");
                vistaAE.btnOcuparAula.setEnabled(true);
                vistaAE.btnDesocuparAula.setEnabled(false);
            }
        }
        if(e.getSource() == vistaAE.btnDesocuparAula){
            int id = ventanaAulaEstado.modificamos(vistaAE);
            if(id >= 0){
                mea.CambioEstado(id, "Desocupado");
                //vistaAulaEstado.listarEstadoAula(vistaAE, ma.listar(), mea.listar(),"Ocupado");
                vistaAE.btnDesocuparAula.setEnabled(true);
                vistaAE.btnOcuparAula.setEnabled(false);
            }
        }
        if(e.getSource() == vistaAE.btnSalir){
            vistaAE.dispose();
            conrtrollerPrincipal vistaMenu = new conrtrollerPrincipal();
        }
    }
    
}
