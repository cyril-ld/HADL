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
import org.miage.hadl.m1.Serveur;
import org.miage.hadl.m2.Composant;
import org.miage.hadl.m2.Configuration;
import org.miage.hadl.m2.Connector;
import org.miage.hadl.m2.Glue;

/**
 *
 * @author Cyril LD
 */
public class Launcher {

    public static void main(String... params) {

        // Création de la configuration globale
        Configuration BigCS = new ConfigurationImpl("BigCS");

        // Création du client
        PortFourni portFourniClient = new PortFourni(null);
        PortRequis portRequisClient = new PortRequis(null);
        Composant client = new Client(BigCS, portFourniClient, portRequisClient);

        // Création du serveur light
        PortFourni portFourniServeurLight = new PortFourni(null);
        PortRequis portRequisServeurLight = new PortRequis(null);
        Composant serveur = new Serveur(BigCS, portFourniServeurLight, portRequisServeurLight);

        // Création du connecteur RPC
        Glue RPCGlueIn = new GlueImpl(null);
        CalledRole RPCroleEntree = new CalledRole(RPCGlueIn);
        CallerRole RPCroleSortie = new CallerRole(RPCGlueIn);
        RPCGlueIn.setRoleEntree(RPCroleEntree);
        RPCGlueIn.setRoleSortie(RPCroleSortie);
        Glue RPCGlueOut = new GlueImpl(null);
        Connector connecteur = new RPCConnector(BigCS);
    }
}
