package com.cinema.demo.service;

import com.cinema.demo.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public final class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public Film addFilm(Film film) {
        Film savedFilm = filmRepository.save(film);
        return savedFilm;
    }

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film getById(UUID id) {
        return filmRepository.getOne(id);
    }

    @Override
    public void deleteById(UUID id) {
        filmRepository.deleteById(id);
    }

}
