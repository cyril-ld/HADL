/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import java.util.ArrayList;
import java.util.List;

/**
 * Une configuration est l'élément racine d'une architecture, il permet de faire le lien entre les différents
 * éléments la composant.
 *
 * @author Cyril LD
 */
public abstract class Configuration implements Element {

    /**
     * Nom de la config
     */
    private String nom;

    /**
     * Elements de la configuration
     */
    private List<Element> elements;

    /**
     * Référence sur l'élément père
     */
    private Configuration pere;

    /**
     * Ports de la configuration, lui permettant de dialoguer avec d'autres systèmes
     */
    private List<PortConfiguration> portsConfiguration;

    /**
     * Permet de savoir si la configuration est l'élément racine.
     *
     * @return true / false
     */
    public boolean isRootElement() {
        return this.pere == null;
    }

    /**
     * Ajoute un élément dans la configuration
     *
     * @param element - L'élément à ajouter, doit être différent de null
     *
     * @throws IllegalArgumentException - Dans le cas où l'élément passé est null
     */
    public void addElement(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("L'élément doit être initialisé !");
        } else if (this.elements == null) {
            this.elements = new ArrayList<>();
        }

        // Modification du père de l'élément
        element.setFather(this);

        this.elements.add(element);
    }

    /**
     * Construit une nouvelle configuration
     *
     * @param p_oPere               - L'élément père de la configuration
     * @param p_sNom                - Le nom de la configuration
     * @param p_cPortsConfiguration - Les ports offerts par la configuration
     * @param p_cElements           - Les éléments que l'on souhaite affecter à la configuration
     *
     * @throws IllegalArgumentException - Lorsque le père passé n'est pas une configuration (seul élément à pouvoir
     *                                  contenir une configuration)
     */
    public Configuration(Configuration p_oPere, String p_sNom, List<PortConfiguration> p_cPortsConfiguration, List<Element> p_cElements) {
        this.pere = p_oPere;
        this.nom = p_sNom;

        if (p_cPortsConfiguration != null) {
            this.portsConfiguration = p_cPortsConfiguration;
        } else {
            this.portsConfiguration = new ArrayList<>();
        }

        if (p_cElements != null) {
            this.elements = p_cElements;
        } else {
            this.elements = new ArrayList<>();
        }
    }

    /**
     * @return the elements
     */
    public List<Element> getElements() {
        return elements;
    }

    /**
     * Permet de récupérer les éléments d'un type dans la configuration.
     *
     * @param type - Le type de l'élément à récupérer (Attachement.class, Binding.class, ...)
     *
     * @return - La liste des éléments du type demandé
     */
    public List<Element> getAll(Class type) {
        List<Element> result;

        result = new ArrayList<>();

        // Equivalent à un foreach sur les éléments, nouveau depuis Java 1.8
        this.elements.stream().filter((item) -> (item.getClass() == type)).forEach((item) -> {
            result.add(item);
        });
        return result;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    /**
     * @return the portsConfiguration
     */
    public List<PortConfiguration> getPortsConfiguration() {
        return portsConfiguration;
    }

    /**
     * @param portsConfiguration the portsConfiguration to set
     */
    public void setPortsConfiguration(List<PortConfiguration> portsConfiguration) {
        this.portsConfiguration = portsConfiguration;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public Element getFather() {
        return this.pere;
    }

    @Override
    public void setFather(Element p_oPere) {
        if (p_oPere != null) {
            if (p_oPere.getClass() != Configuration.class) {
                throw new IllegalArgumentException("Le père d'une configuration doit être une configuration !");
            }
        }
        this.pere = (Configuration) p_oPere;
    }
}
