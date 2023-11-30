package com.siseth.authorization.module.internal.authorization.service;

import com.siseth.authorization.module.internal.property.ApplicationProps;
import com.siseth.authorization.module.keycloak.api.KeyCloakUserInfoDTO;
import com.siseth.authorization.module.keycloak.model.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final ApplicationProps props;
    public KeyCloakUserInfoDTO userInfo(String token, String realm) {
        return new UserInfo(token, props.getRealm(realm)).getResponse();
    }
}
