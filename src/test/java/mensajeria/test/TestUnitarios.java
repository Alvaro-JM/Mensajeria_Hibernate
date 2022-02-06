package mensajeria.test;

import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeria.controlador.EmpresaControlador;
import mensajeria.modelo.Empresa;
import mensajeria.modelo.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Alvaro
 */
public class TestUnitarios {
    
    public TestUnitarios() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void test_leerBD_cargarListaEmpresaConDatos_enControlador_comparacionTamaniosLista() {
        try {
            HibernateUtil.buildSessionFactory();
            EmpresaControlador instance = new EmpresaControlador();
            int sizeBefore = instance.getListaEmpresas().size();
            instance.leerEmpresa();
            int sizeAfter = instance.getListaEmpresas().size();

            assertEquals(0, sizeBefore);
            assertNotEquals(sizeBefore, sizeAfter);
        } catch (Exception ex) {
            Logger.getLogger(TestUnitarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            HibernateUtil.closeSessionFactory();
        }
    }

    @Test
    public void test_insertarEmpresa_enBD_comparacionTamaniosTrasLectura() {
        try {
            HibernateUtil.buildSessionFactory();
            EmpresaControlador instance = new EmpresaControlador();
            instance.leerEmpresa();
            int sizeBefore = instance.getListaEmpresas().size();

            instance.insertarEmpresa("test_empresa_thJkl980HAsse78V", "test_cif", "test_director", "test_web");

            instance.getListaEmpresas().clear();
            instance.leerEmpresa();
            int sizeAfter = instance.getListaEmpresas().size();

            //Borra la empresa recién añadida
            String nombre = "test_empresa_thJkl980HAsse78V";
            Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empresa c WHERE c.nombre_empresa = :nombre_empresa");
            query.setParameter("nombre_empresa", nombre);
            Empresa empresa = (Empresa) query.uniqueResult();
            Session sesion = HibernateUtil.getCurrentSession();
            sesion.beginTransaction();
            sesion.delete(empresa);
            sesion.getTransaction().commit();
            sesion.close();
            
            assertEquals(sizeAfter, sizeBefore + 1);
        } catch (Exception ex) {
            Logger.getLogger(TestUnitarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }
    
   @Test
    public void test_modificarEmpresa_enBD_modificarNombreEmpresa_primerElemento_nombreAleatorio(){
        Random random = new Random();
        
        try {
            HibernateUtil.buildSessionFactory();
            
            //Se carga la lista con las empresas de la BD
            EmpresaControlador instance = new EmpresaControlador();
            instance.leerEmpresa();
            
            //Genera nombre aleatorio
            String randomName = "";
            for (int i = 0; i < 10; i++) {
                randomName += (char) (random.nextInt(57) + 65);
            }
            
            //Accede al primer elemento y se almacena el nombre original
            Empresa empresa = instance.getListaEmpresas().get(0);
            String originalName = empresa.getNombre_empresa();
            
            //Modifica el nombre dejando los demás valores tal cual
            instance.modificarEmpresa(empresa, randomName, empresa.getCif(), empresa.getDirector(), empresa.getWeb());
            
            //Se limpia la lista y se vuelve a cargar desde la BD
            instance.getListaEmpresas().clear();
            instance.leerEmpresa();
            
            //Se accede de nuevo al primer elemento y se comprueba el nombre
            empresa = instance.getListaEmpresas().get(0);
            assertEquals(randomName, empresa.getNombre_empresa());
            
            //Modifica el nombre por el que tenía originalemente
            instance.modificarEmpresa(empresa, originalName, empresa.getCif(), empresa.getDirector(), empresa.getWeb());
            
        } catch (Exception ex) {
            Logger.getLogger(TestUnitarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }
    
    @Test
    public void test_eliminarEmpresa_borradoLogico_elementoNuevo(){
        
        try {
            HibernateUtil.buildSessionFactory();
            
            //Se introduce un elemento
            EmpresaControlador instance = new EmpresaControlador();
            instance.insertarEmpresa("test_eliminar_empresa_thJkl980HAsse78V", "test_cif", "test_director", "test_web");
            
            //Se accede al elemento
            instance.getListaEmpresas().clear();
            instance.leerEmpresa();
            Empresa empresa = null;
            Iterator<Empresa> it = instance.getListaEmpresas().iterator();
            boolean empresaEncontrada = false;
            while (!empresaEncontrada && it.hasNext()) {
                empresa = it.next();
                if (empresa.getNombre_empresa().equals("test_eliminar_empresa_thJkl980HAsse78V")) {
                    empresaEncontrada = true;
                }
            }
            
            //Se elimina dicho elemento
            instance.eliminarEmpresa(empresa);
            
            //Se busca el elemento en la BD 
            String nombre = "test_eliminar_empresa_thJkl980HAsse78V";
            Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empresa c WHERE c.nombre_empresa = :nombre_empresa");
            query.setParameter("nombre_empresa", nombre);
            Empresa empresaBuscada = (Empresa) query.uniqueResult();
            
            //Se comprueba el atributo activo (BORRADO LÓGICO)
            boolean activo = empresaBuscada.isActivo();
            assertEquals(false, activo);
            
            //Se elimina la empresa recién añadida
            Session sesion = HibernateUtil.getCurrentSession();
            sesion.beginTransaction();
            sesion.delete(empresaBuscada);
            sesion.getTransaction().commit();
            sesion.close();
            
            
        } catch (Exception ex) {
            Logger.getLogger(TestUnitarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        
    }
}