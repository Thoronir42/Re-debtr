package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

import java.util.List;

public interface PostDao extends GenericDao<Post>{
    Post findByLocatorAndId(String locator, long id);

    List<Post> getPostOfProfile(UserProfile profile);

    List<Post> prepareDashboard(UserProfile profile);
}
