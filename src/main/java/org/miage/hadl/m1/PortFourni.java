/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.m2.enums.PORT_TYPE;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class PortFourni extends PortInterne {

    private Message message;

    public PortFourni(Composant p_oPere) {
        super(p_oPere);
        this.typePort = PORT_TYPE.PORT_FOURNI;
    }

    @Override
    public void transmettreMessage(Message p_oMessage) {
        this.message = p_oMessage;
        System.out.println("Je suis un port fourni, je demande à la configuration un attachement !");
        this.getPere().getFather().faireSuivreMessage(this);
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

}
