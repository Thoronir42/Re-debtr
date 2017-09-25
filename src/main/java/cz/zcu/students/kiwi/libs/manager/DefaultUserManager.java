package cz.zcu.students.kiwi.libs.manager;

import javax.transaction.Transactional;

import cz.zcu.students.kiwi.libs.security.Encoder;
import cz.zcu.students.kiwi.libs.dao.UserDao;
import cz.zcu.students.kiwi.libs.domain.User;
import cz.zcu.students.kiwi.libs.domain.UserValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
@Service
@Transactional
public class DefaultUserManager implements UserManager {

    private UserDao userDao;
    private Encoder encoder;

    @Autowired
    public DefaultUserManager(UserDao userDao, Encoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User u = userDao.findByUsername(username);

        return u != null && encoder.validate(password, u.getPassword());
    }

    @Override
    public void register(User newUser) throws UserValidationException {
        if(!newUser.isNew()) {
            throw new RuntimeException("User already exists, use save method for updates!");
        }

        newUser.validate();

        User existinCheck = userDao.findByUsername(newUser.getUsername());
        if(existinCheck != null) {
            throw new UserValidationException("Username already taken!");
        }

        newUser.setPassword(encoder.encode(newUser.getPassword()));

        userDao.save(newUser);
    }
}
