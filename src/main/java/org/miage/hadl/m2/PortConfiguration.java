/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import org.miage.hadl.m2.enums.PORT_TYPE;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril
 */
public abstract class PortConfiguration implements InterfaceCommunication {

    /**
     * Référence sur l'élément père
     */
    private Configuration pere;

    /**
     * Type de port
     */
    protected PORT_TYPE typePort;

    /**
     * Constructeur.
     *
     * @param p_oPere - La configuration à laquelle est relié ce port
     */
    public PortConfiguration(Configuration p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le port de configuration !");
        }
        this.pere = p_oPere;
    }

    /**
     * @return the pere
     */
    public Configuration getPere() {
        return pere;
    }

    /**
     * @param pere the pere to set
     */
    public void setPere(Configuration pere) {
        this.pere = pere;
    }

    /**
     * Méthode appelée lorsqu'un message est reçu
     *
     * @param message - Le message reçu
     */
    public abstract void messageRecu(Message message);
}
