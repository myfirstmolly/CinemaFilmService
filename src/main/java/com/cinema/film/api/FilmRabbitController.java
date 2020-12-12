package com.cinema.film.api;

import com.cinema.film.model.Film;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/film/rabbit")
public class FilmRabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String createFilm(@RequestBody Film film) {
        rabbitTemplate.convertAndSend("exchange", "filmkey", film);
        return "Sent successfully";
    }
}
