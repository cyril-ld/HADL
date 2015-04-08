/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.PortConfiguration;
import org.miage.hadl.m2.enums.PORT_TYPE;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class PortConfigurationRequis extends PortConfiguration {

    private Message message;

    public PortConfigurationRequis(Configuration p_oPere) {
        super(p_oPere);
        this.typePort = PORT_TYPE.PORT_REQUIS;
    }

    @Override
    public void transmettreMessage(Message message) {
        System.out.println("Je suis un port requis de configuration, je viens de récupérer le message !");
        this.message = message;
        this.getPere().faireSuivreMessageEnExterne(this);
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

}
