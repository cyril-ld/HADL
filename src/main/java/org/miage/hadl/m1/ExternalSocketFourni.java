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
public class ExternalSocketFourni extends PortInterne {

    private Message message;

    public ExternalSocketFourni(Composant p_oPere) {
        super(p_oPere);
    }

    @Override
    public void transmettreMessage(Message message) {
        System.out.println("Je suis un socket fourni, je viens de récupérer le message !");
        this.message = message;
        this.getPere().getFather().faireSuivreMessageEnExterne(this);
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
