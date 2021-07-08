package kazmierczak.jan.model.cinema_room.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.HashMap;
import java.util.Map;

public class CreateCinemaRoomDtoValidator implements Validator<CreateCinemaRoomDto> {
    @Override
    public Map<String, String> validate(CreateCinemaRoomDto createCinemaRoomDto) {
        var errors = new HashMap<String, String>();

        if (createCinemaRoomDto == null) {
            errors.put("CreateCinemaRoomDto", "is null");
            return errors;
        }

        var name = createCinemaRoomDto.getName();
        if(hasIncorrectName(name)){
            errors.put("name", "is incorrect");
        }

        var rows = createCinemaRoomDto.getRows();
        if(hasIncorrectRows(rows)){
            errors.put("rows", "number is less than 0");
        }

        var places = createCinemaRoomDto.getPlaces();
        if(hasIncorrectPlaces(places)){
            errors.put("places", "number is less than 0");
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
     *
     * @param rows we want to validate
     * @return true if rows number is less than 0, otherwise return false
     */
    private boolean hasIncorrectRows(int rows) {
        return rows < 1;
    }

    /**
     *
     * @param places we want to validate
     * @return true if places number is less than 0, otherwise return false
     */
    private boolean hasIncorrectPlaces(int places) {
        return places < 1;
    }
}
