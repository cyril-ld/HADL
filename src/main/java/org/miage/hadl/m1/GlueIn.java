/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Connector;
import org.miage.hadl.m2.Glue;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class GlueIn extends Glue {

    public GlueIn(Connector p_oPere) {
        super(p_oPere);
    }

    @Override
    public void transfererMessage(Message message) {
        this.roleSortie.transmettreMessage(message);
    }

}
