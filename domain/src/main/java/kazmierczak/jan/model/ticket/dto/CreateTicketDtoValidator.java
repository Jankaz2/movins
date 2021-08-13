package kazmierczak.jan.model.ticket.dto;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import kazmierczak.jan.model.seat.dto.validator.CreateSeatDtoValidator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreateTicketDtoValidator implements Validator<CreateTicketDto> {

    @Override
    public Map<String, String> validate(CreateTicketDto createTicketDto) {
        var errors = new HashMap<String, String>();

        if (createTicketDto == null) {
            errors.put("createTicketDto", "is null");
            return errors;
        }

        var userId = createTicketDto.getUserId();
        if (hasIncorrectUser(userId)) {
            errors.put("userId", "is incorrect: " + userId);
        }

        var seanceId = createTicketDto.getSeanceId();
        if (hasIncorrectSeance(seanceId)) {
            errors.put("seanceId", "is incorrect: " + seanceId);
        }

        var seatDto = createTicketDto.getSeat();
        hasIncorrectSeat(seatDto);

        var price = createTicketDto.getPrice();
        if (hasIncorrectPrice(price)) {
            errors.put("price", "is incorrect: " + price);
        }

        return errors;
    }

    /**
     * @param seanceId we want to validate
     * @return true if seanceId is null, otherwise return false
     */
    private boolean hasIncorrectSeance(Long seanceId) {
        return seanceId == null;
    }

    /**
     * @param userId we want to validate
     * @return true if userId is null, otherwise return false
     */
    private boolean hasIncorrectUser(Long userId) {
        return userId == null;
    }

    /**
     * @param createSeatDto we want to validate
     */
    private void hasIncorrectSeat(CreateSeatDto createSeatDto) {
        Validator.validate(new CreateSeatDtoValidator(), createSeatDto);
    }

    /**
     * @param price we want to validate
     * @return true if price is incorrect
     */
    private boolean hasIncorrectPrice(double price) {
        return price < 0;
    }

    /**
     * @param localDate we want to validate
     * @return true if date is null, otherwise return false
     */
    private boolean hasIncorrectPurchaseDate(LocalDate localDate) {
        return localDate == null;
    }
}
