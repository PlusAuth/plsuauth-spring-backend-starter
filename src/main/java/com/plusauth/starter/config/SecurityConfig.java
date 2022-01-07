package com.plusauth.starter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${plusauth.audience}")
  private String audience;

  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
  private String jwtSetUri;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  private JwtDecoder jwtDecoder() {
    // Build at+jwt token decoder with Nimbus
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwtSetUri)
        .jwtProcessorCustomizer(p -> p.setJWSTypeVerifier(
            new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("at+jwt")))) // Add support for at+jwt token type
        .build();

    // Add audience validator to jwt decoder
    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
    jwtDecoder.setJwtValidator(withAudience);

    return jwtDecoder;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {

    // Add authorization to users endpoint
    http.requiresChannel().anyRequest().requiresInsecure().and().cors()
        .and().csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/users/**").hasAuthority("SCOPE_users:read")
        .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("SCOPE_users:write")
        .antMatchers(HttpMethod.PUT, "/users/**").hasAuthority("SCOPE_users:update")
        .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("SCOPE_users:delete")
        .anyRequest()
        .authenticated() // All requests require authentication
        .and()
        .oauth2ResourceServer() // add oauth2 resource server configuration
        .jwt().decoder(jwtDecoder()); // set jwt decoder

  }
}