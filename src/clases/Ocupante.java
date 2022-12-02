package clases;

import java.util.Objects;

/**
 *
 * @author ISMAEL
 */
public class Ocupante {
    private String nombre,carrera,cargo;
    private int cuil;
     
    public Ocupante(){}
    
    public Ocupante(String nom, String car, String carg, int cuil){
        this.nombre =nom;
        this.carrera = car;
        this.cargo = carg;
        this.cuil = cuil;
    }
    
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public void setCarrera(String car){
        this.carrera = car;
    }
    public void setCargo(String carg){
        this.cargo= carg;
    }
    public void setCuil(int cuil){
        this.cuil = cuil;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getCarrera(){
        return this.carrera;
    }
    public String getCargo(){
        return this.cargo;
    }
    public int getCuil(){
        return this.cuil;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Ocupante ocupante = (Ocupante) o;
        return Objects.equals(nombre, ocupante.nombre)
            && Objects.equals(carrera, ocupante.carrera)
            && Objects.equals(cargo, ocupante.cargo)
            && Objects.equals(cuil, ocupante.cuil);
    }
}
