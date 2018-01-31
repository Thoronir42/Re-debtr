package cz.zcu.students.kiwi.temp;

import cz.zcu.students.kiwi.libs.auth.AclRole;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDao;
import cz.zcu.students.kiwi.redebtr.persistence.ProfileContactDao;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultDataInitializer {

    @Autowired
    public UserManager userManager;
    @Autowired
    public UserProfileDao userProfiles;
    @Autowired
    public ProfileContactDao profileContacts;
    @Autowired
    public PostDao posts;

    @Autowired
    public IpsumGenerator ipsum;


    public String init() {
        StringBuilder sb = new StringBuilder();

        try {
            List<Map.Entry<User, UserProfile>> usersAndProfiles = addUsers();
            List<UserProfile> profiles = usersAndProfiles.stream().map(Map.Entry::getValue).collect(Collectors.toList());
            sb.append("Added ").append(usersAndProfiles.size()).append(" users with profiles\n");

            int contacts = addContacts(profiles);
            sb.append("Added ").append(contacts).append(" contacts\n");

            List<Post> posts = addPosts(profiles);
            sb.append("Added ").append(posts.size()).append(" posts\n");

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
            up = userManager.createProfile(ipsum.words(1), ipsum.words(1), u);
            entries.add(addUser(u, up));
        }

        return entries;
    }

    private int addContacts(List<UserProfile> profiles) throws ValidationException {
        int size = profiles.size();
        int n = 0;
        for (int i = 0; i < size; i++) {
            for (int d = 1; d <= 2; d++) {
                addContact(profiles.get(i), profiles.get(((i + d) % size)), (i + d) % 2 == 1);
                n++;
            }
        }

        return n;
    }

    private List<Post> addPosts(List<UserProfile> users) throws ValidationException {
        List<Post> posts = new ArrayList<>();

        int size = users.size();
        for (int i = 0, d = 0; i < 77; i++, d += 3) {
            int author = i % size;
            int target = (i + d) % size;

            System.out.println(author + " => " + target);
            posts.add(addPost(users.get(author), users.get(target), "Post " + i));
        }

        return posts;
    }

    private Map.Entry<User, UserProfile> addUser(User user, UserProfile profile) throws ValidationException {
        userManager.register(user);
        userProfiles.create(profile);

        return new AbstractMap.SimpleImmutableEntry<>(user, profile);
    }

    private void addContact(UserProfile from, UserProfile to, boolean accepted) {
        ProfileContact contact = new ProfileContact(from, to)
                .setDateResolved(new Date())
                .setStatus(accepted ? ProfileContact.Status.Accepted : ProfileContact.Status.Requested);

        this.profileContacts.create(contact);

//        this.profileContacts.addContact(from, to);
    }

    private Post addPost(UserProfile author, UserProfile target, String text) {
        Post p = new Post()
                .setTarget(target)
                .setAuthor(author)
                .setMessage(text);

        posts.create(p);
        return p;
    }

}
