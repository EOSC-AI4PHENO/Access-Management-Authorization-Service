package com.siseth.authorization.module.keycloak.model.common;

import com.siseth.authorization.module.keycloak.exception.ForbiddenException;
import com.siseth.authorization.module.keycloak.exception.ServerException;
import com.siseth.authorization.module.keycloak.exception.UnauthorizedException;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class HttpClientKeyCloak implements ClientKeyCloak  {

    @SneakyThrows
    protected HttpClient builder() {
        return HttpClients
                .custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
    }

    protected List<NameValuePair> getParams() {
        return new ArrayList<NameValuePair>();
    }

    @SneakyThrows
    protected String decode(HttpEntity entity) {
        return EntityUtils.toString(entity, "UTF-8");
    }

    protected HttpPost addAuthHeader(HttpPost http, String token) {
        http.addHeader("Authorization","Bearer " + token);
        return http;
    }

    protected void checkStatus(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode > 199 && statusCode < 300) // status 2xx - ok
            return;
        switch (statusCode){
            case 401:
                throw new UnauthorizedException("");
            case 403:
                throw new ForbiddenException("");
            case 500:
                throw new ServerException("Server error");
        }

    }

}
