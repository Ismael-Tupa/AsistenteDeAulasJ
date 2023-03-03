package modelos;

/**
 *
 * @author ISMAEL
 */
import clases.Aula;
import clases.BDLocal;
import clases.Conexion;
import clases.EstadoAula;
import clases.Ocupante;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class modelo {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public int InicioModel(){
        int res = 0;
        con = Conexion.getInstance();
        System.out.println("Estas?");
        if(con == null){
            res=1;
            System.out.println("no");
        }
        return res;
    }
    
    //Aula
    public List listarAula(int estadoBD){
        List<Aula>datos = new ArrayList<>();
        String sql = "select * from aulas order by nombre";
        try{
            if(estadoBD == 1){
                datos = BDLocal.LeerListAulas();
                
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Aula a = new Aula();
                    a.setNombre(rs.getString(2));
                    a.setCantidad(rs.getInt(3));
                    a.setDescripcion(rs.getString(4));
                    datos.add(a);
                }
                return datos;
            } 
        }catch(SQLException e){
            System.out.println("Error en el modelo listarAula :"+e);
            
        }
        return datos;
    }
    public int agregarAula(int estadoBD, Aula a){
        int res = 1;
        String sql = "insert into aulas values (?,?,?,?)";
        try{
            if(estadoBD == 1){
                System.out.println("jjjjjjj");
                List <Aula> aulas = BDLocal.LeerListAulas();
                aulas.add(a);
                //System.out.println(aulas.get(aulas.size()).getNombre());
                BDLocal.GuardarListAula(aulas);
                List <EstadoAula> estadosAulas = BDLocal.LeerListEstado();
                EstadoAula ea = new EstadoAula();
                ea.setEstado("Desocupado");
                ea.setAula(a);
                estadosAulas.add(ea);
                BDLocal.GuardarListEstado(estadosAulas);
                res = 1;
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement(sql);
                ps.setString(1, "0");
                ps.setString(2, a.getNombre());
                ps.setInt(3, a.getCantidad());
                ps.setString(4, a.getDescripcion());
                ps.executeUpdate();
                ps = con.prepareStatement("select last_insert_id() from aulas group by last_insert_id();");
                rs = ps.executeQuery();
                int idAula = 0;
                while(rs.next()){
                    idAula = rs.getInt(1);
                }
                if(idAula > 0){
                    ps = con.prepareStatement("insert into estadoAula values (?,?,?,?);");
                    ps.setString(1, "0");
                    ps.setString(2, "Desocupado");
                    ps.setInt(3, idAula);
                    ps.setString(4, null);
                    ps.executeUpdate();
                }
                res = 1;
            }
        }catch(SQLException e){
            System.out.println("Error en el modelo agregarAula: "+ e);
            res = 2;
        }
        return res;
    }
    public int actualizarAula(int estadoBD, Aula a, String nom){
        int res = 1;
        String sql = "update aulas set nombre=?, cantidad=? , descripcion=? where nombre=?";
        try{
            if(estadoBD == 1){
                List <Aula> aulas = BDLocal.LeerListAulas();
                for(int i=0; i<aulas.size();i++){
                    if(aulas.get(i).getNombre().equals(nom)){
                        aulas.get(i).setNombre(a.getNombre());
                        aulas.get(i).setCantidad(a.getCantidad());
                        aulas.get(i).setDescripcion(a.getDescripcion());

                    }
                }
                BDLocal.GuardarListAula(aulas);
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement(sql);
            
                ps.setString(1, a.getNombre());
                ps.setInt(2, a.getCantidad());
                ps.setString(3, a.getDescripcion());
                ps.setString(4, nom);
                ps.executeUpdate();
            }   
        }catch(SQLException e){
            System.out.println("Error en el modelo actualizarAula: "+e);
            res = 2;
        }
        return res;
    }
    public int eliminarAula(int estadoBD, String nom){
        int res = 1;
        String sql = "delete from aulas where nombre = ?";
        try{
            if(estadoBD == 1){
                List <Aula> aulas = BDLocal.LeerListAulas();
                Aula a = new Aula();
                for(int i=0; i<aulas.size(); i++){
                    if(aulas.get(i).getNombre().equals(nom)){
                        a = aulas.get(i);
                        aulas.remove(aulas.get(i));
                    }
                }
                BDLocal.GuardarListAula(aulas);
                List <EstadoAula> estadosAulas = BDLocal.LeerListEstado();
                for(int i=0; i<estadosAulas.size(); i++){
                    if(estadosAulas.get(i).getAula().equals(a)){
                        estadosAulas.remove(estadosAulas.get(i));
                    }
                }
                BDLocal.GuardarListEstado(estadosAulas);
                res = 1;
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement("select id_aula from aulas where nombre=?");
                ps.setString(1, nom);
                rs = ps.executeQuery();
                while(rs.next()){
                    res = rs.getInt(1);
                }
                ps = con.prepareStatement("delete from estadoAula where aula=?");
                ps.setInt(1, res);
                ps.executeUpdate();
                ps = con.prepareStatement(sql);
                ps.setString(1, nom);
                ps.executeUpdate();
                res = 1;
            }
            
        }catch(SQLException e){
            System.out.println("Error en el modelo eliminarAula: "+e);
            res = 2;
        }
        return res;
    }   
    
    //Ocupante
    public List listarOcupante(int estadoBD){
        List<Ocupante>datos = new ArrayList<>();
        String sql = "select * from ocupante order by nombre";
        try{
            if(estadoBD == 1){
                datos = BDLocal.LeerListOcupante();
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Ocupante o = new Ocupante();
                    o.setNombre(rs.getString(2));
                    o.setCarrera(rs.getString(3));
                    o.setCargo(rs.getString(4));
                    o.setCuil(rs.getInt(5));
                    datos.add(o);
                }
                return datos;
            }
        }catch(SQLException e){
            System.out.println("Error en el modelos listarOcupante :"+e);    
        }
        return datos;
    }
    public int agregarOcupante(int estadoBD, Ocupante o){
        int res = 1;
        String sql = "insert into ocupante values (?,?,?,?,?)";
        try{
            if(estadoBD == 1){
                List <Ocupante> ocupantes = BDLocal.LeerListOcupante();
                ocupantes.add(o);
                BDLocal.GuardarListOcupante(ocupantes);
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement(sql);
                ps.setString(1, "0");
                ps.setString(2, o.getNombre());
                ps.setString(3, o.getCarrera());
                ps.setString(4, o.getCargo());
                ps.setInt(5, o.getCuil());
                ps.executeUpdate();
            }
        }catch(SQLException e){
            System.out.println("Error en el modelo agregarOcupante: "+e);
            res = 2;
        }
        return res;
    }
    public int actualizarOcupante(int estadoBD, Ocupante o, int num){
        int res = 1;
        String sql = "update ocupante set nombre=?, carrera=? , cargo=? , cuil=? where cuil=?";
        try{
            if(estadoBD == 1){
                List <Ocupante> ocupantes = BDLocal.LeerListOcupante();
                for(int i=0; i<ocupantes.size(); i++){
                    if(ocupantes.get(i).getCuil() == num){
                        ocupantes.get(i).setNombre(o.getNombre());
                        ocupantes.get(i).setCuil(o.getCuil());
                        ocupantes.get(i).setCargo(o.getCargo());
                        ocupantes.get(i).setCarrera(o.getCarrera());
                    }
                }
                BDLocal.GuardarListOcupante(ocupantes);
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement(sql);
                ps.setString(1, o.getNombre());
                ps.setString(2, o.getCarrera());
                ps.setString(3, o.getCargo());
                ps.setInt(4, o.getCuil());
                ps.setInt(5, num);
                ps.executeUpdate();
            }
        }catch(SQLException e){
            System.out.println("Error en el modelo atualizarOcupante: "+e);
            res = 2;
        }
        return res;
    }
    public int eliminarOcupante(int estadoBD, int num){
        int res = 1;
        String sql = "delete from ocupante where cuil = ?";
        try{
            if(estadoBD == 1){
                List <Ocupante> ocupantes = BDLocal.LeerListOcupante();
                List <EstadoAula> estadosAulas = BDLocal.LeerListEstado();
                Ocupante o = new Ocupante();
                for(int i=0; i<ocupantes.size(); i++){
                    if(ocupantes.get(i).getCuil() == num){
                        o = ocupantes.get(i);
                        ocupantes.remove(ocupantes.get(i));
                    }
                }
                for(int i=0; i<estadosAulas.size(); i++){
                    if(estadosAulas.get(i).getOcupante() != null){
                        if(estadosAulas.get(i).getOcupante().equals(o)){
                            estadosAulas.get(i).setOcupante(null);
                        }
                    }
                }
                BDLocal.GuardarListOcupante(ocupantes);
                BDLocal.GuardarListEstado(estadosAulas);
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement("select idocupante from ocupante where cuil=?");
                ps.setInt(1, num);
                rs = ps.executeQuery();
                while(rs.next()){
                    res = rs.getInt(1);
                }
                ps = con.prepareStatement("update estadoAula set ocupante=? where aula=?");
                ps.setInt(1, 0);
                ps.setInt(2, res);
                ps.executeUpdate();

                ps = con.prepareStatement(sql);
                ps.setInt(1, num);
                ps.executeUpdate();
            }
            res = 1;    
        }catch(SQLException e){
            System.out.println("Error en el modelo eliminarOcupante: "+e);
            res = 2;
        }
        return res;
    }
    
    //Estado aula
    public List listarEstado(int estadoBD){
        List<EstadoAula>datos = new ArrayList<>();
        String sql = "SELECT * FROM aulas left JOIN estadoAula ON aulas.id_aula = estadoAula.aula left join ocupante on estadoAula.ocupante = ocupante.idocupante;";
        try{
            if(estadoBD == 1){
                datos = BDLocal.LeerListEstado();
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Aula aula = new Aula();
                    aula.setNombre(rs.getString(2));
                    aula.setCantidad(rs.getInt(3));
                    aula.setDescripcion(rs.getString(4));
                    EstadoAula ea = new EstadoAula();
                    ea.setEstado(rs.getString(6));
                    ea.setAula(aula);
                    if(rs.getInt(8) > 0){
                        Ocupante o = new Ocupante();
                        o.setNombre(rs.getString(10));
                        o.setCarrera(rs.getString(11));
                        o.setCargo(rs.getString(12));
                        o.setCuil(rs.getInt(13));
                        ea.setOcupante(o);
                    }
                    datos.add(ea);
                }
                return datos;
            }
            
        }catch(SQLException e){
            System.out.println("Error en el modelo listarEstados: "+e);
        }
        return datos;
    }
    public int CambioEstado(int estadoBD, int num,String nom, String op){
        int res = 1;
        String sql = ("update estadoAula set estado=?, ocupante=? where id=?");
        try{
            if(estadoBD == 1){
                System.out.println("{{{{{{{{1"+ nom);
                List <Aula> aulas = BDLocal.LeerListAulas();
                List <Ocupante> ocupantes = BDLocal.LeerListOcupante();
                List <EstadoAula> estados = BDLocal.LeerListEstado();
                Aula a = new Aula();
                for(int i=0; i<aulas.size(); i++){
                    if(aulas.get(i).getNombre() == nom){
                        System.out.println("{{{{{{{{2");
                        a = aulas.get(i);
                    }
                }
                Ocupante o = new Ocupante();
                for(int i=0; i<ocupantes.size(); i++){
                    
                    if(ocupantes.get(i).getCuil() == num){
                        System.out.println("{{{{{{{{3");
                        o = ocupantes.get(i);
                    }
                }
               
                for(int i=0; i<estados.size(); i++){
                    if(estados.get(i).getAula().equals(a)){
                        if(op == "Desocupado"){
                            System.out.println("{{{{{{{{4");
                            estados.get(i).setEstado("Ocupado");
                            estados.get(i).setOcupante(o);
                        }else if(op == "Ocupado"){
                            System.out.println("{{{{{{{{5");
                            estados.get(i).setEstado("Desocuapdo");
                            estados.get(i).setOcupante(null);
                        }
                    }
                }
                BDLocal.GuardarListEstado(estados);
            }else{
                con = Conexion.getInstance();
                ps = con.prepareStatement("select id_aula from aulas where nombre=?");
                ps.setString(1, nom);
                rs = ps.executeQuery();
                while(rs.next()){
                    res = rs.getInt(1);
                }
                ps = con.prepareStatement("select id from estadoAula where estado=? and aula=?");
                ps.setString(1, op);
                ps.setInt(2, res);
                rs = ps.executeQuery();
                while(rs.next()){
                    res = rs.getInt(1);
                }
                ps = con.prepareStatement("select idocupante from ocupante where cuil=?");
                ps.setInt(1, num);
                rs = ps.executeQuery();
                while(rs.next()){
                    num = rs.getInt(1);
                }
                if(op.equals("Desocupado")){
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "Ocupado");
                    ps.setInt(2, num);
                    ps.setInt(3, res);
                    ps.executeUpdate();
                }else if(op.equals("Ocupado")){
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "Desocupado");
                    ps.setString(2, null);
                    ps.setInt(3, res);
                    ps.executeUpdate();
                }
            }
            res = 1;
        }catch(SQLException e){
            res = 2;
        }
        return res;
    }
   
}
