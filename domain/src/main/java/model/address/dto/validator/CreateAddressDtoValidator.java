package model.address.dto.validator;

import config.validator.Validator;
import model.address.dto.CreateAddressDto;

import java.util.HashMap;
import java.util.Map;

public class CreateAddressDtoValidator implements Validator<CreateAddressDto> {

    @Override
    public Map<String, String> validate(CreateAddressDto createAddressDto) {
        var errors = new HashMap<String, String>();

        if (createAddressDto == null) {
            errors.put("createAddressDto", "is null");
            return errors;
        }

        var city = createAddressDto.getCity();
        if (hasIncorrectCityOrStreet(city)) {
            errors.put("city", "is incorrect: " + city);
        }

        var street = createAddressDto.getStreet();
        if (hasIncorrectCityOrStreet(street)) {
            errors.put("street", "is incorrect: " + street);
        }

        var number = createAddressDto.getNumber();
        if (hasIncorrectNumber(number)) {
            errors.put("number", "is incorrect: " + number);
        }

        return errors;
    }

    /**
     *
     * @param cityOrStreet city or street we want to validate
     * @return true if object is null or his length is less than 3
     * or matches regex
     */
    private boolean hasIncorrectCityOrStreet(String cityOrStreet) {
        return cityOrStreet == null
                || cityOrStreet.length() < 3
                || !cityOrStreet.matches("^[A-Z][a-z]+$");
    }

    /**
     * @param number we want to validate
     * @return true if number is null or numebr is less than 1
     */
    private boolean hasIncorrectNumber(Integer number) {
        return number == null
                || number < 1;
    }
}
