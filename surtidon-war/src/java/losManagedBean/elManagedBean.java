/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losManagedBean;

import entidadesDeBaseDeDatos.Inventarioelsurtidon;
import fachadasDeBaseDeDatos.InventarioelsurtidonFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ripper
 */
@ManagedBean (name = "inventario")
@SessionScoped
public class elManagedBean {
    @EJB
    private InventarioelsurtidonFacade inventarioelsurtidonFacade;
    private Inventarioelsurtidon inventarioelsurtidon;
    
    /**
     * Creates a new instance of elManagedBean
     */
    public elManagedBean() {
    }
    
    public List<Inventarioelsurtidon> llamarLista(){
        return inventarioelsurtidonFacade.irPorLaLista();
    }
    
    public Inventarioelsurtidon irPorDetalles(){
        return inventarioelsurtidon;
    }
    
    public String mostrarDetalles(Inventarioelsurtidon inventarioelsurtidon){
        this.inventarioelsurtidon = inventarioelsurtidon;
        return "DETAILS";
    }
    
    public String actualizar() {
        System.out.println("###UPDATE###");
        inventarioelsurtidon = inventarioelsurtidonFacade.actualizarLista(inventarioelsurtidon);
        return "SAVED";
    }
    
    public String lista() {
        System.out.println("###LIST###");
        return "LIST";
    }
}
