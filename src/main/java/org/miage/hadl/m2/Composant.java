/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

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
    }

    /**
     * Méthode appelée lorsqu'un message est reçu.
     *
     * @param message - Le message reçu
     */
    public abstract void messageReçu(Message message);

    @Override
    public Element getFather() {
        return this.pere;
    }

    @Override
    public void setFather(Element p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le composant !");
        } else if (p_oPere.getClass() != Configuration.class) {
            throw new IllegalArgumentException("Le père d'un composant ne peut être qu'une configuration !");
        }
        this.pere = (Configuration) p_oPere;
    }
}
