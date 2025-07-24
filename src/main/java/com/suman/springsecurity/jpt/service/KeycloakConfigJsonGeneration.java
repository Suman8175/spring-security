package com.suman.springsecurity.jpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.suman.springsecurity.jpt.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class KeycloakConfigJsonGeneration {
    private final GetEndpoints apiEndpointService;

    public KeycloakConfigJsonGeneration(GetEndpoints apiEndpointService) {
        this.apiEndpointService = apiEndpointService;
    }

    public KeycloakAuthorizationConfig generateKeycloakConfig() throws JsonProcessingException {

        List<Scopes> scopes = new ArrayList<>();
        scopes.add(Scopes.GET);
        scopes.add(Scopes.PUT);
        scopes.add(Scopes.POST);
        scopes.add(Scopes.DELETE);
        scopes.add(Scopes.PATCH);

        Map<String , Set<String>> allEndpoint = apiEndpointService.getAllApiEndpointsByController();
        Set<String> binPartitionControllerEndPoint = allEndpoint.get("UserController");

        Resources binPartitionControllerResource = new Resources();
        binPartitionControllerResource.setName("bin-partition-controller-resource");
        binPartitionControllerResource.setOwnerManagedAccess(true);
        binPartitionControllerResource.setDisplayName("Bin Partition Controller Resource");
        binPartitionControllerResource.setScopes(scopes);
        binPartitionControllerResource.setUris(binPartitionControllerEndPoint);

        Set<String> cardArtControllerEndPoint = allEndpoint.get("CardArtController");

        Resources cardArtControllerResource = new Resources();
        cardArtControllerResource.setName("card-art-controller-resource");
        cardArtControllerResource.setOwnerManagedAccess(true);
        cardArtControllerResource.setDisplayName("Card Art Controller Resource");
        cardArtControllerResource.setScopes(scopes);
        cardArtControllerResource.setUris(cardArtControllerEndPoint);

        Set<String> cardControllerEndPoint = allEndpoint.get("CardController");

        Resources cardControllerResource = new Resources();
        cardControllerResource.setName("card-controller-resource");
        cardControllerResource.setOwnerManagedAccess(true);
        cardControllerResource.setDisplayName("Card Controller Resource");
        cardControllerResource.setScopes(scopes);
        cardControllerResource.setUris(cardControllerEndPoint);

        Set<String> cbsHookControllerEndPoint = allEndpoint.get("CbsHookController");

        Resources cbsHookControllerResource = new Resources();
        cbsHookControllerResource.setName("cbs-hook-controller-resource");
        cbsHookControllerResource.setOwnerManagedAccess(true);
        cbsHookControllerResource.setDisplayName("Cbs Hook Controller Resource");
        cbsHookControllerResource.setScopes(scopes);
        cbsHookControllerResource.setUris(cbsHookControllerEndPoint);

        Set<String> journalControllerEndPoint = allEndpoint.get("JournalController");

        Resources journalControllerResource = new Resources();
        journalControllerResource.setName("journal-controller-resource");
        journalControllerResource.setOwnerManagedAccess(true);
        journalControllerResource.setDisplayName("Journal Controller Resource");
        journalControllerResource.setScopes(scopes);
        journalControllerResource.setUris(journalControllerEndPoint);

        Set<String> notificationControllerEndPoint = allEndpoint.get("NotificationController");

        Resources notificationControllerResource = new Resources();
        notificationControllerResource.setName("notification-controller-resource");
        notificationControllerResource.setOwnerManagedAccess(true);
        notificationControllerResource.setDisplayName("Notification Controller Resource");
        notificationControllerResource.setScopes(scopes);
        notificationControllerResource.setUris(notificationControllerEndPoint);

        Set<String> productControllerEndPont = allEndpoint.get("ProductController");

        Resources productControllerResource = new Resources();
        productControllerResource.setName("product-controller-resource");
        productControllerResource.setOwnerManagedAccess(true);
        productControllerResource.setDisplayName("Product Controller Resource");
        productControllerResource.setScopes(scopes);
        productControllerResource.setUris(productControllerEndPont);

        Set<String> productTypeControllerEndPoint = allEndpoint.get("ProductTypeController");

        Resources productTypeControllerResource = new Resources();
        productTypeControllerResource.setName("product-type-controller-resource");
        productTypeControllerResource.setOwnerManagedAccess(true);
        productTypeControllerResource.setDisplayName("Product Type Controller Resource");
        productTypeControllerResource.setScopes(scopes);
        productTypeControllerResource.setUris(productTypeControllerEndPoint);

        Set<String> transactionControllerEndPoint = allEndpoint.get("TransactionController");

        Resources transactionControllerResource = new Resources();
        transactionControllerResource.setName("transaction-controller-resource");
        transactionControllerResource.setOwnerManagedAccess(true);
        transactionControllerResource.setDisplayName("Transaction Controller Resource");
        transactionControllerResource.setScopes(scopes);
        transactionControllerResource.setUris(transactionControllerEndPoint);

        Set<String> welcomeControllerEndPoint = allEndpoint.get("WelcomeController");

        Resources welcomeControllerResource = new Resources();
        welcomeControllerResource.setName("welcome-controller-resource");
        welcomeControllerResource.setOwnerManagedAccess(true);
        welcomeControllerResource.setDisplayName("Welcome Controller Resource");
        welcomeControllerResource.setScopes(scopes);
        welcomeControllerResource.setUris(welcomeControllerEndPoint);

        Set<String> cardStackControllerEndPoint = allEndpoint.get("CardStackController");

        Resources cardStackControllerResource = new Resources();
        cardStackControllerResource.setName("Card-stack-controller-resource");
        cardStackControllerResource.setOwnerManagedAccess(true);
        cardStackControllerResource.setDisplayName("Card Stack Controller Resource");
        cardStackControllerResource.setScopes(scopes);
        cardStackControllerResource.setUris(cardStackControllerEndPoint);

        Set<String> deliveryAddressesControllerEndPoint = allEndpoint.get("DeliveryAddressesController");

        Resources deliveryAddressesControllerResource = new Resources();
        deliveryAddressesControllerResource.setName("delivery-addresses-controller-resource");
        deliveryAddressesControllerResource.setOwnerManagedAccess(true);
        deliveryAddressesControllerResource.setDisplayName("Delivery Addresses Controller Resource");
        deliveryAddressesControllerResource.setScopes(scopes);
        deliveryAddressesControllerResource.setUris(deliveryAddressesControllerEndPoint);

        Set<String> printerControllerEndPoint = allEndpoint.get("PrinterController");

        Resources printerControllerResource = new Resources();
        printerControllerResource.setName("printer-controller-resource");
        printerControllerResource.setOwnerManagedAccess(true);
        printerControllerResource.setDisplayName("Printer Controller Resource");
        printerControllerResource.setScopes(scopes);
        printerControllerResource.setUris(printerControllerEndPoint);

        Set<String> cardFileControllerEndPoint = allEndpoint.get("CardFileController");

        Resources cardFileControllerResource = new Resources();
        cardFileControllerResource.setName("card-file-controller-resource");
        cardFileControllerResource.setOwnerManagedAccess(true);
        cardFileControllerResource.setDisplayName("Card File Controller Resource");
        cardFileControllerResource.setScopes(scopes);
        cardFileControllerResource.setUris(cardFileControllerEndPoint);

        Roles adminRole = new Roles();
        adminRole.setId("admin");
        adminRole.setRequired(false);
        Roles demoRole = new Roles();
        demoRole.setId("demo");
        demoRole.setRequired(false);

        List<Roles> roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(demoRole);

        List<Roles> adminRoles = new ArrayList<>();
        adminRoles.add(adminRole);

        ObjectMapper objectMapper = new ObjectMapper();
        String rolesString = objectMapper.writeValueAsString(adminRoles);

        String rolesString1 = objectMapper.writeValueAsString(roles);

        Config configPolicy1 = new Config();
        configPolicy1.setFetchRoles("true");
        configPolicy1.setRoles(rolesString);

        Config configPolicy2 = new Config();
        configPolicy2.setFetchRoles("true");
        configPolicy2.setRoles(rolesString1);

        String permission1Scope = objectMapper.writeValueAsString(List.of(Scopes.GET));
        String permission2Scope = objectMapper.writeValueAsString(scopes);

        String permission1Policy = objectMapper.writeValueAsString(List.of("demo-access-policy"));
        String permission2Policy = objectMapper.writeValueAsString(List.of("admin-access-policy"));

        Config configPermission1 = new Config();
        configPermission1.setScopes(permission1Scope);
        configPermission1.setApplyPolicies(permission1Policy);

        Config configPermission2 = new Config();
        configPermission2.setScopes(permission2Scope);
        configPermission2.setApplyPolicies(permission2Policy);

        String positive = "POSITIVE";
        String unanimous = "UNANIMOUS";
        Policies adminAccessPolicy = new Policies();
        adminAccessPolicy.setName("admin-access-policy");
        adminAccessPolicy.setDescription("Allowing access to admin resource");
        adminAccessPolicy.setType("role");
        adminAccessPolicy.setLogic(positive);
        adminAccessPolicy.setDecisionStrategy(unanimous);
        adminAccessPolicy.setConfig(configPolicy1);

        Policies adminDemoPolicy = new Policies();
        adminDemoPolicy.setName("demo-access-policy");
        adminDemoPolicy.setDescription("Allowing access to demo resource");
        adminDemoPolicy.setType("role");
        adminDemoPolicy.setLogic(positive);
        adminDemoPolicy.setDecisionStrategy(unanimous);
        adminDemoPolicy.setConfig(configPolicy2);

        Policies adminPermission = new Policies();
        adminPermission.setName("admin-permission");
        adminPermission.setDescription("Allowing access to admin resource");
        adminPermission.setType("scope");
        adminPermission.setLogic(positive);
        adminPermission.setDecisionStrategy(unanimous);
        adminPermission.setConfig(configPermission2);

        Policies demoPermission = new Policies();
        demoPermission.setName("demo-permission");
        demoPermission.setDescription("Allowing access to demo resource");
        demoPermission.setType("scope");
        demoPermission.setLogic(positive);
        demoPermission.setDecisionStrategy(unanimous);
        demoPermission.setConfig(configPermission1);

        List<Policies> policies = new ArrayList<>();
        policies.add(adminAccessPolicy);
        policies.add(adminDemoPolicy);
        policies.add(adminPermission);
        policies.add(demoPermission);

        KeycloakAuthorizationConfig keycloakAuthorizationConfig = new KeycloakAuthorizationConfig();
        keycloakAuthorizationConfig.setAllowRemoteResourceManagement(true);
        keycloakAuthorizationConfig.setPolicyEnforcementMode("ENFORCING");
        keycloakAuthorizationConfig.setResources(List.of(binPartitionControllerResource, cardArtControllerResource, cardControllerResource, cbsHookControllerResource, journalControllerResource, notificationControllerResource, productControllerResource, productTypeControllerResource, transactionControllerResource, welcomeControllerResource, cardStackControllerResource, printerControllerResource, cardFileControllerResource, deliveryAddressesControllerResource));
        keycloakAuthorizationConfig.setDecisionStrategy(unanimous);
        keycloakAuthorizationConfig.setPolicies(policies);
        keycloakAuthorizationConfig.setScopes(scopes);
        log.info("Keycloak Authorization Config: {}", keycloakAuthorizationConfig);
        return keycloakAuthorizationConfig;
    }

    public String generateConfigurationAsJsonFile() throws IOException {
        KeycloakAuthorizationConfig config = generateKeycloakConfig();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(config);
        log.info("JSON: {}", json);

        String filePath = "keycloak-resource-config.json";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
            log.info("JSON file generated successfully at: {}", filePath);
        }

        return filePath;
    }
}
