package cz.zcu.students.kiwi.redebtr.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class LayoutMAV extends ModelAndView {
    private static String LAYOUT = "index.jsp";

    public LayoutMAV(String viewName) {
        super(LAYOUT, new ModelMap());

        this.getModelMap().put("pageTemplate", viewName);
    }

    public LayoutMAV(String viewName, Map<String, ?> model) {
        super(LAYOUT, model);

        this.getModelMap().put("pageTemplate", viewName);
    }
}
