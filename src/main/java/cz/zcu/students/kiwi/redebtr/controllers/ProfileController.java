package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.exceptions.BadRequestException;
import cz.zcu.students.kiwi.libs.exceptions.NotFoundException;
import cz.zcu.students.kiwi.redebtr.model.ProfileContact;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDao;
import cz.zcu.students.kiwi.redebtr.persistence.PostDaoJpa;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/user/")
public class ProfileController extends BaseController {

    @Autowired
    private UserProfileDao profiles;

    @Autowired
    private PostDao postDao;

    // regex because trailing dot is truncated otherwise
    @RequestMapping(value = "/profile/{locator:.+}", method = RequestMethod.GET)
    public ModelAndView getProfile(HttpServletRequest req, ModelMap model, @PathVariable String locator) {
        if (locator.length() < 1) {
            throw new BadRequestException("Id must not be empty");
        }

        User currentUser = authHelper.getCurrentUser(req);

        UserProfile profile = profiles.findByLocator(locator);
        if(profile == null) {
            throw new NotFoundException("User " + locator + " could not be found");
        }

        List<UserProfile> contacts = profiles.findConnectionsByStatus(profile, ProfileContact.Status.Accepted);

        model.put("profile", profile);
        model.put("contacts", contacts);
        if(currentUser != null) {
            boolean itsMe = currentUser.getProfile().equals(profile);
            model.put("currentUsersProfile", itsMe);
            if(!itsMe) {
                profile.setContactStatus(profiles.findRelationStatus(currentUser.getProfile(), profile));
            }
        }

        model.put("posts", this.postDao.getPostOfProfile(profile));

        return new LayoutMAV("user/profile.jsp", model);
    }


}
