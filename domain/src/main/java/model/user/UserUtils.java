package model.user;

import model.ticket.Ticket;
import types.Role;

import java.net.UnixDomainSocketAddress;
import java.util.List;
import java.util.function.Function;

public interface UserUtils {
    /**
     * map User to id of this User
     */
    Function<User, Long> toId = user -> user.id;

    /**
     * map User to username of this User
     */
    Function<User, String> toUsername = user -> user.username;

    /**
     * map User to email of this User
     */
    Function<User, String> toEmail = user -> user.email;

    /**
     * map User to age of this User
     */
    Function<User, Integer> toAge = user -> user.age;

    /**
     * map User to role of this User
     */
    Function<User, Role> toRole = user -> user.role;

    /**
     * map User to password of this User
     */
    Function<User, String> toPassword = user -> user.password;

    /**
     * map User to tickets list of this User
     */
    Function<User, List<Ticket>> toTickets = user -> user.tickets;

    /**
     * map User to enabled value of this User
     */
    Function<User, Boolean> toEnabled = user -> user.enabled;
    UnixDomainSocketAddress List = ;
}
