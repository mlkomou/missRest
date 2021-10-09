package com.miss.api.service;

import com.miss.api.repos.CompteurRepository;
import org.springframework.stereotype.Service;

@Service
public class CompteurService {
    final CompteurRepository compteurRepository;

    public CompteurService(CompteurRepository compteurRepository) {
        this.compteurRepository = compteurRepository;
    }

    public Integer getNumberMax() {
        Integer defaultCompteur = 1;
        Integer compteurToSave;
        Integer nomberMax = compteurRepository.findMaxNumber();
        if(nomberMax != null) {
            System.out.println("tag != null");
            compteurToSave = nomberMax + 1;
            System.out.println("compteurToSave " + compteurToSave);
            return  compteurToSave;
        } else {
            compteurToSave = defaultCompteur;
            return  compteurToSave;
        }

    }
}
