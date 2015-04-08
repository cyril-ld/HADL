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
import org.miage.hadl.m1.PortInterneFourni;
import org.miage.hadl.m1.PortInterneRequis;
import org.miage.hadl.m1.RPCConnector;
import org.miage.hadl.m1.Serveur;
import org.miage.hadl.m1.enums.MODE_FONCTIONNEMENT_SERVEUR;
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

        // ======================================================================== Création de la configuration globale
        Configuration BigCS = new ConfigurationImpl("BigCS");

        // ========================================================================================== Création du client
        Composant client = new Client(BigCS);
        PortInterneFourni portFourniClient = new PortInterneFourni(client);
        PortInterneRequis portRequisClient = new PortInterneRequis(client);
        client.addPort(portFourniClient);
        client.addPort(portRequisClient);

        // ================================================================== Création du serveur light (mode composant)
        Composant serveur = new Serveur(BigCS, MODE_FONCTIONNEMENT_SERVEUR.COMPOSANT);
        PortInterneFourni portFourniServeurLight = new PortInterneFourni(serveur);
        PortInterneRequis portRequisServeurLight = new PortInterneRequis(serveur);
        serveur.addPort(portFourniServeurLight);
        serveur.addPort(portRequisServeurLight);

        // ================================================================================== Création du connecteur RPC
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

        // =================================================================================== Création des attachements
        Attachement clientToRPC = new AttachementImpl(BigCS, portFourniClient, RPCINroleEntree);
        Attachement RPCToClient = new AttachementImpl(BigCS, portRequisClient, RPCOUTRoleSortie);
        Attachement RPCToServeur = new AttachementImpl(BigCS, portRequisServeurLight, RPCINroleSortie);
        Attachement ServeurToRPC = new AttachementImpl(BigCS, portFourniServeurLight, RPCOUTRoleEntree);

        // ============================================================== Ajout des elements dans la configuration BigCS
        BigCS.addElement(client);
        BigCS.addElement(serveur);
        BigCS.addElement(clientToRPC);
        BigCS.addElement(RPCToClient);
        BigCS.addElement(RPCToServeur);
        BigCS.addElement(ServeurToRPC);

        // Et maintenant on demande au client d'envoyer son message afin que le serveur lui réponde, la suite dans les traces !
        ((Client) client).sendMessage("Fera-t-il beau demain ?");
    }
}
