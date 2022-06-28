package com.example.bsep.controller;

import com.example.bsep.model.Csr;
import com.example.bsep.service.CsrService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "csr")
public class CsrController {

    @Autowired
    private CsrService service;

    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody Csr csr) throws Exception {
        return new ResponseEntity<>(service.add(csr).getCsr(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Csr>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Csr> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping("confirm/{id}")
    public ResponseEntity<Boolean> confirm(@PathVariable Long id){
        return new ResponseEntity<>(service.verify(id), HttpStatus.OK);
    }

}
