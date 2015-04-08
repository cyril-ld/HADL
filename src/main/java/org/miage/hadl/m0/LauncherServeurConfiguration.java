/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.hadl.m0;

import org.miage.hadl.m1.AttachementImpl;
import org.miage.hadl.m1.BindingImpl;
import org.miage.hadl.m1.CalledRole;
import org.miage.hadl.m1.CallerRole;
import org.miage.hadl.m1.Client;
import org.miage.hadl.m1.ConfigurationImpl;
import org.miage.hadl.m1.ConnectionManager;
import org.miage.hadl.m1.GlueImpl;
import org.miage.hadl.m1.PortConfigurationFourni;
import org.miage.hadl.m1.PortConfigurationRequis;
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
 * Permet de lancer la configuration BigCS avec le serveur en tant que configuration.
 *
 * @author Cyril LD
 */
public class LauncherServeurConfiguration {

    public static void main(String... args) {

        // ======================================================================== Création de la configuration globale
        Configuration BigCS = new ConfigurationImpl("BigCS");
        Configuration ServeurConfig = new ConfigurationImpl("Serveur");

        // ========================================================================= Création des ports de configuration
        PortConfigurationRequis portConfigRequis = new PortConfigurationRequis(ServeurConfig);
        PortConfigurationFourni portConfigFourni = new PortConfigurationFourni(ServeurConfig);

        // ========================================================================================== Création du client
        Composant client = new Client(BigCS);
        PortInterneFourni portFourniClient = new PortInterneFourni(client);
        PortInterneRequis portRequisClient = new PortInterneRequis(client);
        client.addPort(portFourniClient);
        client.addPort(portRequisClient);

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

        // ========================================================================================= Création du serveur
        Composant serveur = new Serveur(BigCS, MODE_FONCTIONNEMENT_SERVEUR.CONFIGURATION);
        PortInterneFourni portFourniServeurLight = new PortInterneFourni(serveur);
        PortInterneRequis portRequisServeurLight = new PortInterneRequis(serveur);
        serveur.addPort(portFourniServeurLight);
        serveur.addPort(portRequisServeurLight);

        // ============================================================================= Création des attachements BigCS
        Attachement clientToRPC = new AttachementImpl(BigCS, portFourniClient, RPCINroleEntree);
        Attachement RPCToClient = new AttachementImpl(BigCS, portRequisClient, RPCOUTRoleSortie);
        Attachement RPCToServeur = new AttachementImpl(BigCS, portRequisServeurLight, RPCINroleSortie);
        Attachement ServeurToRPC = new AttachementImpl(BigCS, portFourniServeurLight, RPCOUTRoleEntree);

        // ============================================================================== Création du Connection Manager
        Composant connectionManager = new ConnectionManager(ServeurConfig);
        PortInterneFourni portFourniCM = new PortInterneFourni(connectionManager);
        PortInterneRequis portRequisCM = new PortInterneRequis(connectionManager);
        connectionManager.addPort(portFourniCM);
        connectionManager.addPort(portRequisCM);

        // Création des bindings
        BindingImpl portServToPortConfRequis = new BindingImpl(ServeurConfig, portRequisServeurLight, portConfigRequis);
        BindingImpl portConfRequisToPortCMRequis = new BindingImpl(ServeurConfig, portRequisCM, portConfigRequis);

        // Création des attachements ServeurConfig
        // Ajout des composants de la configuration Serveur
        ServeurConfig.addPortConfiguration(portConfigFourni);
        ServeurConfig.addPortConfiguration(portConfigRequis);
        ServeurConfig.addElement(connectionManager);
        ServeurConfig.addElement(portConfRequisToPortCMRequis);

        // ============================================================== Ajout des elements dans la configuration BigCS
        BigCS.addElement(client);
        BigCS.addElement(serveur);
        BigCS.addElement(clientToRPC);
        BigCS.addElement(RPCToClient);
        BigCS.addElement(RPCToServeur);
        BigCS.addElement(ServeurToRPC);
        BigCS.addElement(ServeurConfig);
        BigCS.addElement(portServToPortConfRequis);

        // Et maintenant on demande au client d'envoyer son message afin que le serveur lui réponde, la suite dans les traces !
        ((Client) client).sendMessage("Fera-t-il beau demain ?");
    }
}
