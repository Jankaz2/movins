package model.cinema.dto.validator;

import config.validator.Validator;
import model.adress.Address;
import model.cinema_room.CinemaRoom;
import model.cinema.dto.CreateCinemaDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateCinemaDtoValidator implements Validator<CreateCinemaDto> {
    @Override
    public Map<String, String> validate(CreateCinemaDto createCinemaDto) {
        var errors = new HashMap<String, String>();

        if (createCinemaDto == null) {
            errors.put("createCinemaDto", "is null");
            return errors;
        }

        var name = createCinemaDto.getName();
        if (hasIncorrectName(name)) {
            errors.put("name", "is incorrect: " + name);
        }

        var address = createCinemaDto.getAddress();
        if (hasIncorrectAddress(address)) {
            errors.put("address", "is incorrect: " + address);
        }

        var cinemaRooms = createCinemaDto.getCinemaRooms();
        if (hasIncorrectCinemaRoomsList(cinemaRooms)) {
            errors.put("cinema rooms", "are incorrect: " + cinemaRooms);
        }
        return errors;
    }

    /**
     * @param name we want to validate
     * @return true if name is incorrect, otherwise return false
     */
    private boolean hasIncorrectName(String name) {
        return name.matches(".*[0-9].*") || name.length() < 4;
    }

    /**
     * @param address we want to validate
     * @return true if address is null, otherwise return false
     */
    private boolean hasIncorrectAddress(Address address) {
        return address == null;
    }

    /**
     * @param cinemaRoomList we want to validate
     * @return true if cinemaRoomList is null or empty, otherwise return false
     */
    private boolean hasIncorrectCinemaRoomsList(List<CinemaRoom> cinemaRoomList) {
        return cinemaRoomList == null || cinemaRoomList.isEmpty();
    }
}
