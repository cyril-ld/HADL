/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import java.util.List;

/**
 * Une configuration est l'élément racine d'une architecture, il permet de faire le lien entre les différents
 * éléments la composant.
 *
 * @author Cyril LD
 */
public abstract class Configuration implements Element {

    /**
     * Différents éléments constituant la configuration (Binding / Composant / Attachement / Connector / Configuration)
     */
    protected List<Element> elements;

    /**
     * Référence sur l'élément père
     */
    protected Element pere;
}
