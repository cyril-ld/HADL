/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Binding;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.PortConfiguration;
import org.miage.hadl.m2.PortInterne;

/**
 *
 * @author Cyril LD
 */
public class BindingImpl extends Binding {

    public BindingImpl(Configuration p_oPere, PortInterne p_oPortInterne, PortConfiguration p_oPortExterne) {
        super(p_oPere);
        this.portInterne = p_oPortInterne;
        this.portExterne = p_oPortExterne;
    }

}
