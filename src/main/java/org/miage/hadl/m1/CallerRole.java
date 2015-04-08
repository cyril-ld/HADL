/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m1;

import org.miage.hadl.m2.Glue;
import org.miage.hadl.m2.Role;
import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public class CallerRole extends Role {

    private Message message;

    public CallerRole(Glue p_oPere) {
        super(p_oPere);
    }

    @Override
    public void transmettreMessage(Message message) {
        this.message = message;
        this.pere.getPere().getFather().faireSuivreMessage(this);
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

}
