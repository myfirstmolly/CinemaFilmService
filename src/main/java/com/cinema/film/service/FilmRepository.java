package com.cinema.film.service;

import com.cinema.film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID> {
    Film findByName(String name);
    Film findByDirector(String director);
}
