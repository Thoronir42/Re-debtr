package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

import java.util.List;

public interface PostDao {
    List<Post> prepareDashboard(UserProfile profile);
}
