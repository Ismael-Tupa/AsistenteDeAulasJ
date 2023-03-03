package controladores;
//Clases utilizadas
import clases.Aula;
import clases.BDLocal;
import clases.Ocupante;
import ventanas.ventanaMenuPrincipal;
import clases.Validaciones;
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
    int estadoBD = 0;
    public conrtrollerPrincipal(){
        estadoBD = m.InicioModel();
        vista.MenuPrincipal(ventanaMenu);
     
        //Btn de ventana menu principal
        this.ventanaMenu.btnAula.addActionListener(this);
        this.ventanaMenu.btnOcupante.addActionListener(this);
        this.ventanaMenu.btnGestionAula.addActionListener(this);
        this.ventanaMenu.btnSincronisar.addActionListener(this);
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
            System.out.println("aaaaa");
            estadoBD = m.InicioModel();
            ventanaA.setVisible(true);
            vista.listarRegistrosAula(ventanaA, m.listarAula(estadoBD));
            ventanaMenu.dispose();
        }
        if(e.getSource() == ventanaMenu.btnOcupante){
            estadoBD = m.InicioModel();
            ventanaO.setVisible(true);
            vista.listarRegistrosOcupante(ventanaO, m.listarOcupante(estadoBD));
            ventanaO.btnSeleccionarOcupante.setEnabled(false);
            ventanaMenu.dispose();
        }
        if(e.getSource() == ventanaMenu.btnGestionAula){
            estadoBD = m.InicioModel();
            ventanaAE.setVisible(true);
            vista.listarEstadoAula(ventanaAE, m.listarEstado(estadoBD), "todo");
            ventanaAE.btnDesocuparAula.setEnabled(false);
            ventanaAE.btnOcuparAula.setEnabled(false);
            ventanaAE.btnSelecOcupante.setEnabled(false);
            ventanaMenu.dispose();
        }
        if(e.getSource() == ventanaMenu.btnSincronisar){
            BDLocal.InicioLocalBD(m.listarAula(estadoBD), m.listarOcupante(estadoBD), m.listarEstado(estadoBD));
            System.out.println("listo");
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
                    int r = m.eliminarAula(estadoBD, nom);
                    if(r == 1){
                        Carteles.cartelito("Se elimino correctamente");
                    }
                }
                vista.listarRegistrosAula(ventanaA,m.listarAula(estadoBD));
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
            
            if(Validaciones.TextNumber((String) res[0])){
                if(Validaciones.Number((String) res[1])){
                    if(Validaciones.Text((String) res[2])){
                        Aula aula = new Aula();
                        aula.setNombre((String) res[0]);
                        aula.setCantidad((int) res[1]);
                        aula.setDescripcion((String) res[2]);
                        if("Editar".equals(op)){
                            int r = m.actualizarAula(estadoBD, aula, (String) res[3]);
                            if(r == 1){
                                Carteles.cartelito("Actualizacion exitosa");
                                num = -1;
                            }
                        }
                        if("Nuevo".equals(op)){
                            int r = m.agregarAula(estadoBD,aula);
                            if(r == 1){
                                Carteles.cartelito("Se agrego correctamente");
                            }

                        }
                        vista.listarRegistrosAula(ventanaA,m.listarAula(estadoBD));
                        ventanaNA.dispose();
                    }else{
                        Carteles.cartelito("Error al validar la cantidad");
                        vista.mostrarRegistroAula(ventanaNA,ventanaA, op);
                    }
                }else{
                    Carteles.cartelito("Error al validar la cantidad");
                    vista.mostrarRegistroAula(ventanaNA,ventanaA, op);
                }
            }else{
                Carteles.cartelito("Error al validar el nombre");
                vista.mostrarRegistroAula(ventanaNA,ventanaA, op);
            }
            
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
                    int r = m.eliminarOcupante(estadoBD, num);
                    if(r == 1){
                        Carteles.cartelito("Se elimino correctamente");
                    }
                }
                vista.listarRegistrosOcupante(ventanaO,m.listarOcupante(estadoBD));
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
            if(Validaciones.Text((String) res[0])){
                if(Validaciones.Text((String) res[1])){
                    if(Validaciones.Text((String) res[2])){
                        if(Validaciones.Number((String) res[3])){
                            Ocupante o = new Ocupante();
                            o.setNombre((String) res[0]);
                            o.setCarrera((String) res[1]);
                            o.setCargo((String) res[2]);
                            o.setCuil((int) res[3]);
                            if("Editar".equals(op)){
                                int r = m.actualizarOcupante(estadoBD, o, (int) res[4]);
                                if(r == 1){
                                    Carteles.cartelito("Actualizacion exitosa");
                                    num = -1;
                                }
                            }
                            if("Nuevo".equals(op)){
                                int r = m.agregarOcupante(estadoBD, o);
                                if(r == 1){
                                    Carteles.cartelito("Se agrego correctamente");
                                }

                            }
                            vista.listarRegistrosOcupante(ventanaO,m.listarOcupante(estadoBD));
                            ventanaNO.dispose();
                        }else{
                            Carteles.cartelito("Error al validar el cuil, solo numeros");
                            vista.mostrarRegistroOcupante(ventanaNO,ventanaO, op);
                        }
                    }else{
                        Carteles.cartelito("Error al validar el cargo, solo texto");
                        vista.mostrarRegistroOcupante(ventanaNO,ventanaO, op);
                    }
                }else{
                    Carteles.cartelito("Error al validar la carrera, solo texto");
                    vista.mostrarRegistroOcupante(ventanaNO,ventanaO, op);
                }
            }else{
                Carteles.cartelito("Error al validar el nombre, solo texto");
                vista.mostrarRegistroOcupante(ventanaNO,ventanaO, op);
            }
        }
        //Aula estado
        if(e.getSource() == ventanaAE.btnAulaDisponible){
            vista.listarEstadoAula(ventanaAE, m.listarEstado(estadoBD), "Desocupado");
            ventanaAE.btnDesocuparAula.setEnabled(false);
            ventanaAE.btnOcuparAula.setEnabled(true);
            ventanaAE.btnSelecOcupante.setEnabled(true);
        }
        if(e.getSource() == ventanaAE.btnAulaOcupada){
            vista.listarEstadoAula(ventanaAE, m.listarEstado(estadoBD), "Ocupado");
            ventanaAE.btnDesocuparAula.setEnabled(true);
            ventanaAE.btnOcuparAula.setEnabled(false);
            ventanaAE.btnSelecOcupante.setEnabled(false);
        }
        if(e.getSource() == ventanaAE.btnDesocuparAula){
            String nom = vista.elegirAulaDesocupada(ventanaAE);
            if(nom != null){
                int res = m.CambioEstado(estadoBD, 0, nom, "Ocupado");
                if(res == 1){
                    Carteles.cartelito("Aula libre");
                    vista.listarEstadoAula(ventanaAE, m.listarEstado(estadoBD), "Ocupado");
                }else{
                    Carteles.cartelito("Otra vez");
                }
            }
        }
        if(e.getSource() == ventanaAE.btnEstadoAula){
            vista.listarEstadoAula(ventanaAE, m.listarEstado(estadoBD), "todo");
            ventanaAE.btnDesocuparAula.setEnabled(false);
            ventanaAE.btnOcuparAula.setEnabled(false);
            ventanaAE.btnSelecOcupante.setEnabled(false);
        }
        if(e.getSource() == ventanaAE.btnSelecOcupante){
            ventanaO.setVisible(true);
            vista.listarRegistrosOcupante(ventanaO, m.listarOcupante(estadoBD));
            ventanaO.btnSeleccionarOcupante.setEnabled(true);
        }
        if(e.getSource() == ventanaAE.btnOcuparAula){
            String nom = vista.elegirAulaDesocupada(ventanaAE);
            if(nom != null){
                if(cuil > 0){
                    int res = m.CambioEstado(estadoBD, cuil, nom, "Desocupado");
                    System.out.print(res);
                    vista.listarEstadoAula(ventanaAE, m.listarEstado(estadoBD), "Desocupado");
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
