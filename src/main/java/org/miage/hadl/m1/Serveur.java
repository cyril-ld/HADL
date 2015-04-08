/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class Serveur extends Composant {

    public Serveur(Configuration p_oPere) {
        super(p_oPere);
    }

    /**
     * Construit un composant avec une configuration minimale.
     *
     * @param p_oPere       - La configuration dans laquelle se place le composant
     * @param p_oPortFourni - Un port interne qui sert de port fourni
     * @param p_oPortRequis - Un port interne qui sert de port requis
     */
//    public Serveur(Configuration p_oPere, PortFourni p_oPortFourni, PortRequis p_oPortRequis) {
//        super(p_oPere);
//        p_oPortFourni.setPere(this);
//        p_oPortRequis.setPere(this);
//        this.ports = new ArrayList<>();
//        this.ports.add(p_oPortFourni);
//        this.ports.add(p_oPortRequis);
//    }
    @Override
    public void messageReçu(Message message) {
        System.out.println("Je suis le serveur light, je viens de recevoir le message : " + message.getContent());
        this.sendMessage("Il fera beau demain !");
    }

    public void sendMessage(String p_sMessage) {
        PortFourni portEnvoi;

        for (PortInterne item : this.ports) {
            if (item.getClass() == PortFourni.class) {
                portEnvoi = (PortFourni) item;
                System.out.println("Je suis le serveur light, je réponds au message !");
                portEnvoi.transmettreMessage(new Message(p_sMessage));
                break; // Par soucis de simplicité, on n'envoie qu'à un seul port fourni
            }
        }
    }

}
