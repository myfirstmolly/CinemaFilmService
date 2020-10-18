package com.cinema.demo.service;

import com.cinema.demo.model.Film;

import java.util.List;
import java.util.UUID;

public interface FilmService {
    Film addFilm(Film film);
    List<Film> getAll();
    Film getById(UUID id);
    void deleteById(UUID id);
}
