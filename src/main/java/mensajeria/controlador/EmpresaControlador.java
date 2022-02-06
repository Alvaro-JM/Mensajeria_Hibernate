package mensajeria.controlador;

import java.util.ArrayList;
import java.util.List;
import mensajeria.modelo.Empresa;
import mensajeria.modelo.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Alvaro
 */
public class EmpresaControlador {
    
    private List<Empresa> listaEmpresas;

    public EmpresaControlador() {
        listaEmpresas = new ArrayList<>();
    }
    
    public void leerEmpresa() {
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Empresa");
        listaEmpresas = (ArrayList<Empresa>) query.list();
        System.out.println("listaEmpresas en controlador " + listaEmpresas.size());
    }
        
    public void insertarEmpresa(String nombre_empresa, String cif, String director, String web) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(new Empresa(nombre_empresa, cif, director, web));
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void modificarEmpresa(Empresa empresa, String nombre_empresa, String cif,
            String director, String web) {
        
        empresa.setNombre_empresa(nombre_empresa);
        empresa.setCif(cif);
        empresa.setDirector(director);
        empresa.setWeb(web);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(empresa);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public void eliminarEmpresa(Empresa empresa) {
        empresa.setActivo(false);
        
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(empresa);
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }
    
}
