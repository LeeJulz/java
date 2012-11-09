/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansDeNotificacion;

import entidadesDeBaseDeDatos.Inventarioelsurtidon;
import entidadesDeBaseDeDatos.Usuarios;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author ripper
 */
@MessageDriven(mappedName = "jms/NotificationQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NotificationBean implements MessageListener {
    
    public NotificationBean() {
    }
    
    @Override
    public void onMessage(Message message)
    {
        try
        {
            Object msgObj = ((ObjectMessage)message).getObject();
            if (msgObj != null)
            {
                Inventarioelsurtidon inventarioelsurtidon = (Inventarioelsurtidon)msgObj;
                System.out.println("El Producto con los siguientes datos ha sido actualizado:");
                StringBuilder sb = new StringBuilder();
                sb.append("Producto ID=");
                sb.append(inventarioelsurtidon.getIdproducto());
                sb.append(", ");
                sb.append("Nombre del producto=");
                sb.append(inventarioelsurtidon.getNombredelproducto());
                sb.append(", ");
                sb.append("Precio=");
                sb.append(inventarioelsurtidon.getPrecio());
                sb.append(", ");
                sb.append("Marca=");
                sb.append(inventarioelsurtidon.getMarca());
                sb.append(", ");
                sb.append("Cantidad=");
                sb.append(inventarioelsurtidon.getCantidad());
                sb.append(", ");
                sb.append("Unidad=");
                sb.append(inventarioelsurtidon.getUnidad());
                sb.append(", ");
                sb.append("Descripcion=");
                sb.append(inventarioelsurtidon.getDescripcion());
                System.out.println(sb.toString());
            }
        }
        catch (JMSException ex)
        {
            Logger.getLogger(NotificationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try
        {
            Object msgObj = ((ObjectMessage)message).getObject();
            if (msgObj != null)
            {
                Usuarios usuarios = (Usuarios)msgObj;
                System.out.println("El Usuario con los siguientes datos ha sido actualizado:");
                StringBuilder sb = new StringBuilder();
                sb.append("Usuario ID=");
                sb.append(usuarios.getIdusuario());
                sb.append(", ");
                sb.append("Nombre de usuario=");
                sb.append(usuarios.getNombredeusuario());
                sb.append(", ");
                sb.append("Contra=");
                sb.append(usuarios.getContra());
                sb.append(", ");
                sb.append("Nombre=");
                sb.append(usuarios.getNombre());
                sb.append(", ");
                sb.append("Apellidos=");
                sb.append(usuarios.getApellidos());
                sb.append(", ");
                sb.append("Email=");
                sb.append(usuarios.getEmail());
                System.out.println(sb.toString());
            }
        }
        catch (JMSException ex)
        {
            Logger.getLogger(NotificationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
