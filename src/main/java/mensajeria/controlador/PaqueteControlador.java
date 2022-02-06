package mensajeria.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mensajeria.modelo.HibernateUtil;
import mensajeria.modelo.Paquete;
import mensajeria.modelo.Repartidor;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Alvaro
 */
public class PaqueteControlador {
    
    private List<Paquete> listaPaquetes;

    public PaqueteControlador() {
        listaPaquetes = new ArrayList<>();
        //leerPaquete();
    }
    
    public void leerPaquete() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Paquete");
        listaPaquetes = (ArrayList<Paquete>) query.list();
    }
    
    public void insertarPaquete(Date fecha_entrega, String direccion_destino, String telefono_destino,
            String direccion_origen, String telefono_origen, Repartidor repartidor) {
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(new Paquete(fecha_entrega, direccion_destino, telefono_destino, 
                direccion_origen, telefono_origen, repartidor));
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void modificarPaquete(Paquete paquete, Date fecha_entrega, String direccion_destino,
            String telefono_destino, String direccion_origen, String telefono_origen, Repartidor repartidor) {
        
        paquete.setFecha_entrega(fecha_entrega);
        paquete.setDireccion_destino(direccion_destino);
        paquete.setTelefono_destino(telefono_destino);
        paquete.setDireccion_origen(direccion_origen);
        paquete.setTelefono_origen(telefono_origen);
        paquete.setRepartidor(repartidor);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(paquete);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void eliminarPaquete(Paquete paquete) {
        paquete.setActivo(false);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(paquete);
        sesion.getTransaction().commit();
        sesion.close();
    }

    public List<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }
    
}
