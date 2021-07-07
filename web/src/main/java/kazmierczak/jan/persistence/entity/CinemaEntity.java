package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import kazmierczak.jan.model.cinema.Cinema;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kazmierczak.jan.persistence.entity.AddressEntity.*;
import static kazmierczak.jan.model.cinema.CinemaUtils.*;

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

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
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
                .cinemaRooms(cinemaRooms
                        .stream()
                        .map(CinemaRoomEntity::toCinemaRoom)
                        .toList())
                .build();
    }

    /**
     * @param cinema we want to map
     * @return cinema entity obejct mapped from cinema
     */
    public static CinemaEntity fromCinemaToEntity(Cinema cinema) {
        var cinemaId = cinemaToId.apply(cinema);
        var cinemaName = cinemaToName.apply(cinema);
        var cinemaAddress = cinemaToAddress.apply(cinema);

        return CinemaEntity
                .builder()
                .id(cinemaId)
                .name(cinemaName)
                .address(fromAddressToEntity(cinemaAddress))
                .build();
    }
}
