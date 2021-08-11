package kazmierczak.jan.model.seat.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;

import java.util.HashMap;
import java.util.Map;

public class CreateSeatDtoValidator implements Validator<CreateSeatDto> {
    @Override
    public Map<String, String> validate(CreateSeatDto createSeatDto) {
        var errors = new HashMap<String, String>();

        if (createSeatDto == null) {
            errors.put("createSeatDto", "is null");
            return errors;
        }

        var row = createSeatDto.getRow();
        if (hasIncorrectRow(row)) {
            errors.put("row", "is incorrect: " + row);
        }

        var place = createSeatDto.getPlace();
        if (hasIncorrectPlace(place)) {
            errors.put("place", "is incorrect: " + place);
        }

        var cinemaRoomId = createSeatDto.getCinemaRoomId();
        if(hasIncorrectCinemaRoomId(cinemaRoomId)){
            errors.put("cinemaRoomId", "is incorrect: " + cinemaRoomId);
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

    /**
     *
     * @param id of cinema room
     * @return true if id is null, otherwise return false
     */
    private boolean hasIncorrectCinemaRoomId(Long id) {
        return id == null;
    }
}
