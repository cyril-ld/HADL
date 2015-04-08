/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m0;

import org.miage.hadl.m1.CalledRole;
import org.miage.hadl.m1.CallerRole;
import org.miage.hadl.m1.Client;
import org.miage.hadl.m1.ConfigurationImpl;
import org.miage.hadl.m1.GlueImpl;
import org.miage.hadl.m1.PortFourni;
import org.miage.hadl.m1.PortRequis;
import org.miage.hadl.m1.RPCConnector;
import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.Connector;
import org.miage.hadl.m2.Glue;

/**
 * Permet de lancer la configuration BigCS avec le serveur en tant que configuration.
 *
 * @author Cyril LD
 */
public class LauncherServeurConfiguration {

    public static void main(String... args) {

        // Création de la configuration globale
        Configuration BigCS = new ConfigurationImpl("BigCS");

        // Création du client
        Composant client = new Client(BigCS);
        PortFourni portFourniClient = new PortFourni(client);
        PortRequis portRequisClient = new PortRequis(client);
        client.addPort(portFourniClient);
        client.addPort(portRequisClient);

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
    }
}
