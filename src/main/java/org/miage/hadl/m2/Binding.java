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
    private Configuration pere;

    public Binding(Configuration p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le binding !");
        }
        this.pere = p_oPere;
    }

    @Override
    public Element getFather() {
        return this.pere;
    }

    @Override
    public void setFather(Element p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le binding !");
        } else if (p_oPere.getClass().getSuperclass() != Configuration.class) {
            throw new IllegalArgumentException("Le père du binding est une configuration !");
        }
        this.pere = (Configuration) p_oPere;
    }

    public PortInterne getPortInterne() {
        return portInterne;
    }

    public void setPortInterne(PortInterne portInterne) {
        this.portInterne = portInterne;
    }

    public PortConfiguration getPortExterne() {
        return portExterne;
    }

    public void setPortExterne(PortConfiguration portExterne) {
        this.portExterne = portExterne;
    }

}
