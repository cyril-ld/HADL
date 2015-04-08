/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

/**
 * Super élément de la configuration, qui va permettre à la Configuration de faire le lien avec d'autres éléments,
 * et ainsi lui permettre de contenir d'autres éléments.
 *
 * @author Cyril
 */
public interface Element {

    /**
     * Récupère une référence sur le père de l'élément
     *
     * @return l'élément père
     */
    public Element getFather();

    /**
     * Modifie l'élément père de l'élément
     *
     * @param p_oPere - Le père de l'élément courant
     */
    public void setFather(Element p_oPere);
}
