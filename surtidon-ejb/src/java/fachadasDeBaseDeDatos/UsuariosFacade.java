/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadasDeBaseDeDatos;

import entidadesDeBaseDeDatos.Usuarios;
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
 * @author ripper
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

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

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public List<Usuarios> recuperarListaDeUsuarios() {
        Query query = em.createNamedQuery("Usuarios.findAll");
        return query.getResultList();
    }

    public Usuarios update(Usuarios usuarios) {
        Usuarios actualizar = em.merge(usuarios);
        try {
            sendJMSMessageToNotificationQueue(usuarios);
        } catch (JMSException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El usuario fue actualizado");
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
