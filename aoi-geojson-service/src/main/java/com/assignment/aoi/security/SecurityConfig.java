package com.assignment.aoi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
     * throws Exception { http .authorizeHttpRequests(auth -> auth
     * .anyRequest().authenticated() // permit all other endpoints )
     * .oauth2ResourceServer(oauth2 -> oauth2 .jwt(jwt ->
     * jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())) // Use the new
     * JwtConfigurer );
     *
     * return http.build(); }
     */


	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
			 .authorizeHttpRequests(auth -> auth
				 .requestMatchers("/api/**").authenticated()
				 .anyRequest().authenticated() // Require authentication for all other endpoints
			 )
			 .oauth2Login()
			 .and()
			 .oauth2ResourceServer(oauth2 -> oauth2
				 .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())) // Use JWT for resource server
			 )
			 .logout(logout -> logout
				 .logoutSuccessHandler(oidcLogoutHandler(null)) // Use the OIDC logout handler
			 );
		 return http.build();
	 }
 
	 @Bean
	 public LogoutSuccessHandler oidcLogoutHandler(ClientRegistrationRepository clientRegistrationRepository) {
		 OidcClientInitiatedLogoutSuccessHandler handler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
		 handler.setPostLogoutRedirectUri("{baseUrl}");
		 return handler;
	 }
 
	 private JwtAuthenticationConverter jwtAuthenticationConverter() {
		 JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		 // Customize converter if needed, e.g., to extract roles/authorities from the JWT
		 return converter;
	 }
}
