package dev.cere.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(
        exclude = {
            DataSourceAutoConfiguration.class,
            DataSourceTransactionManagerAutoConfiguration.class,
            HibernateJpaAutoConfiguration.class
        })
public class ServiceConfig {

    @Value("${spring.security.oauth2.resourceserver.opaque-token.introspection-uri}")
    private String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
    private String clientSecret;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        x ->
                                x.requestMatchers(HttpMethod.GET, "/content/**")
                                        .hasAuthority("test_read")
                                        .requestMatchers(HttpMethod.POST, "/content/**")
                                        .hasAuthority("test_write")
                                        .requestMatchers(HttpMethod.PUT, "/content/**")
                                        .hasAuthority("test_write")
                                        .requestMatchers(HttpMethod.DELETE, "/content/**")
                                        .hasAuthority("test_write")
                                        .requestMatchers(HttpMethod.GET, "/recommendations/**")
                                        .hasAuthority("test_read")
                                        .requestMatchers(HttpMethod.GET, "/reviews/**")
                                        .hasAuthority("test_read")
                                        .requestMatchers(HttpMethod.POST, "/reviews/**")
                                        .hasAuthority("test_write")
                                        .requestMatchers(HttpMethod.PUT, "/reviews/**")
                                        .hasAuthority("test_write")
                                        .requestMatchers(HttpMethod.DELETE, "/reviews/**")
                                        .hasAuthority("test_write")
                                        .anyRequest()
                                        .authenticated())
                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.opaqueToken(
                                        opaqueToken ->
                                                opaqueToken
                                                        .introspectionUri(introspectionUri)
                                                        .introspectionClientCredentials(
                                                                clientId, clientSecret)));
        return http.build();
    }
}
