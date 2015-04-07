/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

/**
 *
 * @author Cyril LD
 */
public abstract class Glue {

    /**
     * Référence sur le père
     */
    protected Element pere;

    /**
     * Rôle en entrée de la glue
     */
    protected Role roleEntree;

    /**
     * Rôle en sortie de la glue
     */
    protected Role roleSortie;
}
