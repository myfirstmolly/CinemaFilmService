package com.cinema.film.rabbitmq;

import com.cinema.film.model.Film;
import com.cinema.film.service.FilmService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    FilmService filmsService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consume(Film film) {
        filmsService.addFilm(film);
    }
}
