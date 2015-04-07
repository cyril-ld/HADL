/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

/**
 * Le binding permet de faire le lien entre un port interne et un port de configuration. Permettant ainsi de faire
 * dialoguer un composant avec une configuration
 *
 * @author Cyril
 */
public abstract class Binding implements Element {

    /**
     * Port de composant
     */
    protected PortInterne portInterne;

    /**
     * Port de configuration
     */
    protected PortConfiguration portExterne;

    /**
     * Référence sur l'élément père
     */
    protected Element pere;
}
