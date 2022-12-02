package controladores;
//Clases utilizadas
import clases.Aula;
import clases.Ocupante;
import ventanas.ventanaMenuPrincipal;
//librerias utilizadas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.modelo;
import ventanas.Vcontrol;
import ventanas.ventanaAulaEstado;
import ventanas.ventanaOcupante;
import ventanas.ventanaAulas;
import ventanas.ventanaNuevaAula;
import ventanas.ventanaNuevoOcupante;
import vistas.Carteles;
import vistas.vista;

/**
 * @author ISMAEL
 */
public class conrtrollerPrincipal implements ActionListener{
    //Ventanas
    ventanaAulas ventanaA = new ventanaAulas();
    ventanaNuevaAula ventanaNA = new ventanaNuevaAula();
    ventanaMenuPrincipal ventanaMenu = new ventanaMenuPrincipal();
    ventanaOcupante ventanaO = new ventanaOcupante();
    ventanaNuevoOcupante ventanaNO = new ventanaNuevoOcupante();
    ventanaAulaEstado ventanaAE = new ventanaAulaEstado();
    //Modelos
    modelo m = new modelo();
    
    //var de opcion del guardado
    String op = "";
    int num = -1;
    int cuil = 0;
    public conrtrollerPrincipal(){
        vista.MenuPrincipal(ventanaMenu);
     
        //Btn de ventana menu principal
        this.ventanaMenu.btnAula.addActionListener(this);
        this.ventanaMenu.btnOcupante.addActionListener(this);
        this.ventanaMenu.btnGestionAula.addActionListener(this);
        //btn de ventana aula
        this.ventanaA.jButtonEditar.addActionListener(this);
        this.ventanaA.jButtonEliminar.addActionListener(this);
        this.ventanaA.jButtonNuevo.addActionListener(this);
        this.ventanaA.jButtonSalir.addActionListener(this);
        //btn de ventana nueva aula
        this.ventanaNA.jButtonCancelar.addActionListener(this);
        this.ventanaNA.jButtonGuardar.addActionListener(this);
        //btn de ventana ocupante
        this.ventanaO.btnEditarOcupante.addActionListener(this);
        this.ventanaO.btnEliminarOcupante.addActionListener(this);
        this.ventanaO.btnNuevoOcupante.addActionListener(this);
        this.ventanaO.btnSalirOcupante.addActionListener(this);
        this.ventanaO.btnSeleccionarOcupante.addActionListener(this);
        //btn de ventana nuevo ocupante
        this.ventanaNO.btnCancelarOcupante.addActionListener(this);
        this.ventanaNO.btnGuardarOcupante.addActionListener(this);
        //btn de ventana aula estado
        this.ventanaAE.btnAulaDisponible.addActionListener(this);
        this.ventanaAE.btnAulaOcupada.addActionListener(this);
        this.ventanaAE.btnDesocuparAula.addActionListener(this);
        this.ventanaAE.btnEstadoAula.addActionListener(this);
        this.ventanaAE.btnOcuparAula.addActionListener(this);
        this.ventanaAE.btnSalir.addActionListener(this);
        this.ventanaAE.btnSelecOcupante.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Menu principal
        if(e.getSource() == ventanaMenu.btnAula){
            ventanaA.setVisible(true);
            vista.listarRegistrosAula(ventanaA, m.listarAula());
            ventanaMenu.dispose();
        }
        if(e.getSource() == ventanaMenu.btnOcupante){
            ventanaO.setVisible(true);
            vista.listarRegistrosOcupante(ventanaO, m.listarOcupante());
            ventanaO.btnSeleccionarOcupante.setEnabled(false);
            ventanaMenu.dispose();
        }
        if(e.getSource() == ventanaMenu.btnGestionAula){
            ventanaAE.setVisible(true);
            vista.listarEstadoAula(ventanaAE, m.listarEstado(), "todo");
            ventanaAE.btnDesocuparAula.setEnabled(false);
            ventanaAE.btnOcuparAula.setEnabled(false);
            ventanaAE.btnSelecOcupante.setEnabled(false);
            ventanaMenu.dispose();
        }
        //Aula
        if(e.getSource() == ventanaA.jButtonNuevo){
            op = "Nuevo";
            num = -1;
            vista.mostrarRegistroAula(ventanaNA,ventanaA, op);
        }
        if(e.getSource() == ventanaA.jButtonEliminar){
            
            String nom = vista.eliminarRegistroAula(ventanaA);
            if(nom != null){
                int input = Carteles.cartelitoTwo("Seguro de eliminar");
                if(input == 0){// 0=yes, 1=no, 2=cancel
                    int r = m.eliminarAula(nom);
                    if(r == 1){
                        Carteles.cartelito("Se elimino correctamente");
                    }
                }
                vista.listarRegistrosAula(ventanaA,m.listarAula());
            }
        }
        if(e.getSource() == ventanaA.jButtonEditar){
            int fila = vista.mostrarRegistroAula(ventanaNA, ventanaA, "Editar");
            if(fila > -1){
                op = "Editar";
                num = fila;
            }
        }
        if(e.getSource() == ventanaA.jButtonSalir){
            ventanaA.dispose();
            vista.MenuPrincipal(ventanaMenu);
            op = "";
            num = -1;
        }
        //nueva Aula
        if(e.getSource() == ventanaNA.jButtonGuardar){
            Object[] res = (Object[]) vista.entregarRegistroAula(ventanaNA,ventanaA,num);
            Aula aula = new Aula();
            aula.setNombre((String) res[0]);
            aula.setCantidad((int) res[1]);
            aula.setDescripcion((String) res[2]);
            if("Editar".equals(op)){
                int r = m.actualizarAula(aula, (String) res[3]);
                if(r == 1){
                    Carteles.cartelito("Actualizacion exitosa");
                    num = -1;
                }
            }
            if("Nuevo".equals(op)){
                int r = m.agregarAula(aula);
                if(r == 1){
                    Carteles.cartelito("Se agrego correctamente");
                }
                
            }
            vista.listarRegistrosAula(ventanaA,m.listarAula());
            ventanaNA.dispose();
        }
        if(e.getSource() == ventanaNA.jButtonCancelar){
            ventanaNA.dispose();
            num = -1;
        }
   
        //Ocupante
        if(e.getSource() == ventanaO.btnEditarOcupante){
            int fila = vista.mostrarRegistroOcupante(ventanaNO, ventanaO, "Editar");
            if(fila > -1){
                op = "Editar";
                num = fila;
            }
        }
        if(e.getSource() == ventanaO.btnEliminarOcupante){
            
            int num = vista.eliminarRegistroOcupante(ventanaO);
            if(num > 0){
                int input = Carteles.cartelitoTwo("Seguro de eliminar");
                // 0=yes, 1=no, 2=cancel
                if(input == 0){
                    int r = m.eliminarOcupante(num);
                    if(r == 1){
                        Carteles.cartelito("Se elimino correctamente");
                    }
                }
                vista.listarRegistrosOcupante(ventanaO,m.listarOcupante());
            }
        }
        if(e.getSource() == ventanaO.btnNuevoOcupante){
            op = "Nuevo";
            num = -1;
            vista.mostrarRegistroOcupante(ventanaNO,ventanaO, op);
        }
        if(e.getSource() == ventanaO.btnSalirOcupante){
            ventanaO.dispose();
            vista.MenuPrincipal(ventanaMenu);
            op = "";
            num = -1;
        }
        if(e.getSource() == ventanaO.btnSeleccionarOcupante){
            cuil = vista.elegirOcupanteAula(ventanaO);
            ventanaO.dispose();
        }
        //Nuevo ocupante
        if(e.getSource() == ventanaNO.btnCancelarOcupante){
            ventanaNO.dispose();
            num = -1;
        }
        if(e.getSource() == ventanaNO.btnGuardarOcupante){
            Object[] res = (Object[]) vista.entregarRegistroOcupante(ventanaNO,ventanaO,num);
            Ocupante o = new Ocupante();
            o.setNombre((String) res[0]);
            o.setCarrera((String) res[1]);
            o.setCargo((String) res[2]);
            o.setCuil((int) res[3]);
            if("Editar".equals(op)){
                int r = m.actualizarOcupante(o, (int) res[4]);
                if(r == 1){
                    Carteles.cartelito("Actualizacion exitosa");
                    num = -1;
                }
            }
            if("Nuevo".equals(op)){
                int r = m.agregarOcupante(o);
                if(r == 1){
                    Carteles.cartelito("Se agrego correctamente");
                }
                
            }
            vista.listarRegistrosOcupante(ventanaO,m.listarOcupante());
            ventanaNO.dispose();
        }
        //Aula estado
        if(e.getSource() == ventanaAE.btnAulaDisponible){
            vista.listarEstadoAula(ventanaAE, m.listarEstado(), "Desocupado");
            ventanaAE.btnDesocuparAula.setEnabled(false);
            ventanaAE.btnOcuparAula.setEnabled(true);
            ventanaAE.btnSelecOcupante.setEnabled(true);
        }
        if(e.getSource() == ventanaAE.btnAulaOcupada){
            vista.listarEstadoAula(ventanaAE, m.listarEstado(), "Ocupado");
            ventanaAE.btnDesocuparAula.setEnabled(true);
            ventanaAE.btnOcuparAula.setEnabled(false);
            ventanaAE.btnSelecOcupante.setEnabled(false);
        }
        if(e.getSource() == ventanaAE.btnDesocuparAula){
            String nom = vista.elegirAulaDesocupada(ventanaAE);
            if(nom != null){
                int res = m.CambioEstado(0, nom, "Ocupado");
                if(res == 1){
                    Carteles.cartelito("Aula libre");
                    vista.listarEstadoAula(ventanaAE, m.listarEstado(), "Ocupado");
                }else{
                    Carteles.cartelito("Otra vez");
                }
            }
        }
        if(e.getSource() == ventanaAE.btnEstadoAula){
            vista.listarEstadoAula(ventanaAE, m.listarEstado(), "todo");
            ventanaAE.btnDesocuparAula.setEnabled(false);
            ventanaAE.btnOcuparAula.setEnabled(false);
            ventanaAE.btnSelecOcupante.setEnabled(false);
        }
        if(e.getSource() == ventanaAE.btnSelecOcupante){
            ventanaO.setVisible(true);
            vista.listarRegistrosOcupante(ventanaO, m.listarOcupante());
            ventanaO.btnSeleccionarOcupante.setEnabled(true);
        }
        if(e.getSource() == ventanaAE.btnOcuparAula){
            String nom = vista.elegirAulaDesocupada(ventanaAE);
            if(nom != null){
                if(cuil > 0){
                    int res = m.CambioEstado(cuil, nom, "Desocupado");
                    System.out.print(res);
                    vista.listarEstadoAula(ventanaAE, m.listarEstado(), "Desocupado");
                    if(res == 1 ){
                        Carteles.cartelito("Asignacion exitosa");
                    }else{
                        Carteles.cartelito("Ocurrio un problema intentelo otra vez");
                    }
                    cuil = 0;
                }else{
                    Carteles.cartelito("Debe selecionar un Ocupante");
                }
            }
        }
        if(e.getSource() == ventanaAE.btnSalir){
            ventanaAE.dispose();
            vista.MenuPrincipal(ventanaMenu);
        }
    }
}
