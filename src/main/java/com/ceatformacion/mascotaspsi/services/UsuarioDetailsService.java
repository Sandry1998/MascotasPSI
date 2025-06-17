package com.ceatformacion.mascotaspsi.services;


import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
Declara un servicio que le indica a Spring que pueda utilizar Spring Security con los datos guardados en nuestra BBDD
 */

@Service
public class UsuarioDetailsService implements UserDetailsService {

    UsuarioRepository usuarioRepository;

    //Inyectamos el constructor

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Metodo que se ejecuta automáticamente cuando alguien intenta iniciar sesión con un nombre de usuario
    //Spring llama este metodo para obtener los datos de usuario

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //buscando el usuario en la bbdd y si no lo encuentra, enviar un mensaje de error
        return usuarioRepository.findByUsername(username).map(UsuarioDetails::new).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
