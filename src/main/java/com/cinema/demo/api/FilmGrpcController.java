package com.cinema.demo.api;

import com.cinema.demo.*;
import com.cinema.demo.model.Film;
import com.cinema.demo.service.FilmService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class FilmGrpcController extends FilmServiceGrpc.FilmServiceImplBase {

    @Autowired
    private FilmService filmService;

    @Override
    public void all(AllFilmsRequest request, StreamObserver<AllFilmsResponse> responseObserver) {
        List<Film> films = filmService.getAll();
        List<FilmResponse> convertedFilms = films.stream().
                map(Film::toFilmResponse).
                collect(Collectors.toList());
        AllFilmsResponse response = AllFilmsResponse.newBuilder().
                addAllFilms(convertedFilms).
                build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(FilmRequest request, StreamObserver<FilmResponse> responseObserver) {
        Film film = filmService.addFilm(Film.fromFilmRequest(request));
        responseObserver.onNext(film.toFilmResponse());
        responseObserver.onCompleted();
    }

    @Override
    public void byId(FilmByIdRequest request, StreamObserver<FilmResponse> responseObserver) {
        Film film = filmService.getById(UUID.fromString(request.getId()));
        responseObserver.onNext(film.toFilmResponse());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(FilmByIdRequest request, StreamObserver<DeleteFilmResponse> responseObserver) {
        filmService.deleteById(UUID.fromString(request.getId()));
        responseObserver.onNext(DeleteFilmResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
