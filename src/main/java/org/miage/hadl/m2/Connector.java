/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import java.util.ArrayList;
import java.util.List;

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
     * Constructeur minimal
     *
     * @param p_oPere
     */
    public Connector(Configuration p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le connecteur !");
        }
        this.pere = p_oPere;
        this.glues = new ArrayList<>();
    }

    /**
     * Constructeur.
     *
     * @param p_oPere
     * @param p_cGlues
     */
    public Connector(Configuration p_oPere, List<Glue> p_cGlues) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le connecteur !");
        }
        this.pere = p_oPere;
        this.glues = p_cGlues;
    }

    @Override
    public Configuration getFather() {
        return this.pere;
    }

    @Override
    public void setFather(Element p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour le connecteur !");
        } else if (p_oPere.getClass().getSuperclass() != Configuration.class) {
            throw new IllegalArgumentException("Le père du connecteur doit être une configuration !");
        }
        this.pere = (Configuration) p_oPere;
    }

    /**
     * Permet d'ajouter une glue dans la collection de glues
     *
     * @param p_oGlue
     */
    public void ajouterGlue(Glue p_oGlue) {
        p_oGlue.setPere(this);
        this.glues.add(p_oGlue);
    }
}
