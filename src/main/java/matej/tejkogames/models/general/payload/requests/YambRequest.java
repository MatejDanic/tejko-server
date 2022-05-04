package matej.tejkogames.models.general.payload.requests;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import matej.tejkogames.models.general.User;
import matej.tejkogames.models.general.YambChallenge;
import matej.tejkogames.models.yamb.Dice;
import matej.tejkogames.models.yamb.YambForm;
import matej.tejkogames.models.yamb.enums.BoxType;
import matej.tejkogames.models.yamb.enums.YambType;
import matej.tejkogames.constants.YambConstants;

public class YambRequest {

    private User user;

    @NotBlank
    private YambType type = YambType.CLASSIC;

    @Size(min = 1, max = 6)
    private String formCode = "DUFA";

    @Min(YambConstants.NUMBER_OF_DICE_MIN)
    @Max(YambConstants.NUMBER_OF_DICE_MAX)
    private Integer numberOfDice = 5;

    private YambForm form;

    private BoxType announcement;

    private Set<Dice> diceSet;

    private Integer rollCount;

    private YambChallenge challenge;

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

    public YambChallenge getChallenge() {
        return challenge;
    }

    public void setChallenge(YambChallenge challenge) {
        this.challenge = challenge;
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

    @Override
    public String toString() {
        return "Yamb Request\nType: " + type + "\nForm Code: " + formCode + "\nChallenge: " + challenge;
    }

}
