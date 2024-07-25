package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicService;

@Controller
public class MusicController {
    @Autowired
    private MusicService service;

    @GetMapping("index")
    public String indexView() {
        return "index";
    }

    @PostMapping(value="menu", params="select")
    public String listView(Model model) {
        Iterable<Music> list = service.findAll();
        model.addAttribute("list", list);
        return "list";
    }

    @PostMapping(value="menu", params="insert")
    public String musicInputView() {
        return "music-input";
    }

    @PostMapping("insert")
    public String musicConfirmView(MusicForm form) {
        Music music = new Music(form.getSong_id(), form.getSong_name(), form.getSinger());
        service.insertMusic(music);
        return "music-complete";
    }

    @PostMapping(value="menu", params="update")
    public String updateInputView() {
        return "music-update-input";
    }

    @PostMapping("update")
    public String updateView(@RequestParam("song_id") Integer song_id, Model model) {
        Music music = service.findById(song_id).orElse(null);
        model.addAttribute("music", music);
        return "music-update";
    }

    @PostMapping("update/confirm")
    public String updateConfirmView(MusicForm form) {
        Music music = new Music(form.getSong_id(), form.getSong_name(), form.getSinger());
        service.updateMusic(music);
        return "music-complete";
    }

    @PostMapping(value="menu", params="delete")
    public String deleteInputView() {
        return "music-delete-input";
    }

    @PostMapping("delete")
    public String deleteView(@RequestParam("song_id") Integer song_id, Model model) {
        service.deleteMusic(song_id);
        return "music-delete-complete";
    }
}
