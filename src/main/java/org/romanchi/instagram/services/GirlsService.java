package org.romanchi.instagram.services;

import org.romanchi.instagram.model.entities.Girl;
import org.romanchi.instagram.repositories.GirlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GirlsService {

    private final GirlsRepository girlsRepository;

    @Autowired
    public GirlsService(GirlsRepository girlsRepository) {
        this.girlsRepository = girlsRepository;
    }

    public void saveGirl(Girl girl){
        girlsRepository.save(girl);
    }

    public List<Girl> getAllLikedGirls(){
        return girlsRepository.findAll();
    }
}
