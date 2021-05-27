package model.cinema.dto.validator;

import config.validator.Validator;
import model.address.dto.CreateAddressDto;
import model.address.dto.validator.CreateAddressDtoValidator;
import model.cinema.dto.CreateCinemaDto;

import java.util.HashMap;
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
        hasIncorrectAddress(address);

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
     */
    private void hasIncorrectAddress(CreateAddressDto address) {
        Validator.validate(new CreateAddressDtoValidator(), address);
    }
}
