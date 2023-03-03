/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.Aula;
import clases.Ocupante;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ISMAEL
 */
public class modeloTest {
    
    public modeloTest() {
    }

    @Test
    public void testListarAula() {
        System.out.println("listarAula");
        modelo instance = new modelo();
        
        List expResult = null;
        List result = instance.listarAula();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAgregarAula() {
        System.out.println("agregarAula");
        Aula a = null;
        modelo instance = new modelo();
        int expResult = 0;
        int result = instance.agregarAula(a);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testActualizarAula() {
        System.out.println("actualizarAula");
        Aula a = null;
        String nom = "";
        modelo instance = new modelo();
        int expResult = 0;
        int result = instance.actualizarAula(a, nom);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEliminarAula() {
        System.out.println("eliminarAula");
        String nom = "";
        modelo instance = new modelo();
        int expResult = 0;
        int result = instance.eliminarAula(nom);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testListarOcupante() {
        System.out.println("listarOcupante");
        modelo instance = new modelo();
        List expResult = null;
        List result = instance.listarOcupante();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAgregarOcupante() {
        System.out.println("agregarOcupante");
        Ocupante o = null;
        modelo instance = new modelo();
        int expResult = 0;
        int result = instance.agregarOcupante(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testActualizarOcupante() {
        System.out.println("actualizarOcupante");
        Ocupante o = null;
        int num = 0;
        modelo instance = new modelo();
        int expResult = 0;
        int result = instance.actualizarOcupante(o, num);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEliminarOcupante() {
        System.out.println("eliminarOcupante");
        int num = 0;
        modelo instance = new modelo();
        int expResult = 0;
        int result = instance.eliminarOcupante(num);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testListarEstado() {
        System.out.println("listarEstado");
        modelo instance = new modelo();
        List expResult = null;
        List result = instance.listarEstado();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCambioEstado() {
        System.out.println("CambioEstado");
        int num = 0;
        String nom = "";
        String op = "";
        modelo instance = new modelo();
        int expResult = 0;
        int result = instance.CambioEstado(num, nom, op);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
