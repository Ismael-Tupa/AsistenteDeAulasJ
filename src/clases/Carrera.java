package clases;

/**
 *
 * @author ISMAEL
 */
public class Carrera {
        private int id;
       	private String nombre;
        private String tipo;
        private int duracion;

        public Carrera(){}
        
	public Carrera(int id, String nom, String tipo, int dur) {
            this.id = id;
            this.nombre = nom;
            this.tipo = tipo;
            this.duracion = dur;
	}
	public void setId(int id){
            this.id = id;
        }
	public void setNombre(String nom) {
            this.nombre = nom;
	}
	public void setTipo(String tipo){
            this.tipo = tipo;
        }
        public void setDuracion(int dur){
            this.duracion = dur;
        }
        public int getId(){
            return this.id;
        }
	public String getNombre() {
		return this.nombre;
	}
        public String getTipo(){
            return this.tipo;
        }
        public int getDuracion(){
            return this.duracion;
        }
	
}
