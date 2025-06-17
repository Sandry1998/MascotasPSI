package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Usuario;
import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.AttributedString;


@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/altaUsuario")
    public String altaUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "altaUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario, Model model){
        if(usuarioRepository.findByUsername(usuario.getUsername()).isEmpty()){
            Usuario user = new Usuario();
            user.setUsername(usuario.getUsername());
            user.setPassword(passwordEncoder.encode(usuario.getPassword()));
            user.setRol(usuario.getRol());
            usuarioRepository.save(user);
            return "redirect:/";//->Se guarda el usuario y se vuelve a la home
        }else{
            model.addAttribute("error","Usuario ya existe");
            return "altaUsuario";
        }

    }

}
