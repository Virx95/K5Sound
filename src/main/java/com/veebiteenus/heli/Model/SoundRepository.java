package com.veebiteenus.heli.Model;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface SoundRepository extends MongoRepository<Sound, Long> {

    Sound findByWord(String word);

}
