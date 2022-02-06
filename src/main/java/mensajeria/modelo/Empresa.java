package mensajeria.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "empresas", catalog = "mensajeria")
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id_empresa;
    @Column(name = "nombre_empresa", nullable = false, length = 50)
    private String nombre_empresa;
    @Column(name = "cif", nullable = false, length = 50)
    private String cif;
    @Column(name = "director", nullable = false, length = 50)
    private String director;
    @Column(name = "web", nullable = false, length = 50)
    private String web;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Oficina> oficinas;

    public Empresa() {
    }
    
    public Empresa(String nombre_empresa, String cif, String director, String web) {
        this.nombre_empresa = nombre_empresa;
        this.cif = cif;
        this.director = director;
        this.web = web;
        setActivo(true);
    }

    public Empresa(int id_empresa, String nombre_empresa, String cif, String director,
            String web, boolean activo) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
        this.cif = cif;
        this.director = director;
        this.web = web;
        this.activo = activo;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    @Override
    public String toString() {
        return nombre_empresa;
    }
    
}
