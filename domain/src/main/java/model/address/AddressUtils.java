package model.address;

import model.cinema.Cinema;

import java.util.List;
import java.util.function.Function;

public interface AddressUtils {
    /**
     * map address object to id of this object
     */
    Function<Address, Long> addressToId = address -> address.id;

    /**
     * map address object to city of this object
     */
    Function<Address, String> addressToCity = address -> address.city;

    /**
     * map address object to street of this object
     */
    Function<Address, String> addressToStreet = address -> address.street;

    /**
     * map address object to number of this object
     */
    Function<Address, Integer> addressToNumber = address -> address.number;

    /**
     * map address object to cinema list of this object
     */
    Function<Address, List<Cinema>> addressToCinemas = address -> address.cinemas;
}
