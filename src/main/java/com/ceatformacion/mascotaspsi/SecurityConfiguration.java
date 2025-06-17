package com.ceatformacion.mascotaspsi;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //configurar las paginas que según rol mostrara o negará
        http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.GET,"/",
                "/index","/images/**").permitAll()
                //acceso al crud, solo el admin crea, borra, actualiza y el user y admin pueden ver
                .requestMatchers(HttpMethod.GET,"/crud").hasAnyRole("Admin","User")
                .requestMatchers(HttpMethod.POST,"/crud").hasRole("Admin")

                //Formulario de gestión de usuarios: solo el Admin
                //get muestro la página y con el post leo los datos
                .requestMatchers(HttpMethod.GET,"/altaUsuario","/formulario").hasRole("Admin")
                .requestMatchers(HttpMethod.POST,"/guardarUsuario").hasRole("Admin")
                .requestMatchers("/editar/**","/borrar/**").hasRole("Admin")

                //Cualquier otra ruta, necesita autenticación
                .anyRequest().authenticated()
        ).formLogin(form->form.loginPage("/login").loginProcessingUrl("/login")
                .defaultSuccessUrl("/crud",true)
                .permitAll()
        ).logout(LogoutConfigurer::permitAll);

        return http.build();

    }

    //encripta la contraseña y lee las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Expone un objeto de Spring usar internamente para autenticar usuarios, y lo hace accesible para el programador lo pueda usar también...
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();

    }



}
