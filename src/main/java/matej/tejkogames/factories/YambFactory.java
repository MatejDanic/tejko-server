package matej.tejkogames.factories;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.Yamb;
import matej.tejkogames.models.general.YambChallenge;
import matej.tejkogames.models.yamb.enums.YambType;
import matej.tejkogames.utils.YambUtil;

@Component
public class YambFactory {

    public Yamb createYamb(User user, YambType type, String formCode, int numberOfDice, YambChallenge challenge) {
        Yamb yamb = new Yamb();
        yamb.setUser(user);
        yamb.setType(type);
        yamb.setNumberOfDice(numberOfDice);
        yamb.setChallenge(challenge);
        yamb.setForm(YambUtil.generateYambForm(formCode));
        yamb.setDiceSet(YambUtil.generateDiceSet(numberOfDice));
        yamb.setStartDate(LocalDateTime.now());
        return yamb;
    }
}
