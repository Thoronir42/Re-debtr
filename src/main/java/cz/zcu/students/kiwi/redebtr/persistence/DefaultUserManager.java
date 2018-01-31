package cz.zcu.students.kiwi.redebtr.persistence;

import javax.transaction.Transactional;

import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DefaultUserManager implements UserManager {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) throws ValidationException {
        if (!user.isNew()) {
            throw new RuntimeException("User already exists, use save method for updates!");
        }

        User existingUser = userDao.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalStateException("Username already taken!");
        }

        userDao.create(user, true);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public UserProfile createProfile(String name, String surname, User user) {
        UserProfile profile = new UserProfile(user)
                .setFirstName(name)
                .setLastName(surname);
        String locator = profile.getFullName().replace(" ", ".").trim().toLowerCase();
        return profile.setLocator(locator);
    }
}
