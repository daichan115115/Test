package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Music;

public interface MusicService {
    Iterable<Music> findAll();
    Optional<Music> findById(Integer id);
    void insertMusic(Music music);
    void updateMusic(Music music);
    void deleteMusic(Integer id);
}
