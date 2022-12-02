package controladores;

/**
 *
 * @author ISMAEL
 */
//clases utilizadas
import ventanas.ventanaAulas;
import ventanas.ventanaNuevaAula;
import clases.Aula;
import modelos.modelo;
//librerias utilizadas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vistas.Carteles;

public class controlerAulas implements ActionListener{
    modelo ma = new modelo();
    ventanaAulas vistaGA = new ventanaAulas();
    ventanaNuevaAula vistaNA = new ventanaNuevaAula();
    
    
    String op = "";
    
    public controlerAulas(){
        //btn de la ventanaAulas
        vistaGA.setVisible(true);
        ventanaAulas.listarRegistros(vistaGA ,ma.listarAula());
        this.vistaGA.jButtonNuevo.addActionListener(this);
        this.vistaGA.jButtonEditar.addActionListener(this);
        this.vistaGA.jButtonEliminar.addActionListener(this);
        this.vistaGA.jButtonSalir.addActionListener(this);
        //btn de la vistaNueva
        this.vistaNA.jButtonGuardar.addActionListener(this);
        this.vistaNA.jButtonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaGA.jButtonNuevo){
            op ="Nuevo";
            ventanaAulas.mostrarRegitroNuevo(vistaNA, op);
        }
        if(e.getSource() == vistaGA.jButtonEditar){
            int fila = ventanaAulas.modificamos(vistaGA, vistaNA);
            if(fila > -1){
                op = "Editar";
            }    
        }
        if(e.getSource() == vistaGA.jButtonEliminar){
            int input = Carteles.cartelitoTwo("Seguro de eliminar");
            // 0=yes, 1=no, 2=cancel
            if(input == 0){
                int id = ventanaAulas.eliminarRegistro(vistaGA);
                if(id >= 0){
                    int r = ma.eliminarAula("");
                    if(r == 1){
                        Carteles.cartelito("Se elimino correctamente");
                    }
                }
                ventanaAulas.listarRegistros(vistaGA,ma.listarAula());
            }
            
        }
        if(e.getSource() == vistaGA.jButtonSalir){
            vistaGA.dispose();
            conrtrollerPrincipal vistaMenu = new conrtrollerPrincipal();
        }
        if(e.getSource() == vistaNA.jButtonGuardar){
            if("Editar".equals(op)){
                Object[] res = (Object[])ventanaAulas.modificarRegistro(vistaNA);
                Aula aula = new Aula();
                //aula.setId((int) res[0]);
                aula.setNombre((String)res[1]);
                aula.setCantidad((int)res[2]);
                aula.setDescripcion((String)res[3]);
                int r = ma.actualizarAula(aula,"");
                if(r == 1){
                    Carteles.cartelito("Actualizacion exitosa");
                }
                ventanaAulas.listarRegistros(vistaGA,ma.listarAula());
                vistaNA.dispose();
            }
            if("Nuevo".equals(op)){
                Object[] res = (Object[]) ventanaAulas.entregarRegistro(vistaNA);
                Aula aula = new Aula();
                aula.setNombre((String) res[0]);
                aula.setCantidad((int) res[1]);
                aula.setDescripcion((String) res[2]);
                int r = ma.agregarAula(aula);
                if(r == 1){
                    Carteles.cartelito("Se agrego correctamente");
                }
                ventanaAulas.listarRegistros(vistaGA,ma.listarAula());
                vistaNA.dispose();
            }
        }
        if(e.getSource() == vistaNA.jButtonCancelar){
            vistaNA.dispose();
        }
    }
    
}
