package com.API.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Modales
{
    @GetMapping("/hello")
    public ResponseEntity<String> Saludar()
    {
        return ResponseEntity.ok("Hola mundo! soy Gustavo");
    }
    @PostMapping
    public ResponseEntity<String> Despedirse()
    {
        return ResponseEntity.ok("Hasta pronto");
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> Eliminar()
    {
        return ResponseEntity.ok("eliminando...");
    }

  //  @PutMapping("/modificar}" modifica un valor en la base de datos
}


