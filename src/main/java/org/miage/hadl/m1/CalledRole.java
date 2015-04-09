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
public class CalledRole extends Role {

    private Message message;

    public CalledRole(Glue p_oPere) {
        super(p_oPere);
    }

    @Override
    public void transmettreMessage(Message message) {
        System.out.println("[ROLE] Je suis un role appelé de " + this.pere.getClass() + ", je viens de récupérer le message !");
        this.message = message;
        this.pere.transfererMessage(message);
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(Message message) {
        this.message = message;
    }

}
