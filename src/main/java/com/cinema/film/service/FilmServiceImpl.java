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
        List<Film> films = filmRepository.findAll();
        Film foundFilm = new Film();
        for (Film film : films) {
            if (film.getName().equals(name)) {
                foundFilm = film;
                break;
            }
        }
        return foundFilm;
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
        restTemplate.delete("http://cinema-seances:8082/seance/delete-by-film/" + id.toString());
        filmRepository.deleteById(id);
    }
}
