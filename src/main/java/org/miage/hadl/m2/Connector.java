/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

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
    protected Element pere;
}
