package com.example.exercicemongo.produits;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/produit")
public class Service {
    private final Repository _p;

    public Service(Repository repository) {
        this._p = repository;
    }

    @GetMapping
    public Collection<Produit> get() {
        return _p.findAll();
    }

    @GetMapping("/{description}")
    public Produit getByDescription(@PathVariable String description ) {
        return _p.findByDescription(description);
    }

    @GetMapping("/count")
    public long countProduits() {
        return _p.count();
    }

    @PostMapping("/{description}/{prix}")
    public Produit add(@PathVariable String description, @PathVariable double prix) {
        if(description.isEmpty()) return null;
        return _p.insert(new Produit(null, description, prix));
    }

    @DeleteMapping
    public void remove(@RequestBody Produit p) {
        _p.delete(p);
    }

}