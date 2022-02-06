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
@Table(name = "repartidores", catalog = "mensajeria")
public class Repartidor implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id_repartidor;
    @Column(name = "dni", nullable = false, length = 50)
    private String dni;
    @Column(name = "nombre_repartidor", nullable = false, length = 50)
    private String nombre_repartidor;
    @Column(name = "telefono_repartidor", nullable = false, length = 50)
    private String telefono_repartidor;
    @Column(name = "antiguedad", nullable = false)
    private int antiguedad;
    @ManyToOne
    @JoinColumn(name="id_oficina")
    private Oficina oficina;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @OneToMany(mappedBy = "repartidor", cascade = CascadeType.ALL)
    private List<Paquete> paquetes;

    public Repartidor() {
    }

    public Repartidor(String dni, String nombre_repartidor, String telefono_repartidor, int antiguedad, Oficina oficina) {
        this.dni = dni;
        this.nombre_repartidor = nombre_repartidor;
        this.telefono_repartidor = telefono_repartidor;
        this.antiguedad = antiguedad;
        this.oficina = oficina;
        setActivo(true);
    }

    
    public Repartidor(int id_repartidor, String dni, String nombre_repartidor, 
            String telefono_repartidor, int antiguedad, Oficina oficina, boolean activo) {
        this.id_repartidor = id_repartidor;
        this.dni = dni;
        this.nombre_repartidor = nombre_repartidor;
        this.telefono_repartidor = telefono_repartidor;
        this.antiguedad = antiguedad;
        this.oficina = oficina;
        this.activo = activo;
    }

    public int getId_repartidor() {
        return id_repartidor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre_repartidor() {
        return nombre_repartidor;
    }

    public void setNombre_repartidor(String nombre_repartidor) {
        this.nombre_repartidor = nombre_repartidor;
    }

    public String getTelefono_repartidor() {
        return telefono_repartidor;
    }

    public void setTelefono_repartidor(String telefono_repartidor) {
        this.telefono_repartidor = telefono_repartidor;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    @Override
    public String toString() {
        return nombre_repartidor;
    }
    
}
