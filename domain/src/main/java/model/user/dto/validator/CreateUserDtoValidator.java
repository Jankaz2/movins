package model.user.dto.validator;

import config.validator.Validator;
import model.user.dto.CreateUserDto;
import types.Role;

import java.util.HashMap;
import java.util.Map;

public class CreateUserDtoValidator implements Validator<CreateUserDto> {
    @Override
    public Map<String, String> validate(CreateUserDto createUserDto) {
        var errors = new HashMap<String, String>();

        if (createUserDto == null) {
            errors.put("create user dto", "is null");
            return errors;
        }

        var name = createUserDto.getName();
        if (hasIncorrectName(name)) {
            errors.put("name", "is incorrect: " + name);
        }

        var surname = createUserDto.getSurname();
        if (hasIncorrectSurname(surname)) {
            errors.put("surname", "is incorrect: " + surname);
        }

        var age = createUserDto.getAge();
        if (hasIncorrectAge(age)) {
            errors.put("age", "is incorrect: " + age);
        }

        var email = createUserDto.getEmail();
        if (hasIncorrectEmail(email)) {
            errors.put("email", "is incorrect: " + email);
        }

        var password = createUserDto.getPassword();
        if (hasIncorrectPassword(password)) {
            errors.put("password", "is incorrect: " + password);
        }

        var role = createUserDto.getRole();
        if (hasIncorrectRole(role)) {
            errors.put("role", "is incorrect: " + role);
        }

        return errors;
    }

    /**
     * @param name we want to validate
     * @return true if name is null or is too short or syntax is wrong,
     * otherwise return false
     */
    private boolean hasIncorrectName(String name) {
        return name == null
                || name.length() < 3
                || !name.matches("^[A-Z][a-z]+$");
    }

    /**
     * @param surname we want to validate
     * @return true if surname is null or is too short or syntax is wrong,
     * otherwise return false
     */
    private boolean hasIncorrectSurname(String surname) {
        return surname == null
                || surname.length() < 5
                || !surname.matches("^[A-Z][a-z]+$");
    }

    /**
     * @param age we want to valiate
     * @return true if age is null or is less or equal to 0,
     * otherwise return false
     */
    private boolean hasIncorrectAge(Integer age) {
        return age == null || age <= 0;
    }

    /**
     * @param email we want to validate
     * @return true if email is null or does not match syntax,
     * otherwise return false
     */
    private boolean hasIncorrectEmail(String email) {
        return email == null
                || !email.matches("^[A-Za-z0-9.]{5,}@[a-z0-9]{2,}.[a-z]{2,}$");
    }

    /**
     * @param password we want to validate
     * @return true if password length is less than 8,
     * otherwise return false
     */
    private boolean hasIncorrectPassword(String password) {
        return password.length() < 8;
    }

    /**
     * @param role we want to validate
     * @return true if role is null, otherwise return false
     */
    private boolean hasIncorrectRole(Role role) {
        return role == null;
    }
}
