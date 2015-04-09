/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import java.util.ArrayList;
import java.util.List;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public abstract class Composant implements Element {

    /**
     * Référence sur le père
     */
    private Configuration pere;

    /**
     * Liste des ports internes au système offerts par le composant
     */
    protected List<PortInterne> ports;

    public Composant(Configuration p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le composant !");
        }
        this.pere = p_oPere;
        this.ports = new ArrayList<>();
    }

    /**
     * Méthode appelée lorsqu'un message est reçu.
     *
     * @param message - Le message reçu
     * @param sender  - L'interface de communication envoyant le message
     */
    public abstract void messageReçu(Message message, InterfaceCommunication sender);

    @Override
    public Configuration getFather() {
        return this.pere;
    }

    @Override
    public void setFather(Element p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le composant !");
        } else if (p_oPere.getClass().getSuperclass() != Configuration.class) {
            throw new IllegalArgumentException("Le père d'un composant ne peut être qu'une configuration !");
        }
        this.pere = (Configuration) p_oPere;
    }

    /**
     * Permet d'ajouter un port à postériori.
     *
     * @param p_oPort - Le port à ajouter
     */
    public void addPort(PortInterne p_oPort) {
        if (this.ports == null) {
            this.ports = new ArrayList<>();
        }
        p_oPort.setPere(this);
        this.ports.add(p_oPort);
    }
}
