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
public abstract class PortInterne implements InterfaceCommunication {

    /**
     * Référence sur l'élément père
     */
    private Composant pere;

    /**
     * Type de port
     */
    protected PORT_TYPE typePort;

    public PortInterne(Composant p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le port interne !");
        }
        this.pere = p_oPere;
    }

    /**
     * Méthode appelée lorsque le port reçoit un message.
     * Lorsque le port est un port fourni, la méthode doit se charger de récupérer auprès de la configuration
     * l'attachement avec lequel elle va dialoguer.
     * Dans le cas d'un port requis, recevant le message depuis l'attachement, il suffit de remonter l'info au composant
     * père.
     *
     * @param message
     */
    public abstract void receiveMessage(Message message);

    /**
     * @return the pere
     */
    public Composant getPere() {
        return pere;
    }

    /**
     * @param pere the pere to set
     */
    public void setPere(Composant pere) {
        this.pere = pere;
    }
}
