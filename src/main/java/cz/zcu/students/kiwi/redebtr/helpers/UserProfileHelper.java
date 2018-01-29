package cz.zcu.students.kiwi.redebtr.helpers;

import cz.zcu.students.kiwi.redebtr.model.UserProfile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Random;


public class UserProfileHelper {

    private static String HEXDEC = "0123456789ABCDEF";

    public static String getProfileAvatar(UserProfile profile) {
        if(profile == null || profile.getAvatar() == null) {
            return "/img/profile/blank.png";
        }

        return "/img/profile/" + profile.getAvatar();
    }

    public static String getProfileBackground(UserProfile profile) {
        if(profile == null || profile.getFullName() == null) {
            return "#FFFFFF";
        }

        Random r = new Random(profile.getFullName().hashCode());
        StringBuilder s = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            s.append(HEXDEC.charAt(r.nextInt(16)));
        }

        return s.toString();
    }

    public static String getProfileUrl(HttpServletRequest req, UserProfile profile) {
        if(profile == null) {
            return "";
        }
        return req.getContextPath() + "/user/profile/" + profile.getLocator();
    }

}
