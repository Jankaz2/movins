package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.address.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static model.address.AddressUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "addresses")
public class AddressEntity extends BaseEntity {
    private String city;
    private String street;
    private Integer number;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    @Builder.Default
    private List<CinemaEntity> cinemas = new ArrayList<>();

    /**
     *
     * @return Address object mapped from AddressEntity
     */
    public Address toAddress() {
        return Address
                .builder()
                .id(id)
                .city(city)
                .street(street)
                .number(number)
                .cinemas(new ArrayList<>())
                .build();
    }

    /**
     *
     * @param address we want to map
     * @return address entity object
     */
    public static AddressEntity fromAddressToEntity(Address address) {
        var addressId = addressToId.apply(address);
        var addressCity = addressToCity.apply(address);
        var addressStreet = addressToStreet.apply(address);
        var addressNumber = addressToNumber.apply(address);

        return AddressEntity
                .builder()
                .id(addressId)
                .city(addressCity)
                .street(addressStreet)
                .number(addressNumber)
                .build();
    }
}
