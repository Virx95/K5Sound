package com.veebiteenus.heli.controller;

import com.mongodb.*;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

import com.veebiteenus.heli.Model.Sound;
import com.veebiteenus.heli.Model.SoundRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class SoundController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "\\uploads\\";

    private SoundRepository soundRepository;

    public SoundController(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    @RequestMapping(value = "/sound/{word}", method = RequestMethod.GET)
    public Sound getSound(@PathVariable("word") String word) {
        return soundRepository.findByWord(word);
    }

    @RequestMapping(value = "/sound", method = RequestMethod.POST)
    public Sound saveNewSound(@RequestParam("word") String word,
                              @RequestParam("file") MultipartFile file) throws IOException {
        String filepath = UPLOAD_DIR + word + "." + file.getOriginalFilename().split("\\.")[1];

        File writable = new File(filepath);
        file.transferTo(writable);
        writable.createNewFile();
        Sound sound = new Sound(word);
        sound.setFilePath(word);
        sound.setFilePath(filepath);

        return soundRepository.save(sound);
    }

}
