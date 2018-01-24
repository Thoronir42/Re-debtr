package cz.zcu.students.kiwi.temp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

@Service
public class IpsumGenerator {

    private static String[] paragraphs = {
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nullam justo enim, consectetuer nec, ullamcorper ac, vestibulum in, elit. Donec ipsum massa, ullamcorper in, auctor et, scelerisque sed, est. Aliquam in lorem sit amet leo accumsan lacinia. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae.",
            "Suspendisse sagittis ultrices augue. Nullam sit amet magna in magna gravida vehicula. Etiam dictum tincidunt diam. Pellentesque ipsum. Aliquam ornare wisi eu metus. Nulla non lectus sed nisl molestie malesuada. Aliquam erat volutpat.",
            "Phasellus enim erat, vestibulum vel, aliquam a, posuere eu, velit. Donec vitae arcu. Maecenas lorem. Aliquam erat volutpat. Praesent in mauris eu tortor porttitor accumsan. Etiam sapien elit, consequat eget, tristique non, venenatis quis, ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Fusce wisi.",
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean id metus id velit ullamcorper pulvinar. Donec iaculis gravida nulla. Duis sapien nunc, commodo et, interdum suscipit, sollicitudin et, dolor. Pellentesque pretium lectus id turpis. Duis risus. Cras pede libero, dapibus nec, pretium sit amet, tempor quis.",
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Donec quis nibh at felis congue commodo. Aenean id metus id velit ullamcorper pulvinar.",
            "Nullam eget nisl. Mauris metus. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Mauris tincidunt sem sed arcu. Et harum quidem rerum facilis est et expedita distinctio.",
            "Integer lacinia. Mauris dictum facilisis augue. Nullam at arcu a est sollicitudin euismod. Duis viverra diam non justo. Praesent dapibus. Quisque porta. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. In dapibus augue non sapien. Duis bibendum, lectus ut viverra rhoncus, dolor nunc faucibus libero, eget facilisis enim ipsum id lacus.",
            "Duis viverra diam non justo. Pellentesque arcu. Pellentesque sapien. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
    };

    private static String[] words = "Lorem ipsum dolor sit amet quod dissentiet eos ei sit no dolorum recusabo ex alii consul mel".split(" ");

    private Random random;

    public IpsumGenerator() {
        this(System.currentTimeMillis());
    }

    public IpsumGenerator(long seed) {
        this.random = new Random(seed);
    }

    public IpsumGenerator createChild(long seed) {
        return new IpsumGenerator(seed);
    }

    public String paragraphs(int n) {
        StringBuilder s = new StringBuilder();

        do {
            s.append(paragraphs[random.nextInt(paragraphs.length)]);
            if (n > 0) {
                s.append("\n\n");
            }
        } while (--n > 0);

        return s.toString();
    }

    public String words(int n) {
        StringBuilder s = new StringBuilder();

        do {
            s.append(words[random.nextInt(words.length)]);
            if (n > 0) {
                s.append(" ");
            }
        } while (--n > 0);

        return s.toString();
    }

    public void init(int seed) {
        this.random.setSeed(seed);
    }
}
