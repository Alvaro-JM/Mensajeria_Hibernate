package mensajeria.controlador;

import java.util.ArrayList;
import java.util.List;
import mensajeria.modelo.Empresa;
import mensajeria.modelo.HibernateUtil;
import mensajeria.modelo.Oficina;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Alvaro
 */
public class OficinaControlador {
    
    private List<Oficina> listaOficinas;
    
    public OficinaControlador() {
        listaOficinas = new ArrayList<>();
        //leerOficina();
    }
    
    public void leerOficina() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Oficina");
        listaOficinas = (ArrayList<Oficina>) query.list();
    }
    
    public void insertarOficina(String direccion_oficina, String telefono_oficina, String email, String encargado, Empresa empresa) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(new Oficina(direccion_oficina, telefono_oficina, email, encargado, empresa));
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void modificarOficina(Oficina oficina, String direccion_oficina, 
            String telefono_oficina, String email, String encargado, Empresa empresa) {
        
        oficina.setDireccion_oficina(direccion_oficina);
        oficina.setTelefono_oficina(telefono_oficina);
        oficina.setEmail(email);
        oficina.setEncargado(encargado);
        oficina.setEmpresa(empresa);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(oficina);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void eliminarOficina(Oficina oficina) {
        oficina.setActivo(false);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(oficina);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public List<Oficina> getListaOficinas() {
        return listaOficinas;
    }
    
}
