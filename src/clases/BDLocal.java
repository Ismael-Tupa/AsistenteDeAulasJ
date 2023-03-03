package clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISMAEL
 */
public class BDLocal {
    
    public static void InicioLocalBD(List v1, List v2, List v3){
        //listando aulas en txt
        try(ObjectOutputStream oom = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ISMAEL\\Documents\\aulas.txt"))){
            for(int i=0; i <= v1.size(); i++){
                oom.writeObject(v1.get(i));
            }
        }catch(Exception e){
        
        }
        //listando ocupantes en txt
        try(ObjectOutputStream oom = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ISMAEL\\Documents\\ocupantes.txt"))){
            for(int i=0; i <= v2.size(); i++){
                oom.writeObject(v2.get(i));
            }
        }catch(Exception e){
        
        }
        //listando estdo de las aulas en txt
        try(ObjectOutputStream oom = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ISMAEL\\Documents\\EstadoDeAulas.txt"))){
            for(int i=0; i <= v3.size(); i++){
                oom.writeObject(v3.get(i));
            }
        }catch(Exception e){
        
        }
    }
    public static List LeerListAulas(){
        List<Aula>datos = new ArrayList<>();
        try(ObjectInputStream oim = new ObjectInputStream(new FileInputStream("C:\\Users\\ISMAEL\\Documents\\aulas.txt"))){
            while(true){
                Aula a = (Aula) oim.readObject();
                datos.add(a);
            }
        }catch(IOException e){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    public static List LeerListOcupante(){
        List<Ocupante>datos = new ArrayList<>();
        try(ObjectInputStream oim = new ObjectInputStream(new FileInputStream("C:\\Users\\ISMAEL\\Documents\\ocupantes.txt"))){
            while(true){
                Ocupante o = (Ocupante) oim.readObject();
                datos.add(o);
            }
        }catch(IOException e){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    public static List LeerListEstado(){
        List<EstadoAula>datos = new ArrayList<>();
        try(ObjectInputStream oim = new ObjectInputStream(new FileInputStream("C:\\Users\\ISMAEL\\Documents\\EstadoDeAulas.txt"))){
            while(true){
                EstadoAula ea = (EstadoAula) oim.readObject();
                datos.add(ea);
            }
        }catch(IOException e){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    public static void GuardarListAula(List v){
        try(ObjectOutputStream oom = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ISMAEL\\Documents\\aulas.txt"))){
            for(int i=0; i <= v.size(); i++){
                oom.writeObject(v.get(i));
            }
        }catch(Exception e){
        
        }
    }
    public static void GuardarListOcupante(List v){
        try(ObjectOutputStream oom = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ISMAEL\\Documents\\ocupantes.txt"))){
            for(int i=0; i <= v.size(); i++){
                oom.writeObject(v.get(i));
            }
        }catch(Exception e){
        
        }
    }
    public static void GuardarListEstado(List v){
        try(ObjectOutputStream oom = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ISMAEL\\Documents\\EstadoDeAulas.txt"))){
            for(int i=0; i <= v.size(); i++){
                oom.writeObject(v.get(i));
            }
        }catch(Exception e){
        
        }
    }
}
