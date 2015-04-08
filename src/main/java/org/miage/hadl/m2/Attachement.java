/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

/**
 * Un attachement a pour but de faire le lien entre un port de composant (interne) et un port de connecteur réprésenté
 * par un rôle (Caller / Called).
 *
 * @author Cyril LD
 */
public abstract class Attachement implements Element {

    /**
     * Référence sur le père
     */
    private Configuration pere;

    /**
     * Référence sur le port rattaché
     */
    protected PortInterne port;

    /**
     * Référence sur le rôle du connecteur
     */
    protected Role role;

    /**
     * Constructeur.
     *
     * @param p_oPere - Le père de l'élément, doit être une configuration
     */
    public Attachement(Configuration p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour l'attachement !");
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
            throw new IllegalArgumentException("Le père ne peut pas être nul pour l'attachement !");
        } else if (p_oPere.getClass().getSuperclass() != Configuration.class) {
            throw new IllegalArgumentException("Le père de l'attachement doit être une configuration !");
        }
        this.pere = (Configuration) p_oPere;
    }

    /**
     * Retourne le port
     *
     * @return the port
     */
    public PortInterne getPort() {
        return port;
    }

    /**
     * Affecte un port
     *
     * @param p_oPort - La nouvelle valeur du port
     */
    public void setPort(PortInterne p_oPort) {
        this.port = p_oPort;
    }

    /**
     * Retourne le rôle
     *
     * @return le rôle
     */
    public Role getRole() {
        return role;
    }

    /**
     * Affecte le rôle
     *
     * @param p_oRole - La nouvelle valeur du rôle
     */
    public void setRole(Role p_oRole) {
        this.role = p_oRole;
    }
}
