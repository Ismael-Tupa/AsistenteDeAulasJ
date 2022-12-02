package clases;

/**
 *
 * @author ISMAEL
 */
public class EstadoAula {
    private String estado;
    private Aula aula;
    private Ocupante ocupante;
    
    public EstadoAula(){}
    
    public EstadoAula(String est,Aula aula,Ocupante ocu){
        this.estado = est;
        this.aula = aula;
        this.ocupante = ocu;
    }
    
    public void setEstado(String est){
        this.estado = est;
    }
    public void setAula(Aula aula){
        this.aula = aula;
    }
    public void setOcupante(Ocupante ocu){
        this.ocupante = ocu;
    }
    
    public String getEstado(){
        return this.estado;
    }
    public Aula getAula(){
        return this.aula;
    }
    public Ocupante getOcupante(){
        return this.ocupante;
    }
}
