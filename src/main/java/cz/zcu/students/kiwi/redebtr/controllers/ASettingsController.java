package cz.zcu.students.kiwi.redebtr.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

public class ASettingsController extends BaseController {

    @ModelAttribute
    public void initMenu(ModelMap model) {
        List<Link> links = Arrays.asList(
                new Link("Profile", "/settings/profile", "fa-user-o"),
                new Link("Account", "/settings/account", "fa-cog")
        );

        model.addAttribute("sections", links);
    }


    public static class Link {
        String caption, link, icon;

        public Link(String caption, String link, String icon) {
            this.caption = caption;
            this.link = link;
            this.icon = icon;
        }

        public String getCaption() {
            return caption;
        }

        public String getLink() {
            return link;
        }

        public String getIcon() {
            return icon;
        }
    }
}
