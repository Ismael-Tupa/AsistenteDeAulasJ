package modelos;

/**
 *
 * @author ISMAEL
 */
import clases.Conexion;
import clases.Carrera;
import clases.Aula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import vistas.Carteles;

public class Mcarrera {
 
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Carrera>datos = new ArrayList<>();
        String sql = "select * from carrera";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Carrera c = new Carrera();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setTipo(rs.getString(3));
                c.setDuracion(rs.getInt(4));
                datos.add(c);
            }
            return datos;
        }catch(SQLException e){
            Carteles.cartelito(e);
        }
        return datos;
    }
    public int agregar(Carrera c){
        int res = 1;
        String sql = "insert into carrera values (?,?,?,?)";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            ps.setString(1, "0");
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTipo());
            ps.setInt(4, c.getDuracion());
            ps.executeUpdate();
        }catch(SQLException e){
            Carteles.cartelito(e);
            res = 2;
        }
        return res;
    }
    public int Actualizar(Carrera c){
        int res = 1;
        String sql = "update carrera set nombre=?, tipo=? , duracion=? where idcarrera=?";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTipo());
            ps.setInt(3, c.getDuracion());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            Carteles.cartelito(e);
            res = 2;
        }
        return res;
    }
    public int Eliminar(int id){
        int res = 1;
        String sql = "delete from carrera where idcarrera = ?";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        }catch(SQLException e){
            Carteles.cartelito(e);
            res = 2;
        }
        return res;
    }
}
