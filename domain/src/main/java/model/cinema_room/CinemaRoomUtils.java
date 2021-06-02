package model.cinema_room;

import model.cinema.Cinema;
import model.seance.Seance;
import model.seat.Seat;

import java.util.List;
import java.util.function.Function;

public interface CinemaRoomUtils {
    /**
     * map CinemaRoom object to id of this object
     */
    Function<CinemaRoom, Long> toCinemaRoomId = cinemaRoom -> cinemaRoom.id;

    /**
     * map CinemaRoom object to name of this object
     */
    Function<CinemaRoom, String> toCinemaRoomName = cinemaRoom -> cinemaRoom.name;

    /**
     * map CinemaRoom object to rows of this object
     */
    Function<CinemaRoom, Integer> toCinemaRoomRows = cinemaRoom -> cinemaRoom.rows;

    /**
     * map CinemaRoom object to places of this object
     */
    Function<CinemaRoom, Integer> toCinemaRoomPlaces = cinemaRoom -> cinemaRoom.places;

    /**
     * map CinemaRoom object to cinema of this object
     */
    Function<CinemaRoom, Cinema> toCinemaRoomCinema = cinemaRoom -> cinemaRoom.cinema;

    /**
     * map CinemaRoom object to seats of this object
     */
    Function<CinemaRoom, List<Seat>> toCinemaRoomSeats = cinemaRoom -> cinemaRoom.seats;


    /**
     * map CinemaRoom object to seats of this object
     */
    Function<CinemaRoom, List<Seance>> toCinemaRoomSeances = cinemaRoom -> cinemaRoom.seances;
}
