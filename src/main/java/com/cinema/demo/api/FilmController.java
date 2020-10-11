package com.cinema.demo.api;

import com.cinema.demo.model.Film;
import com.cinema.demo.service.FilmService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/film")
@AllArgsConstructor
@NoArgsConstructor
public class FilmController {

    @Autowired
    FilmService filmsService;

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        return filmsService.addFilm(film);
    }

    @GetMapping
    public List<Film> getAll() {
        return filmsService.getAll();
    }

    @GetMapping("{filmId}")
    public Film getById(@PathVariable(value = "filmId") UUID id) {
        return filmsService.getById(id);
    }

    @DeleteMapping("{filmId}")
    public ResponseEntity<Void> deleteFilm(@PathVariable(value = "filmId") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8082/seance/delete-by-film/" + id.toString());

        filmsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
