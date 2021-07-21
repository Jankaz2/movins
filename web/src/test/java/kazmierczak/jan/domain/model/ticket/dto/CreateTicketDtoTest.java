package kazmierczak.jan.domain.model.ticket.dto;

import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateTicketDtoTest {

    @Test
    @DisplayName("when toTicket method works correct")
    public void test1() {
        var creatreMovieDto = CreateMovieDto
                .builder()
                .title("Title")
                .genre("Genre")
                .duration(1)
                .releaseDate(LocalDate.of(2021, 12, 12))
                .build();

        var createSeanceDto = CreateSeanceDto
                .builder()
                .date(LocalDate.of(2021, 12, 12))
                .build();

        var seance = Seance
                .builder()
                .movie(creatreMovieDto.toMovie())
                .date(LocalDate.of(2021, 12, 12))
                .build();

        var user = User
                .builder()
                .username("name")
                .age(18)
                .email("email@email.pl")
                .build();

        var userDto = CreateUserDto
                .builder()
                .username("name")
                .age(18)
                .email("email@email.pl")
                .build();

        var createTicketDto = CreateTicketDto
                .builder()
                .seance(createSeanceDto)
                .user(userDto)
                .build();

        var ticket = Ticket
                .builder()
                .seance(seance)
                .user(user)
                .build();

        assertThat(createTicketDto.toTicket())
                .isEqualTo(ticket);
    }
}
