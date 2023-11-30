package com.siseth.authorization.module.keycloak.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyCloakConstant {

    public static String KEYCLOAK_URL;

    @Value("${app.keycloak.url}")
    public void setUrl(String url) {
        KEYCLOAK_URL = url;
    }

}
