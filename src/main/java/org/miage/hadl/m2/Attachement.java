/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

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
    protected Element pere;

    /**
     * Référence sur le port rattaché
     */
    protected PortInterne port;

    /**
     * Référence sur le rôle du connecteur
     */
    protected Role role;

}
