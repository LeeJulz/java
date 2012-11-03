/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDeBaseDeDatos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ripper
 */
@Entity
@Table(name = "INVENTARIOELSURTIDON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventarioelsurtidon.findAll", query = "SELECT i FROM Inventarioelsurtidon i"),
    @NamedQuery(name = "Inventarioelsurtidon.findByIdproducto", query = "SELECT i FROM Inventarioelsurtidon i WHERE i.idproducto = :idproducto"),
    @NamedQuery(name = "Inventarioelsurtidon.findByNombredelproducto", query = "SELECT i FROM Inventarioelsurtidon i WHERE i.nombredelproducto = :nombredelproducto"),
    @NamedQuery(name = "Inventarioelsurtidon.findByPrecio", query = "SELECT i FROM Inventarioelsurtidon i WHERE i.precio = :precio"),
    @NamedQuery(name = "Inventarioelsurtidon.findByMarca", query = "SELECT i FROM Inventarioelsurtidon i WHERE i.marca = :marca"),
    @NamedQuery(name = "Inventarioelsurtidon.findByCantidad", query = "SELECT i FROM Inventarioelsurtidon i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Inventarioelsurtidon.findByUnidad", query = "SELECT i FROM Inventarioelsurtidon i WHERE i.unidad = :unidad"),
    @NamedQuery(name = "Inventarioelsurtidon.findByDescripcion", query = "SELECT i FROM Inventarioelsurtidon i WHERE i.descripcion = :descripcion")})
public class Inventarioelsurtidon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRODUCTO")
    private Integer idproducto;
    @Size(max = 30)
    @Column(name = "NOMBREDELPRODUCTO")
    private String nombredelproducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    @Size(max = 30)
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "CANTIDAD")
    private Double cantidad;
    @Size(max = 20)
    @Column(name = "UNIDAD")
    private String unidad;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Inventarioelsurtidon() {
    }

    public Inventarioelsurtidon(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombredelproducto() {
        return nombredelproducto;
    }

    public void setNombredelproducto(String nombredelproducto) {
        this.nombredelproducto = nombredelproducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventarioelsurtidon)) {
            return false;
        }
        Inventarioelsurtidon other = (Inventarioelsurtidon) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesDeBaseDeDatos.Inventarioelsurtidon[ idproducto=" + idproducto + " ]";
    }
    
}
