package com.cinema.demo.model;


import com.cinema.demo.FilmRequest;
import com.cinema.demo.FilmResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.UUID;

@EnableAutoConfiguration
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Film {

    @Id
    private final UUID filmId;

    @Column(unique = true)
    private final String name;
    private final String director;
    private final Integer year;
    @Enumerated(EnumType.STRING)
    private final Genre genre;

    public Film(String name, String director, Integer year, Genre genre) {
        this.filmId = UUID.randomUUID();
        this.name = name;
        this.director = director;
        this.year = year;
        this.genre = genre;
    }

    public static Film fromFilmRequest(FilmRequest filmRequest) {
        return new Film(filmRequest.getName(),
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
