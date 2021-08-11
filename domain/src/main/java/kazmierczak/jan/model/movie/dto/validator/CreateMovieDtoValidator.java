package kazmierczak.jan.model.movie.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreateMovieDtoValidator implements Validator<CreateMovieDto> {
    @Override
    public Map<String, String> validate(CreateMovieDto createMovieDto) {
        var errors = new HashMap<String, String>();

        if (createMovieDto == null) {
            errors.put("createMovieDto", "is null");
            return errors;
        }

        var title = createMovieDto.getTitle();
        if (hasIncorrectTitle(title)) {
            errors.put("title", "is incorrect: " + title);
        }

        var genre = createMovieDto.getGenre();
        if (hasIncorrectGenre(genre)) {
            errors.put("genre", "is incorrect: " + genre);
        }

        var duration = createMovieDto.getDuration();
        if (hasIncorrectDuration(duration)) {
            errors.put("duration", "is incorrect: " + duration);
        }

        var releaseDate = createMovieDto.getReleaseDate();
        if (hasIncorretReleaseDate(releaseDate)) {
            errors.put("releaseDate", "is incorrect: " + releaseDate);
        }

        return errors;
    }

    /**
     * @param title we want to check
     * @return true if title is null or title's length is less than 3
     */
    private boolean hasIncorrectTitle(String title) {
        return title == null || title.length() < 2;
    }

    /**
     * @param genre we want to check
     * @return true if genre is null or genre's length is less than 3
     */
    private boolean hasIncorrectGenre(String genre) {
        return genre == null || genre.length() < 3;
    }

    /**
     * @param duration we want to check
     * @return true if duration is null or duration is less than 1
     */
    private boolean hasIncorrectDuration(Integer duration) {
        return duration == null || duration < 1;
    }

    /**
     * @param releaseDate we want to check
     * @return true if releaseDate is null
     */
    private boolean hasIncorretReleaseDate(LocalDate releaseDate) {
        return releaseDate == null;
    }
}
