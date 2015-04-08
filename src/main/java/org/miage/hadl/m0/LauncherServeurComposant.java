/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m0;

import org.miage.hadl.m1.AttachementImpl;
import org.miage.hadl.m1.CalledRole;
import org.miage.hadl.m1.CallerRole;
import org.miage.hadl.m1.Client;
import org.miage.hadl.m1.ConfigurationImpl;
import org.miage.hadl.m1.GlueImpl;
import org.miage.hadl.m1.PortFourni;
import org.miage.hadl.m1.PortRequis;
import org.miage.hadl.m1.RPCConnector;
import org.miage.hadl.m1.Serveur;
import org.miage.hadl.m2.Attachement;
import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.Connector;
import org.miage.hadl.m2.Glue;

/**
 * Permet de lancer la configuration BigCS avec le serveur en tant que composant.
 *
 * @author Cyril LD
 */
public class LauncherServeurComposant {

    public static void main(String... params) {

        // Création de la configuration globale
        Configuration BigCS = new ConfigurationImpl("BigCS");

        // Création du client
        Composant client = new Client(BigCS);
        PortFourni portFourniClient = new PortFourni(client);
        PortRequis portRequisClient = new PortRequis(client);
        client.addPort(portFourniClient);
        client.addPort(portRequisClient);

        // Création du serveur light
        Composant serveur = new Serveur(BigCS);
        PortFourni portFourniServeurLight = new PortFourni(serveur);
        PortRequis portRequisServeurLight = new PortRequis(serveur);
        serveur.addPort(portFourniServeurLight);
        serveur.addPort(portRequisServeurLight);

        // Création du connecteur RPC
        Connector connecteur = new RPCConnector(BigCS);

        Glue RPCGlueIn = new GlueImpl(connecteur);
        CalledRole RPCINroleEntree = new CalledRole(RPCGlueIn);
        CallerRole RPCINroleSortie = new CallerRole(RPCGlueIn);
        RPCGlueIn.setRoleEntree(RPCINroleEntree);
        RPCGlueIn.setRoleSortie(RPCINroleSortie);

        Glue RPCGlueOut = new GlueImpl(connecteur);
        CalledRole RPCOUTRoleEntree = new CalledRole(RPCGlueOut);
        CallerRole RPCOUTRoleSortie = new CallerRole(RPCGlueOut);
        RPCGlueOut.setRoleEntree(RPCOUTRoleEntree);
        RPCGlueOut.setRoleSortie(RPCOUTRoleSortie);

        connecteur.ajouterGlue(RPCGlueIn);
        connecteur.ajouterGlue(RPCGlueOut);

        // Création des attachements
        Attachement clientToRPC = new AttachementImpl(BigCS, portFourniClient, RPCINroleEntree);
        Attachement RPCToClient = new AttachementImpl(BigCS, portRequisClient, RPCOUTRoleSortie);
        Attachement RPCToServeur = new AttachementImpl(BigCS, portRequisServeurLight, RPCINroleSortie);
        Attachement ServeurToRPC = new AttachementImpl(BigCS, portFourniServeurLight, RPCOUTRoleEntree);

        // Ajout des elements dans la configuration BigCS
        BigCS.addElement(client);
        BigCS.addElement(serveur);
        BigCS.addElement(clientToRPC);
        BigCS.addElement(RPCToClient);
        BigCS.addElement(RPCToServeur);
        BigCS.addElement(ServeurToRPC);

        // Et maintenant on demande au client d'envoyer son message !
        ((Client) client).sendMessage("Fera-t-il beau demain ?");
    }
}
