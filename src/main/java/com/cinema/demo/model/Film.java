package com.cinema.demo.model;


import com.cinema.demo.FilmRequest;
import com.cinema.demo.FilmResponse;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.UUID;

@EnableAutoConfiguration
@Entity
@Data
public final class Film {

    @Id
    private final UUID filmId;

    @Column(unique = true)
    private String name;
    private String director;
    private Integer year;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Film() {
        this.filmId = UUID.randomUUID();
    }

    public Film(UUID filmId, String name, String director, Integer year, Genre genre) {
        this.filmId = filmId;
        this.name = name;
        this.director = director;
        this.year = year;
        this.genre = genre;
    }

    public static Film fromFilmRequest(FilmRequest filmRequest) {
        return new Film(UUID.randomUUID(),
                filmRequest.getName(),
                filmRequest.getDirector(),
                filmRequest.getYear(),
                Genre.valueOf(filmRequest.getGenre().toString()));
    }

    public FilmResponse toFilmResponse() {
        return FilmResponse.newBuilder().
                setId(filmId.toString()).
                setName(name).
                setDirector(director).
                setYear(year).
                setGenre(com.cinema.demo.Genre.valueOf(genre.name())).
                build();
    }
}
