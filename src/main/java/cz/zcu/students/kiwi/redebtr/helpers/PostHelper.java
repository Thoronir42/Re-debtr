package cz.zcu.students.kiwi.redebtr.helpers;

import cz.zcu.students.kiwi.redebtr.model.Post;
import javafx.util.Pair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class PostHelper {

    public Pair<Post.Type, String>[] getTypes()
    {
        return new Pair[]{
                new Pair<>(Post.Type.TextPost, "Text post"),
                new Pair<>(Post.Type.DebtPost, "Debt post"),
        };
    }
}
