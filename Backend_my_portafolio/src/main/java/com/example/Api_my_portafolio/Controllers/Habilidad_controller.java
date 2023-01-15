package com.example.Api_my_portafolio.Controllers;

import com.example.Api_my_portafolio.Models.Habilidad;
import com.example.Api_my_portafolio.Models.Persona;
import com.example.Api_my_portafolio.Repositories.Habilidad_repository;
import com.example.Api_my_portafolio.Repositories.Persona_repository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habilidad")
@CrossOrigin(origins = "http://localhost:4200")
public class Habilidad_controller {
    @Autowired
    private Habilidad_repository habilidad_repository;
    @Autowired
    private Persona_repository persona_repository;
    
    @GetMapping("/all/tipo/{tipo}")
    public ResponseEntity<List<Habilidad>> Get_habilidades_tipo(@PathVariable("tipo") String tipo){
        return ResponseEntity.ok(habilidad_repository.Get_habilidades_tipo(tipo));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Habilidad>> Get_habilidades(){
        return ResponseEntity.ok(habilidad_repository.findAll());
    }
    
    @PutMapping("/edit/id/{id_habilidad}")
    public ResponseEntity<Habilidad> Edit_habilidad(@PathVariable("id_habilidad") int id_habilidad, @Valid @RequestBody Habilidad habilidad){
        Optional<Persona> persona_optional = persona_repository.findById(1);
        
        if(!persona_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<Habilidad> habilidad_optional = habilidad_repository.findById(id_habilidad);
        if(!habilidad_optional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        habilidad.setPersona(persona_optional.get());
        habilidad.setId_habilidad(habilidad_optional.get().getId_habilidad());
        habilidad_repository.save(habilidad);
        
        return ResponseEntity.noContent().build();
    }
}