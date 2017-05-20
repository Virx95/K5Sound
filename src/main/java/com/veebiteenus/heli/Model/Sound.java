package com.veebiteenus.heli.Model;


import org.springframework.data.annotation.Id;


public class Sound {

    @Id
    private String id;
    private String word;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
