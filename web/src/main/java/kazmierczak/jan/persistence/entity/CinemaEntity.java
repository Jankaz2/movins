package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.cinema.Cinema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
     *
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
}
