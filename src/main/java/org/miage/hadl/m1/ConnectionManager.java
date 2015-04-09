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
public class ConnectionManager extends Composant {

    public ConnectionManager(Configuration p_oPere) {
        super(p_oPere);
    }

    @Override
    public void messageReçu(Message p_sMessage, InterfaceCommunication sender) {
        PortInterne portEnvoi;

        for (PortInterne item : this.ports) {
            if (sender.getClass() == ExternalSocketRequis.class) {
                if (item.getClass() == PortInterneFourni.class) {
                    portEnvoi = (PortInterneFourni) item;
                    System.out.println("[CONNECTION MANAGER] Je suis un ConnectionManager, je transfère le message à mon port fourni !");
                    portEnvoi.transmettreMessage(p_sMessage);
                    break;
                }
            } else if (sender.getClass() == PortInterneRequis.class) {
                if (item.getClass() == ExternalSocketFourni.class) {
                    System.out.println("[CONNECTION MANAGER] Je suis un ConnectionManager, je transfère le message à mon socket appelant !");
                    portEnvoi = item;
                    portEnvoi.transmettreMessage(p_sMessage);
                    break;
                }
            }
        }
    }
}
