package mensajeria.vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajeria.controlador.EmpresaControlador;
import mensajeria.controlador.OficinaControlador;
import mensajeria.controlador.PaqueteControlador;
import mensajeria.controlador.RepartidorControlador;
import mensajeria.modelo.Empresa;
import mensajeria.modelo.HibernateUtil;
import mensajeria.modelo.Oficina;
import mensajeria.modelo.Paquete;
import mensajeria.modelo.Repartidor;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Alvaro
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        EmpresaControlador ec = new EmpresaControlador();
        OficinaControlador oc = new OficinaControlador();
        RepartidorControlador rc = new RepartidorControlador();
        PaqueteControlador pc = new PaqueteControlador();
        
        //Empresa e1 = new Empresa("UPS", "333", "Manuel", "www.ups.com");
        //Empresa e2 = new Empresa("Correos", "333", "Manuel", "www.correos.es");
        //Empresa e3 = new Empresa(c);
        
        
        
        HibernateUtil.buildSessionFactory();
        
        /*
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        //sesion.save(e1);
        //sesion.save(ofi);
        sesion.save(new Empresa("correos", "34", "Alejandro", "www.direccion.es"));
        sesion.getTransaction().commit();
        sesion.close();
        */
        
        /*
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empresa");
        List<Empresa> empresas = (ArrayList<Empresa>) query.list();      
        Empresa e1 = empresas.get(0);
        Oficina ofi = new Oficina("aaa", "9999", "mail@mail", "Jose Carlos", e1);
        */
        
        /*
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Oficina");
        List<Oficina> oficinas = (ArrayList<Oficina>) query.list();      
        Oficina ofi = oficinas.get(0);
        Repartidor repar = new Repartidor("457", "Fran", "958", 3, ofi);
        */
        
        /*
        Date fecha_entrega = null;
        try {
            fecha_entrega = formato.parse("01/05/2022");
        } catch (ParseException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Repartidor");
        List<Repartidor> repartidores = (ArrayList<Repartidor>) query.list();      
        Repartidor rep = repartidores.get(0);
        Paquete p = new Paquete(fecha_entrega, "españa", "34", "holanda", "999", rep);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        //sesion.save(e1);
        //sesion.save(ofi);
        sesion.save(p);
        sesion.getTransaction().commit();
        sesion.close();
        */
        
        
        
     
        //ec.insertarEmpresa("google", "000", "Javier", "google.es");
        
        
        System.out.println("\nEmpresas:");
        ec.leerEmpresa();
        Iterator<Empresa> it = ec.getListaEmpresas().iterator();
        while (it.hasNext()) {
            Empresa e = it.next();
            System.out.println(e.getNombre_empresa());
        }
        
        System.out.println("\nOficinas:");
        oc.leerOficina();
        Iterator<Oficina> it2 = oc.getListaOficinas().iterator();
        while (it2.hasNext()) {
            Oficina o = it2.next();
            System.out.println(o.getDireccion_oficina());
        }
        
        System.out.println("\nRepartidores:");
        rc.leerRepartidor();
        Iterator<Repartidor> it3 = rc.getListaRepartidores().iterator();
        while (it3.hasNext()) {
            Repartidor r = it3.next();
            System.out.println(r.getNombre_repartidor());
        }
        
        System.out.println("\nPaquetes:");
        pc.leerPaquete();
        Iterator<Paquete> it4 = pc.getListaPaquetes().iterator();
        while (it4.hasNext()) {
            Paquete p = it4.next();
            System.out.println(p.getDireccion_destino());
        }
        
        
        
        
        
        
        
        HibernateUtil.closeSessionFactory();
    }
    
}
