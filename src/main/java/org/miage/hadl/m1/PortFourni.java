/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Element;
import org.miage.hadl.m2.PortInterne;
import org.miage.hadl.m2.enums.PORT_TYPE;

/**
 *
 * @author Cyril LD
 */
public class PortFourni extends PortInterne {

    public PortFourni(Element p_oPere) {
        this.pere = p_oPere;
        this.typePort = PORT_TYPE.PORT_FOURNI;
    }
}
