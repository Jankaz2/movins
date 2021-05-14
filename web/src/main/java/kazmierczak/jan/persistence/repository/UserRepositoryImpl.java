package kazmierczak.jan.persistence.repository;

import kazmierczak.jan.persistence.dao.UserEntityDao;
import kazmierczak.jan.persistence.entity.UserEntity;
import kazmierczak.jan.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import model.user.User;
import model.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kazmierczak.jan.persistence.entity.UserEntity.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserEntityDao userEntityDao;

    /**
     * @param email we want to find user by
     * @return user with this email if it exists
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityDao
                .findByEmail(email)
                .map(UserEntity::toUser);
    }

    /**
     *
     * @param user object we want to add
     * @return added user object
     */
    @Override
    public Optional<User> add(User user) {
        var userEntity = fromUser(user);
        var insertedUser = userEntityDao.save(userEntity);
        return Optional.ofNullable(insertedUser.toUser());
    }

    /**
     *
     * @param id of user we want to delete
     * @return deleted user
     */
    @Override
    public Optional<User> delete(Long id) {
        var userToDelete = userEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("Cannnot find user with this id: " + id));
        userEntityDao.delete(userToDelete);
        return Optional.ofNullable(userToDelete.toUser());
    }

    /**
     *
     * @param id of user we want to find
     * @return user with id from param
     */
    @Override
    public Optional<User> findById(Long id) {
        return userEntityDao
                .findById(id)
                .map(UserEntity::toUser);
    }

    /**
     *
     * @return all users
     */
    @Override
    public List<User> findAll() {
        return userEntityDao
                .findAll()
                .stream()
                .map(UserEntity::toUser)
                .toList();
    }

    /**
     *
     * @param ids list of users ids we want to find
     * @return list of users that got id from ids list
     */
    @Override
    public List<User> findAllById(List<Long> ids) {
        return userEntityDao
                .findAllById(ids)
                .stream()
                .map(UserEntity::toUser)
                .toList();
    }
}
