
import clases.Aula;
import modelos.modelo;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ISMAEL
 */
public class unitTest {
    @Test
    void test(){
        Aula a = new Aula();
        modelo m = new modelo();
        assertEquals(m.agregarAula(a), 1);
        
    }
}
