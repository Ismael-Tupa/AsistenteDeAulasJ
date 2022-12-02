package controladores;

import clases.Carrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelos.Mcarrera;
import ventanas.vistaCursado;
import ventanas.vistaNuevoCursado;

/**
 *
 * @author ISMAEL
 */
public class controlerCursado implements ActionListener{
    vistaCursado vistaC = new vistaCursado();
    Mcarrera mc = new Mcarrera();
    vistaNuevoCursado vistaNC = new vistaNuevoCursado();
    public controlerCursado(){
        vistaC.setVisible(true);
        int hasta = vistaCursado.listarComboBoxC(vistaC,mc.listar());
        vistaCursado.listarComboBoxS(vistaC, hasta);
        
        //btn cursado
        vistaC.btnNuevoCursado.addActionListener(this);
        vistaC.btnFiltrarCursadoC.addActionListener(this);
        vistaC.btnFiltrarCursadoS.addActionListener(this);
        vistaC.btnSalirCursado.addActionListener(this);
        vistaC.btnEditarCursado.addActionListener(this);
        vistaC.btnEliminarCursado.addActionListener(this);
       
        //btn del nuevo cursado
        vistaNC.btnAgregarMateriaCursado.addActionListener(this);
        vistaNC.btnCancelarNuevoCursado.addActionListener(this);
        vistaNC.btnEditarMateriaCursado.addActionListener(this);
        vistaNC.btnElejirCarreraCursado.addActionListener(this);
        vistaNC.btnEliminarMateriaCursado.addActionListener(this);
        vistaNC.btnGuardarNuevoCursado.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaC.btnNuevoCursado){
            
            vistaNC.setVisible(true);
        }
        if(e.getSource() == vistaC.btnFiltrarCursadoC){
            String aa = (String) vistaC.listaCarreraCursado.getSelectedItem();
            List<Carrera>listaC = mc.listar();
            for(int i=0; i < listaC.size(); i++){            
                if(aa.equals(listaC.get(i).getNombre())){
                    vistaCursado.listarComboBoxS(vistaC,listaC.get(i).getDuracion());
                }
            }
        }
        if(e.getSource() == vistaC.btnFiltrarCursadoS){            
            String ad = (String) vistaC.listaSigloCursado.getSelectedItem();
            System.out.println(ad);
        }
        if(e.getSource() == vistaC.btnSalirCursado){
            vistaC.dispose();
            conrtrollerPrincipal vistaMenu = new conrtrollerPrincipal();
        }
    }
}
