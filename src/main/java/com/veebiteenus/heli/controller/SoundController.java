package com.veebiteenus.heli.controller;


import com.veebiteenus.heli.Model.Sound;
import com.veebiteenus.heli.Model.SoundRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class SoundController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

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
