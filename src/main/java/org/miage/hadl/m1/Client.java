/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class Client extends Composant {

    public Client(Configuration p_oPere) {
        super(p_oPere);
    }

    @Override
    public void messageReçu(Message message) {
        System.err.println("Youpi j'ai bien reçu la réponse à ma demande, elle est : " + message.getContent());
    }

    /**
     * Méthode permettant d'envoyer un message
     *
     * @param message
     */
    public void sendMessage(String message) {

    }
}
