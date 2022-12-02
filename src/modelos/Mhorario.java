package modelos;

import clases.Horario;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vistas.Carteles;

/**
 * @author ISMAEL
 */
public class Mhorario {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Horario>datos = new ArrayList<>();
        String sql = "select * from horario";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Horario h = new Horario();
                h.setId(rs.getInt(1));
                h.setHora_i(rs.getString(2));
                h.setHora_f(rs.getString(3));
                h.setSusecion(rs.getInt(4));
                datos.add(h);
            }
            return datos;
        }catch(SQLException e){
            Carteles.cartelito(e);
        }
        return datos;
    }
    public int agregar(Horario h){
        int res = 1;
        String sql = "insert into horario values (?,?,?,?)";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            ps.setString(1, "0");
            ps.setString(2, h.getHora_i());
            ps.setString(3, h.getHora_f());
            ps.setInt(4, h.getSusecion());
            ps.executeUpdate();
        }catch(SQLException e){
            Carteles.cartelito(e);
            res = 2;
        }
        return res;
    }
    public int Actualizar(Horario h){
        int res = 1;
        String sql = "update horario set hora_i=?, hora_f=? , susecion=? where idhorario=?";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, h.getHora_i());
            ps.setString(2, h.getHora_f());
            ps.setInt(3, h.getSusecion());
            ps.setInt(4, h.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            Carteles.cartelito(e);
            res = 2;
        }
        return res;
    }
    public int Eliminar(int id){
        int res = 1;
        String sql = "delete from horario where idhorario = ?";
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
