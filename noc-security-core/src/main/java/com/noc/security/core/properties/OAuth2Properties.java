package com.noc.security.core.properties;

/**
 * 支持多个第三方
 */
public class OAuth2Properties {

    // JWT 秘钥
    private String jwtSigningKey = "noc";

    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
