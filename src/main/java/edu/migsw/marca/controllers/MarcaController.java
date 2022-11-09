package edu.migsw.marca.controllers;

import edu.migsw.marca.entities.MarcaEntity;
import edu.migsw.marca.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    
    @Autowired
    MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaEntity>> getAll(){
        List<MarcaEntity> marcas = marcaService.getAll();
        if(marcas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaEntity> getMarcaById(@PathVariable("id") Long id){
        MarcaEntity marca = marcaService.getMarcaById(id).get();
        if(marca == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @PostMapping()
    public ResponseEntity<MarcaEntity> save(@RequestBody MarcaEntity marca){
        MarcaEntity marcaGuardada = marcaService.save(marca);
        return ResponseEntity.ok(marcaGuardada);
    }

    @GetMapping("/byrut/{rut}")
    public ResponseEntity<List<MarcaEntity>> getByRut(@PathVariable("rut") String rut){
        List<MarcaEntity> marcas = marcaService.getByRut(rut);
        if(marcas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }
}
