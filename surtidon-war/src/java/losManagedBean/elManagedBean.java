/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package losManagedBean;

import entidadesDeBaseDeDatos.Inventarioelsurtidon;
import entidadesDeBaseDeDatos.Usuarios;
import fachadasDeBaseDeDatos.InventarioelsurtidonFacade;
import fachadasDeBaseDeDatos.UsuariosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ripper
 */
@ManagedBean (name = "inventario")
@SessionScoped
public class elManagedBean {
    @EJB
    private UsuariosFacade usuariosFacade;
    private Usuarios usuarios;
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
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exitoso", "Se ha guardado correctamente"));
        return "SAVED";
    }
    
    public String lista() {
        System.out.println("###LIST###");
        return "LIST";
    }
    
    public String borrar() {
        inventarioelsurtidonFacade.remove(inventarioelsurtidon);
        return "Borrado";
    }
    
   public List<Usuarios> llamarListaDeUsuarios(){
        return usuariosFacade.recuperarListaDeUsuarios();
    }
    
    public Usuarios irPorDetallesDeUsuarios(){
        return usuarios;
    }
    
    public String mostrarDetallesDeusuarios(Usuarios usuarios){
        this.usuarios = usuarios;
        return "DETALLES";
    }
    
    public String actualizarDeUsuarios() {
        System.out.println("###ACTUALIZA###");
        usuarios = usuariosFacade.update(usuarios);
        return "SALVADO";
    }
    
    public String listaDeUsuarios() {
        System.out.println("###LISTA###");
        return "LISTA";
    }
    
    public String borrarUsuarios() {
        inventarioelsurtidonFacade.remove(inventarioelsurtidon);
        usuariosFacade.remove(usuarios);
        return "USUARIOBORRADO";
    }
    
}
