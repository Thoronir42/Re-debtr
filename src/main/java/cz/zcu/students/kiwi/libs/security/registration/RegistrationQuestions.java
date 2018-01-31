package cz.zcu.students.kiwi.libs.security.registration;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class RegistrationQuestions {
    private static final String ANSWER = "registration.question.answer";

    private Random rand = new Random();

    public AQuestion prepareQuestion(HttpSession session) {
        AQuestion question = generateQuestion();

        session.setAttribute(ANSWER, question.getAnswer());

        return question;
    }

    public boolean verifyAnswer(HttpSession session, String answer) {
        return answer.equals(session.getAttribute(ANSWER));
    }


    private AQuestion generateQuestion() {
        switch (rand.nextInt() % 2) {
            default:
            case 0: return ArithmeticQuestion.generateQuestion(rand);
            case 1: return CurrentDateQuestion.generateQuestion(rand);
        }

    }
}
