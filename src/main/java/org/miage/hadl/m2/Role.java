/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import org.miage.hadl.transverse.Message;

/**
 * Un rôle est une interface spécialisée permettant à un connecteur d'être utilisé par des composants du système.
 *
 * @author Cyril LD
 */
public abstract class Role extends PortInterne {

    public Role(Composant p_oPere) {
        super(p_oPere);
    }

    /**
     * Méthode appelée lorsqu'un message est reçu
     *
     * @param message - Le message reçu
     */
    public abstract void messageRecu(Message message);
}
