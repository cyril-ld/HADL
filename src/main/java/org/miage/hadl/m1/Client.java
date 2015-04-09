/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.InterfaceCommunication;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class Client extends Composant {

    /**
     * Constructeur minimal
     *
     * @param p_oPere
     */
    public Client(Configuration p_oPere) {
        super(p_oPere);
    }

    @Override
    public void messageReçu(Message message, InterfaceCommunication sender) {
        System.out.println("[CLIENT] Youpi j'ai bien reçu la réponse à ma demande, elle est : " + message.getContent());
    }

    /**
     * Méthode permettant d'envoyer un message.
     * Pour envoyer un message, il suffit de le transmettre à son port fourni.
     *
     * @param p_sMessage - Le message à envoyer !
     */
    public void sendMessage(String p_sMessage) {

        PortInterneFourni portEnvoi;

        for (PortInterne item : this.ports) {
            if (item.getClass() == PortInterneFourni.class) {
                portEnvoi = (PortInterneFourni) item;
                System.out.println("[CLIENT] Je suis un client, je veux savoir le temps qu'il fera demain !");
                portEnvoi.transmettreMessage(new Message(p_sMessage));
                break; // Par soucis de simplicité, on n'envoie qu'à un seul port fourni
            }
        }
    }
}
