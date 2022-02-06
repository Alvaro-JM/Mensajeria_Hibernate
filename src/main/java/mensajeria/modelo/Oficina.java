package mensajeria.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "oficinas", catalog = "mensajeria")
public class Oficina implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id_oficina;
    @Column(name = "direccion_oficina", nullable = false, length = 50)
    private String direccion_oficina;
    @Column(name = "telefono_oficina", nullable = false, length = 50)
    private String telefono_oficina;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "encargado", nullable = false, length = 50)
    private String encargado;
    @ManyToOne
    @JoinColumn(name="id_empresa")
    private Empresa empresa;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL)
    private List<Repartidor> repartidores;

    public Oficina() {
    }

    public Oficina(String direccion_oficina, String telefono_oficina,
            String email, String encargado, Empresa empresa) {
        this.direccion_oficina = direccion_oficina;
        this.telefono_oficina = telefono_oficina;
        this.email = email;
        this.encargado = encargado;
        this.empresa = empresa;
        setActivo(true);
    }
    
    public Oficina(int id_oficina, String direccion_oficina, String telefono_oficina,
            String email, String encargado, Empresa empresa, boolean activo) {
        this.id_oficina = id_oficina;
        this.direccion_oficina = direccion_oficina;
        this.telefono_oficina = telefono_oficina;
        this.email = email;
        this.encargado = encargado;
        this.empresa = empresa;
        this.activo = activo;
    }

    public int getId_oficina() {
        return id_oficina;
    }

    public String getDireccion_oficina() {
        return direccion_oficina;
    }

    public void setDireccion_oficina(String direccion_oficina) {
        this.direccion_oficina = direccion_oficina;
    }

    public String getTelefono_oficina() {
        return telefono_oficina;
    }

    public void setTelefono_oficina(String telefono_oficina) {
        this.telefono_oficina = telefono_oficina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Repartidor> getRepartidores() {
        return repartidores;
    }

    @Override
    public String toString() {
        return direccion_oficina;
    }
    
}
