package kazmierczak.jan.model.cinema;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema_room.CinemaRoom;

import java.util.List;
import java.util.function.Function;

public interface CinemaUtils {
    /**
     * map cinema to Id of this cinema
     */
    Function<Cinema, Long> cinemaToId = cinema -> cinema.id;

    /**
     * map cinema to name of this cinema
     */
    Function<Cinema, String> cinemaToName = cinema -> cinema.name;

    /**
     * map cinema to address of this cinema
     */
    Function<Cinema, Address> cinemaToAddress = cinema -> cinema.address;

    /**
     * map cinema to cinema rooms of this cinema
     */
    Function<Cinema, List<CinemaRoom>> cinemaToCinemaRooms = cinema -> cinema.cinemaRooms;
}
