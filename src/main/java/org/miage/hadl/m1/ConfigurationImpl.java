/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import java.util.List;
import org.miage.hadl.m2.Attachement;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.Element;
import org.miage.hadl.m2.PortConfiguration;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.m2.Role;

/**
 *
 * @author Cyril LD
 */
public class ConfigurationImpl extends Configuration {

    public ConfigurationImpl(Configuration p_oPere, String p_sNom, List<PortConfiguration> p_cPortsConfiguration, List<Element> p_cElements) {
        super(p_oPere, p_sNom, p_cPortsConfiguration, p_cElements);
    }

    public ConfigurationImpl(String p_sNom) {
        super(p_sNom);
    }

    @Override
    public void addAttachement(PortInterne p_oPort, Role p_oRole) {
        Attachement attachement;

        attachement = new ConnectorAttachement(this, p_oPort, p_oRole);
        this.addElement(attachement);
    }
}
