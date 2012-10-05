/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadasDeBaseDeDatos;

import entidadesDeBaseDeDatos.Inventarioelsurtidon;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ripper
 */
@Stateless
public class InventarioelsurtidonFacade extends AbstractFacade<Inventarioelsurtidon> {
    @Resource(mappedName = "jms/NotificationQueue")
    private Queue notificationQueue;
    @Resource(mappedName = "jms/NotificationQueueFactory")
    private ConnectionFactory notificationQueueFactory;
    @PersistenceContext(unitName = "surtidon-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioelsurtidonFacade() {
        super(Inventarioelsurtidon.class);
    }
    
    public List<Inventarioelsurtidon> irPorLaLista(){
        Query query = em.createNamedQuery("Inventarioelsurtidon.findAll");
        return query.getResultList();
        
    }
    
    public Inventarioelsurtidon actualizarLista(Inventarioelsurtidon inventarioelsurtidon) {
        
        Inventarioelsurtidon actualizar = em.merge(inventarioelsurtidon);
        try
        {
            sendJMSMessageToNotificationQueue(inventarioelsurtidon);
        }
        catch (JMSException ex)
        {
            Logger.getLogger(InventarioelsurtidonFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El inventario fue actualizado");
        return actualizar;
    }

    private Message createJMSMessageForjmsNotificationQueue(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToNotificationQueue(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = notificationQueueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(notificationQueue);
            messageProducer.send(createJMSMessageForjmsNotificationQueue(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
