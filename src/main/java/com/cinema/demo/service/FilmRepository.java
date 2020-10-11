package com.cinema.demo.service;
import com.cinema.demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {

}
