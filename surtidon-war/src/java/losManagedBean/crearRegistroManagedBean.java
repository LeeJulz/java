/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losManagedBean;

import entidadesDeBaseDeDatos.Inventarioelsurtidon;
import entidadesDeBaseDeDatos.Usuarios;
import fachadasDeBaseDeDatos.InventarioelsurtidonFacade;
import fachadasDeBaseDeDatos.UsuariosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ripper
 */
@ManagedBean
@RequestScoped
public class crearRegistroManagedBean {
    @EJB
    private InventarioelsurtidonFacade inventarioelsurtidonFacade = new InventarioelsurtidonFacade();
    private Inventarioelsurtidon inventarioelsurtidon = new Inventarioelsurtidon();
    private Usuarios usuarios = new Usuarios();
    private UsuariosFacade usuariosFacade = new UsuariosFacade();
    /**
     * Creates a new instance of crearRegistroManagedBean
     */
    public crearRegistroManagedBean() {
    }
    //Inicia metodos para guardar registros en la BD
    public String gravar() {
        inventarioelsurtidonFacade.create(inventarioelsurtidon);
        return "Guardado";
    }
    
    public Inventarioelsurtidon getInventarioelsurtidon() {
        return inventarioelsurtidon;
    }

    public void setInventarioelsurtidon(Inventarioelsurtidon inventarioelsurtidon) {
        this.inventarioelsurtidon = inventarioelsurtidon;
    }
    //Finalizan elementos necesatrios para guardar resgistros de productos en la bd
    
    //metodos para crear registros en la tabla usuarios
    
    public String gravarUsuarios() {
        usuariosFacade.create(usuarios);
        return "UsuarioGuardado";
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
