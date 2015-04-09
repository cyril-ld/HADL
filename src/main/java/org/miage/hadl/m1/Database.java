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
public class Database extends Composant {

    public Database(Configuration p_oPere) {
        super(p_oPere);
    }

    @Override
    public void messageReçu(Message p_sMessage, InterfaceCommunication sender) {
        PortInterneFourni portEnvoi;

        for (PortInterne item : this.ports) {
            if (item.getClass() == PortInterneFourni.class) {
                portEnvoi = (PortInterneFourni) item;
                System.out.println("Je suis une Database, je transfère à mon port fourni !");
                portEnvoi.transmettreMessage(p_sMessage);
                break; // Par soucis de simplicité, on n'envoie qu'à un seul port fourni
            }
        }
    }

}
