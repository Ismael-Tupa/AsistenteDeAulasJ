package clases;

/**
 * @author ISMAEL
 */
public class Materia {
    private int id;
    private String nombre;
    private String profesor;
    
    public Materia(){}
    
    public Materia(int id,String nom , String pro, String sig, String dia, int carr){
        this.id = id;
        this.nombre = nom;
        this.profesor = pro;

    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public void setProfesor(String pro){
        this.profesor = pro;
    }
   
    
    public int getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getProfesor(){
        return this.profesor;
    }
  
}
