package model.seance;

import model.cinema_room.CinemaRoom;
import model.movie.Movie;
import model.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public interface SeanceUtils {
    /**
     * map seance object to id of this object
     */
    Function<Seance, Long> toSeanceId = seance -> seance.id;

    /**
     * map seance object to movie of this object
     */
    Function<Seance, Movie> toSeanceMovie = seance -> seance.movie;

    /**
     * map seance object to cinemaRoom of this object
     */
    Function<Seance, CinemaRoom> toSeanceCinemaRooom = seance -> seance.cinemaRoom;

    /**
     * map seance object to date of this object
     */
    Function<Seance, LocalDate> toSeanceDate = seance -> seance.date;

    /**
     * map seance object to tickets of this object
     */
    Function<Seance, List<Ticket>> toSeanceTickets = seance -> seance.tickets;
}
