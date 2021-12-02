package com.example.exercicemongo.produits;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Produit, String> {
    Produit findByDescription(String desc);
}
