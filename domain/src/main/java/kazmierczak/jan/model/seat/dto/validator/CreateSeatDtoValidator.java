package kazmierczak.jan.model.seat.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import kazmierczak.jan.model.cinema_room.dto.validator.CreateCinemaRoomDtoValidator;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;

import java.util.HashMap;
import java.util.Map;

public class CreateSeatDtoValidator implements Validator<CreateSeatDto> {
    @Override
    public Map<String, String> validate(CreateSeatDto createSeatDto) {
        var errors = new HashMap<String, String>();

        if (createSeatDto == null) {
            errors.put("createSeatDto", "is null");
        }

        var row = createSeatDto.getRow();
        if (hasIncorrectRow(row)) {
            errors.put("row", "is incorrect");
        }

        var place = createSeatDto.getPlace();
        if (hasIncorrectPlace(place)) {
            errors.put("place", "is incorrect");
        }

        return errors;
    }

    /**
     * @param row we want to validate
     * @return true if row is less than 1
     */
    private boolean hasIncorrectRow(int row) {
        return row < 1;
    }

    /**
     * @param place we want to validate
     * @return true if place is less than 1
     */
    private boolean hasIncorrectPlace(int place) {
        return place < 1;
    }
}
