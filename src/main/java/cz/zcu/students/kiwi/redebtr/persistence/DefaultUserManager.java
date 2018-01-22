package cz.zcu.students.kiwi.redebtr.persistence;

import javax.transaction.Transactional;

import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.libs.security.Encoder;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
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
        if(u == null) {
            encoder.fakeValidate();
            return false;
        }
        return u != null && encoder.validate(password, u.getPassword());
    }

    @Override
    public void register(User newUser) throws ValidationException {
        if(!newUser.isNew()) {
            throw new RuntimeException("User already exists, use save method for updates!");
        }

        newUser.validate();

        User existingUser = userDao.findByUsername(newUser.getUsername());
        if(existingUser != null) {
            throw new ValidationException("Username already taken!");
        }

        userDao.save(newUser);
    }
}
