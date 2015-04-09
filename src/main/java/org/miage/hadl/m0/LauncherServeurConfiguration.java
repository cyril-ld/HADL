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
import org.miage.hadl.m1.ConnectorImpl;
import org.miage.hadl.m1.Database;
import org.miage.hadl.m1.GlueImpl;
import org.miage.hadl.m1.PortConfigurationFourni;
import org.miage.hadl.m1.PortConfigurationRequis;
import org.miage.hadl.m1.PortInterneFourni;
import org.miage.hadl.m1.PortInterneRequis;
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
        Configuration serveurConfig = new ConfigurationImpl("Serveur");

        // ========================================================================= Création des ports de configuration
        PortConfigurationRequis portConfigRequis = new PortConfigurationRequis(serveurConfig);
        PortConfigurationFourni portConfigFourni = new PortConfigurationFourni(serveurConfig);

        // ========================================================================================== Création du client
        Composant client = new Client(BigCS);
        PortInterneFourni portFourniClient = new PortInterneFourni(client);
        PortInterneRequis portRequisClient = new PortInterneRequis(client);
        client.addPort(portFourniClient);
        client.addPort(portRequisClient);

        // ================================================================================== Création du connecteur RPC
        Connector connecteurRPC = new ConnectorImpl(BigCS);

        Glue RPCGlueIn = new GlueImpl(connecteurRPC);
        CalledRole RPCINroleEntree = new CalledRole(RPCGlueIn);
        CallerRole RPCINroleSortie = new CallerRole(RPCGlueIn);
        RPCGlueIn.setRoleEntree(RPCINroleEntree);
        RPCGlueIn.setRoleSortie(RPCINroleSortie);

        Glue RPCGlueOut = new GlueImpl(connecteurRPC);
        CalledRole RPCOUTRoleEntree = new CalledRole(RPCGlueOut);
        CallerRole RPCOUTRoleSortie = new CallerRole(RPCGlueOut);
        RPCGlueOut.setRoleEntree(RPCOUTRoleEntree);
        RPCGlueOut.setRoleSortie(RPCOUTRoleSortie);

        connecteurRPC.ajouterGlue(RPCGlueIn);
        connecteurRPC.ajouterGlue(RPCGlueOut);

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
        Composant connectionManager = new ConnectionManager(serveurConfig);
        PortInterneFourni externalSocketCaller = new PortInterneFourni(connectionManager);
        PortInterneRequis externalSocketCalled = new PortInterneRequis(connectionManager);
        PortInterneFourni DBQuery = new PortInterneFourni(connectionManager);
        PortInterneRequis SecurityCheck = new PortInterneRequis(connectionManager);
        connectionManager.addPort(externalSocketCalled);
        connectionManager.addPort(DBQuery);
        /*
         * Avant d'ajouter ces deux là, il va falloir trouver comment gérer plusieurs mêmes ports
         */
//        connectionManager.addPort(SecurityCheck);
//        connectionManager.addPort(externalSocketCaller);

        // ============================================================================ Création du connecteur SQL Query
        Connector SQLQueryConnector = new ConnectorImpl(serveurConfig);
        Glue SQLQueryGlue = new GlueImpl(SQLQueryConnector);
        CalledRole SQLQueryRoleEntree = new CalledRole(SQLQueryGlue);
        CallerRole SQLQueryRoleSortie = new CallerRole(SQLQueryGlue);
        SQLQueryGlue.setRoleEntree(SQLQueryRoleEntree);
        SQLQueryGlue.setRoleSortie(SQLQueryRoleSortie);
        SQLQueryConnector.ajouterGlue(SQLQueryGlue);

        // ============================================================================== Création du composant Database
        Composant database = new Database(serveurConfig);
        PortInterneFourni securityManagement = new PortInterneFourni(database);
        PortInterneRequis querying = new PortInterneRequis(database);
        database.addPort(securityManagement);
        database.addPort(querying);

        // ======================================================================== Création du connecteur SecurityQuery
        Connector securityQuery = new ConnectorImpl(serveurConfig);
        Glue securityQueryGlue = new GlueImpl(securityQuery);
        CalledRole securityQueryRoleEntree = new CalledRole(securityQueryGlue);
        CallerRole securityQueryRoleSortie = new CallerRole(securityQueryGlue);
        securityQueryGlue.setRoleEntree(securityQueryRoleEntree);
        securityQueryGlue.setRoleSortie(securityQueryRoleSortie);
        securityQuery.ajouterGlue(securityQueryGlue);

        // ======================================================================================= Création des bindings
        BindingImpl portServToPortConfRequis = new BindingImpl(serveurConfig, portRequisServeurLight, portConfigRequis);
        BindingImpl portConfRequisToPortCMRequis = new BindingImpl(serveurConfig, externalSocketCalled, portConfigRequis);

        // ===================================================================== Création des attachements ServeurConfig
        Attachement DBQueryAttachement = new AttachementImpl(serveurConfig, DBQuery, SQLQueryRoleEntree);
        Attachement queryingAttachement = new AttachementImpl(serveurConfig, querying, SQLQueryRoleSortie);
        Attachement securityManagementAttachement = new AttachementImpl(serveurConfig, securityManagement, securityQueryRoleEntree);

        // ============================================================ Ajout des composants de la configuration Serveur
        serveurConfig.addPortConfiguration(portConfigFourni);
        serveurConfig.addPortConfiguration(portConfigRequis);
        serveurConfig.addElement(connectionManager);
        serveurConfig.addElement(portConfRequisToPortCMRequis);
        serveurConfig.addElement(SQLQueryConnector);
        serveurConfig.addElement(DBQueryAttachement);
        serveurConfig.addElement(database);
        serveurConfig.addElement(queryingAttachement);
        serveurConfig.addElement(securityManagementAttachement);

        // ============================================================== Ajout des elements dans la configuration BigCS
        BigCS.addElement(client);
        BigCS.addElement(serveur);
        BigCS.addElement(clientToRPC);
        BigCS.addElement(RPCToClient);
        BigCS.addElement(RPCToServeur);
        BigCS.addElement(ServeurToRPC);
        BigCS.addElement(serveurConfig);
        BigCS.addElement(portServToPortConfRequis);

        // Et maintenant on demande au client d'envoyer son message afin que le serveur lui réponde, la suite dans les traces !
        ((Client) client).sendMessage("Fera-t-il beau demain ?");
    }
}
