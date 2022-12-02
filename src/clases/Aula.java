package clases;

import java.util.Objects;

/**
 *
 * @author ISMAEL
 */
public class Aula {
   	private String nombre;
	private int capacidad;
	private String descripcion;
	
        public Aula(){}
        
    public Aula(String nom, int can, String des, int id) {
	this.nombre = nom;
	this.capacidad = can;
	this.descripcion = des;
    }
        
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return capacidad;
	}

	public void setCantidad(int cantidad) {
		this.capacidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(nombre, aula.nombre)
            && Objects.equals(capacidad, aula.capacidad)
            && Objects.equals(descripcion, aula.descripcion);
    }
}
