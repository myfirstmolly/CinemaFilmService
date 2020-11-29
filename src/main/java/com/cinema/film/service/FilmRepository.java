package com.cinema.film.service;

import com.cinema.film.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {
    Film findByName(String name);
}
