/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

/**
 * Un rôle est une interface spécialisée permettant à un connecteur d'être utilisé par des composants du système.
 *
 * @author Cyril LD
 */
public abstract class Role implements InterfaceCommunication {

    /**
     * Glue qui permet de faire l'interface avec le connecteur
     */
    protected Glue pere;

    /**
     * Constructeur
     *
     * @param p_oPere - La glue liant le rôle au connecteur
     */
    public Role(Glue p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le Role !");
        }
        this.pere = p_oPere;
    }
}
