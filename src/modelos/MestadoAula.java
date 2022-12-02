package modelos;

import clases.Aula;
import clases.Conexion;
import clases.EstadoAula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ISMAEL
 */
public class MestadoAula {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<EstadoAula>datos = new ArrayList<>();
        String sql = "select * from estadoAula";
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                EstadoAula a = new EstadoAula();
                //a.setId(rs.getInt(1));
                a.setEstado(rs.getString(2));
                //a.setAula(rs.getInt(3));
                datos.add(a);
            }
            return datos;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return datos;
    }
    public int CambioEstado(int id,String op){
        int res = 1;
        String sql = ("update estadoAula set estado=? where id=?");
        try{
            con = Conexion.getInstance();
            ps = con.prepareStatement(sql);
            ps.setString(1, op);
            ps.setInt(2, id);
            ps.executeUpdate();
        }catch(SQLException e){
            res = 2;
        }
        return res;
    }
}
