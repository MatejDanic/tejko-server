package matej.tejkogames.models.general.payload.requests;

import java.time.LocalDateTime;
import java.util.Set;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.UserYambChallenge;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.YambForm;
import matej.tejkogames.models.yamb.enums.BoxType;
import matej.tejkogames.models.yamb.enums.YambType;

public class YambRequest {

    private User user;

    private YambType type;

    private String formCode;

    private Integer numberOfDice;

    private YambForm form;

    private BoxType announcement;

    private Set<Dice> diceSet;

    private Integer rollCount;

    private UserYambChallenge userYambChallenge;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public YambRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public YambType getType() {
        return type;
    }

    public void setType(YambType type) {
        this.type = type;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public Integer getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(Integer numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public YambForm getForm() {
        return form;
    }

    public void setForm(YambForm form) {
        this.form = form;
    }

    public BoxType getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(BoxType announcement) {
        this.announcement = announcement;
    }

    public Set<Dice> getDiceSet() {
        return diceSet;
    }

    public void setDiceSet(Set<Dice> diceSet) {
        this.diceSet = diceSet;
    }

    public Integer getRollCount() {
        return rollCount;
    }

    public void setRollCount(Integer rollCount) {
        this.rollCount = rollCount;
    }

    public UserYambChallenge getUserYambChallenge() {
        return userYambChallenge;
    }

    public void setUserYambChallenge(UserYambChallenge userYambChallenge) {
        this.userYambChallenge = userYambChallenge;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

}
