/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import java.util.List;

/**
 *
 * @author Cyril LD
 */
public abstract class Composant implements Element {

    /**
     * Référence sur le père
     */
    protected Element pere;

    /**
     * Liste des ports internes au système offerts par le composant
     */
    protected List<PortInterne> ports;

}
