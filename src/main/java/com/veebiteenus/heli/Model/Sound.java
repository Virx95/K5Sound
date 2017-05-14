package com.veebiteenus.heli.Model;


import org.springframework.data.annotation.Id;


public class Sound {

    @Id
    private String id;
    private String word;
    private String filePath;

    public Sound() {}

    public Sound(String word) {
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
