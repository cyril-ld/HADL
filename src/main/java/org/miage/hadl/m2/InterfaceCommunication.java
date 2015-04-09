package org.miage.hadl.m2;

import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril
 */
public interface InterfaceCommunication {

    /**
     * Dans le cas d'une interface de communication, celle-ci ne connait pas l'objet à qui envoyer le message. On va
     * donc se baser sur la configuration qui possède l'ensemble des attachements inter composants.
     * Le but est donc de demander à la configuration de faire suivre le message.
     *
     * @param message - Le message à transmettre
     */
    public void transmettreMessage(Message message);

    /**
     * Permet de retourner le message contenu, notamment lors des passages par les bindings et attachements
     *
     * @return le message reçu
     */
    public Message getMessage();

    /**
     * Permet de modifier le message contenu
     *
     * @param message - Le nouveau message
     */
    public void setMessage(Message message);
}
