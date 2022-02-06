package mensajeria.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "paquetes", catalog = "mensajeria")
public class Paquete implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id_paquete;
    @Column(name = "fecha_entrega", nullable = false)
    private Date fecha_entrega;
    @Column(name = "direccion_destino", nullable = false, length = 50)
    private String direccion_destino;
    @Column(name = "telefono_destino", nullable = false, length = 50)
    private String telefono_destino;
    @Column(name = "direccion_origen", nullable = false, length = 50)
    private String direccion_origen;
    @Column(name = "telefono_origen", nullable = false, length = 50)
    private String telefono_origen;
    @ManyToOne
    @JoinColumn(name="id_repartidor")
    private Repartidor repartidor;
    @Column(name = "activo", nullable = false)
    private boolean activo;

    public Paquete() {
    }

    public Paquete(Date fecha_entrega, String direccion_destino, String telefono_destino, String direccion_origen, String telefono_origen, Repartidor repartidor) {
        this.fecha_entrega = fecha_entrega;
        this.direccion_destino = direccion_destino;
        this.telefono_destino = telefono_destino;
        this.direccion_origen = direccion_origen;
        this.telefono_origen = telefono_origen;
        this.repartidor = repartidor;
        setActivo(true);
    }

    public Paquete(int id_paquete, Date fecha_entrega, String direccion_destino,
            String telefono_destino, String direccion_origen, String telefono_origen,
            Repartidor repartidor, boolean activo) {
        this.id_paquete = id_paquete;
        this.fecha_entrega = fecha_entrega;
        this.direccion_destino = direccion_destino;
        this.telefono_destino = telefono_destino;
        this.direccion_origen = direccion_origen;
        this.telefono_origen = telefono_origen;
        this.repartidor = repartidor;
        this.activo = activo;
    }

    public int getId_paquete() {
        return id_paquete;
    }
    
    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getDireccion_destino() {
        return direccion_destino;
    }

    public void setDireccion_destino(String direccion_destino) {
        this.direccion_destino = direccion_destino;
    }

    public String getTelefono_destino() {
        return telefono_destino;
    }

    public void setTelefono_destino(String telefono_destino) {
        this.telefono_destino = telefono_destino;
    }

    public String getDireccion_origen() {
        return direccion_origen;
    }

    public void setDireccion_origen(String direccion_origen) {
        this.direccion_origen = direccion_origen;
    }

    public String getTelefono_origen() {
        return telefono_origen;
    }

    public void setTelefono_origen(String telefono_origen) {
        this.telefono_origen = telefono_origen;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
