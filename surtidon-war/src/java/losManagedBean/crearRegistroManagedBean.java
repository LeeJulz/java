/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losManagedBean;

import entidadesDeBaseDeDatos.Inventarioelsurtidon;
import fachadasDeBaseDeDatos.InventarioelsurtidonFacade;
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
    /**
     * Creates a new instance of crearRegistroManagedBean
     */
    public crearRegistroManagedBean() {
    }
    
    
    //Inicia metodos para guardar registros en la BD
    public String gravar() {
        inventarioelsurtidonFacade.create(inventarioelsurtidon);
        return null;
    }

    public Inventarioelsurtidon getInventarioelsurtidon() {
        return inventarioelsurtidon;
    }

    public void setInventarioelsurtidon(Inventarioelsurtidon inventarioelsurtidon) {
        this.inventarioelsurtidon = inventarioelsurtidon;
    }
    //Finalizan elementos necesatrios para guardar resgustros en la bd
}
