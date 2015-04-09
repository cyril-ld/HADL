/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m1.enums.MODE_FONCTIONNEMENT_SERVEUR;
import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.InterfaceCommunication;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class Serveur extends Composant {

    private MODE_FONCTIONNEMENT_SERVEUR modeFonctionnement;

    public Serveur(Configuration p_oPere, MODE_FONCTIONNEMENT_SERVEUR p_eModeFonctionnement) {
        super(p_oPere);
        this.modeFonctionnement = p_eModeFonctionnement;
    }

    @Override
    public void messageReçu(Message message, InterfaceCommunication sender) {
        System.out.println("[SERVEUR] Je suis le serveur light, je viens de recevoir le message : " + message.getContent());
        switch (this.modeFonctionnement) {
            case COMPOSANT:
                System.out.println("[SERVEUR] Serveur configuré en mode composant, je réponds tout seul.");
                this.sendMessage("Il fera beau demain !");
                break;
            case CONFIGURATION:
                System.out.println("[SERVEUR] Serveur configuré en mode configuration, je passe par la configuration pour savoir où transmettre le message.");
                this.getFather().faireSuivreMessageEnExterne(this.getPortRequis());
                break;
        }
    }

    public void sendMessage(String p_sMessage) {
        PortInterneFourni portEnvoi;

        for (PortInterne item : this.ports) {
            if (item.getClass() == PortInterneFourni.class) {
                portEnvoi = (PortInterneFourni) item;
                System.out.println("[SERVEUR] Je suis le serveur light, je réponds au message !");
                portEnvoi.transmettreMessage(new Message(p_sMessage));
                break; // Par soucis de simplicité, on n'envoie qu'à un seul port fourni
            }
        }
    }

    private PortInterneRequis getPortRequis() {
        PortInterneRequis portReception;

        portReception = null;

        for (PortInterne item : this.ports) {
            if (item.getClass() == PortInterneRequis.class) {
                portReception = (PortInterneRequis) item;
                break; // Par soucis de simplicité, on n'envoie qu'à un seul port fourni
            }
        }
        return portReception;
    }
}
