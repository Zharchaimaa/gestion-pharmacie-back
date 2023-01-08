package org.pharma.cofa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Web Security Config
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 * <p>
 * Configuration de sécurité d'application
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; // injecter le service de point d'enttée

    @Autowired
    JwtRequestFilter jwtRequestFilter; // injecter le filtre du jeton d'identité JWT


    /**
     * configurer le fournisseur d'authentification personnalisé
     *
     * @param authenticationManagerBuilder générateur de fournisseur d'authentification
     * @param authenticationProvider       fournisseur d'authentification personnalisé
     */
    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder authenticationManagerBuilder,
            BPCAuthenticationProvider authenticationProvider
    ) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    /**
     * Configuration de sécurité d'application
     *
     * @param http la requête http
     * @throws Exception qui a causé l'exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cross-Origin Resource Sharing (CORS)
        // restreintes les demandées à partir d'un autre domaine en dehors du domaine de l'application
        http
                .cors(cors -> {
                    CorsConfigurationSource source = request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("http://localhost:4200/")); // autoriser le front-end
                        config.setAllowedHeaders(List.of("*"));
                        config.setAllowedMethods(Stream.of(HttpMethod.values()).map(Enum::name).collect(Collectors.toList()));
                        return config;
                    };
                    cors.configurationSource(source);
                })
                .csrf()
                .disable();

        // configurez les autorisations sur chaque URL (controleur)
        http
                .authorizeRequests()
                // autoriser le controleur accueil, controleur d'requête et la documentation d'API a tous le mode
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers("/api/auth",
                        "/api-docs",
                        "/v3/openapi/**",
                        "/swagger-ui/**",
                        "/bpc/**")
                .permitAll()

                // les autres requêtes nécessite l'authentification
                .anyRequest()
                .authenticated();

        // gestionnaire d'exception personnalisée
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        // gestionnaire de session
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // gestionnaire de validation du jeton d'identité JWT
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * instancier un gestionnaire d'authentification
     *
     * @return gestionnaire d'authentification
     * @throws Exception qui a causé l'exception
     */
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}