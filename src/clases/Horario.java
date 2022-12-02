package clases;

/**
 *
 * @author ISMAEL
 */
public class Horario {
    private int id;
    private String hora_i;
    private String hora_f;
    private int susecion;
    
    
    public Horario(){}
    
    public Horario(int id,String hi,String hf,int sus){
        this.id = id;
        this.hora_i = hi;
        this.hora_f = hf;
        this.susecion = sus;
    }
    
    public int getId(){
        return this.id;
    }
    public String getHora_i() {
	return this.hora_i;
    }
    public String getHora_f() {
        return this.hora_f;
    }
    public int getSusecion(){
        return this.susecion;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setHora_i(String hora_i) {
	this.hora_i = hora_i;
    }
    public void setHora_f(String hora_f) {
	this.hora_f = hora_f;
    }
    public void setSusecion(int sus){
        this.susecion = sus;
    }
    
}
