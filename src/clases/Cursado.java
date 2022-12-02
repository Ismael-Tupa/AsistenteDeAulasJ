package clases;

/**
 *
 * @author ISMAEL
 */
public class Cursado {
    private int id, horario, materia, carrera;
    private String dia, siglo;

    public Cursado(){}
    
    public Cursado(int id, String dia, String sig, int hor, int mat, int car){
        this.id = id;
        this.dia = dia;
        this.siglo = sig;
        this.horario = hor;
        this.materia = mat;
        this.carrera = car;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setDia(String dia){
        this.dia = dia;
    }
    public void setSiglo(String sig){
        this.siglo = sig;
    }
    public void setHorarioCursado(int hor){
        this.horario = hor;
    }
    public void setMateriaCursado(int mat){
        this.materia = mat;
    }
    public void setCarreraCursado(int car){
        this.carrera = car;
    }
    
    public int getId(){
        return this.id;
    }
    public String getDia(){
        return this.dia;
    }
    public String getSiglo(){
        return this.siglo;
    }
    public int getHorarioCursado(){
        return this.horario;
    }
    public int getMateriaCursado(){
        return this.materia;
    }
    public int getCarreraCursado(){
        return this.carrera;
    }
    
}
