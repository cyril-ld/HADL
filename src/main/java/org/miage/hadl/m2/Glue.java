/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m2;

import org.miage.hadl.transverse.Message;

/**
 *
 * @author Cyril LD
 */
public abstract class Glue {

    /**
     * Référence sur le père
     */
    private Connector pere;

    /**
     * Rôle en entrée de la glue
     */
    protected Role roleEntree;

    /**
     * Rôle en sortie de la glue
     */
    protected Role roleSortie;

    public Glue(Connector p_oPere) {
        if (p_oPere == null) {
            throw new IllegalArgumentException("Le père ne peut pas être nul pour la Glue !");
        }
        this.pere = p_oPere;
    }

    /**
     * @return the pere
     */
    public Connector getPere() {
        return pere;
    }

    /**
     * @param pere the pere to set
     */
    public void setPere(Connector pere) {
        this.pere = pere;
    }

    public Role getRoleEntree() {
        return roleEntree;
    }

    public void setRoleEntree(Role roleEntree) {
        this.roleEntree = roleEntree;
    }

    public Role getRoleSortie() {
        return roleSortie;
    }

    public void setRoleSortie(Role roleSortie) {
        this.roleSortie = roleSortie;
    }

    /**
     * Méthode appelée lorsqu'un message est reçu
     *
     * @param message - Le message reçu
     */
    public abstract void transfererMessage(Message message);
}
