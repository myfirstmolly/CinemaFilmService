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
    public void all(AllRequest request, StreamObserver<AllResponse> responseObserver) {
        List<Film> films = filmService.getAll();
        List<FilmResponse> convertedFilms = films.stream().
                map(Film::toFilmResponse).
                collect(Collectors.toList());
        AllResponse response = AllResponse.newBuilder().
                addAllFilms(convertedFilms).
                build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(FilmRequest request, StreamObserver<FilmResponse> responseObserver) {
        filmService.addFilm(Film.fromFilmRequest(request));
        responseObserver.onNext(FilmResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void byId(FilmByIdRequest request, StreamObserver<FilmResponse> responseObserver) {
        Film film = filmService.getById(UUID.fromString(request.getId()));
        responseObserver.onNext(film.toFilmResponse());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(FilmByIdRequest request, StreamObserver<DeleteResponse> responseObserver) {
        filmService.deleteById(UUID.fromString(request.getId()));
        responseObserver.onNext(DeleteResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
