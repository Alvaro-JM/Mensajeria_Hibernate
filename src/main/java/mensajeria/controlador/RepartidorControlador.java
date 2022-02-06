package mensajeria.controlador;

import java.util.ArrayList;
import java.util.List;
import mensajeria.modelo.HibernateUtil;
import mensajeria.modelo.Oficina;
import mensajeria.modelo.Repartidor;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Alvaro
 */
public class RepartidorControlador {
    
    private List<Repartidor> listaRepartidores;

    public RepartidorControlador() {
        listaRepartidores = new ArrayList<>();
        //leerRepartidor();
    }
    
    public void leerRepartidor() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Repartidor");
        listaRepartidores = (ArrayList<Repartidor>) query.list();
    }
    
    public void insertarRepartidor(String dni, String nombre_repartidor, 
            String telefono_repartidor, int antiguedad, Oficina oficina) {
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(new Repartidor(dni, nombre_repartidor, telefono_repartidor, antiguedad, oficina));
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void modificarRepartidor(Repartidor repartidor, String dni, String nombre_repartidor,
            String telefono_repartidor, int antiguedad, Oficina oficina) {
        
        repartidor.setDni(dni);
        repartidor.setNombre_repartidor(nombre_repartidor);
        repartidor.setTelefono_repartidor(telefono_repartidor);
        repartidor.setAntiguedad(antiguedad);
        repartidor.setOficina(oficina);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(repartidor);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void eliminarRepartidor(Repartidor repartidor) {
        repartidor.setActivo(false);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(repartidor);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public List<Repartidor> getListaRepartidores() {
        return listaRepartidores;
    }
    
    
}
