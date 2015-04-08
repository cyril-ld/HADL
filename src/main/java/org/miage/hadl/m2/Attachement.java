/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import org.miage.hadl.transverse.Message;

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

    /**
     * Méthode utilisée pour traiter l'arrivée d'un message. L'élément doit en référer à son père (la configuration)
     * pour récupérer port ou rôle en fonction du sens du message.
     * Composant vers connecteur = rôle
     * Connecteur vers composant = Port
     *
     * @param message
     */
    public abstract void transmettreMessage(Message message);

    @Override
    public Element getFather() {
        return this.pere;
    }

    @Override
    public void setFather(Element p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour l'attachement !");
        } else if (p_oPere.getClass() != Configuration.class) {
            throw new IllegalArgumentException("Le père de l'attachement doit être une configuration !");
        }
        this.pere = (Configuration) p_oPere;
    }

    public PortInterne getPort() {
        return port;
    }

    public Role getRole() {
        return role;
    }
}
