/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Attachement;
import org.miage.hadl.m2.Element;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.m2.Role;

/**
 * Matérialise l'attachement dont le rôle est de transmettre l'information au connecteur.
 *
 * @author Cyril LD
 */
public class ConnectorAttachementIn extends Attachement {

    /**
     * Appelé par la configuration qui branche les différents morceaux entre eux
     *
     * @param p_oPere
     * @param p_oPortInterne
     * @param p_oRole
     */
    public ConnectorAttachementIn(Element p_oPere, PortInterne p_oPortInterne, Role p_oRole) {
        super(p_oPere);
        this.port = p_oPortInterne;
        this.role = p_oRole;
    }

}
