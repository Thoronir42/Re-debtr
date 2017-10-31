package cz.zcu.students.kiwi.temp;

import cz.zcu.students.kiwi.comments.Comment;
import cz.zcu.students.kiwi.comments.CommentThread;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;

public class CommentGenerator {

    public CommentThread thread(int comments, long seed) {
        IpsumGenerator ipsum = new IpsumGenerator(seed);


        CommentThread thread = new CommentThread();

        for (int i = 0; i < comments; i++) {
            Comment c = new Comment();
            UserProfile p = (new UserProfile(ipsum.words(1)))
                    .setName(ipsum.words(1), ipsum.words(1));
            c.setText(ipsum.paragraphs(1))
                    .setAuthor(p);

            thread.addComment(c);
        }

        return thread;
    }
}
