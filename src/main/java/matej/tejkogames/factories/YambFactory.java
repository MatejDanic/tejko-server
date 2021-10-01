package matej.tejkogames.factories;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.yamb.Yamb;
import matej.tejkogames.models.yamb.YambChallenge;
import matej.tejkogames.models.yamb.YambType;
import matej.tejkogames.utils.YambUtil;

@Component
public class YambFactory {

    public Yamb createYamb(User user, YambType type, int numberOfColumns, int numberOfDice, YambChallenge challenge) {
        Yamb yamb = new Yamb();
        yamb.setUser(user);
        yamb.setType(type);
        yamb.setNumberOfColumns(numberOfColumns);
        yamb.setNumberOfDice(numberOfDice);
        yamb.setChallenge(challenge);
        yamb.setForm(YambUtil.generateYambForm(type, numberOfColumns, numberOfDice));
        yamb.setDiceSet(YambUtil.generateDiceSet(numberOfDice));
        yamb.setStartDate(LocalDateTime.now());
        return yamb;
    }
}
