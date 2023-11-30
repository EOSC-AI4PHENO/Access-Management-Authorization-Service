package com.siseth.authorization.module.keycloak.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siseth.authorization.module.internal.property.ApplicationProps;
import com.siseth.authorization.module.keycloak.model.common.HttpClientKeyCloak;
import com.siseth.authorization.module.keycloak.api.KeyCloakUserInfoDTO;
import com.siseth.authorization.module.keycloak.constant.KeyCloakConstant;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

@AllArgsConstructor
public class UserInfo extends HttpClientKeyCloak {

    private String accessToken;

    private ApplicationProps.Realm realm;

    private final String URL = KeyCloakConstant.KEYCLOAK_URL + "userinfo";

    @SneakyThrows
    @Override
    public KeyCloakUserInfoDTO getResponse() {
        HttpPost request = new HttpPost(URL.replace("{{realm}}", realm.getName()));
        request.setEntity(new UrlEncodedFormEntity(getParams()));
        request = addAuthHeader(request, this.accessToken);
        HttpResponse response = builder().execute(request);
        checkStatus(response);
        return new ObjectMapper().readValue(decode(response.getEntity()), KeyCloakUserInfoDTO.class);
    }


}
