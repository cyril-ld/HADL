/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.transverse;

/**
 * Classe représentant un message passant de composant en composant...
 *
 * @author Cyril LD
 */
public class Message {

    private String content;

    /**
     * Créé une instance de message avec le contenu passé en paramètre
     *
     * @param content - Le corps du message
     */
    public Message(String content) {
        this.content = content;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
