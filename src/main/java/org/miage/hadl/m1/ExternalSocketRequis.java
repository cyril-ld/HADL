/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class ExternalSocketRequis extends PortInterne {

    private Message message;

    public ExternalSocketRequis(Composant p_oPere) {
        super(p_oPere);
    }

    @Override
    public void transmettreMessage(Message message) {
        System.out.println("[EXTERNAL SOCKET] Je suis un external socket requis de " + this.getPere().getClass() + ", je viens de récupérer le message !");
        this.message = message;
        this.getPere().messageReçu(message, this);
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(Message message) {
        this.message = message;
    }

}
