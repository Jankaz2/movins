package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.cinema.Cinema;
import model.cinema.CinemaUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.persistence.entity.AddressEntity.*;
import static kazmierczak.jan.persistence.entity.CinemaRoomEntity.*;
import static model.cinema.CinemaUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cinemas")
public class CinemaEntity extends BaseEntity {
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "cinema")
    @Builder.Default
    private List<CinemaRoomEntity> cinemaRooms = new ArrayList<>();

    /**
     * @return Cinema object mapped from CinemaEntity
     */
    public Cinema toCinema() {
        return Cinema
                .builder()
                .id(id)
                .name(name)
                .address(address.toAddress())
                .cinemaRooms(new ArrayList<>())
                .build();
    }

    /**
     *
     * @param cinemas list we want to map
     * @return list of cinema entities
     */
    public static List<CinemaEntity> fromCinemasListToEntity(List<Cinema> cinemas) {
        return cinemas
                .stream()
                .map(CinemaEntity::fromCinemaToEntity)
                .toList();
    }

    /**
     *
     * @param cinema we want to map
     * @return cinema entity obejct mapped from cinema
     */
    public static CinemaEntity fromCinemaToEntity(Cinema cinema) {
        var cinemaId = cinemaToId.apply(cinema);
        var cinemaName = cinemaToName.apply(cinema);
        var cinemaAddress = cinemaToAddress.apply(cinema);
        var cinemaRooms = cinemaToCinemaRooms.apply(cinema);

        return CinemaEntity
                .builder()
                .id(cinemaId)
                .name(cinemaName)
                .address(fromAddressToEntity(cinemaAddress))
                .cinemaRooms(fromCinemaRoomsToEntityList(cinemaRooms))
                .build();
    }
}
