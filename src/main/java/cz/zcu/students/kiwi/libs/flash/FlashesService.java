package cz.zcu.students.kiwi.libs.flash;

import cz.zcu.students.kiwi.libs.FlashMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.List;

@Service
@Scope(WebApplicationContext.SCOPE_SESSION)
public class FlashesService {
    private List<FlashMessage> messages;

    public FlashesService() {
        messages = new LinkedList<>();
    }

    public void add(FlashMessage message) {
        this.messages.add(message);
    }

    public boolean hasMessages() {
        return !messages.isEmpty();
    }

    public List<FlashMessage> dump() {
        List<FlashMessage> result = messages;

        messages = new LinkedList<>();

        return result;
    }
}
