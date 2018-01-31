package cz.zcu.students.kiwi.redebtr.controllers;

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

    @RequestMapping(value = "{locator}/{id}", method = RequestMethod.GET)
    protected ModelAndView getPost(@PathVariable("id") String id) {
        return new LayoutMAV("post/postDetail.jsp");
    }

    @RequestMapping(value = "/{locator}", method = RequestMethod.POST)
    protected String createPost(
            HttpServletRequest req,
            @PathVariable(name = "locator") String targetDashboard,
            @RequestParam(name = "post-type") String type,
            @RequestParam(name = "text") String text
    ) {
        User u = authHelper.getCurrentUser(req);
        if (u == null) {
            throw new ForbiddenException();
        }

        UserProfile targetProfile = profiles.findByLocator(targetDashboard);
        if (targetProfile == null) {
            throw new NotFoundException("User profile could not be found");
        }

        Post p = new Post()
                .setTarget(targetProfile)
                .setAuthor(u.getProfile())
                .setMessage(text);

        posts.create(p);

        return "redirect:/user/profile/" + targetDashboard;
    }
}
