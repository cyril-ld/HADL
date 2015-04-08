/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import org.miage.hadl.m2.enums.PORT_TYPE;

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

    /**
     * Constructeur
     *
     * @param p_oPere - Le composant utilisant le port
     */
    public PortInterne(Composant p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le port interne !");
        }
        this.pere = p_oPere;
    }

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
