package com.cinema.film.service;

import com.cinema.film.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public Film getByName(String name) {
        return filmRepository.findByName(name);
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
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://cinema-seances/seance/delete-by-film/" + id.toString());
        filmRepository.deleteById(id);
    }
}
