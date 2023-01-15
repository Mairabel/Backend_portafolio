package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Usuario;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Persona_repository;
import com.example.Api_my_portafolio.Repositories.Usuario_repository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class Usuario_controller {
    @Autowired
    private Usuario_repository usuario_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/autenticar/{nombre}/{clave}")
    public boolean Add_autenticar(@PathVariable("nombre") String nombre, @PathVariable("clave") String clave){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        Usuario user = persona_optional.get().getUsuario();
        return user.getNombre().equals(nombre) && user.getClave().equals(clave);
    }
}