package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.redebtr.helpers.AddressHelper;
import cz.zcu.students.kiwi.temp.DefaultDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/debug/")
public class DebugController {

    @Autowired
    DefaultDataInitializer initializer;

    @GetMapping("init-data")
    public @ResponseBody
    String initData(HttpServletRequest req) {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        if (!AddressHelper.isLocalAddress(ipAddress)) {
            return "Init data is allowed to be executed only from localhost";
        }

        return "<pre>" + initializer.init() + "</pre>";

    }
}
