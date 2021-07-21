package kazmierczak.jan.model.seance.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.movie.dto.validator.CreateMovieDtoValidator;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreateSeanceDtoValidator implements Validator<CreateSeanceDto> {
    @Override
    public Map<String, String> validate(CreateSeanceDto createSeanceDto) {
        var errors = new HashMap<String, String>();

        if(createSeanceDto == null) {
            errors.put("createSeanceDto", "is null");
            return errors;
        }

        var date = createSeanceDto.getDate();
        if(hasIncorrectDate(date)){
            errors.put("date", "is incorrect: " + date);
        }

        /*var movie = createSeanceDto.getMovie();
        hasIncorrectMovieDto(movie);*/

        return errors;
    }

    /**
     *
     * @param localDate we want to check
     * @return true if localDate is null or is before today
     */
    private boolean hasIncorrectDate(LocalDate localDate) {
        return localDate == null || localDate.isBefore(LocalDate.now());
    }

    private void hasIncorrectMovieDto(CreateMovieDto createMovieDto){
        Validator.validate(new CreateMovieDtoValidator(), createMovieDto);
    }
}
