package com.veebiteenus.heli.controller;


import com.veebiteenus.heli.Model.Sound;
import com.veebiteenus.heli.Model.SoundRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class SoundController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    private SoundRepository soundRepository;

    public SoundController(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    @RequestMapping(value = "/sound/{word}", method = RequestMethod.GET)
    public Sound getSound(@PathVariable("word") String word) {
        return soundRepository.findByWord(word);
    }

    @RequestMapping(value = "/sound", method = RequestMethod.GET)
    public List<Sound> getAllSounds() {
        return soundRepository.findAll();
    }

    @RequestMapping(value = "/sound", method = RequestMethod.POST)
    public Sound saveNewSound(@RequestParam("word") String word,
                              @RequestParam("file") MultipartFile file) throws IOException {
        String filepath = UPLOAD_DIR + word + "." + file.getOriginalFilename().split("\\.")[1];
        File writable = new File(filepath);
        file.transferTo(writable);
        writable.createNewFile();
        Sound sound = new Sound(word);
        sound.setUrl("/uploads/" + file.getOriginalFilename());
        return soundRepository.save(sound);
    }

    @RequestMapping(value = "/search/{word}", method = RequestMethod.GET)
    public List<Sound> search(@PathVariable("word") String word) {
        return soundRepository.findAll().stream()
                .filter(s -> filter(s.getWord(), word))
                .limit(5)
                .collect(Collectors.toList());
    }

    public boolean filter(String str1, String str2) {
        if (str2.length() > str1.length()) {
            return false;
        } else {
            return str1.toLowerCase().substring(0, str2.length()).equals(str2.toLowerCase());
        }
    }

}
