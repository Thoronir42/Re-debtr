package cz.zcu.students.kiwi.redebtr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/post")
public class PostController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView getPost(@RequestParam("id") String id) {
        return new LayoutMAV("post/postDetail.jsp");
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String createPost(@RequestParam(name = "target-dashboard") String targetDashboard,
                                @RequestParam(name = "post-type") String type,
                                @RequestParam(name = "content") String content) {
        System.out.println("Posting " + type + ", to " + targetDashboard);
        System.out.println("Post content: " + content);


        return "redirect:/user/profile?u=" + targetDashboard;
    }
}
