package modelos;

/**
 *
 * @author ISMAEL
 */
import clases.Aula;
import clases.Conexion;
import clases.EstadoAula;
import clases.Ocupante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class modelo {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //Aula
    public List listarAula(){
        List<Aula>datos = new ArrayList<>();
        String sql = "select * from aulas order by nombre";
        try{
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
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return datos;
    }
    public int agregarAula(Aula a){
        int res = 1;
        String sql = "insert into aulas values (?,?,?,?)";
        try{
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
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            res = 2;
        }
        return res;
    }
    public int actualizarAula(Aula a, String nom){
        int res = 1;
        String sql = "update aulas set nombre=?, cantidad=? , descripcion=? where nombre=?";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getCantidad());
            ps.setString(3, a.getDescripcion());
            ps.setString(4, nom);
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            res = 2;
        }
        return res;
    }
    public int eliminarAula(String nom){
        int res = 1;
        String sql = "delete from aulas where nombre = ?";
        try{
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
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            res = 2;
        }
        return res;
    }
    
    //Ocupante
    public List listarOcupante(){
        List<Ocupante>datos = new ArrayList<>();
        String sql = "select * from ocupante order by nombre";
        try{
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
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return datos;
    }
    public int agregarOcupante(Ocupante o){
        int res = 1;
        String sql = "insert into ocupante values (?,?,?,?,?)";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            ps.setString(1, "0");
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getCarrera());
            ps.setString(4, o.getCargo());
            ps.setInt(5, o.getCuil());
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            res = 2;
        }
        return res;
    }
    public int actualizarOcupante(Ocupante o, int num){
        int res = 1;
        String sql = "update ocupante set nombre=?, carrera=? , cargo=? , cuil=? where cuil=?";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getCarrera());
            ps.setString(3, o.getCargo());
            ps.setInt(4, o.getCuil());
            ps.setInt(5, num);
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            res = 2;
        }
        return res;
    }
    public int eliminarOcupante(int num){
        int res = 1;
        String sql = "delete from ocupante where cuil = ?";
        try{
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
            res = 1;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            res = 2;
        }
        return res;
    }
    
    //Estado aula
    public List listarEstado(){
        List<EstadoAula>datos = new ArrayList<>();
        String sql = "SELECT * FROM aulas left JOIN estadoAula ON aulas.id_aula = estadoAula.aula left join ocupante on estadoAula.ocupante = ocupante.idocupante;";
        try{
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
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return datos;
    }
    public int CambioEstado(int num,String nom, String op){
        int res = 1;
        String sql = ("update estadoAula set estado=?, ocupante=? where id=?");
        try{
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
            
            res = 1;
        }catch(SQLException e){
            res = 2;
        }
        return res;
    }
}
