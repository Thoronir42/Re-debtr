package cz.zcu.students.kiwi.redebtr.helpers;

import cz.zcu.students.kiwi.libs.security.Encoder;
import cz.zcu.students.kiwi.redebtr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EncoderSetter {

    private final Encoder encoder;

    @Autowired
    public EncoderSetter(Encoder encoder) {
        this.encoder = encoder;

    }

    @EventListener(ContextRefreshedEvent.class)
    public void set() {
        User.encoder = encoder;
    }
}
