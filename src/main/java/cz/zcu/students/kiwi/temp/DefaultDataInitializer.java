package cz.zcu.students.kiwi.temp;

import cz.zcu.students.kiwi.libs.auth.AclRole;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DefaultDataInitializer {

    @Autowired
    public UserProfileDao userProfiles;

    @Autowired
    public UserManager userManager;

    @Autowired
    public IpsumGenerator ipsum;


    public String init() {
        StringBuilder sb = new StringBuilder();

        try {
            List<Map.Entry<User, UserProfile>> usersAndProfiles = addUsers();
            sb.append("Added ").append(usersAndProfiles.size()).append(" users with profiles\n");

            sb.append("\nInitialization complete\n");
        } catch (ValidationException e) {
            sb.append(e.toString());
        }

        return sb.toString();
    }

    private List<Map.Entry<User, UserProfile>> addUsers() throws ValidationException {
        ArrayList<Map.Entry<User, UserProfile>> entries = new ArrayList<>();

        User u = new User("user1", "a@a", "User@1234")
                .setStatus(User.Status.Active).setRole(AclRole.User);
        UserProfile up = new UserProfile(u).setName("Karel", "Noháv").setLocator("kanoha");

        entries.add(addUser(u, up));

        u = new User("user2", "a@a", "User@1234")
                .setStatus(User.Status.Unverified).setRole(AclRole.User);
        up = new UserProfile(u).setName("Gustav", "Rukavice").setLocator("garuk");
        entries.add(addUser(u, up));


        u = new User("user3", "a@a", "User@1234")
                .setStatus(User.Status.Active).setRole(AclRole.User);
        up = new UserProfile(u).setName("Johanka", "Pukanka").setLocator("pukuju");
        entries.add(addUser(u, up));


        u = new User("admin", "a@a", "Admin@1234")
                .setStatus(User.Status.Active).setRole(AclRole.Admin);
        up = new UserProfile(u).setName("Alferda", "Drahota").setLocator("haha");
        entries.add(addUser(u, up));

        User.Status[] statuses = User.Status.values();
        for (int i = 4; i < 10; i++) {

            u = new User("user" + i, "a@a", "a")
                    .setStatus(statuses[i % statuses.length]);
            up = new UserProfile(u)
                    .setName(ipsum.words(1), ipsum.words(1))
                    .setLocator(ipsum.words(2).replace(" ", "."));
            entries.add(addUser(u, up));
        }

        return entries;
    }


    private Map.Entry<User, UserProfile> addUser(User user, UserProfile profile) throws ValidationException {
        userManager.register(user);
        userProfiles.create(profile);

        return new AbstractMap.SimpleImmutableEntry<>(user, profile);
    }

}
