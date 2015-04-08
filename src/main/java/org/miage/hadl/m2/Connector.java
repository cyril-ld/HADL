/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import java.util.List;
import org.miage.hadl.transverse.Message;

/**
 * Un connecteur permet de faire dialoguer des composants entre eux. Il se compose principalement de glue et
 * d'interfaces de dialogue représentées par des rôles.
 *
 * @author Cyril LD
 */
public abstract class Connector implements Element {

    /**
     * Ensemble des glues liants les ports d'entrée à ceux de sortie.
     */
    protected List<Glue> glues;

    /**
     * Référence sur l'élément père
     */
    private Configuration pere;

    /**
     *
     * @param p_oPere
     */
    public Connector(Configuration p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le connecteur !");
        }
        this.pere = p_oPere;
    }

    /**
     * Méthode appelée lorsqu'un message est reçu
     *
     * @param message - Le message reçu
     */
    public abstract void messageRecu(Message message);

    @Override
    public Element getFather() {
        return this.pere;
    }

    @Override
    public void setFather(Element p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le connecteur !");
        } else if (p_oPere.getClass() != Configuration.class) {
            throw new IllegalArgumentException("Le père du connecteur doit être une configuration !");
        }
        this.pere = (Configuration) p_oPere;
    }
}
