package com.noc.security.app.server;

import com.noc.security.app.jwt.NocJwtTokenEnhancer;
import com.noc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 负责令牌存取
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "noc.security.oauth2", name = "tokenStore", havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * JWT 配置
     * 告诉系统用当有 noc.security.oauth2.tokenStore = jwt 配置时，使用下面这个 TokenStore
     */
    @Configuration
    @ConditionalOnProperty(prefix = "noc.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * JwtAccessTokenConverter:token 生成处理
         *
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            // 秘钥，用来密签
            converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return converter;
        }

        @Bean
        // 可以被覆盖
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer() {
            return new NocJwtTokenEnhancer();
        }

    }

}
