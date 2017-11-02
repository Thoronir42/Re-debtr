package cz.zcu.students.kiwi.redebtr.persistence;

import cz.zcu.students.kiwi.redebtr.model.Reaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class ReactionManager {

    private List<Reaction> reactions;

    public ReactionManager() {
        List<Reaction> reactions = new ArrayList<>(3);

        reactions.add(new Reaction("cool", "thumbs-o-up", "Cool"));
        reactions.add(new Reaction("ew", "thumbs-o-down", "Noo"));
        reactions.add(new Reaction("unsee", "eye-slash", "UNSEE!"));

        this.reactions = Collections.unmodifiableList(reactions);
    }

    public List<Reaction> getStandardReactions() {
        return this.reactions;
    }

}
