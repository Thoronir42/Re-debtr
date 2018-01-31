package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.comments.Comment;
import cz.zcu.students.kiwi.comments.CommentDao;
import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.exceptions.ForbiddenException;
import cz.zcu.students.kiwi.libs.exceptions.NotFoundException;
import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDao;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/post")
public class PostController extends BaseController {

    @Autowired
    UserProfileDao profiles;

    @Autowired
    PostDao posts;

    @Autowired
    CommentDao comments;

    @RequestMapping(value = "{locator}/{id}", method = RequestMethod.GET)
    protected ModelAndView getPost(@PathVariable("id") String id) {
        return new LayoutMAV("post/postDetail.jsp");
    }

    @RequestMapping(value = "/{locator}", method = RequestMethod.POST)
    protected String createPost(
            HttpServletRequest req,
            @PathVariable(name = "locator") String locator,
            @RequestParam(name = "post-type") String type,
            @RequestParam(name = "text") String text
    ) {
        User u = authHelper.getCurrentUser(req);
        if (u == null) {
            throw new ForbiddenException();
        }

        UserProfile targetProfile = profiles.findByLocator(locator);
        if (targetProfile == null) {
            throw new NotFoundException("User profile could not be found");
        }

        Post p = new Post()
                .setTarget(targetProfile)
                .setAuthor(u.getProfile())
                .setMessage(text);

        posts.create(p);

        return "redirect:/user/profile/" + locator;
    }

    @RequestMapping(value = "{locator}/{id}/comment")
    public String addComment(
            HttpServletRequest req,
            @PathVariable(name = "locator") String locator,
            @PathVariable(name = "id") long postId,
            @RequestParam(name = "text") String text
    ) {
        User u = authHelper.getCurrentUser(req);
        if (u == null) {
            throw new ForbiddenException();
        }

        Post post = posts.findByLocatorAndId(locator, postId);
        if (post == null) {
            throw new NotFoundException("Post could not be found");
        }

        Comment comment = new Comment()
                .setAuthor(u.getProfile())
                .setText(text);


        comments.addComment(post, comment);
        posts.update(post);

        return "redirect:/user/profile/" + locator;
    }
}
