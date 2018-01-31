package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.FlashMessage;
import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.flash.FlashesService;
import cz.zcu.students.kiwi.redebtr.model.Post;
import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDao;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController extends BaseController {

    @Autowired
    PostDao posts;

    @Autowired
    UserProfileDao profiles;

    @RequestMapping(method = RequestMethod.GET)
    protected LayoutMAV getDefault(HttpServletRequest req, ModelMap model) {
        User currentUser = authHelper.getCurrentUser(req);
        AuthUser au = authHelper.getAuthUser(currentUser);

        if (!au.isLoggedIn()) {
            return new LayoutMAV("home/guest.jsp");

        }

        UserProfile profile = currentUser.getProfile();
        List<UserProfile> contacts = profiles.findContactsByStatus(profile, ProfileContact.Status.Accepted);

        List<Post> posts = this.posts.prepareDashboard(profile, contacts);

        model.addAttribute("posts", posts);

        return new LayoutMAV("home/dashboard.jsp", model);
    }
}
