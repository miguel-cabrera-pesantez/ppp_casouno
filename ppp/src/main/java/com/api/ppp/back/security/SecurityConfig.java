package com.api.ppp.back.security;

import com.api.ppp.back.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.cors().disable()
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/**", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class).authorizeHttpRequests()
                .requestMatchers("/accion/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/accionConvoca/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/anexos/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/actividad/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/aspecto/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/aspectoPractica/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/calificacion/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                // .requestMatchers("/carrera/**").hasAnyRole("ESTUD", "TEMP",
                // "TISTA","GEREN","RESPP","DIREC","CORDI","RECT")
                .requestMatchers("/convenio/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/convocatoria/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/empresa/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/estudiante/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/materia/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/practica/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/objetivoMateria/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/resultado/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/resultadoMateria/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/semanaActividad/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/solicitudEmpresa/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/solicitudEstudiante/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/sucursal/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/tutorEmpresa/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/tutorInstituto/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/usuario/**")
                .hasAnyRole("ADMIN", "ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/visitaActividad/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/visita/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/notificacion/**")
                .hasAnyRole("ESTUD", "TEMP", "TISTA", "GEREN", "RESPP", "DIREC", "CORDI", "RECT", "ADMIN")
                .requestMatchers("/ingresar").authenticated()
                // Letting Access of fenix to ALL by the moment
                .requestMatchers("/register", "/usuariofenix/**", "/fenix/**", "/carrera/**", "/noticias").permitAll()
                .and().formLogin().and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
